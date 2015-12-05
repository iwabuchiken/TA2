package ta2.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import ta2.adapters.Adp_FHs;
import ta2.adapters.Adp_ListItems;
import ta2.adapters.Adp_WordPatterns;
import ta2.comps.Comp_WP;
import ta2.items.AudioMemo;
import ta2.items.ListItem;
import ta2.items.Memo;
import ta2.items.WordPattern;
import ta2.listeners.dialog.DB_OCL;
import ta2.listeners.dialog.DB_OTL;
import ta2.listeners.dialog.DOI_CL;
import ta2.main.R;
import ta2.utils.CONS.Enums.SortOrder;
import ta2.utils.Tags.DialogTags;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Methods_dlg {

	public static
	Dialog dlg_Template_Cancel
	(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		****************************/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_Cancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg2));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()

	public static Dialog 
	dlg_Template_Cancel_ThirdDialog
	(Activity actv, Dialog dlg1, Dialog dlg2,
			int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2, dlg3));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_Cancel_4thDialog
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3,
		int layoutId, int titleStringId,
		int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog d4 = new Dialog(actv);
		
		//
		d4.setContentView(layoutId);
		
		// Title
		d4.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) d4.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
		
		//
		//dlg.show();
		
		return d4;
		
	}//public static Dialog dlg_template_okCancel()
	
//	public static Dialog 
//	dlg_Template_Cancel_4thDialog_Duration
//	(Activity actv, 
//		Dialog d1, Dialog d2, Dialog d3,
//		int layoutId, int titleStringId,
//		int cancelButtonId, Tags.DialogTags cancelTag,
//		int duration) {
//		/****************************
//		 * Steps
//		 * 1. Set up
//		 * 2. Add listeners => OnTouch
//		 * 3. Add listeners => OnClick
//		 ****************************/
//		
//		// 
//		Dialog d4 = new Dialog(actv);
//		
//		//
//		d4.setContentView(layoutId);
//		
//		// Title
//		d4.setTitle(titleStringId);
//		
//		/****************************
//		 * 2. Add listeners => OnTouch
//		 ****************************/
//		//
//		Button btn_cancel = (Button) d4.findViewById(cancelButtonId);
//		
//		//
//		btn_cancel.setTag(cancelTag);
//		
//		//
//		btn_cancel.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
//		
//		/****************************
//		 * 3. Add listeners => OnClick
//		 ****************************/
//		//
//		btn_cancel.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
//		
//		//
//		//dlg.show();
//		
//		return d4;
//		
//	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_OkCancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, int titleStringId,
			
			int okButtonId, Tags.DialogTags okTag,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		////////////////////////////////
		
		// button: cancel
		
		////////////////////////////////
		Button btn_Ok = (Button) dlg2.findViewById(okButtonId);
		
		btn_Ok.setTag(okTag);
		
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_Ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		////////////////////////////////

		// button: cancel

		////////////////////////////////
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		btn_cancel.setTag(cancelTag);
		
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_OkCancel_SecondDialog
	(Activity actv, Dialog dlg1, Memo memo,
			int layoutId, int titleStringId,
			
			int okButtonId, Tags.DialogTags okTag,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		////////////////////////////////
		
		// button: cancel
		
		////////////////////////////////
		Button btn_Ok = (Button) dlg2.findViewById(okButtonId);
		
		btn_Ok.setTag(okTag);
		
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_Ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, memo));
		
		////////////////////////////////
		
		// button: cancel
		
		////////////////////////////////
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		btn_cancel.setTag(cancelTag);
		
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_OkCancel_SecondDialog
	(Activity actv, Dialog dlg1, WordPattern wp,
			int layoutId, int titleStringId,
			
			int okButtonId, Tags.DialogTags okTag,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		////////////////////////////////
		
		// button: cancel
		
		////////////////////////////////
		Button btn_Ok = (Button) dlg2.findViewById(okButtonId);
		
		btn_Ok.setTag(okTag);
		
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_Ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, wp));
		
		////////////////////////////////
		
		// button: cancel
		
		////////////////////////////////
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		btn_cancel.setTag(cancelTag);
		
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_Cancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, String title,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(title);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg2));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static void
	dlg_ShowMessage(Activity actv, String message) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg.show();
		
	}
	
	public static void
	dlg_ShowMessage
	(Activity actv, String message, int colorId) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok_scrollview, 
//				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_scrollview_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_scrollview_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// background

		////////////////////////////////
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorId));
		
		dlg.show();
		
	}//dlg_ShowMessage

	public static void
	dlg_ShowMessage_SecondDialog
	(Activity actv, String message, Dialog dlg1) {
		
		Dialog dlg2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, dlg1,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg2.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg2.show();
		
	}

	public static void
	dlg_ShowMessage_SecondDialog
	(Activity actv, String message, Dialog dlg1, int colorID) {
		
		Dialog d2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, dlg1,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
		
		TextView tv_Message = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		tv_Message.setTextColor(Color.WHITE);
		
		d2.show();
		
	}
	
	public static void
	dlg_ShowMessage_ThirdDialog
	(Activity actv, 
		String message, Dialog dlg1, Dialog dlg2) {
		
		Dialog dlg3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
				actv, dlg1, dlg2,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg3.show();
		
	}
	
	public static void
	dlg_ShowMessage_4thDialog
	(Activity actv, Dialog d1, Dialog d2, Dialog d3,
			String message, int colorID) {
		
		Dialog d4 = Methods_dlg.dlg_Template_Cancel_4thDialog(
				actv, d1, d2, d3,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_4TH_DIALOG);
		
		TextView tv_Message = 
				(TextView) d4.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		d4.show();
		
	}
	
	public static Dialog 
	dlg_Template_Cancel
	(Activity actv,
			int layoutId, String title, 
			int cancelButtonId, DialogTags cancelTag) {
		// TODO Auto-generated method stub
		
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(title);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;		
	}//dlg_Template_Cancel

	public static
	Dialog dlg_Tmpl_OkCancel_ThirdDialog
	(Activity actv, 
		int layoutId, int titleStringId,
		
		int okButtonId, int cancelButtonId,
		Tags.DialogTags okTag, Tags.DialogTags cancelTag,
		
		Dialog dlg1, Dialog dlg2) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg3));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg3));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		
		return dlg3;
		
	}//public static Dialog dlg_template_okCancel_SecondDialog()

	public static Dialog 
	dlg_Tmpl_OkCancel
	(Activity actv, 
		int layoutId, int titleStringId,
		int okButtonId, int cancelButtonId, 
		DialogTags okTag, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_Duration
	(Activity actv, String message, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// show

		////////////////////////////////
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}

	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_Duration
	(Activity actv, String message, int colorID, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok_scrollview, 
//				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_scrollview_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_scrollview_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// background

		////////////////////////////////
//		tv_Message.setBackgroundColor(colorID);
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));

		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				dlg.dismiss(); // when the task active then close the dialog
				t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
			}
		}, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}

	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_2_Duration
	(Activity actv, String message, int colorID, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok_scrollview, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////
		
		// background
		
		////////////////////////////////
//		tv_Message.setBackgroundColor(colorID);
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				dlg.dismiss(); // when the task active then close the dialog
				t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
			}
		}, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}
	
	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_4thDialog_2_Duration
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3,
		String message, int colorID, int duration) {
		
		final Dialog d4 = Methods_dlg.dlg_Template_Cancel_4thDialog(
				actv, d1, d2, d3,
				R.layout.dlg_tmpl_toast_ok_scrollview, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_4TH_DIALOG);
		
		TextView tv_Message = 
				(TextView) d4.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////
		
		// background
		
		////////////////////////////////
//		tv_Message.setBackgroundColor(colorID);
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d4.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				d4.dismiss(); // when the task active then close the dialog
				t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
			}
		}, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}
	
	public static void 
	dlg_ACTV_MAIN_Admin
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.dlg_actvmain_admin_title,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.GENERIC_DISMISS);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.dlg_actvmain_admin_item_backup_db))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_admin_item_restore_db))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_admin_item_operations))
								.setIconID(R.drawable.menu_icon_admin_32x32_purple)
								.setTextColor_ID(R.color.purple4)
								.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_admin_item_see_log))
								.setIconID(R.drawable.menu_icon_admin_32x32_yellow)
								.setTextColor_ID(R.color.yellow_dark)
								.build());
//		list.add(actv.getString(R.string.dlg_actvmain_admin_item_restore_db));
//		list.add(actv.getString(R.string.dlg_actvmain_admin_item_operations));
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//							actv,
//							//R.layout.dlg_db_admin,
//							R.layout.list_row_simple_1,
//							//android.R.layout.simple_list_item_1,
//							list
//		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_MAIN_ADMIN_LV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
//		// Log
//		String msg_Log = "w => " + w;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
//		// Log
//		msg_Log = "dialog_Width => " + dialog_Width;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LinearLayout.LayoutParams params2 =
//						new LinearLayout.LayoutParams(
						dialog_Width,
