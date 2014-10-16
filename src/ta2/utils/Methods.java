
package ta2.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

// Apache
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import ta2.comps.Comp_WP;
import ta2.items.FilterHistory;
import ta2.items.Memo;
import ta2.items.TI;
import ta2.items.WordPattern;
import ta2.listeners.MP_OCmpL;
import ta2.listeners.dialog.DL;
import ta2.main.ImageActv;
import ta2.main.MemoActv;
import ta2.main.MemoEditActv;
import ta2.main.PhotoActv;
import ta2.main.PlayActv;
import ta2.main.PrefActv;
import ta2.main.LogActv;
import ta2.main.R;
import ta2.main.RecActv;
import ta2.main.ShowListActv;
import ta2.services.Service_ShowProgress;
import ta2.tasks.Task_AudioTrack;

// REF=> http://commons.apache.org/net/download_net.cgi
//REF=> http://www.searchman.info/tips/2640.html

//import org.apache.commons.net.ftp.FTPReply;

public class Methods {
	
	public static void confirm_quit(Activity actv, int keyCode) {
		
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			
			AlertDialog.Builder dialog=new AlertDialog.Builder(actv);
			
	        dialog.setTitle(actv.getString(R.string.generic_tv_confirm));
	        dialog.setMessage(actv.getString(R.string.generic_tv_quit_app));
	        
	        dialog.setPositiveButton(
	        				actv.getString(R.string.generic_bt_ok),
	        				new DL(actv, dialog, 0));
	        
	        dialog.setNegativeButton(
	        				actv.getString(R.string.generic_bt_cancel),
	        				new DL(actv, dialog, 1));
	        
	        dialog.create();
	        dialog.show();
			
		}//if (keyCode==KeyEvent.KEYCODE_BACK)
		
	}//public static void confirm_quit(Activity actv, int keyCode)

	/****************************************
	 *	getMillSeconds_now()
	 * 
	 * <Caller> 
	 * 1. ButtonOnClickListener # case main_bt_start
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	/******************************
		@return format => "yyyyMMdd_HHmmss"
	 ******************************/
	public static String get_TimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static List<String> 
	get_FileList(File dpath) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// Directory exists?

		////////////////////////////////
				
		if (!dpath.exists()) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Dir doesn't exist");
			
			return null;
			
		} else {//if (!dpath.exists() == condition)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Dir exists: " + dpath.getAbsolutePath());
			
		}//if (!dpath.exists() == condition)

		////////////////////////////////

		// Get: File list

		////////////////////////////////
		
		List<String> list_Dir = new ArrayList<String>();
		
		File[] files_list = dpath.listFiles();
		
		if (files_list == null) {
			
			// Log
			String msg_log = "listFiles() => returned null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_log);
			
			return null;
			
		}

		////////////////////////////////

		// Sort list

		////////////////////////////////
		
		Methods.sort_list_files(files_list);
		
		for (File f : files_list) {
			
			list_Dir.add(f.getName());
			
		}//for (File f : files_list)
		
		/*********************************
		 * 3. Return
		 *********************************/
		return list_Dir;
		
	}//get_FileList

	public static void 
	sort_list_files(File[] files) {
		// REF=> http://android-coding.blogspot.jp/2011/10/sort-file-list-in-order-by-implementing.html
		/****************************
		 * 1. Prep => Comparator
		 * 2. Sort
			****************************/
		
		/****************************
		 * 1. Prep => Comparator
			****************************/
		Comparator<? super File> filecomparator = new Comparator<File>(){
			
			public int compare(File file1, File file2) {
				/****************************
				 * 1. Prep => Directory
				 * 2. Calculate
				 * 3. Return
					****************************/
				
				int pad1=0;
				int pad2=0;
				
				if(file1.isDirectory())pad1=-65536;
				if(file2.isDirectory())pad2=-65536;
				
				int res = pad2-pad1+file1.getName().compareToIgnoreCase(file2.getName());
				
				return res;
			} 
		 };//Comparator<? super File> filecomparator = new Comparator<File>()
		 
		/****************************
		 * 2. Sort
			****************************/
		Arrays.sort(files, filecomparator);

	}//public static void sort_list_files(File[] files)

	public static String 
	get_Pref_String
	(Activity actv, String pref_name,
			String pref_key, String defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(
						pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getString(pref_key, defValue);

	}//public static String get_Pref_String

	public static boolean
	set_Pref_String
	(Activity actv, String pName, String pKey, String value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putString(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)

	public static boolean
	set_Pref_String
	(Activity actv, String pName, String pKey, boolean value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putBoolean(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)
	
	
	public static int 
	get_Pref_Int
	(Activity actv, String pref_name, String pref_key, int defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getInt(pref_key, defValue);

	}//public static boolean set_pref(String pref_name, String value)

	public static boolean 
	get_Pref_Boolean
	(Activity actv, 
		String pref_name, String pref_key, boolean defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
		
		/****************************
		 * Return
		 ****************************/
		return prefs.getBoolean(pref_key, defValue);
		
	}//public static boolean set_pref(String pref_name, String value)
	
	/******************************
		@return true => pref set
	 ******************************/
	public static boolean 
	set_Pref_Int
	(Activity actv, 
			String pref_name, String pref_key, int value) {
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
	
		/****************************
		 * 2. Get editor
			****************************/
		SharedPreferences.Editor editor = prefs.edit();
	
		/****************************
		 * 3. Set value
			****************************/
		editor.putInt(pref_key, value);
		
		try {
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
		}
	
	}//set_Pref_Int
	
	public static boolean 
	file_Exists
	(Activity actv, String fpath) {
		// TODO Auto-generated method stub
		
		File f = new File(fpath);
		
		return f.exists();
		
	}//file_Exists

	public static String 
	get_Dirname
	(Activity actv, String target) {

		String[] tokens = target.split(File.separator);
	
		////////////////////////////////
		
		// tokens => null
		
		////////////////////////////////
		if (tokens == null) {
			
			// Log
			String msg_Log = "tokens => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return target;
			
		}
		
		////////////////////////////////

		// tokens => 1

		////////////////////////////////
		if (tokens.length == 1) {
			
			return target;
			
		}
		
		////////////////////////////////

		// tokens > 1

		////////////////////////////////
		String[] tokens_New = Arrays.copyOfRange(tokens, 0, tokens.length - 1);
		
		return StringUtils.join(tokens_New, File.separator);
	
	}//get_Dirname

	public static String 
	get_Filename
	(Activity actv, String target) {
		
		String[] tokens = target.split(File.separator);
		
		////////////////////////////////
		
		// tokens => null
		
		////////////////////////////////
		if (tokens == null) {
			
			// Log
			String msg_Log = "tokens => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return target;
			
		}
		
		////////////////////////////////
		
		// tokens => 1
		
		////////////////////////////////
		if (tokens.length == 1) {
			
			return target;
			
		}
		
		////////////////////////////////
		
		// tokens > 1
		
		////////////////////////////////
//		String[] tokens_New = Arrays.copyOfRange(tokens, 0, tokens.length - 1);
		
		return tokens[tokens.length - 1];
		
//		return StringUtils.join(tokens_New, File.separator);
		
	}//get_Dirname
	
	/****************************
	 * deleteDirectory(File target)()
	 * 
	 * 1. REF=> http://www.rgagnon.com/javadetails/java-0483.html
		****************************/
	public static boolean 
	deleteDirectory
	(File target) {
		
		if(target.exists()) {
			//
			File[] files = target.listFiles();
			
			//
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					
					deleteDirectory(files[i]);
					
				} else {//if (files[i].isDirectory())
					
					String path = files[i].getAbsolutePath();
					
					files[i].delete();
					
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "Removed => " + path);
					
					
				}//if (files[i].isDirectory())
				
			}//for (int i = 0; i < files.length; i++)
			
		}//if(target.exists())
		
		return (target.delete());
	}//public static boolean deleteDirectory(File target)

	/******************************
		@return null => 1. dpath_Target ==> Dir doesn't exist<br>
						2. listFiles ==> returned null
	 ******************************/
	public static List<String> get_DirList(String dpath_Target) {
		/*********************************
		 * 1. Directory exists?
		 * 2. Build list
		 * 2-1. Sort list
		 * 
		 * 3. Return
		 *********************************/
		File dir_Target = new File(dpath_Target);
		
		////////////////////////////////
		
		// Directory exists?
		
		////////////////////////////////
		
		if (!dir_Target.exists()) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Dir doesn't exist");
			
			return null;
			
		} else {//if (!dpath.exists() == condition)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Dir exists: " + dir_Target.getAbsolutePath());
			
		}//if (!dpath.exists() == condition)
		
		////////////////////////////////
		
		// Get: Dir list (Directories only)
		
		////////////////////////////////
		List<String> list_Dir = new ArrayList<String>();
		
		File[] files_list = dir_Target.listFiles(new FileFilter(){
	
			@Override
			public boolean accept(File f) {
				
				return f.isDirectory();
				
			}
			
		});
		
		if (files_list == null) {
			
			// Log
			String msg_log = "listFiles() => returned null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_log);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Sort list
		
		////////////////////////////////
		
		Methods.sort_list_files(files_list);
		
		for (File f : files_list) {
			
			list_Dir.add(f.getName());
			
		}//for (File f : files_list)
		
		/*********************************
		 * 3. Return
		 *********************************/
		return list_Dir;
		
	}//public static List<String> get_file_list(File dpath)

public static String 
	conv_CurrentPathMove_to_CurrentPathMove_New
	(String curPath_Move) {
		// TODO Auto-generated method stub
		
		String[] tokens = curPath_Move.split(File.separator);
		
		////////////////////////////////

		// tokens == 1

		////////////////////////////////
		if (tokens == null) {
			
			return curPath_Move;
			
		} else if (tokens.length == 1) {
			
			return curPath_Move;
			
		}
		
		////////////////////////////////

		// tokens > 1

		////////////////////////////////
		int len = tokens.length;
		
		String[] tokens_New = Arrays.copyOfRange(tokens, 0, len - 1);
//		String[] tokens_New = Arrays.copyOfRange(tokens, 0, len - 2);
		
		return StringUtils.join(tokens_New, File.separator);
		
	}//conv_CurrentPathMove_to_CurrentPathMove_New

	/*********************************
	 * REF=> http://www.searchman.info/tips/2640.html
	 * 
	 * #sqlite db file: "database disk image is malformed"
	 * REF=> http://stackoverflow.com/questions/9058169/sqlite-database-disk-image-is-malformed-on-windows-but-fine-on-android
	 * 
	 * @return
		 * -1	=> SocketException
		 * -2	=> IOException
		 * -3	=> IOException in disconnecting
		 * 
		 * -2	=> Log in failed
		 * >0	=> Reply code
	 * 
	 *********************************/
	public static int 
	ftp_connect_disconnect
	(Activity actv) {
		/*********************************
		 * memo
		 *********************************/
		// FTP client
		FTPClient fp = new FTPClient();
		
		int reply_code;
		
		String server_name = "ftp.benfranklin.chips.jp";
		
		String uname = "chips.jp-benfranklin";

		String passwd = "9x9jh4";
		
//		String fpath = StringUtils.join(
//				new String[]{
//						MainActv.dirPath_db,
//						MainActv.fileName_db
//				}, File.separator);
//		
//		String fpath_audio = StringUtils.join(
//				new String[]{
//						MainActv.dirName_ExternalStorage,
//						"Audios",
//						"Fiddle_music",
//						"Gaelic Folk Song.mp3"
//				}, File.separator);

//		String fpath_remote = "./" + MainActv.fileName_db;
		
		String fpath_remote = "./" + "Gaelic Folk Song.mp3.v2";
		
		/*********************************
		 * Connect
		 *********************************/
		try {
			
			fp.connect(server_name);
			
			reply_code = fp.getReplyCode();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp.getReplyCode()=" + fp.getReplyCode());
			
		} catch (SocketException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -1;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -2;
		}

		//debug
		/*********************************
		 * Disconnect
		 *********************************/
		try {
			
			fp.disconnect();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp => Disconnected");

			return reply_code;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -3;
			
		}

		
//		return 0;
		
//		/*********************************
//		 * Log in
//		 *********************************/
//		boolean res;
//		
//		try {
//			
//			res = fp.login(uname, passwd);
//			
//			if(res == false) {
//				
//				reply_code = fp.getReplyCode();
//				
//				// Log
//				Log.e("Methods.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "Log in failed => " + reply_code);
//				
//				fp.disconnect();
//				
//				return -2;
//				
//			} else {
//				
//				// Log
//				Log.d("Methods.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", "Log in => Succeeded");
//				
//			}
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		/*********************************
//		 * FTP files
//		 *********************************/
//		// �t�@�C�����M
//		FileInputStream is;
//		
//		try {
//			
//			is = new FileInputStream(fpath);
////			is = new FileInputStream(fpath_audio);
//			
////			fp.storeFile("./" + MainActv.fileName_db, is);// �T�[�o�[��
//			res = fp.storeFile(fpath_remote, is);// �T�[�o�[��
//			
////			fp.makeDirectory("./ABC");
//			
//			if (res == true) {
//				
//				// Log
//				Log.d("Methods.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", "File => Stored");
//				
//			} else {//if (res == true)
//
//				// Log
//				Log.d("Methods.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", "Store file => Failed");
//
//			}//if (res == true)
//			
//			is.close();
//
//		} catch (FileNotFoundException e) {
//
//			// Log
//			Log.e("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception: " + e.toString());
//			
//		} catch (IOException e) {
//			
//			// Log
//			Log.e("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception: " + e.toString());
//
//		}
//						
//		/*********************************
//		 * Disconnect
//		 *********************************/
//		try {
//			
//			fp.disconnect();
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "fp => Disconnected");
//
//			return reply_code;
//			
//		} catch (IOException e) {
//			
//			// Log
//			Log.e("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Error: " + e.toString());
//			
//			return -1;
//			
//		}
		
	}//ftp_connect_disconnect

	//REF http://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-timeouts answered Oct 24 '10 at 16:28
	public static boolean isOnline(Activity actv) {
	    ConnectivityManager cm =
	        (ConnectivityManager) actv.getSystemService(Context.CONNECTIVITY_SERVICE);
	    
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	    	
	        return true;
	        
	    }
	    
	    return false;
	    
	}

	public static void 
	start_Activity_PrefActv
	(Activity actv) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, PrefActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);

	}//start_Activity_PrefActv

	public static void 
	start_Activity_MemoActv
	(Activity actv) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, MemoActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}//start_Activity_PrefActv
	
	public static void 
	start_Activity_LogActv
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, LogActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//start_Activity_LogActv
	
	public static void 
	start_Activity_PhotoActv
	(Activity actv) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, PhotoActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}//start_Activity_PhotoActv
	
	public static void 
	start_Activity_RecActv
	(Activity actv) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, RecActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}//start_Activity_RecActv
	
	public static void 
	start_Activity_ShowListActv
	(Activity actv) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, ShowListActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}//start_Activity_ShowListActv
	
	/******************************
		@return
			-1	No db file<br>
			-2	Copying db file => failed<br>
			1	db file => copied<br>
	 ******************************/
	public static int 
	import_DB
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// setup: src, dst

		////////////////////////////////
		// IFM10
		String src_dir = CONS.DB.dPath_dbFile_backup_TWT;
