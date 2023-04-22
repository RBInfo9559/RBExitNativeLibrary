package rb.exit.nativelibrary;

import android.app.Application;
import android.content.Context;

public class ExitCommonHelper extends Application
{
	public static Context mActivity = null;

	public static String rate_url = "https://play.google.com/store/apps/details?id=";
	public static String more_url = "https://play.google.com/store/apps/developer?id=";

	public static boolean is_hide_ad = false;
	public static boolean is_eea_user = false;
	public static boolean is_consent_set = false;
	public static boolean is_show_non_personalize = false;
	public static String native_ad_id = "";
	
}