//						400,
//						200,
//						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		// Log
		msg_Log = "setting params...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
		// Log
		msg_Log = "params => set";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();
		
		
	}//dlg_ACTV_MAIN_Admin

	public static void 
	dlg_Admin_Ops
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// prep: dialog

		////////////////////////////////
		Dialog d2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, d1,
				R.layout.dlg_tmpl_cancel_lv_with_btn, 
				R.string.dlg_actvmain_admin_item_operations, 
				
				R.id.dlg_tmpl_cancel_lv_with_btn_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);

		////////////////////////////////

		// list

		////////////////////////////////
		ListView lv = _dlg_Admin_Ops__Setup_List(actv, d2);
		
		////////////////////////////////
		
		// setup: layout

		////////////////////////////////
		_dlg_Admin_Ops__Setup_Layout(actv, d2);

		////////////////////////////////

		// listener

		////////////////////////////////
		_dlg_Admin_Ops__Setup_Listener(actv, d1, d2, lv);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();
		
		
		
	}//dlg_Admin_Ops

	private static void 
	_dlg_Admin_Ops__Setup_Listener
	(Activity actv, Dialog d1, Dialog d2, ListView lv) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// Set listener to list

		////////////////////////////////
		lv.setTag(Tags.DialogItemTags.ACTV_MAIN_ADMIN_LV_OPS);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1, d2));

		////////////////////////////////

		// button: all dismiss

		////////////////////////////////
		ImageButton ib_AllClear = 
				(ImageButton) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
//		ImageButton ib_AllClear = (ImageButton) findViewById(R.id.dlg_tmpl_cancel_lv_wi);
		
		ib_AllClear.setTag(Tags.DialogTags.GENERIC_CLEAR_SECOND_DIALOG);
		
		ib_AllClear.setOnTouchListener(new DB_OTL(actv, d1, d2));
		
		ib_AllClear.setOnClickListener(new DB_OCL(actv, d1, d2));
		
		
	}//_dlg_Admin_Ops__Setup_Listener
	

	private static void 
	_dlg_Admin_Ops__Setup_Layout
	(Activity actv, Dialog d2) {
		// TODO Auto-generated method stub
	
		////////////////////////////////

		// layout: button

		////////////////////////////////
		LinearLayout llButton =
					(LinearLayout) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		// Log
		String msg_Log = "w => " + w;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		// Log
		msg_Log = "dialog_Width => " + dialog_Width;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
		ll_Main.setLayoutParams(params2);
		
		////////////////////////////////

		// ListView: height

		////////////////////////////////
//		// Log
//		msg_Log = "ll_Main.getHeight() => " + ll_Main.getHeight();
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		ListView lv = (ListView) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_lv);
//		
//		// Log
//		msg_Log = "lv.getHeight() => " + lv.getHeight();
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}//_dlg_Admin_Ops__Setup_Layout
	

	private static ListView 
	_dlg_Admin_Ops__Setup_List
	(Activity actv, Dialog d2) {
		// TODO Auto-generated method stub
		
		List<ListItem> list = new ArrayList<ListItem>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_admin_item_upload_db))
								.setIconID(R.drawable.menu_icon_admin_32x32_green)
								.setTextColor_ID(R.color.green4)
								.build());
		
		// Upload audio files
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.dlg_actvmain_ops_Upload_Audio))
							.setIconID(R.drawable.menu_icon_admin_32x32_green)
							.setTextColor_ID(R.color.green4)
							.build());
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.dlg_actvmain_ops_import_db))
						.setIconID(R.drawable.menu_icon_admin_32x32_blue)
						.setTextColor_ID(R.color.blue1)
						.build());
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_ops_import_patterns))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		
		////////////////////////////////

		// columns

		////////////////////////////////
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_ops_sql_add_col_used))
								.setIconID(R.drawable.menu_icon_admin_32x32_yellow)
								.setTextColor_ID(R.color.black)
								.build());
		
		////////////////////////////////

		// tables

		////////////////////////////////
//		list.add(new ListItem.Builder()
//		.setText(actv.getString(
//				R.string.dlg_actvmain_ops_create_table_patterns))
//				.setIconID(R.drawable.menu_icon_admin_32x32_purple)
//				.setTextColor_ID(R.color.purple4)
//				.build());
//		
//		list.add(new ListItem.Builder()
//		.setText(actv.getString(
//				R.string.dlg_actvmain_ops_drop_table_patterns))
//				.setIconID(R.drawable.menu_icon_admin_32x32_red)
//				.setTextColor_ID(R.color.red)
//				.build());
		
//		list.add(new ListItem.Builder()
//		.setText(actv.getString(
//				R.string.dlg_actvmain_ops_create_table_memos))
//				.setIconID(R.drawable.menu_icon_admin_32x32_purple)
//				.setTextColor_ID(R.color.purple4)
//				.build());
//		
//		list.add(new ListItem.Builder()
//		.setText(actv.getString(
//				R.string.dlg_actvmain_ops_drop_table_memos))
//				.setIconID(R.drawable.menu_icon_admin_32x32_red)
//				.setTextColor_ID(R.color.red)
//				.build());
		
//		list.add(new ListItem.Builder()
//					.setText(actv.getString(
//							R.string.dlg_db_ops_item_drop_create_tbl_admin))
//							.setIconID(R.drawable.menu_icon_admin_32x32_red)
//							.setTextColor_ID(R.color.red)
//							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.dlg_actvmain_ops_Drop_Create_table_filter_history))
							.setIconID(R.drawable.menu_icon_admin_32x32_red)
							.setTextColor_ID(R.color.red)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
						R.string.dlg_actvmain_ops_Drop_Create_Table_FilterHistory_ShowLog))
							.setIconID(R.drawable.menu_icon_admin_32x32_red)
							.setTextColor_ID(R.color.red)
							.build());
		
//		list.add(new ListItem.Builder()
//				.setText(actv.getString(
//						R.string.dlg_actvmain_ops_Drop_Create_table_Upload_History))
//						.setIconID(R.drawable.menu_icon_admin_32x32_red)
//						.setTextColor_ID(R.color.red)
//						.build());
		
//		list.add(new ListItem.Builder()
//				.setText(actv.getString(
//						R.string.dlg_actvmain_ops_Drop_Create_table_Upload_History_Audio))
//						.setIconID(R.drawable.menu_icon_admin_32x32_red)
//						.setTextColor_ID(R.color.red)
//						.build());
		
		list.add(new ListItem.Builder()
				.setText(actv.getString(
						R.string.dlg_actvmain_ops_Drop_Create_table_Audio_Files))
						.setIconID(R.drawable.menu_icon_admin_32x32_red)
						.setTextColor_ID(R.color.red)
						.build());
		
		////////////////////////////////

		// Adapter

		////////////////////////////////
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
		
		////////////////////////////////

		// Set adapter

		////////////////////////////////
		ListView lv = (ListView) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_lv);
		
		lv.setAdapter(adapter);

		////////////////////////////////

		// return

		////////////////////////////////
		return lv;
		
	}//_dlg_Admin_Ops__Setup_List

	public static void 
	conf_Import_DB
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_IMPORT_DB_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.dlg_actvmain_ops_import_db)
								+ "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.dbName_Importing);
