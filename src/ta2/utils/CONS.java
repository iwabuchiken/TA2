package ta2.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ta2.adapters.Adp_FHs;
import ta2.adapters.Adp_LogFileList;
import ta2.adapters.Adp_MemoList;
import ta2.adapters.Adp_ShowLogFile_List;
import ta2.adapters.Adp_TIList;
import ta2.adapters.Adp_WordPatterns;
import ta2.items.FilterHistory;
import ta2.items.ListItem;
import ta2.items.LogItem;
import ta2.items.Memo;
import ta2.items.TI;
import ta2.items.WordPattern;
import ta2.main.R;
import ta2.tasks.Task_AudioTrack;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

public class CONS {

	public static class Intent {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		
		////////////////////////////////

		// MainActv

		////////////////////////////////
		public static String iKey_CurrentPath_MainActv = "current_path";

		////////////////////////////////
		
		// MemoEditActv
		
		////////////////////////////////
		public static String iKey_Memo_Id = "iKey_Memo_Id";
		
		
		/***************************************
		 * Request codes
		 ***************************************/
		public final static int REQUEST_CODE_SEE_BOOKMARKS = 0;
		
		public final static int REQUEST_CODE_HISTORY = 1;
		
		/***************************************
		 * Result code
		 ***************************************/
		public final static int RESULT_CODE_SEE_BOOKMARKS_OK = 1;
		
		public final static int RESULT_CODE_SEE_BOOKMARKS_CANCEL = 0;
		
		public final static int RESULT_CODE_PREF_ACTIVE = 2;
		
		public final static int RESULT_CODE_MEMO_EDIT_ACTIVE = 3;
		
		////////////////////////////////

		// PlayActv

		////////////////////////////////
		public final static String iKey_PlayActv_Memo_Id = "iKey_PlayActv_Memo_Id";
		
		// Used in Service_ShowProgress
		public static String iKey_PlayActv_TaskPeriod
											= "iKey_PlayActv_TaskPeriod";

		
		////////////////////////////////

		// ShowLogActv

		////////////////////////////////
		public static final String iKey_LogActv_LogFileName =
													"iKey_LogActv_LogFileName";
		

	}//public static class Intent
	
	public static class DB {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static final String sortOrder_ASC = "ASC";
		
		//REF spelling http://stackoverflow.com/questions/8948435/how-do-i-order-my-sqlite-database-in-descending-order-for-an-android-app answered Jan 20 '12 at 22:06
		public static final String sortOrder_DESC = "DESC";
		
		////////////////////////////////

		// Paths and names

		////////////////////////////////
		public static String dbName = "ta2.db";
		public static String dbName_IFM11 = "ifm11.db";
		public static String dbName_TWT = "TWT.db";
		
		public static String dbName_Importing = dbName_TWT;
		
		public static String dPath_dbFile;
		
		// Do not hardcode "/data/"; use Context.getFilesDir().getPath()
//		public static String dPath_dbFile = "/data/data/cm7.main/databases";
		
		public final static String dPath_Data_Root = "/mnt/sdcard-ext/ta2_data";
		
		public static String dPath_dbFile_Backup = dPath_Data_Root + "/ta2_backup";
		
		public final static String dPath_Data = dPath_Data_Root + "/data";
		
		public final static String dPath_Log = dPath_Data_Root + "/log";
		
		public final static String dPath_Audio = dPath_Data_Root + "/audio";
		
		public final static String fname_Audio_Ext = ".wav";
		
		public static String dPath_dbFile_backup_IFM11 = 
				"/mnt/sdcard-ext/ifm11_backup";
		
		public static String dPath_dbFile_backup_TWT = 
				"/mnt/sdcard-ext/TWT_backup";
		
//		public static String dPath_dbFile = 
//							Methods.get_DirPath(new MainActv().getFilesDir().getPath());
		
		public static String fname_DB_Backup_Trunk = "ta2_backup";
		
		public static String fname_DB_Backup_ext = ".bk";
		
//		public static String dname_TapeATalk_Sdcard = "tapeatalk_records";
		
		public static final String admin_LastBackup = "last_backup";
		
		////////////////////////////////
		
		// Table: ta2
		
		////////////////////////////////
		public static final String tname_TA2 = "ta2";

		public static final String[] col_names_TA2 = {
			
			"text",				// 0
			"uploaded_at",		// 1
			"twted_at",			// 2
			
			"twt_id",			// 3
			"twt_created_at",	// 4
			
		};
		