//		String src_dir = CONS.DB.dPath_dbFile_backup_IFM11;
//		String src_dir = CONS.DB.dPath_dbFile_backup;
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread()
						.getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return -1;
			
		}//if (src_dir_files.length == condition)
		
		// Latest file
		File f_src_latest = src_dir_files[0];
		
		for (File file : src_dir_files) {
			
			if (f_src_latest.lastModified() < file.lastModified()) {
						
				f_src_latest = file;
				
			}//if (variable == condition)
			
		}//for (File file : src_dir_files)
		
		// Show the path of the latest file
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "f_src_latest=" + f_src_latest.getAbsolutePath());
		
		////////////////////////////////

		// Restore file

		////////////////////////////////
		String src = f_src_latest.getAbsolutePath();
		
		String dst = StringUtils.join(
				new String[]{
						//REF http://stackoverflow.com/questions/9810430/get-database-path answered Jan 23 at 11:24
						actv.getDatabasePath(CONS.DB.dbName).getPath()
				},
//						actv.getFilesDir().getPath() , 
//						CONS.DB.dbName},
				File.separator);
		
		// Log
		String msg_Log = "db path => " 
					+ actv.getDatabasePath(CONS.DB.dbName).getPath();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// build: db file path (dst)

		////////////////////////////////
		String tmp_str = Methods.get_Dirname(actv, dst);
		
		String dst_New = StringUtils.join(
					new String[]{
							
							tmp_str,
							CONS.DB.dbName_TWT
//							CONS.DB.dbName_IFM11
							
					}, 
					File.separator);
		
		// Log
		msg_Log = String.format(
							"src = %s // dst = %s", 
							src, dst_New);
		
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// import (using restoration-related method)

		////////////////////////////////
		boolean res = Methods.restore_DB(
							actv, 
							CONS.DB.dbName, 
							src, dst_New);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res=" + res);

		
//		//debug
//		boolean res = true;
		
		/******************************
			validate
		 ******************************/
		if (res == false) {		// copying db file => failed
			
			String msg = "Copying file => failed";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			d3.dismiss();
			
			return -2;
			
		}
		
		////////////////////////////////

		// dismiss: dlg

		////////////////////////////////
		d3.dismiss();
		d2.dismiss();
		d1.dismiss();
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = "DB => Imported\n" + CONS.DB.dbName_Importing;
		Methods_dlg.dlg_ShowMessage(actv, msg);
		
		
		////////////////////////////////

		// return

		////////////////////////////////
		return 1;

	}//import_DB

	/*********************************
	 * @return true => File copied(i.e. restored)<br>
	 * 			false => Copying failed
	 *********************************/
	public static boolean
	restore_DB
	(Activity actv, String dbName, 
			String src, String dst) {
		/*********************************
		 * 1. Setup db
		 * 2. Setup: File paths
		 * 3. Setup: File objects
		 * 4. Copy file
		 * 
		 *********************************/
		////////////////////////////////

		// Setup db => This process is necessary if the database folder
		//				is not yet created.

		////////////////////////////////
		
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
	
		wdb.close();
	
		/*********************************
		 * 2. Setup: File paths
	
		/*********************************
		 * 3. Setup: File objects
		 *********************************/
	
		/*********************************
		 * 4. Copy file
		 *********************************/
		FileChannel iChannel = null;
		FileChannel oChannel = null;
		
		try {
			iChannel = new FileInputStream(src).getChannel();
			oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied: " + src);
			
//			// debug
//			Toast.makeText(actv, "DB restoration => Done", Toast.LENGTH_LONG).show();
			
			return true;
	
		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Exception: " + e.toString());
	
				}
				
			}
			
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
			
			if (oChannel != null) {
				
				try {
					oChannel.close();
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
	
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
			
			if (oChannel != null) {
				
				try {
					oChannel.close();
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
	
			
			return false;
			
		}//try
		
	}//restore_DB

	/******************************
		@return
			>1	Number of patterns saved<br>
			0	No patterns saved<br>
			-1	Table 'patterns' => not exist<br>
			-2	Can't build list<br>
			-3	Unknown result<br>
	 ******************************/
	public static int 
	import_Patterns
	(Activity actv) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// get: patterns list

		////////////////////////////////
		List<String> patterns_List = _import_Patterns__Get_PatternsList(actv);
		
		/******************************
			validate: null
		 ******************************/
		// Log
		if (patterns_List == null) {
			
			// Log
			String msg_Log = "patterns_List => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -2;
			
		}
		
		String msg_Log = "patterns_List => " + patterns_List.size();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// insert patterns

		////////////////////////////////
		int res = Methods._import_Patterns__SavePatterns(actv, patterns_List);
		
		// Log
		msg_Log = "save pattern: res => " + res;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		if (res == -1) {
			
//			String msg = "Table 'patterns' => doesn't exist";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
//			d3.dismiss();

			return -1;
			
		} else if (res == 0) {
			
			return 0;
			
//			String msg = "No patterns saved";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
//			
//			d3.dismiss();
			
		} else if (res > 0) {
			
			return res;
			
//			String msg = "Patterns saved => " + res;
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.green4);
			
//			d3.dismiss();
//			d2.dismiss();
//			d1.dismiss();
			
		} else {
			
			// Log
			msg_Log = "Unknown result => " + res;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -3;
			
//			String msg = "Unknown result => " + res;
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.yello);
//			
//			d3.dismiss();
			
		}
		
	}//import_Patterns

	private static int 
	_import_Patterns__SavePatterns
	(Activity actv, List<String> patterns_List) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// insert

		////////////////////////////////
		boolean res;
		
		int counter = DBUtils.insert_Data_Patterns(actv, patterns_List);
			
		return counter;
		
	}//_import_Patterns__SavePatterns

	/******************************
		@return null => 1. No such table<br>
						2. Cursor => null<br>
						3. Cursor < 1 <br>
	 ******************************/
	private static List<String> 
	_import_Patterns__Get_PatternsList
	(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////
	
		// db
	
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName_TWT);
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName_IFM11);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
	
		////////////////////////////////
	
		// Table exists?
	
		////////////////////////////////
		String tableName = CONS.DB.tname_Patterns_TWT;
//		String tableName = CONS.DB.tname_MemoPatterns_IFM11;

		boolean res = dbu.tableExists(rdb, tableName);
		
		if (res == true) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);
			
		} else {//if (res == false)
			////////////////////////////////
	
			// no table => return
	
			////////////////////////////////
	//		// Log
	//		Log.d("Methods.java" + "["
	//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
	//				+ "]", "Table doesn't exist: " + tableName);
			
			String msg = "Table doesn't exist: " + tableName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}//if (res == false)
		
		
		////////////////////////////////
	
		// Get cursor
	
		////////////////////////////////
		// "word"
		String orderBy = CONS.DB.col_names_Patterns_TWT[0] + " ASC"; 
//		String orderBy = CONS.DB.col_names_MemoPatterns_IFM11[0] + " ASC"; 
		
		Cursor c = rdb.query(
						CONS.DB.tname_Patterns_TWT,
						CONS.DB.col_names_Patterns_TWT,
//						CONS.DB.tname_MemoPatterns_IFM11,
//						CONS.DB.col_names_MemoPatterns_IFM11,
		//				CONS.DB.col_types_refresh_log_full,
						null, null,		// selection, args 
						null, 			// group by
						null, 		// having
						orderBy);
	
		/******************************
			validate: null
		 ******************************/
		if (c == null) {
	
			String msg = "query => null";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
		
		/******************************
			validate: any entry?
		 ******************************/
		if (c.getCount() < 1) {
	
			String msg = "entry => < 1";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
	
		////////////////////////////////
	
		// cursor: move to first
	
		////////////////////////////////
		c.moveToFirst();
		
		////////////////////////////////
	
		// Get list
	
		////////////////////////////////
		List<String> patternList = new ArrayList<String>();
		
		if (c.getCount() > 0) {
			
			for (int i = 0; i < c.getCount(); i++) {
				
				patternList.add(c.getString(0));	// 0 => "word"
				
				c.moveToNext();
				
			}//for (int i = 0; i < patternList.size(); i++)
			
		} else {//if (c.getCount() > 0)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "!c.getCount() > 0");
			
		}//if (c.getCount() > 0)
		
		
		Collections.sort(patternList);
	
		////////////////////////////////
	
		// return
	
		////////////////////////////////
		return patternList;
		
	}//_import_Patterns__Get_PatternsList

	public static String
	conv_MillSec_to_TimeLabel(long millSec)
	{
		//REF http://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android answered Oct 31 '11 at 12:59
		String dateFormat = CONS.Admin.format_Date;
//		String dateFormat = "yyyy/MM/dd hh:mm:ss.SSS";
		
		DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.JAPAN);
//		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(millSec);
		
		return formatter.format(calendar.getTime());
		
	}//conv_MillSec_to_TimeLabel(long millSec)

	public static String
	conv_MillSec_to_AudioFileLabel(long millSec)
	{
		//REF http://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android answered Oct 31 '11 at 12:59
		String dateFormat = CONS.Admin.format_Date_AudioFile;
//		String dateFormat = "yyyy/MM/dd hh:mm:ss.SSS";
		
		DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.JAPAN);
