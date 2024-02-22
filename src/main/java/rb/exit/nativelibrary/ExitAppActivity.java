package rb.exit.nativelibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class ExitAppActivity extends Activity
{
	/*RelativeLayout rel_native_ad;
	NativeAd ad_mob_native_ad = null;
	AdRequest native_ad_request;*/

	RelativeLayout exit_rel_ad_layout;
	AdRequest exit_banner_ad_request;

	RelativeLayout rel_exit_yes;
	RelativeLayout rel_exit_no;
	TextView app_exit_lbl_Yes;
	TextView app_exit_lbl_No;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		SetView();
	}

	private void SetView()
	{
		try
		{
			setContentView(R.layout.exit_layout);

			rel_exit_yes = findViewById(R.id.app_exit_btn_yes);
			rel_exit_no = findViewById(R.id.app_exit_btn_no);

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
		}
		else
		{
			finishAffinity();
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
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		AdProcess();
	}

	private void AdProcess()
	{
		if(!ExitCommonHelper.is_hide_ad)
		{
			boolean is_online = ExitCommonClass.isOnline(this);
			if(is_online)
			{
				boolean check_play_store_user = ExitCommonHelper.is_play_store_install;
				if(check_play_store_user)
				{
					Log.e("ExitLibrary","Ad Load start...");
					LoadRectangleAd();
				}
				else
				{
					HideViews();
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
		exit_rel_ad_layout = findViewById(R.id.exit_ad_layout);
		exit_rel_ad_layout.setVisibility(View.GONE);
	}

	private void LoadRectangleAd()
	{
		try
		{
			exit_banner_ad_request = new AdRequest.Builder().build();

			exit_rel_ad_layout = findViewById(R.id.exit_ad_layout);
			exit_rel_ad_layout.setVisibility(View.VISIBLE);

			AdView adView = new AdView(this);
			adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
			adView.setAdUnitId(ExitCommonHelper.ad_id);
			adView.loadAd(exit_banner_ad_request);

			//Banner Ad Start //
			exit_rel_ad_layout.addView(adView);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*private void LoadAd()
	{
		try
		{
			//Native Ad Start //
			rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
			rel_native_ad.setVisibility(View.VISIBLE);
			LoadAdMobNativeAd(ExitCommonHelper.ad_id);
			//Native Ad End //
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void LoadAdMobNativeAd(String native_ad_id)
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

		native_ad_request = new AdRequest.Builder().build();
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
	}*/
}