//		tv_ItemName.setText(actv.getString(R.string.commons_import_db_name));
		
		////////////////////////////////

		// show

		////////////////////////////////
		dlg3.show();
		
	}//conf_Import_DB

	public static void 
	conf_Upload_DB
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_UPLOAD_DB_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_actvmain_ops_upload_db)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.dbName);
//		tv_ItemName.setText(actv.getString(R.string.commons_import_db_name));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_Import_DB
	
	/*******************************
	 * upload => since the date (set in a pref var) until now
	 *******************************/
	public static void 
	conf_Upload_Audio
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		///////////////////////////////////
		//
		// validate: any new audio files?
		//
		///////////////////////////////////
		int res_Int = Methods.validate_New_AudioFiles(actv);
		
		if (res_Int == 0) {

			Dialog dlg3 = 
					Methods_dlg.dlg_Template_Cancel_ThirdDialog(
							actv, 
							d1, d2, 
							
							R.layout.dlg_tmpl_toast_ok, 
							R.string.generic_tv_confirm, 
							
							R.id.dlg_tmpl_toast_ok_bt_cancel, 
							Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
			
			TextView tv_Message = 
					(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
			
			String message = "No new audio files";
			
			tv_Message.setText(message);
			
			dlg3.show();
			
			return;
			
//					Methods_dlg.dlg_tmpl_ok
//							actv, 
//							R.layout.dlg_tmpl_confirm_simple, 
//							R.string.generic_tv_confirm, 
//							
//							R.id.dlg_tmpl_confirm_simple_btn_ok, 
//							R.id.dlg_tmpl_confirm_simple_btn_cancel, 
//							
//							Tags.DialogTags.DLG_CONF_UPLOAD_AUDIO_OK, 
////							Tags.DialogTags.DLG_CONF_UPLOAD_DB_OK, 
//							Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
//							
//							d1, d2);
			

		}//if (res_Int == 0)
		
//		String last_update_Str = DBUtils.find_UploadHistory_Audio_Latest(actv);
//		
//		String fname_Threshold = 
//						Methods.conv_TimeLabel_2_FileName(actv, last_update_Str);
//		
//		// Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"last_update_Str => %s | fname_Threshold => %s", 
//				last_update_Str, fname_Threshold
//				);
//		
//		Log.i("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
		///////////////////////////////////
		//
		// dialog
		//
		///////////////////////////////////
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_UPLOAD_AUDIO_OK, 
//						Tags.DialogTags.DLG_CONF_UPLOAD_DB_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_actvmain_ops_Upload_Audio)
//				+ "?");
				+ "?"
				+ " ("
				+ String.valueOf(res_Int)
				+ " files)"
				);
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
//		TextView tv_ItemName = 
//				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
////		dlg_tmpl_confirm_simple_tv_message
//		
//		tv_ItemName.setText(CONS.DB.dbName);
////		tv_ItemName.setText(actv.getString(R.string.commons_import_db_name));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_Upload_Audio
	
	public static void 
	conf_Import_Patterns
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_IMPORT_PATTERNS_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.dlg_actvmain_ops_import_patterns)
								+ "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText("From: " + CONS.DB.dbName_Importing);
//		tv_ItemName.setText("From: " + actv.getString(R.string.commons_import_db_name));
		
		////////////////////////////////

		// show

		////////////////////////////////
		dlg3.show();
		
	}//conf_Import_Patterns

	public static void 
	conf_CreateTable_Patterns
	(Activity actv, 
		Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_CREATE_TABLE_PATTERNS_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.commons_lbl_create_table)
								+ "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_Patterns);
		
		////////////////////////////////

		// show

		////////////////////////////////
		dlg3.show();
		
	}//conf_CreateTable_Patterns
	
	public static void 
	conf_AddColumn_Used
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_ADD_COLUMN_USED_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_add_column)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText("used");
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_AddColumn_Used
	
	public static void 
	conf_CreateTable_Memos
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_CREATE_TABLE_MEMOS_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_create_table)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_TA2);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_CreateTable_Patterns
	
	public static void 
	conf_DropTable_Patterns
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_DROP_TABLE_PATTERNS_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_drop_table)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_Patterns);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropTable_Patterns

	public static void 
	conf_DropTable_Memos
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_DROP_TABLE_MEMOS_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_drop_table)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_TA2);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropTable_Patterns
	
	public static void 
	conf_DropCreate_Table_Admin
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_ADMIN_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_db_ops_item_drop_create_tbl_admin)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_Admin);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropCreate_Table_Admin
	
	public static void 
	conf_DropCreate_Table_FilterHistory
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_actvmain_ops_Drop_Create_table_filter_history)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_FilterHistory);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropCreate_Table_FilterHistory
	
	public static void 
	conf_DropCreate_Table_Audio_Files
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_AUDIO_FILES_OK, 
//						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_actvmain_ops_Drop_Create_table_Audio_Files)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_Audio_Files);
//		tv_ItemName.setText(CONS.DB.tname_FilterHistory);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropCreate_Table_Audio_Files
	
	public static void 
	conf_DropCreate_Table_Generic
//	conf_DropCreate_Table_FilterHistory_ShowLog
	(Activity actv, Dialog d1, Dialog d2,
			Tags.DialogTags tag_OK, Tags.DialogTags tag_Dismiss,
			int message_String_ID, String tableName) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						tag_OK,
						tag_Dismiss,
//						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_SHOWLOG_OK, 
////						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK, 
//						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				message_String_ID)
//				R.string.dlg_actvmain_ops_Drop_Create_table_filter_history)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(tableName);
//		tv_ItemName.setText(CONS.DB.tname_FilterHistory);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropCreate_Table_FilterHistory_ShowLog
	
	public static void 
	conf_DropCreate_Table_UploadHistory
	(Activity actv, 
			Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_UPLOAD_HISTORY_OK, 
//						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_actvmain_ops_Drop_Create_table_Upload_History)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_UploadHistory);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropCreate_Table_UploadHistory
	
	public static void 
	conf_DropCreate_Table_UploadHistory_Audio
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		Dialog dlg3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_ThirdDialog(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags
								.DLG_CONF_DROP_CREATE_TABLE_UPLOAD_HISTORY_AUDIO_OK, 
//						Tags.DialogTags.DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG, 
						
						d1, d2);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.dlg_actvmain_ops_Drop_Create_table_Upload_History_Audio)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.tname_UploadHistory_Audio);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg3.show();
		
	}//conf_DropCreate_Table_UploadHistory_Audio
	
	public static void 
	conf_Restore_DB
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		
		Dialog d2 = 
				Methods_dlg.dlg_Template_OkCancel_SecondDialog(
						actv, d1,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						Tags.DialogTags.DLG_CONF_RESTORE_DB_OK, 
						
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG
				);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.commons_lbl_restore_db)
								+ "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(CONS.DB.dbName);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();		
		
	}//conf_Restore_DB

	public static void 
	conf_Clear_View
	(Activity actv) {
		// TODO Auto-generated method stub
		
		Dialog d1 = 
				Methods_dlg.dlg_Tmpl_OkCancel(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_CLEAR_VIEW_OK, 
						Tags.DialogTags.GENERIC_DISMISS);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d1.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.commons_lbl_clear_view) + "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d1.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText("EditText");
//		tv_ItemName.setText(actv.getString(R.string.commons_import_db_name));
		
		////////////////////////////////

		// show

		////////////////////////////////
		d1.show();
		
	}//conf_Clear_View

	public static void 
	conf_Clear_View_PlayActv
	(Activity actv) {
		// TODO Auto-generated method stub
		
		Dialog d1 = 
				Methods_dlg.dlg_Tmpl_OkCancel(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						Tags.DialogTags.DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK, 
						Tags.DialogTags.GENERIC_DISMISS);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d1.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_clear_view) + "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d1.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText("EditText");