//		DateFormat formatter = new SimpleDateFormat(dateFormat);
		
		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(millSec);
		
		return formatter.format(calendar.getTime());
		
	}//conv_MillSec_to_TimeLabel(long millSec)
	
	public static void 
	createTable_Patterns
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		int res = DBUtils.createTable(
						actv, 
						CONS.DB.dbName, 
						CONS.DB.tname_Patterns, 
						CONS.DB.col_names_Patterns, 
						CONS.DB.col_types_Patterns);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1	Table exists<br>
//		-2	Exception in executing the sql<br>
//		1	Table created<br>
		
		case -1: 
			
			msg = "Table alread exists => " + CONS.DB.tname_Patterns;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
		
		case -2: 
			
			msg = "Exception in executing the sql";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table created => " + CONS.DB.tname_Patterns;
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID,
				CONS.Admin.dflt_MessageDialog_Length);
		
	}//createTable_Patterns

	public static void 
	createTable_Memos
	(Activity actv, 
			Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		String tname = CONS.DB.tname_TA2;
		
		int res = DBUtils.createTable(
				actv, 
				CONS.DB.dbName, 
				tname, 
				CONS.DB.col_names_TA2, 
				CONS.DB.col_types_TA2);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {
		
//		-1	Table exists<br>
//		-2	Exception in executing the sql<br>
//		1	Table created<br>
		
		case -1: 
			
			msg = "Table alread exists => " + tname;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		case -2: 
			
			msg = "Exception in executing the sql";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table created => " + tname;
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID,
				CONS.Admin.dflt_MessageDialog_Length);
		
	}//createTable_Patterns
	
	/******************************
		@return
			-1	CONS.Audio.audioTrack => null<br>
			-2	CONS.Audio.audioTrack.write => NullPointerException<br>
			-3	CONS.Audio.audioTrack => Exception<br>
			1	CONS.Audio.audioTrack => stopped<br>
	 ******************************/
	public static int 
	playSound
	(Activity actv, int bgmResourceId) {
		// TODO Auto-generated method stub
		int minBufferSize = AudioTrack.getMinBufferSize(
				44100,
				AudioFormat.CHANNEL_CONFIGURATION_MONO, 
				AudioFormat.ENCODING_PCM_16BIT);

		CONS.Audio.audioTrack = new AudioTrack(
//				AudioTrack audioTrack = new AudioTrack(
			AudioManager.STREAM_MUSIC, 44100,
			AudioFormat.CHANNEL_CONFIGURATION_MONO, 
			AudioFormat.ENCODING_PCM_16BIT,
			minBufferSize,
			AudioTrack.MODE_STREAM); 
		
//		CONS.Audio.audioTrack.get
		
		float vol = CONS.Audio.dflt_Audio_Volume;
//		float vol = 0.3f;
		
//		CONS.Audio.audioTrack.setStereoVolume(vol, vol);
//		
//		CONS.Audio.audioTrack.play();
		
		int i = 0;
		int bufferSize = 512;
		byte [] buffer = new byte[bufferSize];
		
//		//test
//		CONS.Audio.audioTrack.setLoopPoints(0, buffer.length / 4, -1);
//		
//		// Log
//		String msg_Log = "loop points => set";
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		// Log
//		msg_Log = "buffer.length => " + buffer.length;
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);

		/******************************
			validate: null
		 ******************************/
		if (CONS.Audio.audioTrack == null) {
			
			// Log
			String msg_Log = "CONS.Audio.audioTrack => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}
	
		CONS.Audio.audioTrack.setStereoVolume(vol, vol);

		/******************************
			validate: null
		 ******************************/
		if (CONS.Audio.audioTrack == null) {
			
			// Log
			String msg_Log = "CONS.Audio.audioTrack => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}

		////////////////////////////////

		// setup: stream

		////////////////////////////////
		InputStream inputStream = 
				actv.getResources().openRawResource(bgmResourceId);

		////////////////////////////////

		// set: play

		////////////////////////////////
		CONS.Audio.audioTrack.play();
		
		//InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_1);
		
//		InputStream inputStream = 
//						actv.getResources().openRawResource(bgmResourceId);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.Audio.audioTrack == null) {
			
			// Log
			String msg_Log = "CONS.Audio.audioTrack => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}

		////////////////////////////////

		// write: audio

		////////////////////////////////
		try {
			
			while((i = inputStream.read(buffer)) != -1)
				CONS.Audio.audioTrack.write(buffer, 0, i);
			
		} catch (NullPointerException e) {
			
			// Log
			String msg_Log = "NullPointerException!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e.printStackTrace();
			
			return -2;
			
		} catch (IOException e) {
			
			// Log
			String msg_Log = "IOException!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		////////////////////////////////

		// close: stream

		////////////////////////////////
		try {
			
			inputStream.close();
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Stream closed");
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		/******************************
			validate: null
		 ******************************/
		if (CONS.Audio.audioTrack == null) {
			
			// Log
			String msg_Log = "CONS.Audio.audioTrack => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}

		////////////////////////////////

		// stop

		////////////////////////////////
		
		CONS.Audio.audioTrack.flush();
		
		// Log
		String msg_Log = "audio track => flushed";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		try {
			
			CONS.Audio.audioTrack.stop();
			
		} catch (Exception e) {
			
			// Log
			msg_Log = "Exception!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			e.printStackTrace();
			
			return -3;
			
		}
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Audio stopped");
		
		////////////////////////////////

		// release

		////////////////////////////////
		CONS.Audio.audioTrack.release();
		
		// Log
		msg_Log = "audio => released";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		return 1;
		
	}//playSound

	public static void 
	start_SE
	(Activity actv, int audioID) {
		// TODO Auto-generated method stub
		
		boolean val = Methods.get_Pref_Boolean(
				actv, 
				CONS.Pref.pname_MainActv, 
				actv.getString(R.string.prefs_sound_effect_key), 
				false);
		
		// avoid starting the same task instance more than once
		//		at a time
		if (val == true) {
			
			// Log
			String msg_Log = "Memo => starting SE...";
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			if (CONS.Audio.task_Audio != null) {
				
				// Log
				msg_Log = "CONS.Audio.task_Audio => not null";
				Log.d("BO_CL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				if(CONS.Audio.audioTrack != null) {
//				if (CONS.Audio.task_Audio != null
//						&& CONS.Audio.audioTrack != null) {
				
					// Log
					msg_Log = "cancelling task audio...";
					Log.d("BO_CL.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					CONS.Audio.task_Audio.cancel(true);
					
					CONS.Audio.task_Audio = null;
					
					// Log
					msg_Log = "audio task => re-initializing...";
					Log.d("DOI_CL.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					CONS.Audio.task_Audio = new Task_AudioTrack(actv);
					
				}
				
			} else {

				msg_Log = "NOT cancelling task audio...";
				Log.d("BO_CL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);

			}
			
			int bgmResourceId = audioID;
//			int bgmResourceId = R.raw.tap_x0_094;
			
//			CONS.Audio.task_Audio = new Task_AudioTrack(actv);
//		Task_AudioTrack task = new Task_AudioTrack(actv);
			
//		task.execute("Start");
			CONS.Audio.task_Audio.execute(bgmResourceId);
			
		}//if (val == true)
		
	}
	
	public static void 
	start_SE_new
	(Activity actv, int audioID) {
		// TODO Auto-generated method stub
		
		Task_AudioTrack task_Audio = new Task_AudioTrack(actv);
		
		// Log
		String msg_Log = "starting task audio...";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		task_Audio.execute(audioID);
				
			
	}//start_SE_new

	public static boolean 
	backup_DB
	(Activity actv) {
		/****************************
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 
		 * 2-3. Dst folder => Files within the limit?
		 * 3. Copy
			****************************/
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_Src = StringUtils.join(
					new String[]{
							actv.getDatabasePath(CONS.DB.dbName).getPath()},
//							CONS.fname_db},
					File.separator);
		
		String db_Dst_Folder = StringUtils.join(
					new String[]{
							CONS.DB.dPath_dbFile_Backup,
							CONS.DB.fname_DB_Backup_Trunk},
//							CONS.dpath_db_backup,
//							CONS.fname_db_backup_trunk},
					File.separator);
		
		String db_Dst = db_Dst_Folder + "_"
				+ time_label
//				+ MainActv.fileName_db_backup_ext;
				+ CONS.DB.fname_DB_Backup_ext;
//		+ CONS.fname_db_backup_ext;
//				+ MainActv.fname_db_backup_trunk;

		// Log
		String msg_log = "db_Src = " + db_Src
						+ " / "
						+ "db_Dst = " + db_Dst;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		/****************************
		 * 2. Prep => Files
			****************************/
		File src = new File(db_Src);
		File dst = new File(db_Dst);
		
		/****************************
		 * 2-2. Folder exists?
			****************************/
		File db_Backup = new File(CONS.DB.dPath_dbFile_Backup);
//		File db_backup = new File(CONS.dpath_db_backup);
		
		if (!db_Backup.exists()) {
			
			try {
				
				db_Backup.mkdirs();
//				db_Backup.mkdir();
				
				/******************************
					validate
				 ******************************/
				if (db_Backup.isDirectory()) {
					
					// Log
					Log.d("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Folder created: " + db_Backup.getAbsolutePath());
					
				} else {
					
					// Log
					String msg_Log = "Create folder => not successful";
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					return false;
					
				}
				
			} catch (Exception e) {
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Failed");
				
				return false;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		////////////////////////////////

		// Dst folder => Files within the limit?

		////////////////////////////////
		File[] files_dst_folder = new File(CONS.DB.dPath_dbFile_Backup).listFiles();
//		File[] files_dst_folder = new File(CONS.dpath_db_backup).listFiles();

		if (files_dst_folder != null) {
			
			int num_of_files = files_dst_folder.length;
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "num of backup files = " + num_of_files);
		}
		
		
		/****************************
		 * 3. Copy
			****************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			
			
//			// Log
//			Log.i("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "DB file copied");
//			
//			// debug
//			Toast.makeText(actv, "DB backup => Done", Toast.LENGTH_LONG).show();

		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		}//try

		////////////////////////////////

		// save date

		////////////////////////////////
		boolean res = DBUtils._backup_DB_SaveDate(actv);
		
		// Log
		String msg_Log = "save date => " + res;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		return true;
		
	}//public static boolean db_backup(Activity actv)

	public static boolean
	restore_DB(Activity actv) {
    	
    	// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Starting: restore_DB()");

		/*********************************
		 * Get the absolute path of the latest backup file
		 *********************************/
		// Get the most recently-created db file
		String src_dir = CONS.DB.dPath_dbFile_Backup;
//		String src_dir = "/mnt/sdcard-ext/IFM9_backup";
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread()
						.getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return false;
			
		}//if (src_dir_files.length == condition)
		
		// Latest file
		File f_src_latest = src_dir_files[0];
		
		
		for (File file : src_dir_files) {
			
			if (f_src_latest.lastModified() < file.lastModified()) {
						
				f_src_latest = file;
				
			}//if (variable == condition)
			
		}//for (File file : src_dir_files)
		
		// Show the path of the latest file
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "f_src_latest=" + f_src_latest.getAbsolutePath());
		
		/*********************************
		 * Restore file
		 *********************************/
		String src = f_src_latest.getAbsolutePath();
		String dst = StringUtils.join(
				new String[]{
						//REF http://stackoverflow.com/questions/9810430/get-database-path answered Jan 23 at 11:24
						actv.getDatabasePath(CONS.DB.dbName).getPath()
				},
//						actv.getFilesDir().getPath() , 
//						CONS.DB.dbName},
				File.separator);
		
		boolean res = Methods.restore_DB(
							actv, 
							CONS.DB.dbName, 
							src, dst);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res=" + res);
		
		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
	}//private void restore_DB()

	public static void 
	restore_DB
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// restore

		////////////////////////////////
		boolean res = Methods.restore_DB(actv);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		if (res == true) {
			
			d2.dismiss();
			d1.dismiss();
			
			String msg = "Restore db => Done";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		} else {

			d2.dismiss();
			
			String msg = "Restore db => Failed";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
		}
		
	}//restore_DB

	/******************************
		@return
			-1 Table doesnt exist<br>
			-2 SQLException<br>
			1 Table dropped<br>
	 ******************************/
	
	public static int drop_Table
	(Activity actv, String tname) {
		// TODO Auto-generated method stub

		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		return dbu.dropTable(actv, tname);
		
	}

	public static void 
	drop_Table
	(Activity actv, String tableName,
			Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		int res = Methods.drop_Table(actv, tableName);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = CONS.Admin.dflt_Background_Color;
		
		switch(res) {

		case 1:
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			msg = "Table => dropped: " + tableName;
			
			colorID = R.color.green4;
			
			break;
		
		case -1:
			
			d3.dismiss();
			
			msg = "Table => doesn't exist: " + tableName;
			colorID = R.color.gold2;
			
			break;
			
		case -2:
			
			d3.dismiss();
			
			msg = "SQLException!";
			colorID = R.color.red;
			
			break;
			
		}

		Methods_dlg.dlg_ShowMessage(actv, msg, colorID);
			
	}//drop_Table

	/******************************
		@return
			-1	insertion => failed<br>
			-2	Exception<br>
			1	text => inserted<br>
	 ******************************/
	public static int 
	save_Memo
	(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// view

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		////////////////////////////////

		// get text

		////////////////////////////////
		String text = et.getText().toString();
		
		////////////////////////////////

		// save

		////////////////////////////////
		int res = DBUtils.save_Memo(actv, text);
		
		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
	}//save_Memo

	/******************************
		@param d1 
	 * @return
			-1	insertion => failed<br>
			-2	Exception<br>
			1	text => inserted<br>
	 ******************************/
	public static int 
	save_Memo
	(Activity actv, Dialog d1, int resourceID) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// view
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(resourceID);
//		EditText et = (EditText) actv.findViewById(resourceID);
//		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		////////////////////////////////
		
		// get text
		
		////////////////////////////////
		String text = et.getText().toString();
		
		////////////////////////////////
		
		// save
		
		////////////////////////////////
		int res = DBUtils.save_Memo(actv, text);
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return res;
		
	}//save_Memo
	
	public static void 
	report_DropTable_Memos
	(Activity actv, 
		int res, Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		String msg = null;
		int colorID = 0;

//		-1 Table doesnt exist
//		-2 SQLException
//		1 Table dropped

		switch(res) {

		case -1: 
			
			msg = "Table doesnt exist";
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
		
		case -2: 
			
			msg = "SQLException";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table dropped";
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		default:
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
//				Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID);

	}

	public static void 
	report_Save_Memos
	(Activity actv, int res) {
		// TODO Auto-generated method stub
//		-1	insertion => failed<br>
//		-2	Exception<br>
//		1	text => inserted<br>
		
		String msg = null;
		int colorID = 0;

//		-1 Table doesnt exist
//		-2 SQLException
//		1 Table dropped

		switch(res) {

		case -1: 
			
			msg = "insertion => failed";
			colorID = R.color.red;
			
			break;
		
		case -2: 
			
			msg = "Exception";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "text => inserted";
			colorID = R.color.green4;
			
			break;
			
		default:
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
//				Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID);
		
		
		
	}

	public static void 
	report_Update_Memos
	(Activity actv, int res) {
		// TODO Auto-generated method stub
//		1	update => done
//		0	update => failed
		
		String msg = null;
		int colorID = 0;
		
		switch(res) {
		
		case 1: 
			
			msg = "update => done";
			colorID = R.color.green4;
			
			break;
			
		case 0: 
			
			msg = "update => failed";
			colorID = R.color.red;
			
			break;
			
		default:
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
//				Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID);
		
	}//report_Update_Memos
	
	public static void 
	save_Memo_Temporary
	(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// get value

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		String text = et.getText().toString();
		
		////////////////////////////////

		// save

		////////////////////////////////
		boolean res = Methods.set_Pref_String(
							actv, 
							CONS.Pref.pname_MainActv, 
							CONS.Pref.pkey_Saved_Memo, text);

		if (res == true) {
			
			// Log
			String msg_Log = "text => saved to pref";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "text => not saved to pref";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}
		
		
	}//save_Memo_Temporary

	public static long
	conv_TimeLabel_to_MillSec(String timeLabel)
//	conv_MillSec_to_TimeLabel(long millSec)
	{
//		String input = "Sat Feb 17 2012";
		Date date;
		try {
			date = new SimpleDateFormat(
						CONS.Admin.format_Date, Locale.JAPAN).parse(timeLabel);
			
			return date.getTime();
//			long milliseconds = date.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// Log
			String msg_Log = "Exception: " + e.toString();
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}
		
//		Locale.ENGLISH).parse(input);
		
//		Date date = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH).parse(input);
//		long milliseconds = date.getTime();
		
	}//conv_TimeLabel_to_MillSec(String timeLabel)

	public static void 
	update_MemoActv_ListViews
	(Activity actv, String new_Word) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// which word type

		////////////////////////////////
		String reg_Tags = "^[:#][a-zA-Z]";
		String reg_Literals = "[a-zA-Z]";
		
		Pattern p_Tags = Pattern.compile(reg_Tags);
		Pattern p_Literals = Pattern.compile(reg_Literals);
		
		Matcher m_Tags = null;
		Matcher m_Literals = null;
		
		m_Tags = p_Tags.matcher(new_Word);
		m_Literals = p_Literals.matcher(new_Word);
		
		int type = 0;//	1. tags 2. literals 3. symbols
		
		if (m_Tags.find()) {
			
			type = 1;
			
		}
		
		if (!m_Literals.find()) {
			
			type = 3;
			
		}
		
		if(type == 0) {
			
			type = 2;
			
		}
		
		// Log
		String msg_Log = "type => " + type;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// update lists

		////////////////////////////////
		switch(type) {
		
		case 1:// tags
			
			CONS.MemoActv.list_WP_2.clear();
			CONS.MemoActv.list_WP_2.addAll(DBUtils.find_All_WP_tags(actv));
			
			////////////////////////////////

			// sort

			////////////////////////////////
			Collections.sort(
							CONS.MemoActv.list_WP_2, 
							new Comp_WP(
									
									CONS.Enums.SortType.WORD,
									CONS.Enums.SortOrder.ASC
							));

			
			CONS.MemoActv.adp_WPList_2.notifyDataSetChanged();
			
			break;
		
		case 2:// literals
			
			CONS.MemoActv.list_WP_3.clear();
			CONS.MemoActv.list_WP_3.addAll(DBUtils.find_All_WP_literals(actv));
			
			////////////////////////////////

			// sort

			////////////////////////////////
			Collections.sort(
							CONS.MemoActv.list_WP_3, 
							new Comp_WP(
									
									CONS.Enums.SortType.WORD,
									CONS.Enums.SortOrder.ASC
							));

			CONS.MemoActv.adp_WPList_3.notifyDataSetChanged();
			
			break;
			
		case 3:// symbols
			
			CONS.MemoActv.list_WP_1.clear();
			CONS.MemoActv.list_WP_1.addAll(DBUtils.find_All_WP_symbols(actv));
			
			////////////////////////////////

			// sort

			////////////////////////////////
			Collections.sort(
							CONS.MemoActv.list_WP_1, 
							new Comp_WP(
									
									CONS.Enums.SortType.WORD,
									CONS.Enums.SortOrder.ASC
							));

			CONS.MemoActv.adp_WPList_1.notifyDataSetChanged();
			
			break;
			
		}
		
	}//update_MemoActv_ListViews

	public static void 
	delete_Memo
	(Activity actv, 
		Dialog d1, Dialog d2, Memo memo) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// delete: db

		////////////////////////////////
		int res = DBUtils.delete_Memo(actv, memo);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1 Table doesn't exist
//		-2 Deletion => failed
//		> 1 Deletion => done		
		
		case -1: 
			
			msg = "Table doesn't exist => " + CONS.DB.tname_TA2;
			colorID = R.color.gold2;
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);

			d2.dismiss();

			return;
		
		case -2: 
			
			msg = "Deletion => failed";
			colorID = R.color.red;
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);

			d2.dismiss();
			
			return;
			
		}

		////////////////////////////////

		// delete from: list view

		////////////////////////////////
		CONS.ShowListActv.list_Memos.remove(memo);
		
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		////////////////////////////////

		// pref => remove

		////////////////////////////////
		boolean res_b = Methods.set_Pref_Int(
				actv,
				CONS.Pref.pname_ShowListActv,
				CONS.Pref.pkey_ShowListActv_Current_Position,
//				CONS.Pref.pkey_CurrentPosition,
				CONS.Pref.dflt_IntExtra_value);
		
		if (res_b == true) {
			
			// notify adapter
			CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
			
			// Log
			String msg_Log = "pref position => reset";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "pref position rest => not done";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}

		////////////////////////////////

		// report

		////////////////////////////////
		msg = "Deletion => done";
		colorID = R.color.green4;
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

		////////////////////////////////

		// dismiss

		////////////////////////////////
		d2.dismiss();
		d1.dismiss();
		
//		Methods_dlg.dlg_ShowMessage(
//				actv, 
//				msg,
//				colorID);
		
	}//delete_Memo

	public static void 
	delete_Pattern
	(Activity actv, 
		Dialog d1, Dialog d2, WordPattern wp) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// delete: db

		////////////////////////////////
		int res = DBUtils.delete_Pattern(actv, wp);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		String tname = CONS.DB.tname_Patterns;
		
		switch(res) {

//		-1 Table doesn't exist
//		-2 Deletion => failed
//		> 1 Deletion => done		
		
		case -1: 
			
			msg = "Table doesn't exist => " + tname;
			colorID = R.color.gold2;
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);

			d2.dismiss();

			return;
		
		case -2: 
			
			msg = "Deletion => failed: " + wp.getWord();
			colorID = R.color.red;
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);

			d2.dismiss();
			
			return;
			
		}

		////////////////////////////////

		// delete from: list view

		////////////////////////////////
		
		Methods.update_MemoActv_ListViews(actv, wp.getWord());
		
