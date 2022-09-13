package rb.exit.nativelibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExitAppActivity extends Activity
{
	RelativeLayout rel_native_ad;
	NativeAd ad_mob_native_ad = null;
	AdRequest native_ad_request;

	RelativeLayout rel_exit_yes;
	RelativeLayout rel_exit_no;
	TextView app_exit_lbl_Yes;
	TextView app_exit_lbl_No;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		getWindow().setFlags(1024, 1024);

		SetView();
	}

	private void SetView()
	{
		try
		{
			setContentView(R.layout.exit_layout);

			rel_exit_yes = (RelativeLayout)findViewById(R.id.app_exit_btn_yes);
			rel_exit_no = (RelativeLayout)findViewById(R.id.app_exit_btn_no);

			app_exit_lbl_Yes = findViewById(R.id.app_exit_lbl_yes);
			app_exit_lbl_No = findViewById(R.id.app_exit_lbl_no);

			rel_exit_yes.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					GoToHome();
				}
			});

			rel_exit_no.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					HomeScreen();
				}
			});
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void HomeScreen()
	{
		if(ExitCommonHelper.mActivity != null)
		{
			Intent i = new Intent(getApplicationContext(), ExitCommonHelper.mActivity.getClass());
			startActivity(i);
			finish();
			overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
		}
		else
		{
			finish();
			overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
		}
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		GoToHome();
	}

	protected void GoToHome()
	{
		moveTaskToBack(true);
		finish();
		overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
	}

	public static boolean verifyInstallerId(Context context)
	{
		// A list with valid installers package name
		List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));
		// The package name of the app that has installed your app
		final String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
		// true if your app has been downloaded from Play Store
		return installer != null && validInstallers.contains(installer);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		NativeAdProcess();
	}

	private void NativeAdProcess()
	{
		if(!ExitCommonHelper.is_hide_ad)
		{
			boolean is_online = ExitCommonClass.isOnline(this);
			if(is_online)
			{
				if(ExitCommonHelper.is_eea_user)
				{
					if (ExitCommonHelper.is_consent_set)
					{
						boolean check_play_store_user = verifyInstallerId(getApplicationContext());
						if(check_play_store_user)
						{
							LoadAd();
						}
						else
						{
							HideViews();
						}
					}
					else
					{
						HideViews();
					}
				}
				else
				{
					boolean check_play_store_user = verifyInstallerId(getApplicationContext());
					if(check_play_store_user)
					{
						LoadAd();
					}
					else
					{
						HideViews();
					}
				}
			}
			else
			{
				// Hide Ads
				HideViews();
			}
		}
		else
		{
			// Hide Ads
			HideViews();
		}
	}

	private void HideViews()
	{
		rel_native_ad = (RelativeLayout) findViewById(R.id.ad_layout);
		rel_native_ad.setVisibility(View.GONE);
	}

	private void LoadAd()
	{
		try
		{
			//Native Ad Start //
			rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
			rel_native_ad.setVisibility(View.VISIBLE);
			//LoadUnifiedNativeAd(Exit_CommonHelper.is_show_non_personalize, Exit_CommonHelper.native_ad_id);
			LoadAdMobNativeAd(ExitCommonHelper.is_show_non_personalize, ExitCommonHelper.native_ad_id);
			//Native Ad End //
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void LoadAdMobNativeAd(boolean is_show_non_personalize,String native_ad_id)
	{
		AdLoader.Builder builder = new AdLoader.Builder(this, native_ad_id);
		builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener()
		{
			@Override
			public void onNativeAdLoaded(NativeAd nativeAd)
			{
				FrameLayout frameLayout = (FrameLayout) findViewById(R.id.native_ad_layout);
				NativeAdView adView = (NativeAdView) getLayoutInflater().inflate(R.layout.exit_layout_native_ad, null);
				PopulateAdMobNativeAdView(nativeAd, adView);
				frameLayout.removeAllViews();
				frameLayout.addView(adView);
			}
		});

		VideoOptions videoOptions = new VideoOptions.Builder()
				.setStartMuted(true)
				.build();

		NativeAdOptions adOptions = new NativeAdOptions.Builder()
				.setVideoOptions(videoOptions)
				.build();

		builder.withNativeAdOptions(adOptions);

		AdLoader adLoader = builder.withAdListener(new AdListener()
		{
			@Override
			public void onAdFailedToLoad(LoadAdError loadAdError)
			{
				super.onAdFailedToLoad(loadAdError);
			}
		}).build();

		Bundle non_personalize_bundle = new Bundle();
		non_personalize_bundle.putString("npa", "1");

		if(is_show_non_personalize)
		{
			native_ad_request = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, non_personalize_bundle).build();
		}
		else
		{
			native_ad_request = new AdRequest.Builder().build();
		}

		adLoader.loadAd(native_ad_request);
	}

	private void PopulateAdMobNativeAdView(NativeAd nativeAd, NativeAdView adView)
	{
		ad_mob_native_ad = nativeAd;

		View icon_view = adView.findViewById(R.id.ad_app_icon);
		View headline_view = adView.findViewById(R.id.ad_headline);
		View body_view = adView.findViewById(R.id.ad_body);
		View rating_view = adView.findViewById(R.id.ad_stars);
		View price_view = adView.findViewById(R.id.ad_price);
		View store_view = adView.findViewById(R.id.ad_store);
		View advertiser_view = adView.findViewById(R.id.ad_advertiser);
		View call_to_action_view = adView.findViewById(R.id.ad_call_to_action);

		adView.setIconView(icon_view);
		adView.setHeadlineView(headline_view);
		adView.setBodyView(body_view);
		adView.setStarRatingView(rating_view);
		adView.setPriceView(price_view);
		adView.setStoreView(store_view);
		adView.setAdvertiserView(advertiser_view);
		adView.setCallToActionView(call_to_action_view);

		MediaView mediaView = adView.findViewById(R.id.ad_media);
		adView.setMediaView(mediaView);

		((TextView) headline_view).setText(nativeAd.getHeadline());
		((TextView) body_view).setText(nativeAd.getBody());
		((Button) call_to_action_view).setText(nativeAd.getCallToAction());

		// check before trying to display them.
		if (nativeAd.getIcon() == null)
		{
			icon_view.setVisibility(View.GONE);
		}
		else
		{
			((ImageView) icon_view).setImageDrawable(nativeAd.getIcon().getDrawable());
			icon_view.setVisibility(View.VISIBLE);
		}

		if (nativeAd.getPrice() == null)
		{
			price_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			price_view.setVisibility(View.VISIBLE);
			((TextView) price_view).setText(nativeAd.getPrice());
		}

		if (nativeAd.getStore() == null)
		{
			store_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			store_view.setVisibility(View.VISIBLE);
			((TextView) store_view).setText(nativeAd.getStore());
		}

		if (nativeAd.getStarRating() == null)
		{
			rating_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			((RatingBar) rating_view).setRating(nativeAd.getStarRating().floatValue());
			rating_view.setVisibility(View.VISIBLE);
		}

		if (nativeAd.getAdvertiser() == null)
		{
			advertiser_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			((TextView) advertiser_view).setText(nativeAd.getAdvertiser());
			advertiser_view.setVisibility(View.VISIBLE);
		}

		//mediaView.setVisibility(View.VISIBLE);
		//mainImageView.setVisibility(View.VISIBLE);
		body_view.setVisibility(View.GONE);
		rating_view.setVisibility(View.VISIBLE);
		advertiser_view.setVisibility(View.VISIBLE);
		store_view.setVisibility(View.GONE);
		price_view.setVisibility(View.GONE);

		adView.setNativeAd(nativeAd);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if(ad_mob_native_ad != null)
		{
			ad_mob_native_ad.destroy();
		}
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		if(ad_mob_native_ad != null)
		{
			ad_mob_native_ad.destroy();
		}
	}
}