//		tv_ItemName.setText(actv.getString(R.string.commons_import_db_name));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d1.show();
		
	}//conf_Clear_View_PlayActv
	
	public static void 
	dlg_filter_ShowList
	(Activity actv) {
		// TODO Auto-generated method stub
		boolean res;
		
		////////////////////////////////

		// get dialog

		////////////////////////////////
		Dialog d = _filter_ShowList__GetDialog(actv);

		////////////////////////////////

		// gridview

		////////////////////////////////
		res = _filter_ShowList__GridView(actv, d);

		////////////////////////////////

		// set previous string

		////////////////////////////////
		res = _filter_ShowList__SetString(actv, d);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d.show();
		
	}//filter_ShowList

	public static void 
	dlg_filter_ShowList__ImportActv
	(Activity actv) {
		// TODO Auto-generated method stub
		boolean res;
		
		////////////////////////////////
		
		// get dialog
		
		////////////////////////////////
//		Dialog d = _filter_ShowList__GetDialog(actv);
		Dialog d = _filter_ShowList__GetDialog__ImportActv(actv);
		
		////////////////////////////////
		
		// gridview
		
		////////////////////////////////
		res = _filter_ShowList__GridView(actv, d);
		
		////////////////////////////////
		
		// set previous string
		
		////////////////////////////////
//		res = _filter_ShowList__SetString(actv, d);
		res = _filter_ShowList__SetString__ImportActv(actv, d);

		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d.show();
		
	}//dlg_filter_ShowList__ImportActv
	
	public static void 
	dlg_filter_ShowLogActv
	(Activity actv) {
		// TODO Auto-generated method stub
		boolean res;
		
		////////////////////////////////
		
		// get dialog
		
		////////////////////////////////
		Dialog d = Methods_dlg._filter_ShowLog__GetDialog(actv);
//		Dialog d = _filter_ShowList__GetDialog(actv);
		
		////////////////////////////////
		
		// gridview
		
		////////////////////////////////
		res = Methods_dlg._filter_ShowLog__GridView(actv, d);
//		res = _filter_ShowList__GridView(actv, d);
		
		////////////////////////////////
		
		// set previous string
		
		////////////////////////////////
		res = _filter_ShowLog__SetString(actv, d);
//		res = _filter_ShowList__SetString(actv, d);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d.show();
		
	}//dlg_filter_ShowLogActv
	
	/******************************
		@return
			false => pref val is null<br>
	 ******************************/
	private static boolean 
	_filter_ShowList__SetString
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get pref

		////////////////////////////////
		String pref_FilterString = Methods.get_Pref_String(
						actv, 
						CONS.Pref.pname_MainActv, 
						CONS.Pref.pkey_ShowListActv_Filter_String, 
						null);
		
		if (pref_FilterString == null) {
			
			// Log
			String msg_Log = "pref_FilterString => null";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
		////////////////////////////////

		// set: text

		////////////////////////////////
		EditText et = (EditText) d.findViewById(R.id.dlg_filter_showlist_et_content);
		
		et.setText(pref_FilterString);
		
		////////////////////////////////

		// selection

		////////////////////////////////
		et.setSelection(pref_FilterString.length());
		
		return true;
		
	}//_filter_ShowList__SetString

	/******************************
		@return
			false => pref val is null<br>
	 ******************************/
	private static boolean 
	_filter_ShowList__SetString__ImportActv
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// get pref
		
		////////////////////////////////
		String pref_FilterString = Methods.get_Pref_String(
				actv, 
				CONS.Pref.pname_ImportActv, 
				CONS.Pref.pkey_ImportActv_Filter_String, 
				null);
		
		if (pref_FilterString == null) {
			
			// Log
			String msg_Log = "pref_FilterString => null";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
		////////////////////////////////
		
		// set: text
		
		////////////////////////////////
		EditText et = (EditText) d.findViewById(R.id.dlg_filter_showlist_et_content);
		
		et.setText(pref_FilterString);
		
		////////////////////////////////
		
		// selection
		
		////////////////////////////////
		et.setSelection(pref_FilterString.length());
		
		return true;
		
	}//_filter_ShowList__SetString__ImportActv
	
	/******************************
		@return
			false => pref val is null<br>
	 ******************************/
	private static boolean 
	_filter_ShowLog__SetString
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// get pref
		
		////////////////////////////////
		String pref_FilterString__ShowLogActv = Methods.get_Pref_String(
				actv, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_ShowLogActv_Filter_String, 
//				CONS.Pref.pkey_ShowListActv_Filter_String__ShowLogActv, 
//				CONS.Pref.pkey_ShowListActv_Filter_String__ShowLogActv, 
//				CONS.Pref.pkey_ShowListActv_Filter_String, 
				null);
		
		if (pref_FilterString__ShowLogActv == null) {
			
			// Log
			String msg_Log = "pref_FilterString__ShowLogActv => null";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
		////////////////////////////////
		
		// set: text
		
		////////////////////////////////
		EditText et = (EditText) d.findViewById(R.id.dlg_filter_showlogactv_ET_content);
//		EditText et = (EditText) d.findViewById(R.id.dlg_filter_showlist_et_content);
		
		et.setText(pref_FilterString__ShowLogActv);
		
		////////////////////////////////
		
		// selection
		
		////////////////////////////////
		et.setSelection(pref_FilterString__ShowLogActv.length());
		
		return true;
		
	}//_filter_ShowList__SetString__ShowLogActv
	
	/******************************
		@return
			false 1. Can't get list<br>
				2. Can't build adapter<br>
	 ******************************/
	private static boolean 
	_filter_ShowList__GridView
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// Get: GV

		////////////////////////////////
		GridView gv_Patterns =
				(GridView) d.findViewById(R.id.dlg_filter_showlist_gv);
//		(GridView) d.findViewById(R.id.dlg_filter_showlogactv_GV);

		////////////////////////////////

		// get list

		////////////////////////////////
		List<WordPattern> list_WPs = DBUtils.find_All_WP(actv);
		
		if (list_WPs == null) {
			
			String msg = "Can't get list";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////

		// sort

		////////////////////////////////
		Collections.sort(
				list_WPs, 
				new Comp_WP(
						
						CONS.Enums.SortType.WORD,
						CONS.Enums.SortOrder.ASC
				));

		Collections.sort(
				list_WPs, 
				new Comp_WP(
						
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));
		
		
		////////////////////////////////

		// Adapter

		////////////////////////////////
		CONS.ShowListActv.adp_List_WPs = new Adp_WordPatterns(
				actv,
				R.layout.list_row_gv,
				list_WPs
				);

		if (CONS.ShowListActv.adp_List_WPs == null) {
			
			String msg = "Can't build adapter";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return false;
			
		}

		////////////////////////////////

		// set: adapter

		////////////////////////////////
		gv_Patterns.setAdapter(CONS.ShowListActv.adp_List_WPs);
//		gv.setAdapter(adapter);
		
		////////////////////////////////

		// Listener

		////////////////////////////////
		gv_Patterns.setTag(Tags.DialogItemTags.GV_FILTER_SHOWLIST);
		
		// OnClick
		gv_Patterns.setOnItemClickListener(new DOI_CL(actv, d));
		
		////////////////////////////////

		// return

		////////////////////////////////
		return true;
		
	}//_filter_ShowList__GridView
	
	private static boolean 
	_filter_ShowLog__GridView
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// Get: GV
		
		////////////////////////////////
		GridView gv_Patterns =
				(GridView) d.findViewById(R.id.dlg_filter_showlogactv_GV);
//		(GridView) d.findViewById(R.id.dlg_filter_showlist_gv);
		
		////////////////////////////////
		
		// get list
		
		////////////////////////////////
		List<WordPattern> list_WPs = DBUtils.find_All_WP(actv);
		
		if (list_WPs == null) {
			
			String msg = "Can't get list";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////
		
		// sort
		
		////////////////////////////////
		Collections.sort(
				list_WPs, 
				new Comp_WP(
						
						CONS.Enums.SortType.WORD,
						CONS.Enums.SortOrder.ASC
						));
		
		Collections.sort(
				list_WPs, 
				new Comp_WP(
						
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));
		
		
		////////////////////////////////
		
		// Adapter
		
		////////////////////////////////
		CONS.ShowListActv.adp_List_WPs = new Adp_WordPatterns(
				actv,
				R.layout.list_row_gv,
				list_WPs
				);
		
		if (CONS.ShowListActv.adp_List_WPs == null) {
			
			String msg = "Can't build adapter";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////
		
		// set: adapter
		
		////////////////////////////////
		gv_Patterns.setAdapter(CONS.ShowListActv.adp_List_WPs);
//		gv.setAdapter(adapter);
		
		////////////////////////////////
		
		// Listener
		
		////////////////////////////////
		gv_Patterns.setTag(Tags.DialogItemTags.GV_FILTER_SHOWLOG);
//		gv_Patterns.setTag(Tags.DialogItemTags.GV_FILTER_SHOWLIST);
		
		// OnClick
		gv_Patterns.setOnItemClickListener(new DOI_CL(actv, d));
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return true;
		
	}//_filter_ShowLog__GridView
	

	private static Dialog 
	_filter_ShowList__GetDialog
	(Activity actv) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// setup dialog

		////////////////////////////////
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_filter_showlist);
		
		// Title
		dlg.setTitle(actv.getString(R.string.menu_showlist_filter));
		
		////////////////////////////////

		// Buttons

		////////////////////////////////
		ImageButton bt_OK	= (ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_ok);
//		Button bt_OK	= (Button) dlg.findViewById(R.id.dlg_filter_showlist_bt_ok);
		ImageButton bt_Cancel =
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_cancel);
//		Button bt_Cancel =
//				(Button) dlg.findViewById(R.id.dlg_filter_showlist_bt_cancel);
		ImageButton bt_Clear	=
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_clear);
		ImageButton bt_Reset =
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_reset);
		
		
		////////////////////////////////

		// Listeners

		////////////////////////////////
//		bt_OK.setTag(Tags.DialogTags.dlg_Filter_Timeline_OK);
		bt_OK.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_OK);
		bt_Clear.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_CLEAR);
		bt_Reset.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_RESET);
		
		bt_Cancel.setTag(Tags.DialogTags.GENERIC_DISMISS);
		
		// On touch
		bt_OK.setOnTouchListener(new DB_OTL(actv));
		bt_Clear.setOnTouchListener(new DB_OTL(actv));
		bt_Reset.setOnTouchListener(new DB_OTL(actv));
		
		bt_Cancel.setOnTouchListener(new DB_OTL(actv));
		
		// On click
		bt_OK.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Clear.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Reset.setOnClickListener(new DB_OCL(actv, dlg));
		
		bt_Cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		return dlg;
		
	}//_filter_ShowList__GetDialog

	private static Dialog 
	_filter_ShowList__GetDialog__ImportActv
	(Activity actv) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// setup dialog

		////////////////////////////////
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_filter_showlist);
		
		// Title
		dlg.setTitle(actv.getString(R.string.menu_showlist_filter));
		
		////////////////////////////////

		// Buttons

		////////////////////////////////
		ImageButton bt_OK	= (ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_ok);
