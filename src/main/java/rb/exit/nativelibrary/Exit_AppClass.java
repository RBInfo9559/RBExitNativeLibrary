package rb.exit.nativelibrary;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Exit_AppClass
{
	private static Context mContext;

	public static Typeface font_type;
	
	public static boolean is_online;

	public Exit_AppClass(Context ctx)
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
			String rateUrl = Exit_AppHelper.rate_url + mContext.getPackageName();

			String dialog_header = "Rate App";
			String dialog_message = "Are you sure you want to rate my app?" + "\n" + "Thanks for support!";

			ConformRateDialog(mContext,rateUrl,dialog_header,dialog_message);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void GoToApp(Context ctx, String appUrl)
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
			String shareUrl = Exit_AppHelper.rate_url + mContext.getPackageName();

			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_SUBJECT, app_name);
			sharingIntent.putExtra(Intent.EXTRA_TEXT,app_name + "\n" + shareUrl);
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
			more_intent.setData(Uri.parse(Exit_AppHelper.more_url));
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

	public static SimpleDateFormat df;
	public static SimpleDateFormat sdf;
	public static SimpleDateFormat sdt;

	public static Date current_datetime;

	public static String current_date_time;
	public static String current_date;
	public static String current_time;

	public static String GetCurrentDateTime()
	{
		// TODO Auto-generated method stub
		try
		{
			Calendar c = Calendar.getInstance();
			System.out.println("Current time => " + c.getTime());


			df = new SimpleDateFormat("dd-MM-yyyy HH:mm aa");
			current_date_time = df.format(c.getTime());
			current_datetime = df.parse(current_date_time);

			sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdt = new SimpleDateFormat("HH:mm aa");

			current_date = sdf.format(current_datetime);
			current_time = sdt.format(current_datetime);

			String delegate = "hh:mm aaa";
			current_time = (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current_date_time;
	}

	private static Dialog conform_dialog;
	private static Button conform_dialog_btn_yes;
	private static Button conform_dialog_btn_no;

	private static TextView conform_dialog_txt_header;
	private static TextView conform_dialog_txt_message;

	private static String conform_dialog_header;
	private static String conform_dialog_message;

	public static void ConformRateDialog(final Context mContext, final String appUrl, final String header, final String message)
	{
		conform_dialog = new Dialog(mContext,R.style.TransparentBackground_Exit);
		conform_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		conform_dialog.setContentView(R.layout.exit_dialog_rate);

		conform_dialog_btn_yes = (Button) conform_dialog.findViewById(R.id.dialog_conform_btn_yes);
		conform_dialog_btn_no = (Button) conform_dialog.findViewById(R.id.dialog_conform_btn_no);

		conform_dialog_txt_header = (TextView)conform_dialog.findViewById(R.id.dialog_conform_txt_header);
		conform_dialog_txt_message = (TextView)conform_dialog.findViewById(R.id.dialog_conform_txt_message);

		font_type = Typeface.createFromAsset(mContext.getAssets(), Exit_AppHelper.roboto_font_path);

		conform_dialog_btn_yes.setTypeface(font_type);
		conform_dialog_btn_no.setTypeface(font_type);

		conform_dialog_txt_header.setTypeface(font_type);
		conform_dialog_txt_message.setTypeface(font_type);

		conform_dialog_btn_yes.setText("Rate now");
		conform_dialog_btn_no.setText("Cancel");

		conform_dialog_header = header;
		conform_dialog_message = message;

		conform_dialog_txt_header.setText(conform_dialog_header);
		conform_dialog_txt_message.setText(conform_dialog_message);

		conform_dialog_btn_yes.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Uri uri = Uri.parse(appUrl);
				Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
				try
				{
					mContext.startActivity(goToMarket);
				}
				catch (ActivityNotFoundException e)
				{
					e.printStackTrace();
				}

				conform_dialog.dismiss();
			}
		});

		conform_dialog_btn_no.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				conform_dialog.dismiss();
			}
		});

		conform_dialog.show();

	}
	
}
