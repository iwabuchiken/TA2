
package ta2.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import ta2.listeners.dialog.DL;
import ta2.main.MemoActv;
import ta2.main.R;

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
		
		i.setClass(actv, MemoActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);

	}//start_Activity_PrefActv

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
		String src_dir = CONS.DB.dPath_dbFile_backup_IFM11;
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
							CONS.DB.dbName_IFM11
							
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
		Log.d("MainActv.java" + "["
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
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied: " + src);
			
			// debug
			Toast.makeText(actv, "DB restoration => Done", Toast.LENGTH_LONG).show();
			
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

}//public class Methods

