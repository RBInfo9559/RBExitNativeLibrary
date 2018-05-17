package rb.exit.myexitviewlibrary;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;


public class exit_CommonClass
{
	private static Context mContext;

	public static Typeface font_type;
	
	public static boolean is_online;

	public exit_CommonClass(Context ctx)
	{
		// TODO Auto-generated constructor stub
		mContext = ctx;
	}

	public static void RateApp(Context ctx)
	{
		// TODO Auto-generated method stub
		try 
		{
			mContext = ctx;
			String rateUrl = exit_CommonHelper.rate_url + mContext.getPackageName();

			Uri uri = Uri.parse(rateUrl);
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try
			{
				mContext.startActivity(goToMarket);
			}
			catch (ActivityNotFoundException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public static void GoToApp(Context ctx,String appUrl)
	{
		// TODO Auto-generated method stub
		try 
		{
			mContext = ctx;

			Uri uri = Uri.parse(appUrl.trim());
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try
			{
				mContext.startActivity(goToMarket);
			}
			catch (ActivityNotFoundException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	public static void ShareApp(Context ctx)
	{
		// TODO Auto-generated method stub
		try
		{
			mContext = ctx;
			String app_name = mContext.getResources().getString(R.string.app_name) + " :";
			String shareUrl = exit_CommonHelper.rate_url + mContext.getPackageName();

			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_SUBJECT, app_name + "\n");
			sharingIntent.putExtra(Intent.EXTRA_TEXT,shareUrl);
			mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void MoreApps(Context ctx)
	{
		// TODO Auto-generated method stub
		try
		{
			mContext = ctx;
			Intent more_intent = new Intent(Intent.ACTION_VIEW);
			more_intent.setData(Uri.parse(exit_CommonHelper.more_url));
			mContext.startActivity(more_intent);
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public static boolean isOnline(Context mContext)
	{
		ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected()) 
		{
			is_online = true;
			return is_online;
		}
		else
		{
			is_online = false;
			return is_online;
		}
	}
	
}