//		Button bt_OK	= (Button) dlg.findViewById(R.id.dlg_filter_showlist_bt_ok);
		ImageButton bt_Cancel =
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_cancel);
//		Button bt_Cancel =
//				(Button) dlg.findViewById(R.id.dlg_filter_showlist_bt_cancel);
		ImageButton bt_Clear	=
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_clear);
		ImageButton bt_Reset =
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_reset);
		
		
		////////////////////////////////

		// Listeners

		////////////////////////////////
//		bt_OK.setTag(Tags.DialogTags.dlg_Filter_Timeline_OK);
		bt_OK.setTag(Tags.DialogTags.DLG_FILTER_ACTV_IMPORT_OK);
		bt_Clear.setTag(Tags.DialogTags.DLG_FILTER_ACTV_IMPORT_CLEAR);
		bt_Reset.setTag(Tags.DialogTags.DLG_FILTER_ACTV_IMPORT_RESET);
//		bt_OK.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_OK);
//		bt_Clear.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_CLEAR);
//		bt_Reset.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_RESET);
		
		bt_Cancel.setTag(Tags.DialogTags.GENERIC_DISMISS);
		
		// On touch
		bt_OK.setOnTouchListener(new DB_OTL(actv));
		bt_Clear.setOnTouchListener(new DB_OTL(actv));
		bt_Reset.setOnTouchListener(new DB_OTL(actv));
		
		bt_Cancel.setOnTouchListener(new DB_OTL(actv));
		
		// On click
		bt_OK.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Clear.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Reset.setOnClickListener(new DB_OCL(actv, dlg));
		
		bt_Cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		return dlg;
		
	}//_filter_ShowList__GetDialog__ImportActv 
	
	private static Dialog 
	_filter_ShowLog__GetDialog
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// setup dialog
		
		////////////////////////////////
		Dialog dlg = new Dialog(actv);
		
		//
//		dlg.setContentView(R.layout.dlg_filter_showlist);
		dlg.setContentView(R.layout.dlg_filter_showlist_showlogactv);
		
		// Title
		dlg.setTitle(actv.getString(R.string.menu_showlist_filter));
		
		////////////////////////////////
		
		// Buttons
		
		////////////////////////////////
		ImageButton bt_OK	= 
				(ImageButton) dlg.findViewById(
									R.id.dlg_filter_showlogactv_BT_ok);
		ImageButton bt_Cancel =
				(ImageButton) dlg.findViewById(
									R.id.dlg_filter_showlogactv_BT_cancel);
		ImageButton bt_Clear	=
				(ImageButton) dlg.findViewById(
									R.id.dlg_filter_showlogactv_BT_clear);
		ImageButton bt_Reset =
				(ImageButton) dlg.findViewById(
									R.id.dlg_filter_showlogactv_BT_reset);
		
		
		////////////////////////////////
		
		// Listeners
		
		////////////////////////////////
		// set tags
		bt_OK.setTag(Tags.DialogTags.DLG_FILTER_SHOWLOGACTV_OK);
		bt_Clear.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_SHOWLOGACTV_CLEAR);
		bt_Reset.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_SHOWLOGACTV_RESET);
		
		bt_Cancel.setTag(Tags.DialogTags.GENERIC_DISMISS);
		
		// On touch
		bt_OK.setOnTouchListener(new DB_OTL(actv));
		bt_Clear.setOnTouchListener(new DB_OTL(actv));
		bt_Reset.setOnTouchListener(new DB_OTL(actv));
		
		bt_Cancel.setOnTouchListener(new DB_OTL(actv));
		
		// On click
		bt_OK.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Clear.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Reset.setOnClickListener(new DB_OCL(actv, dlg));
		
		bt_Cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		return dlg;
		
	}//_filter_ShowList__GetDialog__ShowLogActv
	
	public static void 
	dlg_Admin_Patterns
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.dlg_actvmain_admin_title,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.GENERIC_DISMISS);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.generic_tv_register))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.generic_tv_edit))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.generic_tv_delete))
								.setIconID(R.drawable.menu_icon_admin_32x32_purple)
								.setTextColor_ID(R.color.purple4)
								.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							R.layout.list_row_simple_iv_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_MEMO_ADMIN_PATTERNS);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
		ll_Main.setLayoutParams(params2);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();
		
	}//dlg_Admin_Patterns

	public static void 
	register_Patterns
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Dialog d2 = 
				Methods_dlg.dlg_Template_OkCancel_SecondDialog(
						actv, d1,
						R.layout.dlg_tmpl_ok_cancel, 
						R.string.generic_tv_register, 
						
						R.id.dlg_tmpl_ok_cancel_bt_ok, 
						Tags.DialogTags.DLG_REGISTER_PATTERNS_OK, 
						
						R.id.dlg_tmpl_ok_cancel_bt_cancel, 
						Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG
				);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();
		
		
	}//register_Patterns

	public static void 
	dlg_ShowListActv_LongClick
	(Activity actv, Memo memo) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.dlg_actvmain_admin_title,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.GENERIC_DISMISS);
		
		/****************************
		* 2. Prep => List
		****************************/
		List<ListItem> list = new ArrayList<ListItem>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.generic_tv_edit))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.generic_tv_delete))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		
		list.add(new ListItem.Builder()
				.setText(actv.getString(
						R.string.commons_lbl_copy_to_clipboard))
						.setIconID(R.drawable.menu_icon_admin_32x32_purple)
						.setTextColor_ID(R.color.purple4)
						.build());
		
		////////////////////////////////

		// voice memo

		////////////////////////////////
		String text = memo.getText();
		
		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
		Matcher m = p.matcher(text);
		
		// Log
		msg_Log = "CONS.RecActv.fmt_FileName_PlayMemo => "
						+ CONS.RecActv.fmt_FileName_PlayMemo;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "text => " + text;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		if (m.find()) {
//			if (m.matches()) {
			
			list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.generic_tv_play))
					.setIconID(R.drawable.menu_icon_admin_32x32_green)
					.setTextColor_ID(R.color.green4)
					.build());
			
		} else {
			
			///////////////////////////////////
			//
			// other pattern
			//
			///////////////////////////////////
			p = Pattern.compile("^@recorded_(\\d{4}.+wav)");
			m = p.matcher(text);
//			"^@(\\d{4}.+wav)"
			
			if (m.find()) {
//				if (m.matches()) {
				
				list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.generic_tv_play))
						.setIconID(R.drawable.menu_icon_admin_32x32_green)
						.setTextColor_ID(R.color.green4)
						.build());
				
			} else {

			
			///////////////////////////////////
			//
			// no matchi
			//
			///////////////////////////////////
			// Log
			msg_Log = "text => no match";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			}
			
		}
		
		////////////////////////////////
		
		// photo memo
		
		////////////////////////////////
		text = memo.getText();
		
		p = Pattern.compile(CONS.RecActv.fmt_FileName_PhotoMemo);