		public static final String[] col_names_TA2_full = {
			
			//	0
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			
			"text",									// 3
			"uploaded_at",							// 4
			"twted_at",								// 5
			
			"twt_id",								// 6
			"twt_created_at",						// 7
			
		};

		public static final String[] col_types_TA2 = {
			
			"TEXT", "TEXT", "TEXT",	// 0,1,2
			"INTEGER", "TEXT",				// 3,4
			
		};
		
		////////////////////////////////

		// table: patterns

		////////////////////////////////
		public static String tname_Patterns = "patterns";
		
		public static String[] col_names_Patterns = {
			
			"word",							// 0
			"used",							// 0
					
		};
		
		public static String[] col_names_Patterns_full = {
			
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"word",									// 3
			"used",									// 4
		};
		
		public static String[] col_types_Patterns = {
			
			"TEXT", 			// 0
			"TEXT", 			// 1
					
		};
		
		public static String[] col_types_Patterns_full = {
			
			"INTEGER", "TEXT", "TEXT",	// 0,1,2
			"TEXT", 					// 3
			"TEXT", 					// 4
			
		};
		
		////////////////////////////////

		// table: memo_patterns (IFM11)

		////////////////////////////////
		public static String tname_MemoPatterns_IFM11 = "memo_patterns";
		
		public static String[] col_names_MemoPatterns_IFM11 = {
			
			"word",							// 0
			"table_name"					// 1
					
		};

		////////////////////////////////
		
		// table: patterns (app.twitter)
		
		////////////////////////////////
		public static final String tname_Patterns_TWT	= "patterns";
		
		public static final String[] col_names_Patterns_full_TWT	= {
			
				android.provider.BaseColumns._ID,	// 0
				"created_at",						// 1
				"modified_at",						// 2

				"word",						// 3
				"uploaded_at"						// 4
				
		};

		public static final String[] col_names_Patterns_TWT	= {
			
			"word",						// 3
			"uploaded_at"						// 4
			
		};
		
		////////////////////////////////
		
		// table: admin
		
		////////////////////////////////
		public static String tname_Admin = "admin";
		
		public static String[] col_names_Admin = {
			
			"name",							// 0
			"val",							// 1
			
		};
		
		public static String[] col_names_Admin_full = {
			
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"name",									// 3
			"val",									// 4
			
		};
		
		public static String[] col_types_Admin = {
			
			"TEXT", 			// 0
			"TEXT", 			// 0
			
		};
		
		public static String[] col_types_Admin_full = {
			
			"INTEGER", "TEXT", "TEXT",	// 0,1,2
			"TEXT", 					// 3
			"TEXT", 					// 3
			
		};
		
		////////////////////////////////
		
		// table: filter_history
		
		////////////////////////////////
		public static String tname_FilterHistory = "filter_history";
		
		public static String[] col_names_FilterHistory = {
			
			"keywords",							// 0
			"operator",							// 1
			"op_label",							// 1
			
		};
		
		public static String[] col_names_FilterHistory_full = {
			
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"keywords",									// 3
			"operator",									// 4
			"op_label",									// 5
			
		};
		
		public static String[] col_types_FilterHistory = {
			
			"TEXT", 			// 0
			"INTEGER", 			// 1
			"TEXT", 			// 2
			
		};
		
		public static String[] col_types_FilterHistory_full = {
			
			"INTEGER", "TEXT", "TEXT",	// 0,1,2
			"TEXT", 					// 3
			"INTEGER", 					// 4
			"TEXT", 					// 5
			
		};
		
		////////////////////////////////

		// Others

		////////////////////////////////
		public static String jointString_TableName = "__";
		
		public static int pastXDays		= -10;

		public final static String fname_Log = "log.txt";
		
		public final static String fname_Log_Trunk = "log";
		
		public final static String fname_Log_ext = ".txt";

		public static final long logFile_MaxSize = 40000;
		
		////////////////////////////////

		// FileFilter

		////////////////////////////////
		public static enum FFType {
			
			RefreshHistory
		}
		
		////////////////////////////////

		// sqls

		////////////////////////////////
		public static class Sqls {
			
//			public static String _20140817_154650_addCol_IFM11_UpdatedAt_TITLE = 
//									"Add column: uploaded_at";
//			
//			//REF http://stackoverflow.com/questions/7622122/sqlite-add-column-keep-data answered Oct 1 '11 at 18:32
//			public static String _20140817_154650_addCol_IFM11_UpdatedAt_SQL = 
//					"ALTER TABLE" + " "
//					+ CONS.DB.tname_IFM11 + " "
//					+ "ADD COLUMN" + " "
//					+ "uploaded_at" + " "
//					+ "TEXT"
//					;
			
		}
		
	}//public static class DB

