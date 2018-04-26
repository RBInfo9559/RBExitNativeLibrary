package rb.exit.myexitviewlibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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

public class exit_ListActivity extends Activity
{
	String TAG = "exit_ListActivity";

	TextView app_exit_lbl_Yes;
	TextView app_exit_lbl_No;

	RelativeLayout exit_static_ad_lbl;

	GetHomeStaticLeftTask get_exit_app_left_task;
	GetAdStaticLinkTask get_Ad_static_link_task;

	exit_ExitAppClass exit_app_left_data;
	ArrayList<exit_ExitAppClass> array_exit_app_left = new ArrayList<exit_ExitAppClass>();

	exit_AdStaticLink home_ad_left_link;
	ArrayList<exit_AdStaticLink> array_ad_static_link = new ArrayList<exit_AdStaticLink>();

	String static_app_name1;
	String static_app_pakage1;
	String static_app_icon_url1;

	String static_app_name2;
	String static_app_pakage2;
	String static_app_icon_url2;

	String static_app_name3;
	String static_app_pakage3;
	String static_app_icon_url3;

	String static_app_name4;
	String static_app_pakage4;
	String static_app_icon_url4;


	String app_pakage_name;

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
						static_app_pakage1 = array_exit_app_left.get(arr.get(0)).app_pakage_name.trim();
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