//		p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
		m = p.matcher(text);
		
		// Log
		msg_Log = "CONS.RecActv.fmt_FileName_PhotoMemo => "
				+ CONS.RecActv.fmt_FileName_PhotoMemo;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "text => " + text;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		if (m.find()) {
//			if (m.matches()) {
			
			list.add(new ListItem.Builder()
			.setText(actv.getString(
					R.string.generic_tv_view))
					.setIconID(R.drawable.menu_icon_admin_32x32_green)
					.setTextColor_ID(R.color.green4)
					.build());
			
		} else {
			
			// Log
			msg_Log = "text => no match";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							R.layout.list_row_simple_iv_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_SHOWLIST_LV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1, memo));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
		ll_Main.setLayoutParams(params2);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();		
		
	}//dlg_ShowListActv_LongClick

	public static void 
	dlg_ImportActv_LongClick
	(Activity actv, AudioMemo audio_item) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// vars
		
		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////
		
		// dlg
		
		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
				actv,
				R.layout.dlg_tmpl_cancel_lv,
				R.string.dlg_actvmain_admin_title,
				
				R.id.dlg_tmpl_cancel_lv_bt_cancel,
				Tags.DialogTags.GENERIC_DISMISS);
		
		/****************************
		 * 2. Prep => List
		 ****************************/
		List<ListItem> list = new ArrayList<ListItem>();
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.generic_tv_edit))
							.setIconID(R.drawable.menu_icon_admin_32x32)
							.setTextColor_ID(R.color.blue1)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.generic_tv_delete))
							.setIconID(R.drawable.menu_icon_admin_32x32_brown)
							.setTextColor_ID(R.color.black)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.commons_lbl_copy_to_clipboard))
							.setIconID(R.drawable.menu_icon_admin_32x32_purple)
							.setTextColor_ID(R.color.purple4)
							.build());

		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.generic_tv_play))
							.setIconID(R.drawable.menu_icon_admin_32x32_green)
							.setTextColor_ID(R.color.green4)
							.build());

		/****************************
		 * 3. Adapter: rows => image file + text
		 ****************************/
		Adp_ListItems adapter = new Adp_ListItems(
				actv,
				R.layout.list_row_simple_iv_1,
				list
				);
		
		/****************************
		 * 4. Set adapter
		 ****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		 * 5. Set listener to list
		 ****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_IMPORT_LV);
//		lv.setTag(Tags.DialogItemTags.ACTV_SHOWLIST_LV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1, audio_item));
		
		/***************************************
		 * Modify: Button layout
		 ***************************************/
		LinearLayout llButton =
				(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);
		
		////////////////////////////////
		
		// get: screen size
		
		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
				.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		////////////////////////////////
		
		// linear layot: main
		
		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)
		
		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
		ll_Main.setLayoutParams(params2);
		
		/****************************
		 * 6. Show dialog
		 ****************************/
		d1.show();		
		
	}//dlg_ImportActv_LongClick
	
	public static void 
	conf_Delete_Memo
	(Activity actv, Dialog d1, Memo memo) {
		// TODO Auto-generated method stub
		
		Dialog d2 = 
				Methods_dlg.dlg_Template_OkCancel_SecondDialog(
						actv, d1, memo,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						Tags.DialogTags.DLG_CONF_DELETE_MEMO_OK, 
						
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG
				);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.generic_tv_delete)
								+ "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		String msg = null;
		
		if (memo.getText().length() > CONS.ShowListActv.length_Conf_Message) {
			
			msg = memo.getText().substring(0, CONS.ShowListActv.length_Conf_Message);
			
		} else {
			
			msg = memo.getText();

		}
		
		tv_ItemName.setText(msg);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();		
				
	}//conf_Delete_Memo

	public static void 
	dlg_Admin_Patterns_GV
	(Activity actv, WordPattern wp) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.dlg_actvmain_admin_title,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.GENERIC_DISMISS);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.generic_tv_edit))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.generic_tv_delete))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							R.layout.list_row_simple_iv_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_MEMO_ADMIN_PATTERNS_GV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1, wp));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
		ll_Main.setLayoutParams(params2);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();
		
	}//dlg_Admin_Patterns_GV

	public static void 
	conf_Delete_Pattern
	(Activity actv, Dialog d1, WordPattern wp) {
		// TODO Auto-generated method stub
		
		Dialog d2 = 
				Methods_dlg.dlg_Template_OkCancel_SecondDialog(
						actv, d1, wp,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_tv_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						Tags.DialogTags.DLG_CONF_DELETE_PATTERN_OK, 
						
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG
				);
		
		////////////////////////////////

		// view: message

		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
								R.string.generic_tv_delete)
								+ "?");
		
		////////////////////////////////

		// view: item name

		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(wp.getWord());
		
		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();		
		
	}//conf_Delete_Pattern

	public static void
	dlg_ShowMessage_ThirdDialog
	(Activity actv, 
			String message, Dialog dlg1, Dialog dlg2, int colorID) {
		
		Dialog dlg3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
				actv, dlg1, dlg2,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		tv_Message.setTextColor(Color.WHITE);
		
		tv_Message.setText(message);
		
		dlg3.show();
		
	}

	public static void 
	dlg_EditMemo
	(Activity actv) {
		// TODO Auto-generated method stub
		
		int res_i;
		String msg_Log;
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog(actv);
		
		/******************************
			validate: null
		 ******************************/
		if (d1 == null) {
			
			String msg = "dlg => null";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		}
		
		// Log
		msg_Log = "dlg => obtained";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// listviews

		////////////////////////////////
		res_i = _dlg_AddMemo_Set_LV_1(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);

		// LV 2
		res_i = _dlg_AddMemo_Set_LV_2(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 3
		res_i = _dlg_AddMemo_Set_LV_3(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		////////////////////////////////

		// set: listeners

		////////////////////////////////
		res_i = _dlg_AddMemo_Set_Listeners_LVs(actv, d1);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d1.show();

		
	}//dlg_EditMemo

	public static void 
	dlg_EditMemo__PlayerActv
	(Activity actv) {
		// TODO Auto-generated method stub
		
		int res_i;
		String msg_Log;
		
		////////////////////////////////
		
		// dlg
		
		////////////////////////////////
		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog__PlayerActv(actv);
//		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog(actv);

		/******************************
			validate: null
		 ******************************/
		if (d1 == null) {
			
			String msg = "dlg => null";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		}
		
		// Log
		msg_Log = "dlg => obtained";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// listviews
		
		////////////////////////////////
		res_i = _dlg_AddMemo_Set_LV_1(actv, d1);

		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 2
		res_i = _dlg_AddMemo_Set_LV_2(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 3
		res_i = _dlg_AddMemo_Set_LV_3(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		////////////////////////////////
		
		// set: listeners
		
		////////////////////////////////
		res_i = _dlg_AddMemo_Set_Listeners_LVs(actv, d1);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d1.show();
		
		
	}//dlg_EditMemo__PlayerActv
	
	public static void 
	dlg_EditMemo_ImageActv
	(Activity actv) {
		// TODO Auto-generated method stub
		
		int res_i;
		String msg_Log;
		
		////////////////////////////////
		
		// dlg
		
		////////////////////////////////
		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog_ImageActv(actv);
//		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog(actv);
		
		/******************************
			validate: null
		 ******************************/
		if (d1 == null) {
			
			String msg = "dlg => null";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		}
		
		// Log
		msg_Log = "dlg => obtained";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// listviews
		
		////////////////////////////////
		res_i = _dlg_AddMemo_Set_LV_1(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 2
		res_i = _dlg_AddMemo_Set_LV_2(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 3
		res_i = _dlg_AddMemo_Set_LV_3(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		////////////////////////////////
		
		// set: listeners
		
		////////////////////////////////
		res_i = _dlg_AddMemo_Set_Listeners_LVs(actv, d1);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d1.show();
		
		
	}//dlg_EditMemo_ImageActv
	
	public static void 
	dlg_EditMemo_ImageActv_From_ShowList
	(Activity actv) {
		// TODO Auto-generated method stub
		
		int res_i;
		String msg_Log;
		
		// Log
		msg_Log = "dlg_EditMemo_ImageActv_From_ShowList";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// dlg
		
		////////////////////////////////
		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog_ImageActv_From_ShowList(actv);
//		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog_ImageActv(actv);
//		Dialog d1 = Methods_dlg._dlg_AddMemo_GetDialog(actv);
		
		/******************************
			validate: null
		 ******************************/
		if (d1 == null) {
			
			String msg = "dlg => null";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return;
			
		}
		
		// Log
		msg_Log = "dlg => obtained";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// listviews
		
		////////////////////////////////
		res_i = _dlg_AddMemo_Set_LV_1(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 2
		res_i = _dlg_AddMemo_Set_LV_2(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		// LV 3
		res_i = _dlg_AddMemo_Set_LV_3(actv, d1);
		
		// Log
		msg_Log = "res_i => " + res_i;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		dlg = _dlg_AddMemo_Set_Listviews(actv, dlg);
		
		////////////////////////////////
		
		// set: listeners
		
		////////////////////////////////
		res_i = _dlg_AddMemo_Set_Listeners_LVs(actv, d1);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d1.show();
		
		
	}//dlg_EditMemo_ImageActv_From_ShowList
	
	
	private static int 
	_dlg_AddMemo_Set_Listeners_LVs
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// lv 1

		////////////////////////////////
		ListView lv_1 = (ListView) d.findViewById(R.id.dlg_add_memos_lv1);
		
		lv_1.setTag(Tags.DialogItemTags.ACTV_PLAY_ADD_MEMO_LV_1);
		
		lv_1.setOnItemClickListener(new DOI_CL(actv, d));
		
//		lv_1.setOnItemLongClickListener(new DLOI_LCL(actv));

		////////////////////////////////
		
		// lv 2
		
		////////////////////////////////
		ListView lv_2 = (ListView) d.findViewById(R.id.dlg_add_memos_lv2);
		
		lv_2.setTag(Tags.DialogItemTags.ACTV_PLAY_ADD_MEMO_LV_2);
		
		lv_2.setOnItemClickListener(new DOI_CL(actv, d));
		
//		lv_2.setOnItemLongClickListener(new DLOI_LCL(actv));
		
		////////////////////////////////
		
		// lv 3
		
		////////////////////////////////
		ListView lv_3 = (ListView) d.findViewById(R.id.dlg_add_memos_lv3);
		
		lv_3.setTag(Tags.DialogItemTags.ACTV_PLAY_ADD_MEMO_LV_3);
		
		lv_3.setOnItemClickListener(new DOI_CL(actv, d));
		
//		lv_3.setOnItemLongClickListener(new DLOI_LCL(actv));
		
		
		return 0;
		
	}//_dlg_AddMemo_Set_Listeners

	/******************************
		@return
			-1 build list => null<br>
			-2 adapter 1 => null<br>
			1 list => set<br>
	 ******************************/
	private static int
	_dlg_AddMemo_Set_LV_1
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////

		// lv 1

		////////////////////////////////
		////////////////////////////////

		// build list

		////////////////////////////////
		CONS.MemoActv.list_WP_1 = DBUtils.find_All_WP_symbols(actv);
//		CONS.IMageActv.list_WP_1 = DBUtils.find_All_WP_symbols(actv);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.MemoActv.list_WP_1 == null) {
			
			return -1;
			
		}
		
		// Log
		msg_Log = "CONS.MemoActv.list_WP_1.size() => " 
						+ CONS.MemoActv.list_WP_1.size();
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		//debug
//		for (WordPattern wp : CONS.MemoActv.list_WP_1) {
//			
//			// Log
//			msg_Log = String.format(
//							Locale.JAPAN,
//							"(%d) %s (used = %d)", 
//							wp.getDb_Id(), wp.getWord(), wp.getUsed());
//			
//			Log.d("Methods_dlg.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//		}
		
		////////////////////////////////

		// sort

		////////////////////////////////
		Collections.sort(
				CONS.MemoActv.list_WP_1, 
						new Comp_WP(
								
								CONS.Enums.SortType.WORD,
								CONS.Enums.SortOrder.ASC
						));

		Collections.sort(
				CONS.MemoActv.list_WP_1, 
				new Comp_WP(
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));

		////////////////////////////////

		// adapter

		////////////////////////////////
		// Log
		msg_Log = "Constructing an adapter...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.MemoActv.adp_WPList_1 = new Adp_WordPatterns(
//				CONS.MemoActv.adp_WPList_1 = new ArrayAdapter<WordPattern>(
				actv,
				R.layout.list_row_gv,
				CONS.MemoActv.list_WP_1
				);

		/******************************
			validate
		 ******************************/
		if (CONS.MemoActv.adp_WPList_1 == null) {
			
			// Log
			msg_Log = "CONS.MemoActv.adp_WPList_1 => null";
			Log.e("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter 1 => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return -2;
			
		}
		
		////////////////////////////////

		// set adapter

		////////////////////////////////
		ListView lv_1 = (ListView) d.findViewById(R.id.dlg_add_memos_lv1);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_1.setAdapter(CONS.MemoActv.adp_WPList_1);
		
		
		return 1;
		
	}//_dlg_AddMemo_Set_Listviews

	/******************************
		@return
			-1 build list => null<br>
			-2 adapter 1 => null<br>
			1 list => set<br>
	 ******************************/
	private static int
	_dlg_AddMemo_Set_LV_2
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// vars
		
		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////
		
		// lv 2
		
		////////////////////////////////
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		CONS.MemoActv.list_WP_2 = DBUtils.find_All_WP_tags(actv);
//		CONS.IMageActv.list_WP_1 = DBUtils.find_All_WP_symbols(actv);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.MemoActv.list_WP_2 == null) {
			
			return -1;
			
		}
		
		// Log
		msg_Log = "CONS.MemoActv.list_WP_2.size() => " 
				+ CONS.MemoActv.list_WP_2.size();
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// sort
		
		////////////////////////////////
		Collections.sort(
				CONS.MemoActv.list_WP_2, 
				new Comp_WP(
						
						CONS.Enums.SortType.WORD,
						CONS.Enums.SortOrder.ASC
						));
		
		Collections.sort(
				CONS.MemoActv.list_WP_2, 
				new Comp_WP(
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));
		
		////////////////////////////////
		
		// adapter
		
		////////////////////////////////
		// Log
		msg_Log = "Constructing an adapter...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.MemoActv.adp_WPList_2 = new Adp_WordPatterns(
//				CONS.MemoActv.adp_WPList_1 = new ArrayAdapter<WordPattern>(
				actv,
				R.layout.list_row_gv,
				CONS.MemoActv.list_WP_2
				);
		
		/******************************
			validate
		 ******************************/
		if (CONS.MemoActv.adp_WPList_2 == null) {
			
			// Log
			msg_Log = "CONS.MemoActv.adp_WPList_2 => null";
			Log.e("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter 2 => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return -2;
			
		}
		
		////////////////////////////////
		
		// set adapter
		
		////////////////////////////////
		ListView lv_2 = (ListView) d.findViewById(R.id.dlg_add_memos_lv2);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_2.setAdapter(CONS.MemoActv.adp_WPList_2);
		
		
		return 1;
		
	}//_dlg_AddMemo_Set_Listviews
	
	/******************************
		@return
			-1 build list => null<br>
			-2 adapter 1 => null<br>
			1 list => set<br>
	 ******************************/
	private static int
	_dlg_AddMemo_Set_LV_3
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// vars
		
		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////
		
		// lv 3
		
		////////////////////////////////
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		CONS.MemoActv.list_WP_3 = DBUtils.find_All_WP_literals(actv);
//		CONS.IMageActv.list_WP_1 = DBUtils.find_All_WP_symbols(actv);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.MemoActv.list_WP_3 == null) {
			
			return -1;
			
		}
		
		// Log
		msg_Log = "CONS.MemoActv.list_WP_3.size() => " 
				+ CONS.MemoActv.list_WP_3.size();
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// sort
		
		////////////////////////////////
		Collections.sort(
				CONS.MemoActv.list_WP_3, 
				new Comp_WP(
						
						CONS.Enums.SortType.WORD,
						CONS.Enums.SortOrder.ASC
						));
		
		Collections.sort(
				CONS.MemoActv.list_WP_3, 
				new Comp_WP(
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));
		
		////////////////////////////////
		
		// adapter
		
		////////////////////////////////
		// Log
		msg_Log = "Constructing an adapter...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.MemoActv.adp_WPList_3 = new Adp_WordPatterns(
//				CONS.MemoActv.adp_WPList_1 = new ArrayAdapter<WordPattern>(
				actv,
				R.layout.list_row_gv,
				CONS.MemoActv.list_WP_3
				);
		
		/******************************
			validate
		 ******************************/
		if (CONS.MemoActv.adp_WPList_3 == null) {
			
			// Log
			msg_Log = "CONS.MemoActv.adp_WPList_3 => null";
			Log.e("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter 3 => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return -2;
			
		}
		
		////////////////////////////////
		
		// set adapter
		
		////////////////////////////////
		ListView lv_3 = (ListView) d.findViewById(R.id.dlg_add_memos_lv3);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_3.setAdapter(CONS.MemoActv.adp_WPList_3);
		
		
		return 1;
		
	}//_dlg_AddMemo_Set_Listviews

	public static Dialog 
	_dlg_AddMemo_GetDialog
	(Activity actv) {
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_add_memos);
		
		// Title
		dlg.setTitle(R.string.commons_lbl_edit_memo);
		
		/*----------------------------
		 * 1-2. Set text to edit text
			----------------------------*/
//		TI ti = DBUtils.get_TI_From_DbId(actv, db_Id);
//		TI ti = DBUtils.get_TI_From_FileId(actv, db_Id);
		
		EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
		
		if (CONS.PlayActv.memo != null) {
			
			String memo = CONS.PlayActv.memo.getText();
			
			et.setText(memo);
			
			et.setSelection(memo.length());
			
		} else {//if (ti.getMemo() != null)
			
			et.setSelection(0);
			
		}//if (ti.getMemo() != null)
		
		////////////////////////////////

		// Add listeners: OnTouch

		////////////////////////////////
		
		Button btn_add = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_add);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_add_memos_cancel);
		
