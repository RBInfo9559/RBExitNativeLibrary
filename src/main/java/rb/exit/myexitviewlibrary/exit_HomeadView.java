package rb.exit.myexitviewlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimeZone;


public class exit_HomeadView extends RelativeLayout {

    static GetHomeStaticLeftTask get_home_static_left_task;
    static GetAdStaticLinkTask get_Ad_static_link_task;

    static exit_HomeStaticClass home_static_left_data;
    static ArrayList<exit_HomeStaticClass> array_home_static_left = new ArrayList<exit_HomeStaticClass>();

    static exit_AdStaticLink home_ad_left_link;
    static ArrayList<exit_AdStaticLink> array_ad_static_link = new ArrayList<exit_AdStaticLink>();

    static String static_app_name1;
    static String static_app_pakage1;
    static String static_app_icon_url1;

    static String static_app_name2;
    static String static_app_pakage2;
    static String static_app_icon_url2;

    static String static_app_name3;
    static String static_app_pakage3;
    static String static_app_icon_url3;

    static String static_app_name4;
    static String static_app_pakage4;
    static String static_app_icon_url4;

    static String app_pakage_name;

    static RelativeLayout rel_home_static1;
    static ImageView img_home_static1;
    static TextView txt_home_static1;

    static RelativeLayout rel_home_static2;
    static ImageView img_home_static2;
    static TextView txt_home_static2;

    static RelativeLayout rel_home_static3;
    static ImageView img_home_static3;
    static TextView txt_home_static3;

    static RelativeLayout rel_home_static4;
    static ImageView img_home_static4;
    static TextView txt_home_static4;

    static RelativeLayout rel_home_static_main;
    static RelativeLayout homestatic_footer_layout;
    static TextView homestatic_lbl_header,homestatic_lbl_footer;
    static ImageView img_ads_footer;

    static DisplayImageOptions image_loader_options;
    protected static ImageLoader image_loader = ImageLoader.getInstance();
    static ArrayList<Integer> arr;
    static String Set_Link;