//		CONS.MemoActv.li.remove(memo);
//		
//		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();

		////////////////////////////////

		// report

		////////////////////////////////
		msg = "Deletion => done";
		colorID = R.color.green4;
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

		////////////////////////////////

		// dismiss

		////////////////////////////////
		d2.dismiss();
		d1.dismiss();
		
//		Methods_dlg.dlg_ShowMessage(
//				actv, 
//				msg,
//				colorID);
		
	}//delete_Pattern

	public static void 
	start_Activity_MemoEditActv
	(Activity actv, Dialog d1, Memo memo) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// setup: intent

		////////////////////////////////
		Intent i = new Intent();
		
		i.setClass(actv, MemoEditActv.class);

		i.putExtra(CONS.Intent.iKey_Memo_Id, memo.getDb_Id());
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//start_Activity_MemoEditActv

	/******************************
		Used from ShowListActv
	 ******************************/
	public static void 
	start_Activity_ImageActv
	(Activity actv, Dialog d1, Memo memo) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// get: id

		////////////////////////////////
		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PhotoMemo);
//		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
		Matcher m = p.matcher(memo.getText());

//		String file_full_path = null;
		
		String id_str = null;
		
		if (m.find()) {
			
			id_str = m.group(1);
			
			// Log
			String msg_Log = "id_str => " + id_str;
			Log.d("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "no match";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}

		////////////////////////////////

		// get: TI

		////////////////////////////////
		String selection = android.provider.BaseColumns._ID + " = ?";
		
		String[] args = new String[]{
				
				id_str
				
		};
		
		String order = null;
		
		Cursor c = Methods.get_Content(actv, selection, args, order);
		
		/******************************
			validate
		 ******************************/
		if (c == null) {
			
			// Log
			String msg_Log = "cursor => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg_Log, d1);

			return;
			
		}
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// build: TI

		////////////////////////////////
		c.moveToFirst();
		
		CONS.IMageActv.ti = new TI.Builder()
//		TI ti = new TI.Builder()

				.setDb_Id(c.getLong(0))
				.setCreated_at(c.getString(1))
				.setModified_at(c.getString(2))
				
				.setFileId(c.getLong(3))
				.setFile_path(c.getString(4))
				.setFile_name(c.getString(5))
				
				.setDate_added(c.getString(6))
				.setDate_modified(c.getString(7))
				
				.setMemo(c.getString(8))
				.setTags(c.getString(9))
				
				.setLast_viewed_at(c.getString(10))
				.setTable_name(c.getString(11))
		
				.setUploaded_at(c.getString(12))
				
				.build();

//		// fpath
//		String fpath = StringUtils.join(
//				new String[]{
//						
//						ti.getFile_path(),
//						ti.getFile_name()
//				}, 
//				File.separator);
		
		////////////////////////////////

		// set: memo (global)

		////////////////////////////////
		CONS.IMageActv.memo = memo;
		
		// Log
		msg_Log = "CONS.IMageActv.memo => set";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		////////////////////////////////
		
		// setup: intent
		
		////////////////////////////////
		Intent i = new Intent();
		
		i.setClass(actv, ImageActv.class);
		
		// Log
		msg_Log = "ti.getDb_Id() => " + CONS.IMageActv.ti.getDb_Id();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		i.putExtra("file_id", ti.getFileId());
//		i.putExtra("db_id", ti.getDb_Id());
//		i.putExtra("file_path", fpath);
////		i.putExtra("file_path", ti.getFile_path());
//		i.putExtra("file_name", ti.getFile_name());
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

		actv.startActivity(i);

		////////////////////////////////
		
		// dismiss
		
		////////////////////////////////
		d1.dismiss();
		
		
//		Intent i = new Intent();
//		
//		i.setClass(actv, ImageActv.class);
//		
//		i.putExtra(CONS.Intent.iKey_Memo_Id, memo.getDb_Id());
//		
//		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//		
//		actv.startActivity(i);
//		
		
	}//start_Activity_PhotoActv
	
	public static boolean 
	update_Memo
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// view

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		////////////////////////////////

		// get text

		////////////////////////////////
		String text = et.getText().toString();

//		////////////////////////////////
//
//		// update: memo
//
//		////////////////////////////////
//		CONS.MemoEditActv.memo.setText(text);
		
		////////////////////////////////

		// save

		////////////////////////////////
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7

		boolean res = DBUtils.updateData_generic_With_TimeLable(
						actv, 
						CONS.DB.tname_TA2, 
						CONS.MemoEditActv.memo.getDb_Id(), 
						CONS.DB.col_names_TA2_full[3], 
						text);
		
		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
	}//update_Memo

	public static boolean 
	update_Memo_PlayActv
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// view
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_add_memos_et_content);
		
		////////////////////////////////
		
		// get text
		
		////////////////////////////////
		String text = et.getText().toString();
		
