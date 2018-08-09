package rb.exit.nativelibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class Exit_CommonHelper extends Application
{

	public static Context mActivity;

	public static String rate_url = "https://play.google.com/store/apps/details?id=";
	public static String more_url = "https://play.google.com/store/apps/developer?id=";

	public static String home_static_Indai="http://rbinfotech.in/NewWayAdShow/India/app_list.php";
	public static String home_static_Asia="http://rbinfotech.in/NewWayAdShow/Asia/app_list.php";
	public static String home_static_USA="http://rbinfotech.in/NewWayAdShow/USA/app_list.php";
	public static String home_static_Europe="http://rbinfotech.in/NewWayAdShow/Europe/app_list.php";
	public static String home_static_Common="http://rbinfotech.in/NewWayAdShow/Common/app_list.php";
	public static String ad_policy_link="http://rbinfotech.in/NewWayAdShow/AdPolicyLink/ad_p_link.php";
	
	public static String static_ad_link = null,static_ad_name = null;

	public static boolean is_hide_ad = false;
	public static boolean is_eea_user = false;
	public static boolean is_consent_set = false;
	public static boolean is_show_non_personalize = false;
	public static String native_ad_id = "";
	
}