//		Button btn_patterns = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_patterns);
		
		// Tags
//		btn_add.setTag(DialogTags.dlg_add_memos_bt_add);
		btn_add.setTag(DialogTags.DLG_EDIT_MEMOS_BT_OK);
		btn_cancel.setTag(DialogTags.GENERIC_DISMISS);
//		btn_cancel.setTag(DialogTags.dlg_generic_dismiss);
		
//		btn_patterns.setTag(DialogTags.dlg_add_memos_bt_patterns);
//		btn_patterns.setTag(DialogTags.DLG_ADD_MEMOS_BT_PATTERNS);
		
		//
		btn_add.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
//		btn_patterns.setOnTouchListener(new DB_OTL(actv, dlg));
		
		////////////////////////////////

		// Add listeners => OnClick

		////////////////////////////////
		btn_add.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
//		btn_patterns.setOnClickListener(new DB_OCL(actv, dlg));

		
		return dlg;
		
	}//public static Dialog dlg_addMemo(Activity actv, long file_id, String tableName)

	public static Dialog 
	_dlg_AddMemo_GetDialog__PlayerActv
	(Activity actv) {
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_add_memos);
		
		// Title
		dlg.setTitle(R.string.commons_lbl_edit_memo);
		
		/*----------------------------
		 * 1-2. Set text to edit text
			----------------------------*/
