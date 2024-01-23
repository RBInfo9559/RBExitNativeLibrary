package rb.exit.nativelibrary;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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

public class ExitAppClass
{
	private static Context mContext;

	public static boolean is_online;

	public ExitAppClass(Context ctx)
	{
		mContext = ctx;
	}

	public static void RateApp(Context ctx)
	{
		try
		{
			mContext = ctx;
			String rateUrl = ExitAppHelper.rate_url + mContext.getPackageName();

			String dialog_header = "Rate App";
			String dialog_message = "Are you sure you want to rate my app?" + "\n" + "Thanks for support!";

			ConformRateDialog(mContext,rateUrl,dialog_header,dialog_message);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
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

		conform_dialog_btn_yes.setText("Rate Now");
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
