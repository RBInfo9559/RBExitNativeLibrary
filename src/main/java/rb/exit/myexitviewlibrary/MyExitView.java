package rb.exit.myexitviewlibrary;


import android.app.Activity;
import android.app.Application;

import android.content.Context;
import android.content.Intent;

import android.os.Handler;

import android.widget.Toast;


public class MyExitView extends Application
{
    static boolean doubleBackToExitPressedOnce = false;


    public static void init(Context homeActivity)
    {
        exit_CommonHelper.mActivity = homeActivity;

    }



    public static void OpenExitScreen()
    {
        // TODO Auto-generated method stub
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

    private static void OnBackSelect() {

        if (doubleBackToExitPressedOnce) {

            ((Activity)exit_CommonHelper.mActivity).finish();
            ((Activity)exit_CommonHelper.mActivity).overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(exit_CommonHelper.mActivity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