//		TI ti = DBUtils.get_TI_From_DbId(actv, db_Id);
//		TI ti = DBUtils.get_TI_From_FileId(actv, db_Id);
		
		EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
		
		if (CONS.PlayerActv.audio_memo != null) {
			
			String memo = CONS.PlayerActv.audio_memo.getText();
			
			et.setText(memo);
			
			et.setSelection(memo.length());
			
		} else {//if (ti.getMemo() != null)
			
			et.setSelection(0);
			
		}//if (ti.getMemo() != null)
		
		////////////////////////////////
		
		// Add listeners: OnTouch
		
		////////////////////////////////
		
		Button btn_add = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_add);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_add_memos_cancel);
		
//		Button btn_patterns = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_patterns);
		
		// Tags
//		btn_add.setTag(DialogTags.dlg_add_memos_bt_add);
		btn_add.setTag(DialogTags.DLG_EDIT_MEMOS_BT_OK__PLAYER_ACTV);
//		btn_add.setTag(DialogTags.DLG_EDIT_MEMOS_BT_OK);
		btn_cancel.setTag(DialogTags.GENERIC_DISMISS);
//		btn_cancel.setTag(DialogTags.dlg_generic_dismiss);
		
//		btn_patterns.setTag(DialogTags.dlg_add_memos_bt_patterns);
//		btn_patterns.setTag(DialogTags.DLG_ADD_MEMOS_BT_PATTERNS);
		
		//
		btn_add.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
//		btn_patterns.setOnTouchListener(new DB_OTL(actv, dlg));
		
		////////////////////////////////
		
		// Add listeners => OnClick
		
		////////////////////////////////
		btn_add.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
//		btn_patterns.setOnClickListener(new DB_OCL(actv, dlg));
		
		
		return dlg;
		
	}//_dlg_AddMemo_GetDialog__PlayerActv
	
	public static Dialog 
	_dlg_AddMemo_GetDialog_ImageActv
	(Activity actv) {
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_add_memos);
		
		// Title
		dlg.setTitle(R.string.commons_lbl_edit_memo);
		
		/*----------------------------
		 * 1-2. Set text to edit text
			----------------------------*/
		EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
		
		if (CONS.IMageActv.ti != null) {
			
			String memo = CONS.IMageActv.ti.getMemo();
			
			/******************************
				fail-safe
			 ******************************/
			if (memo == null) {
				
				// Log
				String msg_Log = "memo => null";
				Log.d("Methods_dlg.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				memo = "";
				
			}
			
			et.setText(memo);
			
			et.setSelection(memo.length());
			
		} else {//if (ti.getMemo() != null)
			
			et.setSelection(0);
			
		}//if (ti.getMemo() != null)
		
		////////////////////////////////
		
		// Add listeners: OnTouch
		
		////////////////////////////////
		
		Button btn_add = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_add);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_add_memos_cancel);
		
		// Tags
		btn_add.setTag(DialogTags.DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK);
		btn_cancel.setTag(DialogTags.GENERIC_DISMISS);
		
		//
		btn_add.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		////////////////////////////////
		
		// Add listeners => OnClick
		
		////////////////////////////////
		btn_add.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		return dlg;
		
	}//_dlg_AddMemo_GetDialog_ImageActv
	
	public static Dialog 
	_dlg_AddMemo_GetDialog_ImageActv_From_ShowList
	(Activity actv) {
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_add_memos);
		
		// Title
		dlg.setTitle(R.string.commons_lbl_edit_memo);
		
		/*----------------------------
		 * 1-2. Set text to edit text
			----------------------------*/
		EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
		
		if (CONS.IMageActv.memo != null) {
			
			String memo = CONS.IMageActv.memo.getText();
//			if (CONS.IMageActv.ti != null) {
//				
//				String memo = CONS.IMageActv.ti.getMemo();
			
			et.setText(memo);
			
			et.setSelection(memo.length());
			
		} else {//if (ti.getMemo() != null)
			
			et.setSelection(0);
			
		}//if (ti.getMemo() != null)
		
		////////////////////////////////
		
		// Add listeners: OnTouch
		
		////////////////////////////////
		
		Button btn_add = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_add);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_add_memos_cancel);
		
		// Tags
		btn_add.setTag(DialogTags.DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK);
		btn_cancel.setTag(DialogTags.GENERIC_DISMISS);
		
		//
		btn_add.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		////////////////////////////////
		
		// Add listeners => OnClick
		
		////////////////////////////////
		btn_add.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		return dlg;
		
	}//_dlg_AddMemo_GetDialog_ImageActv_From_ShowList

	public static void 
	dlg_Filter_History
	(Activity actv) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// dialog

		////////////////////////////////
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.dlg_actvmain_admin_title,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.GENERIC_DISMISS);
		
		////////////////////////////////

		// Prep => List

		////////////////////////////////
		int limit = 50;
		
		CONS.ShowListActv.list_FS = 
					DBUtils.find_All_FS(actv, CONS.Enums.SortOrder.DESC, limit);
//		list.add(new ListItem.Builder()
//						.setText(actv.getString(
//								R.string.dlg_actvmain_admin_item_see_log))
//								.setIconID(R.drawable.menu_icon_admin_32x32_yellow)
//								.setTextColor_ID(R.color.yellow_dark)
//								.build());
		
		////////////////////////////////

		// Adapter

		////////////////////////////////
		CONS.ShowListActv.adp_List_FHs = new Adp_FHs(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_filter_history,
							//android.R.layout.simple_list_item_1,
							CONS.ShowListActv.list_FS
		);

		/******************************
			validate
		 ******************************/
		if (CONS.ShowListActv.adp_List_FHs == null) {
			
			String msg = "can't build adapter";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		// Log
		msg_Log = "getOp_label() => " + CONS.ShowListActv.list_FS.get(0).getOp_label();
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(CONS.ShowListActv.adp_List_FHs);
		
//		// Log
//		msg_Log = "lv.getChildAt(0).getHeight() => "
//					+ lv.getChildAt(0).getHeight();
		
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		DisplayMetrics displayMetrics = 
					actv.getResources().getDisplayMetrics();

		int disp_Height = displayMetrics.heightPixels;

		int max_LV_Height = disp_Height * CONS.MemoActv.layout_MemoActv_LV_Height / 100;
		
		int lv_Height;
		
		int min_Adp_Size = 4;
		
		// Log
		msg_Log = "CONS.ShowListActv.adp_List_FHs.getCount() => "
					+ CONS.ShowListActv.adp_List_FHs.getCount();
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		if (CONS.ShowListActv.adp_List_FHs.getCount() > min_Adp_Size) {
			
			// Log
			msg_Log = "adapter => more than min";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			lv_Height = max_LV_Height;
			
		} else {

			lv_Height = LayoutParams.WRAP_CONTENT;
			
		}
		
		int lv_Width = 300;
		
		LinearLayout.LayoutParams params_LV =
				new LinearLayout.LayoutParams(
						lv_Width,
//								LayoutParams.WRAP_CONTENT,
								lv_Height);
		
		params_LV.gravity = Gravity.CENTER_HORIZONTAL;
		
		lv.setLayoutParams(params_LV);

		
//		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_SHOWLIST_FILTER_HISTORY_LV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
//		DisplayMetrics displayMetrics = actv.getResources()
//                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LayoutParams.WRAP_CONTENT,
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
		ll_Main.setLayoutParams(params2);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();
		
	}//dlg_Filter_History
	
	
}//public class Methods_dialog