					if(array_exit_app_left.size() > 0)
					{
						rel_exit_app_main.setVisibility(View.VISIBLE);

						Log.e("Left Random No ::", String.valueOf(arr.get(1)));

						static_app_name2 = array_exit_app_left.get(arr.get(1)).app_name.trim();
						static_app_pakage2 = array_exit_app_left.get(arr.get(1)).app_pakage_name.trim();
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

					if(array_exit_app_left.size() > 0)
					{
						rel_exit_app_main.setVisibility(View.VISIBLE);

						Log.e("Left Random No ::", String.valueOf(arr.get(2)));

						static_app_name3 = array_exit_app_left.get(arr.get(2)).app_name.trim();
						static_app_pakage3 = array_exit_app_left.get(arr.get(2)).app_pakage_name.trim();
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

					if(array_exit_app_left.size() > 0)
					{
						rel_exit_app_main.setVisibility(View.VISIBLE);

						Log.e("Left Random No ::", String.valueOf(arr.get(3)));

						static_app_name4 = array_exit_app_left.get(arr.get(3)).app_name.trim();
						static_app_pakage4 = array_exit_app_left.get(arr.get(3)).app_pakage_name.trim();
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
					if(exit_CommonClass.isOnline(exit_ListActivity.this))
					{
						get_Ad_static_link_task = new GetAdStaticLinkTask();
						get_Ad_static_link_task.execute();
					}
				}
				catch(Exception e)
				{
					Toast.makeText(exit_ListActivity.this, e.toString(), Toast.LENGTH_LONG).show();
				}


			}
			break;
			case 1: // Succeeded
			{

				if(array_ad_static_link.size() > 0)
				{
					exit_CommonHelper.static_ad_name = array_ad_static_link.get(0).ad_name.trim();
					exit_CommonHelper.static_ad_link = array_ad_static_link.get(0).ad_link.trim();
				}
			}
			break;
			case 99: // Display Message
			{


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

			// Universal Image Loader start //
			image_loader.init(ImageLoaderConfiguration.createDefault(exit_ListActivity.this));

			image_loader_options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.exit_sample_loading)
					.showImageForEmptyUri(R.drawable.exit_sample_loading)
					.showImageOnFail(R.drawable.exit_sample_loading)
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
			// Universal Image Loader end //


			app_exit_lbl_Yes = findViewById(R.id.app_exit_lbl_Yes);
			app_exit_lbl_No = findViewById(R.id.app_exit_lbl_No);

			setUpAppsLayout();

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


			if(exit_CommonClass.isOnline(exit_ListActivity.this))
			{
				get_exit_app_left_task = new GetHomeStaticLeftTask();
				get_exit_app_left_task.execute();
			}
			else
			{
				InternetCheckDialog();
			}

			app_exit_lbl_Yes.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					GoToHome();
				}
			});

			app_exit_lbl_No.setOnClickListener(new OnClickListener()
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

	protected void HomeScreen() 
	{
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(), exit_CommonHelper.mActivity.getClass());
		startActivity(i);
		finish();
		overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
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

		rel_exit_app_main.setVisibility(View.GONE);

		rel_exit_app_left1.setOnClickListener(new OnClickListener() 
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

		rel_exit_app_left2.setOnClickListener(new OnClickListener() 
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

		rel_exit_app_left3.setOnClickListener(new OnClickListener() 
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

		rel_exit_app_left4.setOnClickListener(new OnClickListener() 
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

		exit_static_ad_lbl.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				try
				{
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(exit_CommonHelper.static_ad_link));
					startActivity(browserIntent);
					overridePendingTransition(R.anim.exit_slide_in_right, R.anim.exit_slide_out_left);
				}
				catch(Exception e)
				{

				}

				
				//Intent browserIntent = new Intent(exit_ListActivity.this,PolicyWebActivity.class);
				//startActivity(browserIntent);
				//overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

			}
		}); 

	}

	//---------

	public class GetAdStaticLinkTask extends AsyncTask<String, Void, String>
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
		/*int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		System.exit(0);*/
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
		internet_dialog = new Dialog(exit_ListActivity.this,R.style.exit_TransparentBackground);
		internet_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		internet_dialog.setContentView(R.layout.exit_dialog_conform_playstore);

		btn_yes = internet_dialog.findViewById(R.id.dialog_clear_btn_yes);
		btn_no = internet_dialog.findViewById(R.id.dialog_clear_btn_no);
		btn_no.setVisibility(View.GONE);

		txt_dialog_header = internet_dialog.findViewById(R.id.dialog_clear_txt_header);
		txt_dialog_message = internet_dialog.findViewById(R.id.dialog_clear_txt_message);


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


	public class GetHomeStaticLeftTask extends AsyncTask<String, Void, String> 
	{
		protected void onPreExecute() 
		{
			app_pakage_name = getApplicationContext().getPackageName().trim();
		}

		public String doInBackground(final String... args) 
		{
			try 
			{
				array_exit_app_left.clear();

				// Rest Client Start //

				String responseString = null;

				exit_RestClient client = new exit_RestClient(Set_Link);
				client.execute(0);
				responseString = client.getResponse();

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

					exit_app_left_data = new exit_ExitAppClass();

					String app_name = jsonObj.optString("app_name");
					String pakage_name = jsonObj.optString("package_name");
					String icon_url = jsonObj.optString("app_icon");

					if(!app_pakage_name.equals(pakage_name))
					{
						exit_app_left_data.app_name = app_name;
						exit_app_left_data.app_pakage_name = pakage_name;
						exit_app_left_data.app_icon_url = icon_url;

						array_exit_app_left.add(exit_app_left_data);
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


	Dialog ad_conform_dialog;
	Button btn_ad_yes;
	Button btn_ad_no;

	TextView txt_ad_header;
	TextView txt_ad_message;

	String ad_header;
	String ad_message;

	public void GotoAppStoreDialog(String appName, final String pakageName)
	{
		ad_conform_dialog = new Dialog(exit_ListActivity.this,R.style.exit_TransparentBackground);
		ad_conform_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ad_conform_dialog.setContentView(R.layout.exit_dialog_conform_playstore);

		btn_ad_yes = ad_conform_dialog.findViewById(R.id.dialog_clear_btn_yes);
		btn_ad_no = ad_conform_dialog.findViewById(R.id.dialog_clear_btn_no);

		txt_ad_header = ad_conform_dialog.findViewById(R.id.dialog_clear_txt_header);
		txt_ad_message = ad_conform_dialog.findViewById(R.id.dialog_clear_txt_message);


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
}