    private static Handler data_handler = new Handler()
    {
        @SuppressLint("NewApi")
        public void handleMessage(Message message)
        {
            switch (message.what)
            {
                case 0: // Succeeded
                {

                    try
                    {
                        //--------------------1

                        if(array_home_static_left.size() > 0)
                        {
                            rel_home_static_main.setVisibility(View.VISIBLE);

                            int max = array_home_static_left.size();
                            Random generator = new Random();
                            arr = new ArrayList<Integer>();
                            for(int i=0; i<max; i++)
                            {
                                int r = generator.nextInt(max);
                                if(!arr.contains(r))
                                {
                                    arr.add(r);
                                }
                                else
                                {
                                    i--;
                                }
                            }

                            Log.e("Left Random No ::", String.valueOf(arr.get(0)));

                            static_app_name1 = array_home_static_left.get(arr.get(0)).app_name.trim();
                            static_app_pakage1 = array_home_static_left.get(arr.get(0)).app_pakage_name.trim();
                            static_app_icon_url1 = array_home_static_left.get(arr.get(0)).app_icon_url.trim();

                            txt_home_static1.setText(static_app_name1);

                            if(static_app_icon_url1.length() > 0)
                            {
                                rel_home_static1.setVisibility(View.VISIBLE);
                                image_loader.displayImage(static_app_icon_url1, img_home_static1,image_loader_options);
                            }
                            else
                            {
                                rel_home_static1.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            rel_home_static1.setVisibility(View.GONE);
                        }

                        //--------------------2

                        if(array_home_static_left.size() > 0)
                        {
                            rel_home_static_main.setVisibility(View.VISIBLE);

                            Log.e("Right Random No ::", String.valueOf(arr.get(1)));

                            static_app_name2 = array_home_static_left.get(arr.get(1)).app_name.trim();
                            static_app_pakage2 = array_home_static_left.get(arr.get(1)).app_pakage_name.trim();
                            static_app_icon_url2 = array_home_static_left.get(arr.get(1)).app_icon_url.trim();

                            txt_home_static2.setText(static_app_name2);

                            if(static_app_icon_url2.length() > 0)
                            {
                                rel_home_static2.setVisibility(View.VISIBLE);
                                image_loader.displayImage(static_app_icon_url2, img_home_static2,image_loader_options);
                            }
                            else
                            {
                                rel_home_static2.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            rel_home_static2.setVisibility(View.GONE);
                        }

                        //--------------------3

                        if(array_home_static_left.size() > 0)
                        {
                            rel_home_static_main.setVisibility(View.VISIBLE);

                            Log.e("Right Random No ::", String.valueOf(arr.get(2)));

                            static_app_name3 = array_home_static_left.get(arr.get(2)).app_name.trim();
                            static_app_pakage3 = array_home_static_left.get(arr.get(2)).app_pakage_name.trim();
                            static_app_icon_url3 = array_home_static_left.get(arr.get(2)).app_icon_url.trim();

                            txt_home_static3.setText(static_app_name3);

                            if(static_app_icon_url3.length() > 0)
                            {
                                rel_home_static3.setVisibility(View.VISIBLE);
                                image_loader.displayImage(static_app_icon_url3, img_home_static3,image_loader_options);
                            }
                            else
                            {
                                rel_home_static3.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            rel_home_static3.setVisibility(View.GONE);
                        }

                        //--------------------4

                        if(array_home_static_left.size() > 0)
                        {
                            rel_home_static_main.setVisibility(View.VISIBLE);

                            Log.e("Right Random No ::", String.valueOf(arr.get(3)));

                            static_app_name4 = array_home_static_left.get(arr.get(3)).app_name.trim();
                            static_app_pakage4 = array_home_static_left.get(arr.get(3)).app_pakage_name.trim();
                            static_app_icon_url4 = array_home_static_left.get(arr.get(3)).app_icon_url.trim();

                            txt_home_static4.setText(static_app_name4);

                            if(static_app_icon_url4.length() > 0)
                            {
                                img_home_static4.setVisibility(View.VISIBLE);
                                image_loader.displayImage(static_app_icon_url4, img_home_static4,image_loader_options);
                            }
                            else
                            {
                                img_home_static4.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            img_home_static4.setVisibility(View.GONE);
                        }
                    }catch(Exception e)
                    {
                        rel_home_static_main.setVisibility(View.INVISIBLE);
                    }

                    try
                    {
                        if(exit_CommonClass.isOnline(myContext))
                        {
                            get_Ad_static_link_task = new GetAdStaticLinkTask();
                            get_Ad_static_link_task.execute();
                        }
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(myContext, e.toString(), Toast.LENGTH_LONG).show();
                    }



                }
                break;

                case 1:
                {
                    try
                    {
                        //--------------------1

                        if(array_ad_static_link.size() > 0)
                        {
                            exit_CommonHelper.static_ad_name = array_ad_static_link.get(0).ad_name.trim();
                            exit_CommonHelper.static_ad_link = array_ad_static_link.get(0).ad_link.trim();
                        }
                    }
                    catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                break;
            }
        }
    };


    LayoutInflater mInflater;
    static Context myContext;
    public exit_HomeadView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        myContext = context;
        init();

    }
    public exit_HomeadView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        myContext = context;
        init();
    }
    public exit_HomeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        myContext = context;
        init();
    }
    public void init()
    {
        View v = mInflater.inflate(R.layout.exit_applink_layout, this, true);

        // Universal Image Loader start //
        image_loader.init(ImageLoaderConfiguration.createDefault(myContext));

        image_loader_options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.exit_sample_loading)
                .showImageForEmptyUri(R.drawable.exit_sample_loading)
                .showImageOnFail(R.drawable.exit_sample_loading)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        // Universal Image Loader end //
        setupBottomView(v);
        Get_Link();
        if(exit_CommonClass.isOnline(myContext))
        {
            get_home_static_left_task = new GetHomeStaticLeftTask();
            get_home_static_left_task.execute();
        }


    }

    //--------------Show Ad

    private static void Get_Link() {
        // TODO Auto-generated method stub
        String mobileTimeZone =  TimeZone.getDefault().getID();
        if(mobileTimeZone.equals("Asia/Kolkata") || mobileTimeZone.equals("Asia/Calcutta"))
        {
            Log.e("Time Zone :: ", mobileTimeZone);
            Set_Link = exit_CommonHelper.home_static_Indai;
        }
        else
        {
            String Contl = stripExtension(mobileTimeZone);
            if(Contl.equals("Asia"))
            {
                Log.e("Time Zone :: ", Contl);
                Set_Link = exit_CommonHelper.home_static_Asia;
            }
            else if(Contl.equals("America"))
            {
                Log.e("Time Zone :: ", Contl);
                Set_Link = exit_CommonHelper.home_static_USA;
            }
            else if(Contl.equals("Europe"))
            {
                Log.e("Time Zone :: ", Contl);
                Set_Link = exit_CommonHelper.home_static_Europe;
            }
            else
            {
                Log.e("Time Zone :: ", Contl);
                Set_Link = exit_CommonHelper.home_static_Common;
            }
        }
    }



    static String stripExtension (String str) {
        // Handle null case specially.
        if (str == null) return null;
        // Get position of last '.'.
        int pos = str.lastIndexOf("/");
        // If there wasn't any '.' just return the string as is.
        if (pos == -1) return str;
        // Otherwise return the string, up to the dot.
        return str.substring(0, pos);
    }

    private static void setupBottomView(View v)
    {
        // TODO Auto-generated method stub

        rel_home_static_main = (RelativeLayout) v.findViewById(R.id.home_static_layout);

        rel_home_static1 = (RelativeLayout) v.findViewById(R.id.homestatic_app_layout_1);
        img_home_static1 = (ImageView) v.findViewById(R.id.homestatic_img_icon_1);
        txt_home_static1 = (TextView) v.findViewById(R.id.homestatic_txt_app_name_1);

        rel_home_static2 = (RelativeLayout) v.findViewById(R.id.homestatic_app_layout_2);
        img_home_static2 = (ImageView) v.findViewById(R.id.homestatic_img_icon_2);
        txt_home_static2 = (TextView) v.findViewById(R.id.homestatic_txt_app_name_2);

        rel_home_static3 = (RelativeLayout)v.findViewById(R.id.homestatic_app_layout_3);
        img_home_static3 = (ImageView)v.findViewById(R.id.homestatic_img_icon_3);
        txt_home_static3 = (TextView)v.findViewById(R.id.homestatic_txt_app_name_3);

        rel_home_static4 = (RelativeLayout)v.findViewById(R.id.homestatic_app_layout_4);
        img_home_static4 = (ImageView)v.findViewById(R.id.homestatic_img_icon_4);
        txt_home_static4 = (TextView)v.findViewById(R.id.homestatic_txt_app_name_4);

        homestatic_lbl_header = (TextView)v.findViewById(R.id.homestatic_lbl_header);
        homestatic_lbl_footer = (TextView)v.findViewById(R.id.homestatic_lbl_footer);

        img_ads_footer = (ImageView)v.findViewById(R.id.homestatic_img_footer);
        homestatic_footer_layout = (RelativeLayout)v.findViewById(R.id.homestatic_footer_layout);


        rel_home_static_main.setVisibility(View.INVISIBLE);

        rel_home_static1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                String app_name = static_app_name1.trim();
                String app_pakage = static_app_pakage1.trim();
                GotoAppStoreDialog(app_name, app_pakage);
            }
        });

        rel_home_static2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                String app_name = static_app_name2.trim();
                String app_pakage = static_app_pakage2.trim();
                GotoAppStoreDialog(app_name, app_pakage);
            }
        });

        rel_home_static3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                String app_name = static_app_name3.trim();
                String app_pakage = static_app_pakage3.trim();
                GotoAppStoreDialog(app_name, app_pakage);
            }
        });

        rel_home_static4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                String app_name = static_app_name4.trim();
                String app_pakage = static_app_pakage4.trim();
                GotoAppStoreDialog(app_name, app_pakage);
            }
        });

        homestatic_footer_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub

                //Intent browserIntent = new Intent(myContext,PolicyWebActivity.class);
                //startActivity(browserIntent);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                try
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(exit_CommonHelper.static_ad_link));
                    myContext.startActivity(browserIntent);
                    ((Activity)myContext).overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
                }
                catch(Exception e)
                {

                }

            }
        });
    }

    static Dialog ad_conform_dialog;
    static Button btn_ad_yes;
    static Button btn_ad_no;

    static TextView txt_ad_header;
    static TextView txt_ad_message;

    static String ad_header;
    static String ad_message;

    public static void GotoAppStoreDialog(String appName, final String pakageName)
    {
        ad_conform_dialog = new Dialog(myContext,R.style.exit_TransparentBackground);
        ad_conform_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ad_conform_dialog.setContentView(R.layout.exit_dialog_conform_playstore);

        btn_ad_yes = (Button) ad_conform_dialog.findViewById(R.id.dialog_clear_btn_yes);
        btn_ad_no = (Button) ad_conform_dialog.findViewById(R.id.dialog_clear_btn_no);

        txt_ad_header = (TextView) ad_conform_dialog.findViewById(R.id.dialog_clear_txt_header);
        txt_ad_message = (TextView) ad_conform_dialog.findViewById(R.id.dialog_clear_txt_message);

        ad_header = appName;
        ad_message = "Are you sure you want to open " + appName + " in Play Store?";

        txt_ad_header.setText(ad_header);
        txt_ad_message.setText(ad_message);

        btn_ad_yes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    myContext.startActivity(new Intent("android.intent.action.VIEW",Uri.parse("market://details?id="+ pakageName)));
                    ad_conform_dialog.dismiss();
                    return;
                }
                catch (ActivityNotFoundException localActivityNotFoundException)
                {
                    myContext.startActivity(new Intent("android.intent.action.VIEW",Uri.parse("http://play.google.com/store/apps/details?id="+ pakageName)));
                }

                ad_conform_dialog.dismiss();
            }
        });

        btn_ad_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad_conform_dialog.dismiss();
            }
        });

        ad_conform_dialog.show();

    }


    public static class GetHomeStaticLeftTask extends AsyncTask<String, Void, String>
    {
        protected void onPreExecute()
        {
            app_pakage_name = myContext.getApplicationContext().getPackageName().trim();
        }

        public String doInBackground(final String... args)
        {
            try
            {
                array_home_static_left.clear();

                // Rest Client Start //

                String responseString = null;

                exit_RestClient client = new exit_RestClient(Set_Link);
                client.execute(0);
                responseString = client.getResponse();
                //Log.e(TAG, responseString);

                JSONObject jsonResultObj = null;

                // we assume that the response body contains the error message
                try
                {
                    jsonResultObj = new JSONObject(responseString);
                }
                catch (Exception e)
                {
                    Log.e("JSON", e.toString());
                }

                if (jsonResultObj == null)
                {
                    data_handler.sendMessage(data_handler.obtainMessage(99));
                }

                JSONArray jsonResultArr = jsonResultObj.optJSONArray("data");
                if (jsonResultArr == null)
                {
                    data_handler.sendMessage(data_handler.obtainMessage(99));
                }


                for (int i = 0; i < jsonResultArr.length(); i++)
                {

                    JSONObject jsonObj = jsonResultArr.optJSONObject(i);

                    home_static_left_data = new exit_HomeStaticClass();

                    String app_name = jsonObj.optString("app_name");
                    String pakage_name = jsonObj.optString("package_name");
                    String icon_url = jsonObj.optString("app_icon");

                    if(!app_pakage_name.equals(pakage_name))
                    {
                        home_static_left_data.app_name = app_name;
                        home_static_left_data.app_pakage_name = pakage_name;
                        home_static_left_data.app_icon_url = icon_url;

                        array_home_static_left.add(home_static_left_data);
                    }
                }
                data_handler.sendMessage(data_handler.obtainMessage(0));
            }
            catch (final Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        // can use UI thread here
        protected void onPostExecute(final String result)
        {

        }
    }

    //---------

    public static class GetAdStaticLinkTask extends AsyncTask<String, Void, String>
    {
        protected void onPreExecute()
        {
        }

        public String doInBackground(final String... args)
        {
            try
            {
                array_ad_static_link.clear();

                // Rest Client Start //

                String responseString = null;

                exit_RestClient client = new exit_RestClient(exit_CommonHelper.ad_policy_link);
                client.execute(0);
                responseString = client.getResponse();
                //Log.e(TAG, responseString);

                JSONObject jsonResultObj = null;

                // we assume that the response body contains the error message
                try
                {
                    jsonResultObj = new JSONObject(responseString);
                }
                catch (Exception e)
                {
                    Log.e("JSON", e.toString());
                }

                if (jsonResultObj == null)
                {
                    data_handler.sendMessage(data_handler.obtainMessage(99));
                }

                JSONArray jsonResultArr = jsonResultObj.optJSONArray("data");
                if (jsonResultArr == null)
                {
                    data_handler.sendMessage(data_handler.obtainMessage(99));
                }


                for (int i = 0; i < jsonResultArr.length(); i++)
                {

                    JSONObject jsonObj = jsonResultArr.optJSONObject(i);

                    home_ad_left_link = new exit_AdStaticLink();

                    String get_ad_name = jsonObj.optString("ad_name");
                    String get_ad_link = jsonObj.optString("ad_link");



                    home_ad_left_link.ad_name = get_ad_name;
                    home_ad_left_link.ad_link = get_ad_link;


                    array_ad_static_link.add(home_ad_left_link);

                }
                data_handler.sendMessage(data_handler.obtainMessage(1));
            }
            catch (final Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        // can use UI thread here
        protected void onPostExecute(final String result)
        {

        }
    }
}
