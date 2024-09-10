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
					LoadRectangleAd();
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
			exit_rel_ad_layout.removeAllViews();
			exit_rel_ad_layout.addView(adView);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