	public static class 
	Pref {
		////////////////////////////////

		// Commons

		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		
		public static SharedPreferences prefs_MainActv;
		
		public static String pname_MainActv = "pname_MainActv";
//		public static String pname_CurrentPath = "current_path";
		
		public static String pkey_CurrentPath = "pkey_CurrentPath";
		
		public static String pkey_CurrentPosition_MainActv = "pkey_CurrentPosition";

//		public static String pkey_CurrentPosition_MainActv = "prefs_db_auto_backup_key";
		
		////////////////////////////////

		// MemoActv

		////////////////////////////////
		public static final String pkey_Saved_Memo = "pkey_Saved_Memo";

		////////////////////////////////

		// PrefActv

		////////////////////////////////
		public static final int DEFAULT_PROGRESS = 50;
		
		public static int currentProgress;
		
		public static int oldProgress;

		public static final int MAX_PROGRESS = 100;
		
		public static final String 
		pkey_PrefActv_MemoListSize_Dropdown_CurrentSelection = 
							"pkey_PrefActv_MemoListSize_Dropdown_CurrentSelection";
		
		////////////////////////////////

		// ShowListActv

		////////////////////////////////
		public static String pname_ShowListActv = "pname_ShowListActv"; 
		
		
		public static final String 
		pkey_ShowListActv_Filter_String = 
							"pkey_ShowListActv_Filter_String";

		public static final String 
		pkey_ShowListActv_Current_Position = 
							"pkey_ShowListActv_Current_Position";
		
		////////////////////////////////
		
		// PhotoActv
		
		////////////////////////////////
		public static String pname_PhotoActv = "pname_PhotoActv"; 
		
		public static final String 
		pkey_PhotoActv_Current_Position = 
							"pkey_PhotoActv_Current_Position";
		
		////////////////////////////////
		
		// PlayActv.java
		
		////////////////////////////////
		public static final String pname_PlayActv = "pname_PlayActv";
		
		public static final String pkey_PlayActv_CurrentPosition = 
											"pkey_PlayActv_CurrentPosition";
		
		public static final String pkey_PlayActv_CurrentFileName = 
											"pkey_PlayActv_CurrentFileName";

		////////////////////////////////

		// LogActv

		////////////////////////////////
		public static String pkey_CurrentPosition_LogActv = 
									"pkey_CurrentPosition_LogActv";
		
	}//Pref

	public static class MainActv {
		
		public static ArrayAdapter<ListItem> adp_Item_List = null;

	}

	public static class MemoActv {
		
		public static List<WordPattern> list_WP_1;
		
		public static List<WordPattern> list_WP_2;
		
		public static List<WordPattern> list_WP_3;
		
		public static ArrayAdapter<WordPattern> adp_WPList_1 = null;
		
		public static ArrayAdapter<WordPattern> adp_WPList_2 = null;
		
		public static ArrayAdapter<WordPattern> adp_WPList_3 = null;
		
		////////////////////////////////

		// layout-related

		////////////////////////////////
		public final static int layout_MemoActv_LV_Height = 60;
		
		
	}
	
	public static class MemoEditActv {
		
		public static Memo memo;
		
	}
	
	public static class 
	ShowListActv {
		
		public static List<Memo> list_Memos;
		
		public static List<FilterHistory> list_FS;
		
		public static Adp_MemoList adp_List_Memos = null;
		
		public static Adp_WordPatterns adp_List_WPs = null;

		public static Adp_FHs adp_List_FHs = null;
		
		
		public static final int length_Conf_Message		= 20;
		
		public static int list_Pos_Current = -1;
		public static int list_Pos_Prev = -1;
		
		public final static int dialog_LV_Height_Ratio = 60;	// out of 100
		
		////////////////////////////////

		// intent-related

		////////////////////////////////
		public static int BACK_FROM_X_ACTV;
		
	}//ShowListActv
	
	public static class TNActv {
		
//		public static List<TI> list_TNActv_Main = null;
//		
//		public static Adp_TIList adp_TNActv_Main;
//		
//		public static Adp_TIList_Move adp_TNActv_Main_Move;
		
		public static int list_Pos_Current = -1;
		public static int list_Pos_Prev = -1;
		
		public static boolean moveMode;

		public static Menu menu;
		
		public static List<Integer> checkedPositions = new ArrayList<Integer>();
		
		// TNActv, long click, move files
		public static ArrayAdapter<String> adp_DirList;
		
