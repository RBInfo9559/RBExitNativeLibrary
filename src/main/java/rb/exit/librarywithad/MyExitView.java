package rb.exit.librarywithad;


import android.app.Activity;
import android.app.Application;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MyExitView extends Application
{
    static boolean doubleBackToExitPressedOnce = false;
    private static Context mContext;

    public static void init(Context homeActivity)
    {
        mContext = homeActivity;
        exit_CommonHelper.mActivity = homeActivity;

    }

    public static void OpenExitScreen(boolean isHideAds,boolean isEEAUser,boolean isConsentSet,boolean isShowNonPersonalize,String nativeAdID)
    {
        // TODO Auto-generated method stub
        exit_CommonHelper.is_hide_ad = isHideAds;
        exit_CommonHelper.is_eea_user = isEEAUser;
        exit_CommonHelper.is_consent_set = isConsentSet;
        exit_CommonHelper.is_show_non_personalize = isShowNonPersonalize;
        exit_CommonHelper.native_ad_id = nativeAdID.trim();

        if(exit_CommonClass.isOnline(exit_CommonHelper.mActivity))
        {
            ExitScreen();
        }
        else
        {
            OnBackSelect();
        }
    }


    private static void ExitScreen()
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(exit_CommonHelper.mActivity, exit_ListActivity.class);
        exit_CommonHelper.mActivity.startActivity(i);
        ((Activity)exit_CommonHelper.mActivity).finish();
        ((Activity)exit_CommonHelper.mActivity).overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
    }

    private static void OnBackSelect()
    {
        if (doubleBackToExitPressedOnce)
        {
            ((Activity)exit_CommonHelper.mActivity).finish();
            ((Activity)exit_CommonHelper.mActivity).overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(exit_CommonHelper.mActivity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public static void RateApp(Context ctx)
    {
        // TODO Auto-generated method stub
        try
        {
            mContext = ctx;
            String rateUrl = AppHelper.rate_url + mContext.getPackageName();

            String dialog_header = "Rate App";
            String dialog_message = "Are you sure you want to rate my app?" + "\n" + "Thanks for support!";

            ConformRateDialog(mContext,rateUrl,dialog_header,dialog_message);
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
            String shareUrl = AppHelper.rate_url + mContext.getPackageName();

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

    public static void ConformRateDialog(final Context mContext, final String appUrl, final String header, final String message)
    {
        final Dialog conform_dialog = new Dialog(mContext,R.style.TransparentBackground_Exit);
        conform_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        conform_dialog.setContentView(R.layout.exit_dialog_rate);

        Button conform_dialog_btn_yes = (Button) conform_dialog.findViewById(R.id.dialog_conform_btn_yes);
        Button conform_dialog_btn_no = (Button) conform_dialog.findViewById(R.id.dialog_conform_btn_no);

        TextView conform_dialog_txt_header = (TextView)conform_dialog.findViewById(R.id.dialog_conform_txt_header);
        TextView conform_dialog_txt_message = (TextView)conform_dialog.findViewById(R.id.dialog_conform_txt_message);

        Typeface font_type = Typeface.createFromAsset(mContext.getAssets(), AppHelper.roboto_font_path);

        conform_dialog_btn_yes.setTypeface(font_type);
        conform_dialog_btn_no.setTypeface(font_type);

        conform_dialog_txt_header.setTypeface(font_type);
        conform_dialog_txt_message.setTypeface(font_type);

        conform_dialog_btn_yes.setText("Rate now");
        conform_dialog_btn_no.setText("Cancel");

        String conform_dialog_header = header;
        String conform_dialog_message = message;

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