//		////////////////////////////////
//
//		// update: memo
//
//		////////////////////////////////
//		CONS.MemoEditActv.memo.setText(text);
		
		////////////////////////////////
		
		// save
		
		////////////////////////////////
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7
		
		boolean res = DBUtils.updateData_generic_With_TimeLable(
				actv, 
				CONS.DB.tname_TA2, 
				CONS.PlayActv.memo.getDb_Id(), 
				CONS.DB.col_names_TA2_full[3], 
				text);
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return res;
		
	}//update_Memo
	
	/*********************************
	 * REF=> http://www.searchman.info/tips/2640.html
	 * 
	 * #sqlite db file: "database disk image is malformed"
	 * REF=> http://stackoverflow.com/questions/9058169/sqlite-database-disk-image-is-malformed-on-windows-but-fine-on-android
	 * @return
	 * -1	=> SocketException<br>
	 * -2	=> IOException<br>
	 * -3	=> IOException in disconnecting<br>
	 * -4	=> Login failed<br>
	 * -5	=> IOException in logging-in<br>
	 * 
	 * -6	=> storeFile returned false<br>
	 * -7	=> can't find the source file<br>
	 * -8	=> can't find the source file; can't disconnect FTP client<br>
	 * -9	=> storeFile ---> IOException<br>
	 * -10	=> storeFile ---> IOException; can't disconnect FTP client<br>
	 * 
	 * -11	=> set file type ---> failed<br>
	 * -12	=> IOException in logging-in; can't disconnect FTP client<br>
	 * 
	 * -2	=> Log in failed<br>
	 * >0	=> Reply code<br>
	 * 
	 *********************************/
	public static int 
	ftp_Remote_DB
	(Activity actv) {
		/*********************************
		 * memo
		 *********************************/
		// FTP client
		FTPClient fp = new FTPClient();
		
		int reply_code;
		
		// backup db name
		String dbName_backup = String.format(Locale.JAPAN,
					"%s_%s%s", 
					CONS.DB.fname_DB_Backup_Trunk,
					Methods.get_TimeLabel(
							Methods.getMillSeconds_now()),
					CONS.DB.fname_DB_Backup_ext
					);
		
		String fpath_Src = 
					actv.getDatabasePath(CONS.DB.dbName).getAbsolutePath();
		
		String fpath_remote = StringUtils.join(
				new String[]{
						CONS.Remote.remote_Root_DBFile,
						dbName_backup
				}, File.separator);
		
		/*********************************
		 * Connect
		 *********************************/
		try {
			
			// Log
			String msg_Log = "connecting...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			fp.connect(CONS.Remote.server_Name);
//			fp.connect(server_name);
			
			reply_code = fp.getReplyCode();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp.getReplyCode()=" + fp.getReplyCode());
			
		} catch (SocketException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -1;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -2;
		}
		
		/*********************************
		 * Log in
		 *********************************/
		boolean res;
		
		try {
			
//			res = fp.login(uname, passwd);
			res = fp.login(
					CONS.Remote.uname, 
					CONS.Remote.passwd);
			
			if(res == false) {
				
				reply_code = fp.getReplyCode();
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Log in failed => " + reply_code);
				
				fp.disconnect();
				
				return -4;
				
			} else {
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Log in => Succeeded");
				
			}
			
		} catch (IOException e1) {
			
			// Log
			String msg_Log = "IOException";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e1.printStackTrace();
			
			try {
				
				fp.disconnect();
				
				return -5;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return -12;
				
			}
			
		}
		
		/*********************************
		 * FTP files
		 *********************************/
		// �t�@�C�����M
		FileInputStream is;
		
		try {
			
			// Log
			String msg_Log = "fpath_Src => " + fpath_Src;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			is = new FileInputStream(fpath_Src);
//			is = new FileInputStream(fpath_audio);
			
			// Log
			msg_Log = "Input stream => created";
//			String msg_Log = "Input stream => created";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////*////////////////////////
			
			// set: file type
			
			////////////////////////////////
			// REF http://stackoverflow.com/questions/7740817/how-to-upload-an-image-to-ftp-using-ftpclient answered Oct 12 '11 at 13:52
			res = fp.setFileType(FTP.BINARY_FILE_TYPE);
			
			/******************************
				validate
			 ******************************/
			if (res == false) {
				
				// Log
				msg_Log = "set file type => failed";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				is.close();
				
				fp.disconnect();
				
				return -11;
				
			}
			
			////////////////////////////////
			
			// store
			
			////////////////////////////////
			// Log
			msg_Log = "Stroing file to remote... => "
					+ fpath_remote;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
//			fp.storeFile("./" + MainActv.fileName_db, is);// �T�[�o�[��
			res = fp.storeFile(fpath_remote, is);// �T�[�o�[��
			
//			fp.makeDirectory("./ABC");
			
			if (res == true) {
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "File => Stored");
				
			} else {//if (res == true)
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Store file => Failed");
				
				fp.disconnect();
				
				return -6;
				
			}//if (res == true)
			
			is.close();
			
		} catch (FileNotFoundException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			try {
				
				fp.disconnect();
				
				return -7;
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
				
				return -8;
				
			}
			
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			try {
				fp.disconnect();
				
				return -9;
				
			} catch (IOException e1) {
				
				
				e1.printStackTrace();
				
				return -10;
				
			}
			
		}
		
		
		//debug
		/*********************************
		 * Disconnect
		 *********************************/
		try {
			
			// Log
			String msg_Log = "disconnecting...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			fp.disconnect();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp => Disconnected");
			
			return reply_code;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -3;
			
		}
		
	}//ftp_DB_to_Remote

	public static void 
	write_Log
	(Activity actv, String message,
			String fileName, int lineNumber) {
		
		////////////////////////////////

		// validate: dir exists

		////////////////////////////////
		File dpath_Log = new File(CONS.DB.dPath_Log);
		
		if (!dpath_Log.exists()) {
			
			dpath_Log.mkdirs();
			
			// Log
			String msg_Log = "log dir => created: " + dpath_Log.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "log dir => exists: " + dpath_Log.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// file

		////////////////////////////////
		File fpath_Log = new File(CONS.DB.dPath_Log, CONS.DB.fname_Log);
		
		////////////////////////////////

		// file exists?

		////////////////////////////////
		if (!fpath_Log.exists()) {
			
			try {
				
				fpath_Log.createNewFile();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				String msg = "Can't create a log file";
				Methods_dlg.dlg_ShowMessage_Duration(actv, msg, R.color.gold2);
				
				return;
				
			}
			
		} else {
			
			// Log
			String msg_Log = "log file => exists";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// write

		////////////////////////////////
		try {
			
			//REF append http://stackoverflow.com/questions/8544771/how-to-write-data-with-fileoutputstream-without-losing-old-data answered Dec 17 '11 at 12:37
			FileOutputStream fos = new FileOutputStream(fpath_Log, true);
//			FileOutputStream fos = new FileOutputStream(fpath_Log);
			
			String text = String.format(Locale.JAPAN,
							"[%s] [%s : %d] %s\n", 
							Methods.conv_MillSec_to_TimeLabel(
											Methods.getMillSeconds_now()),
							fileName, lineNumber,
							message
						);
			
			//REF getBytes() http://www.adakoda.com/android/000240.html
			fos.write(text.getBytes());
//			fos.write(message.getBytes());
			
//			fos.write("\n".getBytes());
			
			fos.close();
			
			// Log
			String msg_Log = "log => written";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
//			FileChannel oChannel = new FileOutputStream(fpath_Log).getChannel();
//			
//			oChannel.wri
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}//write_Log

	public static void 
	addCol_PatternsUsed
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
//		String colName = "word";
		String colName = "used";
		String colType = "TEXT";
		
		
		int res = DBUtils.add_Column(actv, CONS.DB.tname_Patterns, colName, colType);
//		int res = DBUtils.add_Column(actv, CONS.DB.tname_Patterns, "used", "TEXT");
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		if (res == 1) {
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			String msg = "Column 'used' => created";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		} else if (res == -1) {

			d3.dismiss();
			d2.dismiss();
			d1.dismiss();

			String msg = "Column exists";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
	}//addCol_PatternsUsed

	public static int 
	update_Pattern_Used
	(Activity actv, long db_Id) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// update

		////////////////////////////////
		int res = DBUtils.update_Pattern_Used(actv, db_Id);

		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1 find pattern => failed
//		-2 SQLException
//		1 update => executed

		case -1: 
			
			msg = "find pattern => failed: " + db_Id;
			colorID = R.color.red;
			
			break;
		
		case -2: 
			
			msg = "SQLException: " + db_Id;
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "update => executed: " + db_Id;
			colorID = R.color.green4;
			
			break;
			
		}

		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg);
		
//		Methods_dlg.dlg_ShowMessage(
//				actv, 
//				msg,
//				colorID);
		
		return res;
		
	}//update_Pattern_Used

	public static boolean 
	is_SpecialChars
	(Activity actv, String w) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// build: list

		////////////////////////////////
		List<String> list_Specials = new ArrayList<String>();

		for (String chr : CONS.Admin.special_Chars) {
			
			list_Specials.add(chr);
			
		}
		
		////////////////////////////////

		// judge

		////////////////////////////////
		return list_Specials.contains(w) ? true : false;
		