		// TNActv, long click, move files
		public static List<String> dir_List;

		// MainActv: option "Search"
		public static List<Long> searchedItems;
		
		// TNActv refers to this var; if true, _Setup_SetList()
		//		builds other TI list using the var "searchedItems"
		public static boolean searchDone	= false;

		// Used => Deleting TI in TNActv list view
		public static int inList_Pos;
		
	}
	
	public static class IMageActv {
		
		public static ArrayAdapter<String> adp_ImageActv_GridView = null;

		public static TI ti;
		
		public static Bitmap bm;

		public static Memo memo;	// hold an instance of Memo for use in ImageActv
									//		when ImageActv is called from ShowList 
		
//		public static List<String> patternList = null;
		
//		public static List<WordPattern> patternList;
//		
//		public static Adp_WordPatterns adapter;
		
	}
	
	public static class Admin {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static final String dirString_UpperDir	= "..";
		
		public static final float DLG_WIDTH_RATIO = 0.8f;
		
		public static final String dName_backup = "cm5_backup";
		
		public static final String char_Space_Half	= " ";
		
		public static final String char_Space_Whole	= "　";
		
		// millseconds; used in Methods_dlg.dlg_ShowMessage_Duration
		public static final int dflt_MessageDialog_Length	= 3000;
		
		// X out of 100
		// Usage => e.g. width = screen_width * 100 / ratio_Dialog_to_Screen_W
		public static final int ratio_Dialog_to_Screen_W = 70;
		
		// default colors
		public static int dflt_Background_Color = R.color.white;
		
		public static String[] special_Chars = new String[]{
			
			"()", "[]",
			"（）", "「」", "『』", "〈〉", "【】", "｛｝", "\"\"", "''",
			
		};
		
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		public static String fname_List = "list.txt";
		
		////////////////////////////////

		// Utilities

		////////////////////////////////
		public static Vibrator vib;
		
		public static final int vibLength_click = 35;
		
		public static final String format_Date = "yyyy/MM/dd HH:mm:ss.SSS";
		
		public static final String format_Date_AudioFile = "yyyy-MM-dd_HH-mm-ss-SSS";
		
		public static final String format_Clock = "%02d:%02d";
		
	}//public static class Admin

	public static class Paths {
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		
		public static String dpath_Storage_Sdcard = "/mnt/sdcard-ext";
		
		public static String dpath_Storage_Internal = "/mnt/sdcard";
		
		public static String  dname_Base = "ta2";
		
		public static String dpath_Storage_Camera = "/mnt/sdcard-ext/dcim/Camera";
		
		////////////////////////////////

		// RecActv

		////////////////////////////////
		public static String fpath_AudioRecorded;
		
	}
	
	public static class Retval {
		////////////////////////////////

		// Errors

		////////////////////////////////
		/******************************
			Refresh DB
		 ******************************/
		public static int CantCreateTable =		-10;
		
		public static int CantRefreshAudioDir=	-11;
		
		public static int NoNewFiles =			-12;
		
		
		
	}

	public static class Remote {
		
		public static enum FtpType {
			
			IMAGE, DB_FILE,
			
		}
		
		public static enum HttpType {
			
			IMAGE,
			
		}
		
		////////////////////////////////

		// ftp

		////////////////////////////////
		public static String server_Name = "ftp.benfranklin.chips.jp";
		
		public static String uname = "chips.jp-benfranklin";
		
		public static String passwd = "9x9jh4";
		
		public static String remote_Root_Image = "./cake_apps/images/ta2";

		public static String remote_Root_DBFile = "./android_app_data/TA2/db";
		
		// initialize res:int in Task_FTP.doInBackground()
		public static int initial_IntValue = -100;
		
		////////////////////////////////

		// status code

		////////////////////////////////
		public static final int status_220		= 220;

		public static final int status_Created	= 201;
		
		public static final int status_NOT_CREATED	= -201;
		
		public static final int status_OK		= 200;
		
		public static class Http {
			
			public static final String url_Post_ImageData =
						"http://benfranklin.chips.jp/cake_apps/Cake_TA2/images/add";
			
		}
		
	}

	public static class Audio {
		
		public static AudioTrack audioTrack;
		
		public static Task_AudioTrack task_Audio;
		
		public static float dflt_Audio_Volume = 1.0f;
		
//		public static class Clip {
//			
//			public static int dialog_Item		= R.raw.tap_select04_x0_183;
//			
////			public static int dialog_Item_UnderConstruction	= 
////													R.raw.tap_select04_x0_183;
//			
//			public static int dialog_Cancel		= R.raw.tap_hightlight_x0_362;
////			public static int dialog_Cancel		= R.raw.tap_x0_094;
//			
//		}
		
	}
	
