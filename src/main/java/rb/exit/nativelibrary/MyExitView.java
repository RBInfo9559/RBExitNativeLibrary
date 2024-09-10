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

    public static void OpenExitScreen(boolean isHideAds,boolean isPlayStoreInstall,String adID)
    {
        ExitCommonHelper.is_hide_ad = isHideAds;
        ExitCommonHelper.is_play_store_install = isPlayStoreInstall;
        ExitCommonHelper.ad_id = adID.trim();

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
}