//		return false;
		
	}//if(Methods.is_SpecialChars(actv, w))

	/******************************
		@return
			-1 find pattern => failed<br>
			-2 SQLException<br>
			1 update => executed<br>
	 ******************************/
	public static int 
	add_WP_to_Memo_SpecialChars
	(Activity actv, EditText et, WordPattern item) {
		// TODO Auto-generated method stub
		
//		////////////////////////////////
//	
//		// EditText
//	
//		////////////////////////////////
//		EditText et = (EditText) et.findViewById(R.id.dlg_add_memos_et_content);
		
		////////////////////////////////
		
		// build: text
	
		////////////////////////////////
		//REF http://stackoverflow.com/questions/3609174/android-insert-text-into-edittext-at-current-position answered Aug 31 '10 at 15:32
		int pos_Current = et.getSelectionStart();
		
		String tmp = et.getText().toString();
		
		tmp = tmp.substring(0, pos_Current) +
				" " + 
				item.getWord() + 
				" "
				+ tmp.substring(pos_Current);
		
		////////////////////////////////
	
		// set
	
		////////////////////////////////
		et.setText(tmp);
		
		// + 1 => space length
		et.setSelection(pos_Current + 2);
	//	et.setSelection(tmp.length());
		
		////////////////////////////////
	
		// update: used
	
		////////////////////////////////
		int res = Methods.update_Pattern_Used(actv, item.getDb_Id());
	
		return res;
		
	}//add_WP_to_Memo_SpecialChars
	
	/******************************
		@return
			-1 find pattern => failed<br>
			-2 SQLException<br>
			1 update => executed<br>
	 ******************************/
	public static int 
	add_WP_to_Memo_SpecialChars_RecActv
	(Activity actv, EditText et, WordPattern item) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// build: text
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/3609174/android-insert-text-into-edittext-at-current-position answered Aug 31 '10 at 15:32
		int pos_Current = et.getSelectionStart();
		
		String tmp = et.getText().toString();
		
		tmp = tmp.substring(0, pos_Current) +
				" " + 
				item.getWord() + 
				" "
				+ tmp.substring(pos_Current);
		
		////////////////////////////////
		
		// set
		
		////////////////////////////////
		et.setText(tmp);
		
		// + 1 => space length
		et.setSelection(pos_Current + 2);
		//	et.setSelection(tmp.length());
		
		////////////////////////////////
		
		// update: used
		
		////////////////////////////////
		int res = Methods.update_Pattern_Used(actv, item.getDb_Id());
		
		return res;
		
	}//add_WP_to_Memo_SpecialChars_RecActv
	
	/******************************
		@return
			-1 find pattern => failed<br>
			-2 SQLException<br>
			1 update => executed<br>
	 ******************************/
	public static int 
	add_WP_to_Memo
	(Activity actv, EditText et, WordPattern item) {
		// TODO Auto-generated method stub
		
//		////////////////////////////////
//		
//		// EditText
//		
//		////////////////////////////////
//		EditText et = (EditText) et.findViewById(R.id.dlg_add_memos_et_content);
		
		////////////////////////////////
		
		// build: text
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/3609174/android-insert-text-into-edittext-at-current-position answered Aug 31 '10 at 15:32
		int pos_Current = et.getSelectionStart();
		
		String tmp = et.getText().toString();
		
		tmp = tmp.substring(0, pos_Current) +
				" " + 
				item.getWord() + 
				" "
				+ tmp.substring(pos_Current);
	//	+ tmp.substring(pos_Current + item.getWord().length());
	//	tmp = tmp + item.getWord() + " ";
		
		////////////////////////////////
		
		// set
		
		////////////////////////////////
		et.setText(tmp);
		
		// + 1 => space length
		et.setSelection(pos_Current + item.getWord().length() + 2);
	//	et.setSelection(tmp.length());
		
		////////////////////////////////
		
		// update: used
		
		////////////////////////////////
		int res = Methods.update_Pattern_Used(actv, item.getDb_Id());
		

		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
	}//add_WP_to_Memo

	public static void 
	recActv_Rec_2
	(Activity actv) {
		////////////////////////////////

		// change icon

		////////////////////////////////
		ImageButton ib_Rec = (ImageButton) actv.findViewById(R.id.actv_rec_bt_rec);
		
		ib_Rec.setImageResource(R.drawable.actv_rec_rec_recording);
		
		ib_Rec.setEnabled(false);

		// stop
		ImageButton ib_Stop = (ImageButton) actv.findViewById(R.id.actv_rec_bt_stop);
		
		ib_Stop.setImageResource(R.drawable.actv_rec_stop);
		
		ib_Stop.setEnabled(true);

		////////////////////////////////

		// set: file name

		////////////////////////////////
		CONS.Paths.fpath_AudioRecorded = String.format("%s/%s%s", 
								CONS.DB.dPath_Audio, 
								Methods.conv_MillSec_to_AudioFileLabel(
											Methods.getMillSeconds_now()),
								CONS.DB.fname_Audio_Ext);;

		////////////////////////////////

		// record

		////////////////////////////////
		CONS.RecActv.rc = new Recorder();
		
		CONS.RecActv.rc.startRecording();

		// Log
		String msg_Log = "CONS.RecActv.rc => started recording";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		// Log
		msg_Log = "state => " + CONS.RecActv.rc.getState();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

//		////////////////////////////////
//
//		// set: file name
//
//		////////////////////////////////
//		CONS.Paths.fpath_AudioRecorded = String.format("%s/%s%s", 
//								CONS.DB.dPath_Audio, 
//								Methods.conv_MillSec_to_AudioFileLabel(
//											Methods.getMillSeconds_now()),
//								CONS.DB.fname_Audio_Ext);;
								
//		CONS.Paths.fpath_AudioRecorded = CONS.RecActv.rc.getFilename();
//		CONS.Paths.fpath_AudioRecorded = filePath;

	}//recActv_Rec_2
	
	public static void 
	recActv_Rec
	(Activity actv) {
		// TODO Auto-generated method stub
	
		////////////////////////////////

		// validate: folder exists

		////////////////////////////////
		boolean res = Methods.conf_DirExists(actv, CONS.DB.dPath_Audio);
		
		if (res == false) {

			String msg = "Can't setup folder";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		CONS.RecActv.mr = new MediaRecorder();
		
		CONS.RecActv.mr.setAudioSource(MediaRecorder.AudioSource.MIC);
		
		CONS.RecActv.mr.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		
		CONS.RecActv.mr.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		
		String filePath = String.format("%s/%s%s", 
					CONS.DB.dPath_Audio, 
					Methods.conv_MillSec_to_AudioFileLabel(Methods.getMillSeconds_now()),
					CONS.DB.fname_Audio_Ext
		);
		
		CONS.RecActv.mr.setOutputFile(filePath);
		
	    try {
	    	
	    	CONS.RecActv.mr.prepare();
	    	
	    	// Log
			String msg_Log = "prepare => done";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
	    	
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// Log
			String msg_Log = "Exception";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
	    
	    CONS.RecActv.mr.start();	//録音開始
	
	    // Log
		String msg_Log = "record => started";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// set: file name

		////////////////////////////////
		CONS.Paths.fpath_AudioRecorded = filePath;
		
	}//start_Rec

	
	/******************************
		If the dir doesn't exist => create one
	 ******************************/
	private static boolean 
	conf_DirExists
	(Activity actv, String dpath) {
		// TODO Auto-generated method stub

		File d = new File(dpath);
		
		if (!d.exists()) {
			
			try {
				
				d.mkdirs();
//				db_Backup.mkdir();
				
				/******************************
					validate
				 ******************************/
				if (d.isDirectory()) {
					
					// Log
					Log.d("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Folder created: " + d.getAbsolutePath());
					
				} else {
					
					// Log
					String msg_Log = "Create folder => not successful: " + d.getAbsolutePath();
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					return false;
					
				}
				
			} catch (Exception e) {
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
						.getLineNumber() + "]", 
						"Create folder => Failed: " + d.getAbsolutePath());
				
				return false;
				
			}
			
		} else {//if (!d.exists())
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: " + d.getAbsolutePath());
			
		}//if (!d.exists())
		
		return true;
		
	}//conf_DirExists

	public static void 
	recActv_Stop_2
	(Activity actv) {
		////////////////////////////////

		// validate

		////////////////////////////////
		if (CONS.RecActv.rc == null) {
			
			// Log
			String msg_Log = "CONS.RecActv.rc => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		// Log
		String msg_Log = "state => " + CONS.RecActv.rc.getState();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// stop

		////////////////////////////////
		CONS.RecActv.rc.stopRecording();
		
		// Log
		msg_Log = "CONS.RecActv.rc => stopped";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		// Log
		msg_Log = "state => " + CONS.RecActv.rc.getState();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// change icon

		////////////////////////////////
		ImageButton ib_Rec = (ImageButton) actv.findViewById(R.id.actv_rec_bt_rec);
		
		ib_Rec.setImageResource(R.drawable.actv_rec_rec);

		ib_Rec.setEnabled(true);

		// stop
		ImageButton ib_Stop = (ImageButton) actv.findViewById(R.id.actv_rec_bt_stop);
		
		ib_Stop.setImageResource(R.drawable.actv_rec_stop_not_in_use);
		
		ib_Stop.setEnabled(false);
		
		////////////////////////////////

		// memo

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_rec_et);
		
		String memo = et.getText().toString();
		
		////////////////////////////////

		// db

		////////////////////////////////
//		// Log
//		msg_Log = "CONS.Paths.fname_AudioRecorded => " + CONS.Paths.fname_AudioRecorded;
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		String text = String.format(CONS.RecActv.fmt_FileName, 
//				String text = String.format("@%s %s", 
						 
//						Methods.get_Filename(actv, CONS.RecActv.fname_Generated_WavFile), 
						Methods.get_Filename(actv, CONS.Paths.fpath_AudioRecorded), 
//						CONS.Paths.fpath_AudioRecorded, 
						memo);
		
		int res = DBUtils.save_Memo(actv, text);
		
		// null => CONS.RecActv.fname_Generated_WavFile
//		CONS.RecActv.fname_Generated_WavFile = null;
		CONS.Paths.fpath_AudioRecorded = null;
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1 insertion => failed
//		-2 Exception
//		1 text => inserted		
		
		case -1: 
			
			msg = "insertion => failed";
			colorID = R.color.red;
			
			break;
		
		case -2: 
			
			msg = "Exception";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "text => inserted";
			colorID = R.color.green4;
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID,
				CONS.Admin.dflt_MessageDialog_Length);

	}//recActv_Stop_2
	
	public static void 
	recActv_Stop
	(Activity actv) {
		// TODO Auto-generated method stub
		
		if (CONS.RecActv.mr == null) {
			
			// Log
			String msg_Log = "CONS.RecActv.mr => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////

		// stop

		////////////////////////////////
		CONS.RecActv.mr.stop();
		
		CONS.RecActv.mr.release();
		
		// Log
		String msg_Log = "CONS.RecActv.mr => released";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// memo

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_rec_et);
		
		String memo = et.getText().toString();
		
		////////////////////////////////

		// db

		////////////////////////////////
//		// Log
//		msg_Log = "CONS.Paths.fname_AudioRecorded => " + CONS.Paths.fname_AudioRecorded;
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		String text = String.format("@%s %s", 
						Methods.get_Filename(actv, CONS.Paths.fpath_AudioRecorded), 
//						CONS.Paths.fpath_AudioRecorded, 
						memo);
		
		int res = DBUtils.save_Memo(actv, text);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1 insertion => failed
//		-2 Exception
//		1 text => inserted		
		
		case -1: 
			
			msg = "insertion => failed";
			colorID = R.color.red;
			
			break;
		
		case -2: 
			
			msg = "Exception";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "text => inserted";
			colorID = R.color.green4;
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID,
				CONS.Admin.dflt_MessageDialog_Length);
		
	}//start_StopRec

	public static void 
	start_Activity_PlatyActv
	(Activity actv, Memo memo) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent();
		
		i.setClass(actv, PlayActv.class);

		i.putExtra(CONS.Intent.iKey_Memo_Id, memo.getDb_Id());
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
	}//start_Activity_PlatyActv

	public static void
	play_File(Activity actv) {
		
		String msg_Log;
		
		////////////////////////////////

		// validate

		////////////////////////////////
//		if (CONS.PlayActv.mp == null) {
			
			CONS.PlayActv.mp = new MediaPlayer();
			
			// Log
			msg_Log = "mp => instantiated";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
//		}
		
		/*********************************
		 * 2. OnCompletionListener
		 *********************************/
//		CONS.PlayActv.mp = new MediaPlayer();
		
		try {
			
			CONS.PlayActv.mp.setOnCompletionListener(new MP_OCmpL(actv));
		
			// Log
			msg_Log = "setOnCompletionListener => done";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (Exception e) {
			// TODO: handle exception
			// Log
			msg_Log = "Exception";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "setOnCompletionListener => exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			e.printStackTrace();
			
			return;
			
		}
		
		/*********************************
		 * 3. Set data source
		 *********************************/
		CONS.PlayActv.mp.reset();
		
		// Log
		msg_Log = "mp => reset()";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		String file_full_path = StringUtils.join(
						new String[]{
								CONS.DB.dPath_Audio,
								CONS.PlayActv.fname_Audio
						},
						File.separator);

		// Log
		msg_Log = "file_full_path = " + file_full_path;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		try {

			CONS.PlayActv.mp.setDataSource(file_full_path);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Data source => Set");
			
		} catch (IllegalArgumentException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			
		} catch (IllegalStateException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());

		} catch (IOException e) {

			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());

		}//try

		/*********************************
		 * 4. Prepare mp
		 *********************************/
		try {

			CONS.PlayActv.mp.prepare();
			
		} catch (IllegalStateException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());

		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());

		}//try

		//debug
		// Log
		msg_Log = "getDuration() = " + CONS.PlayActv.mp.getDuration();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		/***************************************
		 * Position set in the preference?
		 ***************************************/
		long pref_Position = 
				Methods.get_Pref_Long(
						actv,
						CONS.Pref.pname_PlayActv,
						CONS.Pref.pkey_PlayActv_CurrentPosition,
						CONS.Pref.dflt_LongExtra_value);
		
		// Log
		msg_Log = "prefPosition = " + pref_Position;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		