	public static class 
	Enums {
		
		public static enum SortType {
			
			FileName, POSITION, CREATED_AT,
			WORD, USED,
			
		}

		// Sort order
		public static enum SortOrder {
			
				ASC, DESC,
				
		};

		public static enum MoveMode {
			// TIListAdapter.java
			ON, OFF,
			
		}//public static enum MoveMode

		public static enum ListType {
			
			STANDARD, SEARCH, HISTORY, ANY,
			
		}
		
	}//Enums

	public static class 
	RecActv {
	
		public static MediaRecorder mr;
		
		public static Recorder rc;
		
		public static final String AUDIO_RECORDER_FOLDER = CONS.DB.dPath_Audio;
		
		public static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
		
		public static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
		
		public static final int RECORDER_SAMPLERATE = 44100;
		public static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_STEREO;
		public static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
		
		public int bufferSize = 0;
		public Thread recordingThread = null;
		public boolean isRecording = false;
		
		// voice memo
		public static final String fmt_FileName = "@%s %s";
		
		public static final String fmt_FileName_Photo = "&%d %s";
		
		// '?' => needs to be outside of "()"
		public static final String fmt_FileName_PhotoMemo = "^&(\\d+)?";	
//		public static final String fmt_FileName_PhotoMemo = "^&(\\d+?)";
		
		public static final String fmt_FileName_PlayMemo = "^@(\\d{4}.+wav)";
//		public static final String fmt_FileName_PlayMemo = "^@\\d{4}.+wav";
//		public static final String fmt_FileName_PlayMemo = "^@\\d{4}";
//		public static final String fmt_FileName_PlayMemo = "@\\d{4}";
		
		public static String fname_Generated_WavFile;
		
	}//RecActv
	
	public static class 
	PlayActv {
		
		public static Memo memo;
		
		public static String fname_Audio;

		public static MediaPlayer mp;

		public static SeekBar sb;

		public static TextView tvCurrentPosition;
		
		public static long len_Audio;
		
		public static int playActv_task_Period = 1000;
		
	}
	
	public static class 
	PrefActv {
		
		public static final String sp_NoSelection_label = "No selection";
		
		public static final int sp_NoSelection_value = -10;
		
	}

	public static class
	PhotoActv {
		
		public static final String content_Uri = "content://ifm11.main.CV/";
		
		public static List<TI> list_TIs;

		public static Adp_TIList adp_List_TIs = null;
		
		public static int list_Pos_Current = -1;
		public static int list_Pos_Prev = -1;
		
	}

	public static class
	IFM11 {
	
		////////////////////////////////
		
		// Table: ifm11
		
		////////////////////////////////
		public static final String tname_IFM11 = "ifm11";

		public static final String[] col_names_IFM11 = {
			
			"file_id", "file_path", "file_name",	// 0,1,2
			"date_added", "date_modified",			// 3,4
			"memos", "tags",						// 5,6
			"last_viewed_at",						// 7
			"table_name",							// 8
			"uploaded_at",							// 9
			
		};
		
		public static final String[] col_names_IFM11_full = {
			
			//	0
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"file_id", "file_path", "file_name",	// 3,4,5
			"date_added", "date_modified",			// 6,7
			"memos", "tags",						// 8,9
			"last_viewed_at",						// 10
			"table_name",							// 11
			"uploaded_at",							// 12
			
		};

		public static final String[] col_types_IFM11 = {
			"INTEGER", "TEXT", "TEXT",	// 0,1,2
			"TEXT", "TEXT",				// 3,4
			"TEXT", "TEXT",				// 5,6
			"TEXT",						// 7
			"TEXT",						// 8
			"TEXT",						// 9
		};		
		
	}//IFM11

	public static class LogActv {
		
		public static List<String> list_LogFiles = null;
		
//		public static ArrayAdapter<String> adp_LogFile_List = null;
		
		public static Adp_LogFileList adp_LogFile_List;
		
	}

	public static class ShowLogActv {
		
		public static List<LogItem> list_ShowLog_Files = null;
		
//		public static ArrayAdapter<String> adp_LogFile_List = null;
		
		public static Adp_ShowLogFile_List adp_ShowLog_File_List;
		
		public static String fname_Target_LogFile = null;
		
		public static List<String> list_RawLines = null;
		
	}

}//public class CONS
