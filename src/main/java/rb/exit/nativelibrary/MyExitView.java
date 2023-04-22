package rb.exit.nativelibrary;


import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
        ExitCommonHelper.mActivity = homeActivity;
    }

    public static void OpenExitScreen(boolean isHideAds,boolean isEEAUser,boolean isConsentSet,boolean isShowNonPersonalize,String nativeAdID)
    {
        ExitCommonHelper.is_hide_ad = isHideAds;
        ExitCommonHelper.is_eea_user = isEEAUser;
        ExitCommonHelper.is_consent_set = isConsentSet;
        ExitCommonHelper.is_show_non_personalize = isShowNonPersonalize;
        ExitCommonHelper.native_ad_id = nativeAdID.trim();

        if(ExitCommonClass.isOnline(ExitCommonHelper.mActivity))
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
        Intent i = new Intent(ExitCommonHelper.mActivity, ExitAppActivity.class);
        ExitCommonHelper.mActivity.startActivity(i);
        ((Activity) ExitCommonHelper.mActivity).finish();
        ((Activity) ExitCommonHelper.mActivity).overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
    }

    private static void OnBackSelect()
    {
        if (doubleBackToExitPressedOnce)
        {
            ((Activity) ExitCommonHelper.mActivity).finish();
            ((Activity) ExitCommonHelper.mActivity).overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(ExitCommonHelper.mActivity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

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

    public static void ShareApp(Context ctx)
    {
        try
        {
            mContext = ctx;
            String app_name = mContext.getResources().getString(R.string.app_name) + " :";
            String shareUrl = ExitAppHelper.rate_url + mContext.getPackageName();

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

        conform_dialog_btn_yes.setText("Rate Now");
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