//		//debug
//		// Log
//		msg_Log = "getDuration() = " + CONS.PlayActv.mp.getDuration();
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
		if (pref_Position >= 0) {
			
			CONS.PlayActv.mp.seekTo((int) pref_Position);
			
			// Log
			msg_Log = "seekTo() => done";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}//if (prefPosition == condition)
		
		/***************************************
		 * Prepare: Service
		 ***************************************/
		Intent i = new Intent((Context) actv, Service_ShowProgress.class);

		i.putExtra(
				CONS.Intent.iKey_PlayActv_TaskPeriod, 
				CONS.PlayActv.playActv_task_Period);
		
		//
//		i.putExtra("counter", timeLeft);
		
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Starting service...");
		//
		actv.startService(i);

		
		try {
			/*********************************
			 * 5. Start
			 *********************************/
			CONS.PlayActv.mp.start();
			
			// Log
			msg_Log = "mp => started";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (Exception e) {
			// TODO: handle exception
			// Log
			msg_Log = "Exception";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "mp.start => exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			e.printStackTrace();
			
			return;
			
		}
		
		////////////////////////////////

		// buttons

		////////////////////////////////
		// stop
		ImageButton ib_Stop = (ImageButton) actv.findViewById(R.id.actv_play_ib_stop);
		
		ib_Stop.setImageResource(R.drawable.actv_rec_stop);
		
		ib_Stop.setEnabled(true);
		
		// play
		ImageButton ib_Play = (ImageButton) actv.findViewById(R.id.actv_play_ib_play);
		
		ib_Play.setImageResource(R.drawable.actv_rec_rec_recording);
		
		ib_Play.setEnabled(false);
		
	}//play_File(Activity actv, AI ai)

	public static void
	stop_Player(Activity actv) {
		
		String msg_Log;
		
		// TODO Auto-generated method stub
		if (CONS.PlayActv.mp != null && CONS.PlayActv.mp.isPlaying()) {

			int player_Pos = CONS.PlayActv.mp.getCurrentPosition();
			
			////////////////////////////////

			// stop

			////////////////////////////////
			CONS.PlayActv.mp.stop();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Player => Stopped");
			
//			////////////////////////////////
//
//			// release
//
//			////////////////////////////////
//			CONS.PlayActv.mp.release();
			
			// Log
			msg_Log = "player => released";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			/***************************************
			 * Stop: Service
			 ***************************************/
			Intent i = new Intent((Context) actv, Service_ShowProgress.class);

			//
//			i.putExtra("counter", timeLeft);

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Stopping service...");

			//
//			actv.startService(i);
			actv.stopService(i);

//			////////////////////////////////
//
//			// pref: stop position
//
//			////////////////////////////////
//			boolean res = Methods.get_Pref_Boolean(
//					actv, 
//					CONS.Pref.pname_MainActv, 
//					actv.getString(R.string.prefactv_key_resume_position), 
//					false);
//			
//			if (res == true) {
//				
			boolean res = Methods.setPref_Long(
							actv,
							CONS.Pref.pname_PlayActv,
	//						CONS.Pref.pkey_PlayActv_position,
							CONS.Pref.pkey_PlayActv_CurrentPosition,
	//						CONS.Pref.pkey_CurrentPosition,
							player_Pos);
//				
				if (res == true) {
					
					// Log
					msg_Log = "Position => set: " 
								+ Methods.conv_MillSec_to_ClockLabel(player_Pos);
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {

					// Log
					msg_Log = "Position => not set: " 
								+ Methods.conv_MillSec_to_ClockLabel(player_Pos);
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				}//if (res == true)
//				
//			}//if (res == true)

			////////////////////////////////

			// buttons

			////////////////////////////////
			// stop
			ImageButton ib_Stop = (ImageButton) actv.findViewById(R.id.actv_play_ib_stop);
			
			ib_Stop.setImageResource(R.drawable.actv_rec_stop_not_in_use);
			
			ib_Stop.setEnabled(false);
			
			// play
			ImageButton ib_Play = (ImageButton) actv.findViewById(R.id.actv_play_ib_play);
			
			ib_Play.setImageResource(R.drawable.actv_rec_rec);
			
			ib_Play.setEnabled(true);

			
		} else if (CONS.PlayActv.mp == null) {//if (mp.isPlaying())

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "CONS.PlayActv.mp => null");

		} else if (!CONS.PlayActv.mp.isPlaying()) {//if (mp.isPlaying())

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "CONS.PlayActv.mp => Not playing");

		}//if (mp.isPlaying())	
		
	}//stop_Player(Activity actv)

	public static boolean
	setPref_Long
	(Activity actv, String pName, String pKey, long value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);

		/****************************
		 * 2. Get editor
			****************************/
		SharedPreferences.Editor editor = prefs.edit();

		/****************************
		 * 3. Set value
			****************************/
		editor.putLong(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}

	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)

	/***************************************
	 * 20130320_120437<br>
	 * @param t ... Value in seconds, <i>not</i> in mill seconds
	 ***************************************/
	public static String convert_intSec2Digits_lessThanHour(int t) {
		
		int sec = t % 60;
		
		if (t / 60 < 1) {
			
//			return "00:00:" + String.valueOf(sec);
//			return "00:00:" + Methods.convert_sec2digits(sec, 2);
			return "00:" + Methods.convert_sec2digits(sec, 2);
			
		}//if (t / 60 < 1)
		
//		int min = (t - sec) % 60;
		int min = ((t - sec) % (60 * 60)) / 60;
		
		return Methods.convert_sec2digits(min, 2) + ":"
			+ Methods.convert_sec2digits(sec, 2);
			
	}//public static String convert_intSec2Digits(int time)

	private static 
	String convert_sec2digits
	(int sec, int i) {
		
		int current_len = String.valueOf(sec).length();
		
		if (current_len < i) {
			
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j < i - current_len; j++) {
				
				sb.append("0");
			}
			
			sb.append(String.valueOf(sec));
			
			return sb.toString();
			
		}//if (current_len == condition)
		
		return String.valueOf(sec);
		
	}//private static String convert_sec2digits(int sec, int i)

	/******************************
		REF http://stackoverflow.com/questions/625433/how-to-convert-milliseconds-to-x-mins-x-seconds-in-java answered Mar 9 '09 at 10:01
	 ******************************/
	public static String
	conv_MillSec_to_ClockLabel(long millSec)
	{
		return String.format(
			Locale.JAPAN,
			CONS.Admin.format_Clock, 
	//		"%02d:%02d", 
			TimeUnit.MILLISECONDS.toMinutes(millSec),
			TimeUnit.MILLISECONDS.toSeconds(millSec) - 
			TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millSec))
		);
		
	}//conv_MillSec_to_ClockLabel(long millSec)

	/******************************
		@return
			-1	file => exist not<br>
			-2	IllegalArgumentException<br>
			-3	IllegalStateException<br>
			-4	IOException<br>
	 ******************************/
	public static long 
	get_AudioLength
	(String fileFullPath) {
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Methods: " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "File path=" + fileFullPath);
		
		/******************************
			validate
		 ******************************/
		File f = new File(fileFullPath);
		
		if (!f.exists()) {
			
			// Log
			String msg_Log = "file => exist not: " + f.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}
		
		MediaPlayer mp = new MediaPlayer();
		
//		int len = 0;
		long len = 0;
		
		try {
			mp.setDataSource(fileFullPath);
			
			mp.prepare();
			
//			len = mp.getDuration() / 1000;
			len = mp.getDuration();
			
			// REF=> http://stackoverflow.com/questions/9609479/android-mediaplayer-went-away-with-unhandled-events
			mp.reset();
			
			// REF=> http://stackoverflow.com/questions/3761305/android-mediaplayer-throwing-prepare-failed-status-0x1-on-2-1-works-on-2-2
			mp.release();
			
		} catch (IllegalArgumentException e) {
			
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
			
			e.printStackTrace();
			
			return -2;
			
		} catch (IllegalStateException e) {
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
			
			e.printStackTrace();
			
			return -3;

		} catch (IOException e) {
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
			
			e.printStackTrace();
			
			return -4;
			
		}//try
		
		return len;
		
	}//private static long getFileLength(String fileFullPath)

	public static long get_Pref_Long
	(Activity actv, String pref_name, String pref_key, long defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
		
		/****************************
		 * Return
		 ****************************/
		return prefs.getLong(pref_key, defValue);
		
	}//public static boolean set_pref(String pref_name, String value)

	public static void 
	update_ProgressLable() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate

		////////////////////////////////
		if (CONS.PlayActv.mp == null) {
			
			// Log
			String msg_Log = "CONS.PlayActv.mp => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////

		// get: position

		////////////////////////////////
		int currentPosition = CONS.PlayActv.mp.getCurrentPosition();
		
//		TextView tvCurrentPosition = (TextView) this.findViewById(R.id.actv_play_tv_current_position);
		if (CONS.PlayActv.tvCurrentPosition == null) {
			
			// Log
			String msg_Log = "CONS.PlayActv.tvCurrentPosition => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			CONS.PlayActv.tvCurrentPosition = 
					(TextView) (new PlayActv()).findViewById(R.id.actv_play_tv_current_position);
//			(TextView) this.findViewById(R.id.actv_play_tv_current_position);
			
		}//if (CONS.PlayActv.tvCurrentPosition == null)
//		CONS.PlayActv.tvCurrentPosition = (TextView) this.findViewById(R.id.actv_play_tv_current_position);

		////////////////////////////////

		// set: position

		////////////////////////////////
		CONS.PlayActv.tvCurrentPosition.setText(
					Methods.conv_MillSec_to_ClockLabel(currentPosition));
//		Methods.convert_intSec2Digits_lessThanHour((int)currentPosition / 1000));

	}//update_ProgressLable


	/******************************
		filter memo list with a single keyword
	 ******************************/
	public static List<Memo>
	filter_MemoList_Single_KW
	(Activity actv, int id_Checked, EditText et) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		List<Memo> list_Memos = new ArrayList<Memo>();

		String keyword = et.getText().toString();
		
		////////////////////////////////

		// filter

		////////////////////////////////
		if (id_Checked == R.id.dlg_filter_showlist_rb_not) {
			
			String text = null;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				text = memo.getText();
				
				if (text.contains(keyword)) {
					
					continue;
					
				}
				
				list_Memos.add(memo);
				
			}
			
//			where = CONS.DB.col_names_TA2[0] + " NOT LIKE ?";
			
		} else {//if (id_Checked == R.id.dlg_filter_showlist_rb_not)
			
			String text = null;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				text = memo.getText();
				
				if (text.contains(keyword)) {
					
					list_Memos.add(memo);
					
				}
				
			}
//			where = CONS.DB.col_names_TA2[0] + " LIKE ?";
			
		}//if (id_Checked == R.id.dlg_filter_showlist_rb_not)
		
//		// Log
//		msg_Log = "where => " + where;
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		//REF http://monoist.atmarkit.co.jp/mn/articles/1209/21/news003.html "正しく動作する記述を以下に"
//		where = CONS.DB.col_names_TA2[0] + " like ?";
//		String where = CONS.DB.col_names_IFM11[11] + " = ?";
		
//		args = new String[]{
//				
//				"%" + et.getText().toString() + "%"
//				
//		};
		
		////////////////////////////////

		// return

		////////////////////////////////
		return list_Memos;
		
	}//filter_MemoList

	/******************************
		filter memo list with a single keyword
	 ******************************/
	public static List<Memo>
	filter_MemoList_Single_KW
	(Activity actv, int id_Checked, String kw) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// vars
		
		////////////////////////////////
//		String msg_Log;
		
		List<Memo> list_Memos = new ArrayList<Memo>();
		
