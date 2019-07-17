package rb.exit.nativelibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class Exit_ListActivity extends Activity
{
	String TAG = "Exit_ListActivity";

	Typeface font_type;

	RelativeLayout rel_native_ad;
	AdRequest native_ad_request;

	RelativeLayout rel_exit_yes;
	RelativeLayout rel_exit_no;
	TextView app_exit_lbl_Yes;
	TextView app_exit_lbl_No;

	RelativeLayout exit_static_ad_lbl;

	//GetHomeStaticLeftTask get_exit_app_left_task;
	//GetPrivacyLinkTask get_Ad_static_link_task;

	Exit_ExitAppClass exit_app_left_data;
	ArrayList<Exit_ExitAppClass> array_exit_app_left = new ArrayList<Exit_ExitAppClass>();

	Exit_AdStaticLink home_ad_left_link;
	ArrayList<Exit_AdStaticLink> array_ad_static_link = new ArrayList<Exit_AdStaticLink>();

	String static_app_name1;
	String static_app_package1;
	String static_app_icon_url1;

	String static_app_name2;
	String static_app_package2;
	String static_app_icon_url2;

	String static_app_name3;
	String static_app_package3;
	String static_app_icon_url3;

	String static_app_name4;
	String static_app_package4;
	String static_app_icon_url4;


	String app_package_name;

	RelativeLayout rel_exit_app_main;

	TextView txt_ads_header;
	TextView txt_ads_footer;

	RelativeLayout rel_exit_app_left1;
	ImageView img_exit_app_left1;
	TextView txt_exit_app_left1;

	RelativeLayout rel_exit_app_left2;
	ImageView img_exit_app_left2;
	TextView txt_exit_app_left2;

	RelativeLayout rel_exit_app_left3;
	ImageView img_exit_app_left3;
	TextView txt_exit_app_left3;

	RelativeLayout rel_exit_app_left4;
	ImageView img_exit_app_left4;
	TextView txt_exit_app_left4;

	DisplayImageOptions image_loader_options;
	protected ImageLoader image_loader = ImageLoader.getInstance();

	ArrayList<Integer> arr;
	String Set_Link;

	RequestQueue requestQueue;

	private Handler data_handler = new Handler()
	{
		public void handleMessage(Message message)
		{
			switch (message.what)
			{
				case 0: // Succeeded
				{
					try
					{
						if(array_exit_app_left.size() > 0)
						{
							rel_exit_app_main.setVisibility(View.VISIBLE);

							int max = array_exit_app_left.size();
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

							static_app_name1 = array_exit_app_left.get(arr.get(0)).app_name.trim();
							static_app_package1 = array_exit_app_left.get(arr.get(0)).app_pakage_name.trim();
							static_app_icon_url1 = array_exit_app_left.get(arr.get(0)).app_icon_url.trim();

							txt_exit_app_left1.setText(static_app_name1);

							if(static_app_icon_url1.length() > 0)
							{
								rel_exit_app_left1.setVisibility(View.VISIBLE);
								image_loader.displayImage(static_app_icon_url1, img_exit_app_left1,image_loader_options);
							}
							else
							{
								rel_exit_app_left1.setVisibility(View.GONE);
							}
						}
						else
						{
							rel_exit_app_left1.setVisibility(View.GONE);
						}

						//--------------------------------2
						if(array_exit_app_left.size() > 1)
						{
							rel_exit_app_main.setVisibility(View.VISIBLE);

							Log.e("Left Random No ::", String.valueOf(arr.get(1)));

							static_app_name2 = array_exit_app_left.get(arr.get(1)).app_name.trim();
							static_app_package2 = array_exit_app_left.get(arr.get(1)).app_pakage_name.trim();
							static_app_icon_url2 = array_exit_app_left.get(arr.get(1)).app_icon_url.trim();

							txt_exit_app_left2.setText(static_app_name2);

							if(static_app_icon_url2.length() > 0)
							{
								rel_exit_app_left2.setVisibility(View.VISIBLE);
								image_loader.displayImage(static_app_icon_url2, img_exit_app_left2,image_loader_options);
							}
							else
							{
								rel_exit_app_left2.setVisibility(View.GONE);
							}
						}
						else
						{
							rel_exit_app_left2.setVisibility(View.GONE);
						}
						//--------------------------------
						//--------------------------------3
						if(array_exit_app_left.size() > 2)
						{
							rel_exit_app_main.setVisibility(View.VISIBLE);

							Log.e("Left Random No ::", String.valueOf(arr.get(2)));

							static_app_name3 = array_exit_app_left.get(arr.get(2)).app_name.trim();
							static_app_package3 = array_exit_app_left.get(arr.get(2)).app_pakage_name.trim();
							static_app_icon_url3 = array_exit_app_left.get(arr.get(2)).app_icon_url.trim();

							txt_exit_app_left3.setText(static_app_name3);

							if(static_app_icon_url3.length() > 0)
							{
								rel_exit_app_left3.setVisibility(View.VISIBLE);
								image_loader.displayImage(static_app_icon_url3, img_exit_app_left3,image_loader_options);
							}
							else
							{
								rel_exit_app_left3.setVisibility(View.GONE);
							}
						}
						else
						{
							rel_exit_app_left3.setVisibility(View.GONE);
						}
						//--------------------------------

						//--------------------------------4
						if(array_exit_app_left.size() > 3)
						{
							rel_exit_app_main.setVisibility(View.VISIBLE);

							Log.e("Left Random No ::", String.valueOf(arr.get(3)));

							static_app_name4 = array_exit_app_left.get(arr.get(3)).app_name.trim();
							static_app_package4 = array_exit_app_left.get(arr.get(3)).app_pakage_name.trim();
							static_app_icon_url4 = array_exit_app_left.get(arr.get(3)).app_icon_url.trim();

							txt_exit_app_left4.setText(static_app_name4);

							if(static_app_icon_url4.length() > 0)
							{
								rel_exit_app_left4.setVisibility(View.VISIBLE);
								image_loader.displayImage(static_app_icon_url4, img_exit_app_left4,image_loader_options);
							}
							else
							{
								rel_exit_app_left4.setVisibility(View.GONE);
							}
						}
						else
						{
							rel_exit_app_left4.setVisibility(View.GONE);
						}
					}catch(Exception e)
					{
						rel_exit_app_main.setVisibility(View.INVISIBLE);
					}
					//--------------------------------

					try
					{
						if(Exit_CommonClass.isOnline(Exit_ListActivity.this))
						{
							GetPrivacyLinkVolleyProcess();
						}
					}
					catch(Exception e)
					{
						//Toast.makeText(Exit_ListActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					}
				}
				break;
				case 1: // Succeeded
				{

					if(array_ad_static_link.size() > 0)
					{
						Exit_CommonHelper.static_ad_name = array_ad_static_link.get(0).ad_name.trim();
						Exit_CommonHelper.static_ad_link = array_ad_static_link.get(0).ad_link.trim();
					}
				}
				break;
				case 99: // Display Message
				{
					//Toast.makeText(Exit_ListActivity.this, "Server Issue!", Toast.LENGTH_LONG).show();
				}
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		getWindow().setFlags(1024, 1024);

		setview();
	}

	private void setview()
	{
		// TODO Auto-generated method stub
		try
		{
			setContentView(R.layout.exit_layout);

			// Creates the Volley request queue
			requestQueue = Volley.newRequestQueue(this);

			// Universal Image Loader start //
			image_loader.init(ImageLoaderConfiguration.createDefault(Exit_ListActivity.this));

			image_loader_options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.exit_sample_loading)
					.showImageForEmptyUri(R.drawable.exit_sample_loading)
					.showImageOnFail(R.drawable.exit_sample_loading)
					.cacheInMemory(true)
					.cacheOnDisk(true)
					.considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
			// Universal Image Loader end //

			font_type = Typeface.createFromAsset(getAssets(), Exit_AppHelper.roboto_font_path);

			rel_exit_yes = (RelativeLayout)findViewById(R.id.app_exit_btn_yes);
			rel_exit_no = (RelativeLayout)findViewById(R.id.app_exit_btn_no);

			app_exit_lbl_Yes = findViewById(R.id.app_exit_lbl_yes);
			app_exit_lbl_No = findViewById(R.id.app_exit_lbl_no);

			setUpAppsLayout();

			String mobileTimeZone =  TimeZone.getDefault().getID();

			if(mobileTimeZone.equals("Asia/Kolkata") || mobileTimeZone.equals("Asia/Calcutta"))
			{
				Log.e("Time Zone :: ", mobileTimeZone);
				Set_Link = Exit_CommonHelper.home_static_India;
			}
			else
			{
				String Contl = stripExtension(mobileTimeZone);
				if(Contl.equals("Asia"))
				{
					Log.e("Time Zone :: ", Contl);
					Set_Link = Exit_CommonHelper.home_static_Asia;
				}
				else if(Contl.equals("America"))
				{
					Log.e("Time Zone :: ", Contl);
					Set_Link = Exit_CommonHelper.home_static_USA;
				}
				else if(Contl.equals("Europe"))
				{
					Log.e("Time Zone :: ", Contl);
					Set_Link = Exit_CommonHelper.home_static_Europe;
				}
				else
				{
					Log.e("Time Zone :: ", Contl);
					Set_Link = Exit_CommonHelper.home_static_Common;
				}
			}


			if(Exit_CommonClass.isOnline(Exit_ListActivity.this))
			{
				GetAppListVolleyProcess();
			}
			else
			{
				InternetCheckDialog();
			}

			rel_exit_yes.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					GoToHome();
				}
			});

			rel_exit_no.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					HomeScreen();
				}
			});

		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	static String stripExtension (String str)
	{
		// Handle null case specially.
		if (str == null) return null;
		// Get position of last '.'.
		int pos = str.lastIndexOf("/");
		// If there wasn't any '.' just return the string as is.
		if (pos == -1) return str;
		// Otherwise return the string, up to the dot.
		return str.substring(0, pos);
	}

	protected void HomeScreen()
	{
		// TODO Auto-generated method stub
		if(Exit_CommonHelper.mActivity != null)
		{
			Intent i = new Intent(getApplicationContext(), Exit_CommonHelper.mActivity.getClass());
			startActivity(i);
			finish();
			overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
		}
		else
		{
			finish();
			overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
		}
	}

	private void setUpAppsLayout()
	{
		// TODO Auto-generated method stub
		rel_exit_app_main = findViewById(R.id.app_exit_list_layout);

		txt_ads_header = findViewById(R.id.app_exit_lbl_header);
		txt_ads_footer = findViewById(R.id.app_exit_lbl_footer);

		exit_static_ad_lbl = findViewById(R.id.exit_static_ad_lbl);

		rel_exit_app_left1 = findViewById(R.id.app_exit_app_layout_1);
		img_exit_app_left1 = findViewById(R.id.app_exit_img_icon_1);
		txt_exit_app_left1 = findViewById(R.id.app_exit_txt_app_name_1);

		rel_exit_app_left2 = findViewById(R.id.app_exit_app_layout_2);
		img_exit_app_left2 = findViewById(R.id.app_exit_img_icon_2);
		txt_exit_app_left2 = findViewById(R.id.app_exit_txt_app_name_2);

		rel_exit_app_left3 = findViewById(R.id.app_exit_app_layout_3);
		img_exit_app_left3 = findViewById(R.id.app_exit_img_icon_3);
		txt_exit_app_left3 = findViewById(R.id.app_exit_txt_app_name_3);

		rel_exit_app_left4 = findViewById(R.id.app_exit_app_layout_4);
		img_exit_app_left4 = findViewById(R.id.app_exit_img_icon_4);
		txt_exit_app_left4 = findViewById(R.id.app_exit_txt_app_name_4);

		txt_ads_header.setTypeface(font_type);
		txt_ads_footer.setTypeface(font_type);

		txt_exit_app_left1.setTypeface(font_type);
		txt_exit_app_left2.setTypeface(font_type);
		txt_exit_app_left3.setTypeface(font_type);
		txt_exit_app_left4.setTypeface(font_type);

		rel_exit_app_main.setVisibility(View.GONE);

		rel_exit_app_left1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String app_name = static_app_name1.trim();
				String app_package = static_app_package1.trim();
				GotoAppStoreDialog(app_name, app_package);
			}
		});

		rel_exit_app_left2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String app_name = static_app_name2.trim();
				String app_package = static_app_package2.trim();
				GotoAppStoreDialog(app_name, app_package);
			}
		});

		rel_exit_app_left3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String app_name = static_app_name3.trim();
				String app_package = static_app_package3.trim();
				GotoAppStoreDialog(app_name, app_package);
			}
		});

		rel_exit_app_left4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String app_name = static_app_name4.trim();
				String app_package = static_app_package4.trim();
				GotoAppStoreDialog(app_name, app_package);
			}
		});

		exit_static_ad_lbl.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				try
				{
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Exit_CommonHelper.static_ad_link));
					startActivity(browserIntent);
					overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			}
		});

	}

	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		GoToHome();
	}

	protected void GoToHome()
	{
		// TODO Auto-generated method stub
		moveTaskToBack(true);
		finish();
		overridePendingTransition(R.anim.exit_slide_in_left, R.anim.exit_slide_out_right);
	}

	Dialog internet_dialog;
	Button btn_yes;
	Button btn_no;

	TextView txt_dialog_header;
	TextView txt_dialog_message;

	String header;
	String message;

	public void InternetCheckDialog()
	{
		internet_dialog = new Dialog(Exit_ListActivity.this,R.style.TransparentBackground_Exit);
		internet_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		internet_dialog.setContentView(R.layout.exit_dialog_rate);

		btn_yes = internet_dialog.findViewById(R.id.dialog_conform_btn_yes);
		btn_no = internet_dialog.findViewById(R.id.dialog_conform_btn_no);
		btn_no.setVisibility(View.GONE);

		txt_dialog_header = internet_dialog.findViewById(R.id.dialog_conform_txt_header);
		txt_dialog_message = internet_dialog.findViewById(R.id.dialog_conform_txt_message);


		btn_yes.setText("OK");


		header = "Internet issue";
		message = "Please check your internet connection!!!";

		txt_dialog_header.setText(header);
		txt_dialog_message.setText(message);

		btn_yes.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				internet_dialog.dismiss();
			}
		});

		internet_dialog.show();
	}

	private void GetPrivacyLinkVolleyProcess()
	{
		requestQueue.getCache().remove(Exit_CommonHelper.ad_policy_link);

		StringRequest strReq = new StringRequest(Request.Method.GET, Exit_CommonHelper.ad_policy_link, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				array_ad_static_link.clear();

				String responseString = "";
				responseString = response.toString();
				//Log.e(TAG, responseString);

				JSONObject jsonResultObj = null;
				// we assume that the response body contains the error
				// message
				try
				{
					jsonResultObj = new JSONObject(responseString);

					if (jsonResultObj == null)
					{
						data_handler.sendMessage(data_handler.obtainMessage(99));
					}
					else
					{
						if(jsonResultObj.has("data"))
						{
							JSONArray jsonResultArr = jsonResultObj.optJSONArray("data");
							if (jsonResultArr == null)
							{
								data_handler.sendMessage(data_handler.obtainMessage(99));
							}

							for (int i = 0; i < jsonResultArr.length(); i++)
							{
								JSONObject jsonObj = jsonResultArr.optJSONObject(i);

								home_ad_left_link = new Exit_AdStaticLink();

								String get_ad_name = jsonObj.optString("ad_name");
								String get_ad_link = jsonObj.optString("ad_link");

								home_ad_left_link.ad_name = get_ad_name;
								home_ad_left_link.ad_link = get_ad_link;

								array_ad_static_link.add(home_ad_left_link);

							}
							data_handler.sendMessage(data_handler.obtainMessage(1));
						}
						else
						{
							data_handler.sendMessage(data_handler.obtainMessage(99));
						}
					}
				}
				catch (Exception e)
				{
					Log.e("JSON", e.toString());
				}
			}
		}, new Response.ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				data_handler.sendMessage(data_handler.obtainMessage(99));
			}
		})
		{

		};

		// Adding request to request queue
		//AppController.getInstance().addToRequestQueue(strReq,tag_string_request);
		requestQueue.add(strReq);
	}

	private void GetAppListVolleyProcess()
	{
		app_package_name = getApplicationContext().getPackageName().trim();
		requestQueue.getCache().remove(Set_Link);

		StringRequest strReq = new StringRequest(Request.Method.GET, Set_Link, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				array_exit_app_left.clear();

				String responseString = null;
				responseString = response.toString();
				//Log.e(TAG, responseString);

				JSONObject jsonResultObj = null;
				// we assume that the response body contains the error
				// message
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

				if(jsonResultObj.has("data"))
				{
					JSONArray jsonResultArr = jsonResultObj.optJSONArray("data");
					if (jsonResultArr == null)
					{
						data_handler.sendMessage(data_handler.obtainMessage(99));
					}

					for (int i = 0; i < jsonResultArr.length(); i++)
					{

						JSONObject jsonObj = jsonResultArr.optJSONObject(i);

						exit_app_left_data = new Exit_ExitAppClass();

						String app_name = jsonObj.optString("app_name");
						String package_name = jsonObj.optString("package_name");
						String icon_url = jsonObj.optString("app_icon");

						if(!app_package_name.equals(package_name))
						{
							exit_app_left_data.app_name = app_name;
							exit_app_left_data.app_pakage_name = package_name;
							exit_app_left_data.app_icon_url = icon_url;

							array_exit_app_left.add(exit_app_left_data);
						}

					}

					data_handler.sendMessage(data_handler.obtainMessage(0));
				}
				else
				{
					data_handler.sendMessage(data_handler.obtainMessage(99));
				}
			}
		}, new Response.ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				data_handler.sendMessage(data_handler.obtainMessage(99));
			}
		})
		{

		};

		// Adding request to request queue
		//AppController.getInstance().addToRequestQueue(strReq,tag_string_request);
		requestQueue.add(strReq);
	}

	Dialog ad_conform_dialog;
	Button btn_ad_yes;
	Button btn_ad_no;

	TextView txt_ad_header;
	TextView txt_ad_message;

	String ad_header;
	String ad_message;

	public void GotoAppStoreDialog(String appName, final String pakageName)
	{
		ad_conform_dialog = new Dialog(Exit_ListActivity.this,R.style.TransparentBackground_Exit);
		ad_conform_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ad_conform_dialog.setContentView(R.layout.exit_dialog_rate);

		btn_ad_yes = ad_conform_dialog.findViewById(R.id.dialog_conform_btn_yes);
		btn_ad_no = ad_conform_dialog.findViewById(R.id.dialog_conform_btn_no);

		txt_ad_header = ad_conform_dialog.findViewById(R.id.dialog_conform_txt_header);
		txt_ad_message = ad_conform_dialog.findViewById(R.id.dialog_conform_txt_message);


		ad_header = appName;
		ad_message = "Are you sure you want to open " + appName + " in Play Store?";

		txt_ad_header.setText(ad_header);
		txt_ad_message.setText(ad_message);

		btn_ad_yes.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					startActivity(new Intent("android.intent.action.VIEW",Uri.parse("market://details?id="+ pakageName)));

					ad_conform_dialog.dismiss();
					return;
				}
				catch (ActivityNotFoundException localActivityNotFoundException)
				{
					startActivity(new Intent("android.intent.action.VIEW",Uri.parse("http://play.google.com/store/apps/details?id="+ pakageName)));
				}

				ad_conform_dialog.dismiss();
			}
		});

		btn_ad_no.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ad_conform_dialog.dismiss();
			}
		});

		ad_conform_dialog.show();

	}


	@Override
	protected void onResume()
	{
		super.onResume();
		NativeAdProcess();
	}

	private void NativeAdProcess()
	{
		if(!Exit_CommonHelper.is_hide_ad)
		{
			boolean is_online = Exit_CommonClass.isOnline(this);
			if(is_online)
			{
				if(Exit_CommonHelper.is_eea_user)
				{
					if (Exit_CommonHelper.is_consent_set)
					{
						boolean check_play_store_user = verifyInstallerId(getApplicationContext());
						if(check_play_store_user)
						{
							LoadAd();
						}
						else
						{
							rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
							rel_native_ad.setVisibility(View.GONE);
						}
					}
					else
					{
						rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
						rel_native_ad.setVisibility(View.GONE);
					}
				}
				else
				{
					boolean check_play_store_user = verifyInstallerId(getApplicationContext());
					if(check_play_store_user)
					{
						LoadAd();
					}
					else
					{
						rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
						rel_native_ad.setVisibility(View.GONE);
					}
				}
			}
			else
			{
				// Hide Ads
				rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
				rel_native_ad.setVisibility(View.GONE);
			}
		}
		else
		{
			// Hide Ads
			rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
			rel_native_ad.setVisibility(View.GONE);
		}
	}

	private void LoadAd()
	{
		// TODO Auto-generated method stub
		try
		{
			//Native Ad Start //
			rel_native_ad = (RelativeLayout)findViewById(R.id.ad_layout);
			rel_native_ad.setVisibility(View.VISIBLE);
			LoadUnifiedNativeAd(Exit_CommonHelper.is_show_non_personalize, Exit_CommonHelper.native_ad_id);
			//Native Ad End //
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static boolean verifyInstallerId(Context context)
	{
		// A list with valid installers package name
		List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));
		// The package name of the app that has installed your app
		final String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
		// true if your app has been downloaded from Play Store
		return installer != null && validInstallers.contains(installer);
	}

	private void LoadUnifiedNativeAd(boolean is_show_non_personalize,String native_ad_id)
	{
		AdLoader.Builder builder = new AdLoader.Builder(this, native_ad_id);
		builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener()
		{
			// OnUnifiedNativeAdLoadedListener implementation.
			@Override
			public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd)
			{
				FrameLayout frameLayout = (FrameLayout) findViewById(R.id.native_ad_layout);
				UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.exit_native_ad_unified, null);
				PopulateUnifiedNativeAdView(unifiedNativeAd, adView);
				frameLayout.removeAllViews();
				frameLayout.addView(adView);
			}
		});

		VideoOptions videoOptions = new VideoOptions.Builder()
				.setStartMuted(true)
				.build();

		NativeAdOptions adOptions = new NativeAdOptions.Builder()
				.setVideoOptions(videoOptions)
				.build();

		builder.withNativeAdOptions(adOptions);

		AdLoader adLoader = builder.withAdListener(new AdListener()
		{
			@Override
			public void onAdFailedToLoad(int errorCode)
			{
				Log.e("Unified Native:","Failed to load native ad!");
			}
		}).build();

		Bundle non_personalize_bundle = new Bundle();
		non_personalize_bundle.putString("npa", "1");

		if(is_show_non_personalize)
		{
			native_ad_request = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, non_personalize_bundle).build();
		}
		else
		{
			native_ad_request = new AdRequest.Builder().build();
		}

		adLoader.loadAd(native_ad_request);

	}

	private void PopulateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView)
	{
		// Get the video controller for the ad. One will always be provided, even if the ad doesn't
		// have a video asset.
		VideoController vc = nativeAd.getVideoController();
		vc.mute(true);
		// Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
		// VideoController will call methods on this object when events occur in the video
		// lifecycle.
		vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks()
		{
			public void onVideoEnd()
			{
				// Publishers should allow native ads to complete video playback before refreshing
				// or replacing them with another ad in the same UI location.
				//refresh.setEnabled(true);
				//videoStatus.setText("Video status: Video playback has ended.");
				super.onVideoEnd();
			}
		});

		View icon_view = adView.findViewById(R.id.ad_app_icon);
		View headline_view = adView.findViewById(R.id.ad_headline);
		View body_view = adView.findViewById(R.id.ad_body);
		View rating_view = adView.findViewById(R.id.ad_stars);
		View price_view = adView.findViewById(R.id.ad_price);
		View store_view = adView.findViewById(R.id.ad_store);
		View advertiser_view = adView.findViewById(R.id.ad_advertiser);
		View call_to_action_view = adView.findViewById(R.id.ad_call_to_action);

		adView.setIconView(icon_view);
		adView.setHeadlineView(headline_view);
		adView.setBodyView(body_view);
		adView.setStarRatingView(rating_view);
		adView.setPriceView(price_view);
		adView.setStoreView(store_view);
		adView.setAdvertiserView(advertiser_view);
		adView.setCallToActionView(call_to_action_view);

		MediaView mediaView = adView.findViewById(R.id.ad_media);
		adView.setMediaView(mediaView);

		ImageView mainImageView = adView.findViewById(R.id.ad_image);
		mainImageView.setVisibility(View.GONE);

		// Apps can check the VideoController's hasVideoContent property to determine if the
		// NativeAppInstallAd has a video asset.

		// Some assets are guaranteed to be in every UnifiedNativeAd.
		/*((TextView) headline_view).setText(nativeAd.getHeadline());
		((TextView) body_view).setText(nativeAd.getBody());
		((Button) call_to_action_view).setText(nativeAd.getCallToAction());*/

		if (nativeAd.getHeadline() == null)
		{
			headline_view.setVisibility(View.GONE);
		}
		else
		{
			((TextView) headline_view).setText(nativeAd.getHeadline());
			headline_view.setVisibility(View.VISIBLE);
		}

		if (nativeAd.getBody() == null)
		{
			body_view.setVisibility(View.GONE);
		}
		else
		{
			((TextView) body_view).setText(nativeAd.getBody());
			body_view.setVisibility(View.VISIBLE);
		}

		if (nativeAd.getCallToAction() == null)
		{
			call_to_action_view.setVisibility(View.GONE);
		}
		else
		{
			((Button) call_to_action_view).setText(nativeAd.getCallToAction());
			call_to_action_view.setVisibility(View.VISIBLE);
		}

		// These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
		// check before trying to display them.
		if (nativeAd.getIcon() == null)
		{
			icon_view.setVisibility(View.GONE);
		}
		else
		{
			((ImageView) icon_view).setImageDrawable(nativeAd.getIcon().getDrawable());
			icon_view.setVisibility(View.VISIBLE);
		}

		if (nativeAd.getPrice() == null)
		{
			price_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			price_view.setVisibility(View.VISIBLE);
			((TextView) price_view).setText(nativeAd.getPrice());
		}

		if (nativeAd.getStore() == null)
		{
			store_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			store_view.setVisibility(View.VISIBLE);
			((TextView) store_view).setText(nativeAd.getStore());
		}

		if (nativeAd.getStarRating() == null)
		{
			rating_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			((RatingBar) rating_view).setRating(nativeAd.getStarRating().floatValue());
			rating_view.setVisibility(View.VISIBLE);
		}

		if (nativeAd.getAdvertiser() == null)
		{
			advertiser_view.setVisibility(View.INVISIBLE);
		}
		else
		{
			((TextView) advertiser_view).setText(nativeAd.getAdvertiser());
			advertiser_view.setVisibility(View.VISIBLE);
		}

		//mediaView.setVisibility(View.VISIBLE);
		//mainImageView.setVisibility(View.VISIBLE);
		body_view.setVisibility(View.VISIBLE);
		rating_view.setVisibility(View.VISIBLE);
		advertiser_view.setVisibility(View.VISIBLE);
		store_view.setVisibility(View.GONE);
		price_view.setVisibility(View.GONE);

		adView.setNativeAd(nativeAd);
	}
}