//		String keyword = et.getText().toString();
		
		////////////////////////////////
		
		// filter
		
		////////////////////////////////
		if (id_Checked == R.id.dlg_filter_showlist_rb_not) {
			
			String text = null;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				text = memo.getText();
				
				if (text.contains(kw)) {
					
					continue;
					
				}
				
				list_Memos.add(memo);
				
			}
			
//			where = CONS.DB.col_names_TA2[0] + " NOT LIKE ?";
			
		} else {//if (id_Checked == R.id.dlg_filter_showlist_rb_not)
			
			String text = null;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				text = memo.getText();
				
				if (text.contains(kw)) {
					
					list_Memos.add(memo);
					
				}
				
			}
			
		}//if (id_Checked == R.id.dlg_filter_showlist_rb_not)
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return list_Memos;
		
	}//filter_MemoList
	
	public static List<Memo> 
	filter_MemoList_Multiple_KW
	(Activity actv, int id_Checked, String[] tokens) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		List<Memo> list_Memos = new ArrayList<Memo>();

//		String keyword = et.getText().toString();
		
		////////////////////////////////

		// filter

		////////////////////////////////
		if (id_Checked == R.id.dlg_filter_showlist_rb_not) {
			
			// Log
			msg_Log = "filter => not";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String text = null;
			
			boolean contained = false;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				for (String token : tokens) {
					
					text = memo.getText();
					
					if (text.contains(token)) {
						
						contained = true;
						
						break;
						
					}
					
				}//for (String token : tokens)
				
				if (contained == false) {
					
					list_Memos.add(memo);
					
				}
				
			}//for (Memo memo : CONS.ShowListActv.list_Memos)
			
		} else if (id_Checked == R.id.dlg_filter_showlist_rb_and) {//if (id_Checked == R.id.dlg_filter_showlist_rb_not)

			// Log
			msg_Log = "filter => and";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String text = null;
			
			boolean contained = true;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				text = memo.getText();
					
				// Log
				msg_Log = "text => " + text;
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				// reset value
				contained = true;
				
				for (String token : tokens) {
				
					// Log
					msg_Log = "token => " + token;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
//					text = memo.getText();
					
					if (!text.contains(token)) {
						
						contained = false;
						
						// Log
						msg_Log = String.format(
									"!text.contains(token) => %s, %s", 
									text, token);
						
						Log.d("Methods.java"
								+ "["
								+ Thread.currentThread().getStackTrace()[2]
										.getLineNumber() + "]", msg_Log);
						
						break;
						
					}
					
				}//for (String token : tokens)
				
				if (contained == true) {
					
					list_Memos.add(memo);
					
				}
				
			}//for (Memo memo : CONS.ShowListActv.list_Memos)

		} else if (id_Checked == R.id.dlg_filter_showlist_rb_or) {//if (id_Checked == R.id.dlg_filter_showlist_rb_not)

			// Log
			msg_Log = "filter => or";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String text = null;
			
			boolean contained = false;
			
			for (Memo memo : CONS.ShowListActv.list_Memos) {
				
				// reset
				contained = false;
				
				text = memo.getText();
				
				for (String token : tokens) {
					
					if (text.contains(token)) {
						
						contained = true;
						
						break;
						
					}
					
				}//for (String token : tokens)
				
				if (contained == true) {
					
					list_Memos.add(memo);
					
				}
				
			}//for (Memo memo : CONS.ShowListActv.list_Memos)

		} else {//if (id_Checked == R.id.dlg_filter_showlist_rb_not)

			// Log
			msg_Log = "unknown radio button id => " + id_Checked;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}//if (id_Checked == R.id.dlg_filter_showlist_rb_not)
		
		////////////////////////////////

		// return

		////////////////////////////////
		return list_Memos;
		
	}//filter_MemoList_Multiple_KW

	public static Memo 
	find_Memo_from_ListView
	(Activity actv, long db_Id) {
		// TODO Auto-generated method stub
		
		/******************************
			validate
		 ******************************/
		if (CONS.ShowListActv.list_Memos == null) {
			
			// Log
			String msg_Log = "CONS.ShowListActv.list_Memos => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return null;
			
		}
		
		////////////////////////////////

		// find: memo

		////////////////////////////////
		for (Memo m : CONS.ShowListActv.list_Memos) {
			
			if (m.getDb_Id() == db_Id) {
				
				return m;
				
			}
			
		}
		
		return null;
		
	}//find_Memo_from_ListView

	public static TI 
	find_TI_From_TIList
	(Activity actv, long db_Id) {
		// TODO Auto-generated method stub
		
		for (TI ti : CONS.PhotoActv.list_TIs) {
			
			if (ti.getDb_Id() == db_Id) {
				
				return ti;
				
			}
			
		}
		
		return null;
		
	}//find_TI_From_TIList

	/******************************
		@return
			null<br>
			1. managedQuery => exception<br>
			2. cursor => null<br>
			3. cursor => count less than 1<br>
	 ******************************/
	public  static Cursor 
	get_Content
	(Activity actv, 
		String selection, String[] args, String order) {
		// TODO Auto-generated method stub
	
		actv.getIntent().setData(Uri.parse(CONS.PhotoActv.content_Uri));
		
		Cursor c = null;
		
		try {
			
			// Log
			String msg_Log = "calling query...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			c = actv.managedQuery(
							actv.getIntent().getData(), 
							null, 
							selection, 
							args, 
							order);
			
			// Log
			msg_Log = "query => done";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			// Log
			String msg_Log = "Exception";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e.printStackTrace();
			
			return null;
			
		}
		
		// Log
		String msg_Log = "cursor => obtained";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			return null;
			
		}//if (c == null)
		
		////////////////////////////////

		// get info

		////////////////////////////////
		// Log
		msg_Log = "c.getCount() => " + c.getCount();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		return c;
		
	}//get_Content

	public static void 
	filter_MemoList_History
	(Activity actv, 
		Dialog d1, FilterHistory fh) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		boolean res;
		
		////////////////////////////////

		// tokens

		////////////////////////////////
		String input = fh.getKeywords();
		
		input = input.trim();
		
		input = input.replaceAll("　", " ");
		
		input = input.replaceAll(" +", " ");
		
		String[] tokens = input.split(" ");
		
		if (tokens == null) {
			
			String msg = "Split the input => null: " + input;
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1, R.color.red);
			
			return;
			
		} else if (tokens.length == 0) {
			
			String msg = "tokens.length => 0: " + input;
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1, R.color.red);
			
			return;
			
		}
		
		// Log
		msg_Log = "tokens.length => " + tokens.length;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////
		
		// setup: where, args
		
		////////////////////////////////
//		String where = null;
//		
//		String[] args = null;
		
		List<Memo> list_Memos = null;

		int RB_id_Checked = fh.getOperator();
		
		////////////////////////////////
		
		// dispatch
		
		////////////////////////////////
		if (tokens.length <= 1) {
			
			// Log
			msg_Log = "tokens.length <= 1";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			list_Memos = Methods.filter_MemoList_Single_KW(actv, RB_id_Checked, tokens[0]);
			
		} else {//if (tokens.length <= 1)
			
			list_Memos = Methods.filter_MemoList_Multiple_KW(actv, RB_id_Checked, tokens);
			
		}//if (tokens.length <= 1)

		////////////////////////////////
		
		// update: list
		
		////////////////////////////////
		if (list_Memos == null) {
			
			String msg = "Can't build memo list";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1, R.color.red);
			
			return;
			
		}
		
		// Log
		msg_Log = "list_Memos.size() => " + list_Memos.size();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		CONS.ShowListActv.list_Memos.clear();
		CONS.ShowListActv.list_Memos.addAll(list_Memos);
		
		////////////////////////////////
		
		// notify
		
		////////////////////////////////
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		////////////////////////////////
		
		// dismiss
		
		////////////////////////////////
		d1.dismiss();

//		////////////////////////////////
//		
//		// store: string --> pref
//		
//		////////////////////////////////
//		boolean res = Methods.set_Pref_String(
//				actv, 
//				CONS.Pref.pname_MainActv, 
//				CONS.Pref.pkey_ShowListActv_Filter_String, 
//				input);
//		
//		if (res == true) {
//			
//			// Log
//			msg_Log = "pref filter => set: " + input;
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		} else {
//			
//			// Log
//			msg_Log = "pref filter => not set: " + input;
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}

		////////////////////////////////

		// save: filter history

		////////////////////////////////
		////////////////////////////////

		// save filter?

		////////////////////////////////
		FilterHistory fh_Prev = DBUtils.find_FH_latest(actv);
		
		if (fh.getKeywords().equals(fh_Prev.getKeywords())
			&& fh.getOperator() == fh_Prev.getOperator()
				) {
			
			// Log
			msg_Log = "same filter. not saving";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		} else {
			
			// Log
			msg_Log = "saving filter...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			_filter_MemoList_History__SaveFilter(actv, input, RB_id_Checked, fh);
			
		}
		
////		android.provider.BaseColumns._ID,		// 0
////		"created_at", "modified_at",			// 1,2
////		"keywords",									// 3
////		"operator",									// 4
////		"op_label",									// 5
//		
//		ContentValues cv = new ContentValues();
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[1], 
//				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[2], 
//				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[3], input);
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[4], RB_id_Checked);
//		
////		// operator label
////		RadioButton rb = (RadioButton) rg.findViewById(RB_id_Checked);
////		
////		if (rb != null) {
////			
////			String label = rb.getText().toString();
////			
////			if (label != null && !label.equals("")) {
//				
//				cv.put(CONS.DB.col_names_FilterHistory_full[5], fh.getOp_label());
//				
////			}
////			
////		}
//		
//		res = DBUtils.insert_Data_generic(actv, CONS.DB.tname_FilterHistory, cv);
//		
//		if (res == true) {
//			
//			// Log
//			msg_Log = "filter history => saved: " + input;
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		} else {
//
//			// Log
//			msg_Log = "filter history => not saved: " + input;
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}
		
	}

	private static void 
	_filter_MemoList_History__SaveFilter
	(Activity actv,
		String input, int RB_id_Checked, FilterHistory fh) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		boolean res;
		
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		"keywords",									// 3
//		"operator",									// 4
//		"op_label",									// 5
		
		ContentValues cv = new ContentValues();
		
		cv.put(CONS.DB.col_names_FilterHistory_full[1], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_names_FilterHistory_full[2], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_names_FilterHistory_full[3], input);
		
		cv.put(CONS.DB.col_names_FilterHistory_full[4], RB_id_Checked);
		
//		// operator label
//		RadioButton rb = (RadioButton) rg.findViewById(RB_id_Checked);
//		
//		if (rb != null) {
//			
//			String label = rb.getText().toString();
//			
//			if (label != null && !label.equals("")) {
				
				cv.put(CONS.DB.col_names_FilterHistory_full[5], fh.getOp_label());
				
//			}
//			
//		}
		
		res = DBUtils.insert_Data_generic(actv, CONS.DB.tname_FilterHistory, cv);
		
		if (res == true) {
			
			// Log
			msg_Log = "filter history => saved: " + input;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {

			// Log
			msg_Log = "filter history => not saved: " + input;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}

	}//_filter_MemoList_History__SaveFilter

	public static void 
	save_Filter
	(Activity actv,
			String input, int RB_id_Checked, String op_Label) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		boolean res;
		
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		"keywords",									// 3
//		"operator",									// 4
//		"op_label",									// 5
		
		ContentValues cv = new ContentValues();
		
		cv.put(CONS.DB.col_names_FilterHistory_full[1], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_names_FilterHistory_full[2], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_names_FilterHistory_full[3], input);
		
		cv.put(CONS.DB.col_names_FilterHistory_full[4], RB_id_Checked);
		
//		// operator label
//		RadioButton rb = (RadioButton) rg.findViewById(RB_id_Checked);
//		
//		if (rb != null) {
//			
//			String label = rb.getText().toString();
//			
//			if (label != null && !label.equals("")) {
		
		cv.put(CONS.DB.col_names_FilterHistory_full[5], op_Label);
//		cv.put(CONS.DB.col_names_FilterHistory_full[5], fh.getOp_label());
		
//			}
//			
//		}
		
		res = DBUtils.insert_Data_generic(actv, CONS.DB.tname_FilterHistory, cv);
		
		if (res == true) {
			
			// Log
			msg_Log = "filter history => saved: " + input;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			msg_Log = "filter history => not saved: " + input;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//_filter_MemoList_History__SaveFilter

	
}//public class Methods

