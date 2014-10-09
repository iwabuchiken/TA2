package ta2.adapters;

import java.util.ArrayList;
import java.util.List;

import ta2.items.TI;
import ta2.main.R;
import ta2.utils.CONS;
import ta2.utils.Methods;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Adp_TIList extends ArrayAdapter<TI> {

	/*--------------------------------------------------------
	 * Class fields
		--------------------------------------------------------*/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	//
//	CONS.Enums.MoveMode moveMode = null;
//	Methods.MoveMode moveMode = Methods.MoveMode.OFF;

//	public static ArrayList<Integer> checkedPositions;
	
	/*--------------------------------------------------------
	 * Constructor
		--------------------------------------------------------*/
	//
	public Adp_TIList(Context con, int resourceId, List<TI> items) {
		// Super
		super(con, resourceId, items);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TIListAdapter(Context con, int resourceId, List<TI> items)


//	public Adp_TIList(Context con, int resourceId, List<TI> items, 
//											CONS.Enums.MoveMode moveMode) {
//		// Super
//		super(con, resourceId, items);
//
//		// Context
//		this.con = con;
//		this.moveMode = moveMode;
//
//		// Inflater
//		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		
//
//	}//public TIListAdapter(Context con, int resourceId, List<TI> items, CONS.MoveMode moveMode)

	/*--------------------------------------------------------
	 * Methods
		--------------------------------------------------------*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/*----------------------------
		 * Steps
		 * 0. View
		 * 1. Set layout
		 * 2. Get view
		 * 3. Get item
		 * 4. Get bitmap
		 * 5. Get memo, or, file name
			----------------------------*/
    	////////////////////////////////

		// setup: positions

		////////////////////////////////
		CONS.TNActv.list_Pos_Prev = CONS.TNActv.list_Pos_Current;
		
		CONS.TNActv.list_Pos_Current = position;
    	
    	/*----------------------------
		 * 0. View
			----------------------------*/
    	View v = null;

//    	if (moveMode == null || moveMode == CONS.Enums.MoveMode.OFF) {
    		
    		v = move_ModeOff(v, position, convertView);
    		
//    	} else {
//
//			v = move_mode_on(v, position, convertView);
//			
//    	}//if (moveMode == null || moveMode == Methods.MoveMode.OFF)
    	
		
		
//    	return null;
		return v;
    }//public View getView(int position, View convertView, ViewGroup parent)


	private View move_mode_on(View v, int position, View convertView) {
		
		/*----------------------------
		 * 2. ON
			----------------------------*/
		/*----------------------------
		 * 2.1. Set layout
		 * 2.2. Get view
		 * 2.3. Get item
		 * 
		 * 2.4. Get bitmap
		 * 2.5. Get memo, or, file name
		 * 2.6. CheckedBox => Set listener
		 * 
		 * 2.7. Return
			----------------------------*/
		
		// Log
		Log.d("[" + "TIListAdapter.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "move_mode => on");
		
    	/*----------------------------
		 * 2.1. Set layout
			----------------------------*/
    	if (convertView != null) {

    		v = convertView;
    		
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row_checked_box, null);

		}//if (convertView != null)

    	/*----------------------------
		 * 2.2. Get view
			----------------------------*/
    	ImageView iv = (ImageView) v.findViewById(R.id.list_row_checked_box_iv_thumbnail);

    	/*----------------------------
		 * 2.3. Get item
			----------------------------*/
    	TI ti = getItem(position);

    	/*----------------------------
		 * 2.4. Get bitmap
			----------------------------*/
    	// ContentResolver
    	ContentResolver cr = con.getContentResolver();
    	
    	// Bitmap
    	Bitmap bmp = 
				MediaStore.Images.Thumbnails.getThumbnail(
							cr, 
							ti.getFileId(), 
							MediaStore.Images.Thumbnails.MICRO_KIND, 
							null);
    	
    	/******************************
			validate: null
		 ******************************/
		if (bmp == null) {
			
			//REF http://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap answered Jun 14 '10 at 8:32
			bmp = BitmapFactory.decodeResource(con.getResources(), R.drawable.ic_launcher);
			
		}
    	
    	// Set bitmap
    	iv.setImageBitmap(bmp);
    	
    	/*----------------------------
		 * 2.5. Get memo, or, file name
			----------------------------*/
		TextView tv = (TextView) v.findViewById(R.id.list_row_checked_box_textView1);
		
		tv.setText(ti.getFile_name());
		
//		// move_mode
//		if (TNActv.move_mode == true &&
//				TNActv.checkedPositions.contains((Integer) position)) {
//			
//			tv.setBackgroundColor(Color.BLUE);
//			
//		} else {//if (ThumbnailActivity.move_mode == true)
//				
//				tv.setBackgroundColor(Color.BLACK);
//				
//		}
		
		TextView tv_memo = (TextView) v.findViewById(R.id.list_row_checked_box_textView2);
		
		String memo = ti.getMemo();
		
		if (memo != null) {
			tv_memo.setText(memo);
			
		} else {//if (memo)

			tv_memo.setText("");
		}//if (memo)
		
		/*----------------------------
		 * 2.6. CheckedBox => Set listener
		 * 		1. Set up
		 * 		2. OnClick
		 * 		3. 
			----------------------------*/
		CheckBox cb = (CheckBox) v.findViewById(R.id.list_row_checked_box_checkBox1);
		
//		cb.setTag(Tags.ButtonTags.tilist_cb);
		
//		if (TNActv.checkedPositions.contains((Integer) position)) {
//			
//			cb.setChecked(true);
//			
//			// Log
//			Log.d("TIListAdapter.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + "]", 
//					"cb => true" + "(position => " + TNActv.checkedPositions.size() + ")");
//			
//			
//		} else {//if (ThumbnailActivity.checkedPositions.contains((Integer) position)
//			
//			cb.setChecked(false);
//			
//		}//if (ThumbnailActivity.checkedPositions.contains((Integer) position)
		
//		cb.setOnClickListener(new ButtonOnClickListener((Activity) con, position));
//		
//		
//		cb.setOnLongClickListener(
//					new CustomOnLongClickListener(
//									(Activity) con, position, Tags.ItemTags.tilist_checkbox));

		/*----------------------------
		 * 2.7. Return
			----------------------------*/
		return v;
		
	}//private View move_mode_on()


	private View move_ModeOff(View v, int position, View convertView) {
		
		/*----------------------------
		 * 1. Set layout
		 * 2. Get view
		 * 3. Get item
		 * 
		 * 3-2. Set background
		 * 
		 * 4. Get bitmap
		 * 5. Get memo, or, file name
		 * 
		 * 5-2. Get => last viewed
		 * 
		 * 6. Return
			----------------------------*/
		
    	/*----------------------------
		 * 1. Set layout
			----------------------------*/
		
    	if (convertView != null) {
			
    		v = convertView;
    		
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row, null);
			
		}//if (convertView != null)

    	/*----------------------------
		 * 2. Get view
			----------------------------*/
    	ImageView iv = (ImageView) v.findViewById(R.id.iv_thumbnail);

    	TextView tv = (TextView) v.findViewById(R.id.textView1);
//    	TextView tv = (TextView) v.findViewById(R.id.textView1);
    	
    	/*----------------------------
		 * 3. Get item
			----------------------------*/
    	TI ti = (TI) getItem(position);

    	////////////////////////////////

		// Set background

		////////////////////////////////
		int savedPosition = Methods.get_Pref_Int(
								(Activity)con, 
								CONS.Pref.pname_PhotoActv, 
								CONS.Pref.pkey_PhotoActv_Current_Position, 
								CONS.Pref.dflt_IntExtra_value);
		
		if (savedPosition == position) {
			
			tv.setBackgroundResource(R.color.gold2);
			tv.setTextColor(Color.BLACK);
			
		} else if (savedPosition == -1) {//if (savedPosition == position)
			
		} else {//if (savedPosition == position)
			
			tv.setBackgroundColor(Color.BLACK);
			tv.setTextColor(Color.WHITE);
			
		}//if (savedPosition == position)

    	
    	
    	/*----------------------------
		 * 4. Get bitmap
			----------------------------*/
    	// ContentResolver
    	ContentResolver cr = con.getContentResolver();
    	
    	// Bitmap
    	Bitmap bmp = 
				MediaStore.Images.Thumbnails.getThumbnail(
							cr, 
							ti.getFileId(), 
							MediaStore.Images.Thumbnails.MICRO_KIND, 
							null);
    	
    	// Set bitmap
    	iv.setImageBitmap(bmp);

    	////////////////////////////////

		// file name

		////////////////////////////////
		tv.setText(ti.getFile_name());
		
//		String pref_FontSize = Methods.get_Pref_String(
//				(Activity)con, 
//				CONS.Pref.pname_MainActv, 
//				((Activity)con).getString(R.string.prefs_tnactv_list_font_size_key), 
//				null);
//		
//		if (pref_FontSize != null && !pref_FontSize.equals("")) {
//			
//			tv.setTextSize(Integer.parseInt(pref_FontSize));
//			
//		}
		
		/*----------------------------
		 * 5.2. Memo
			----------------------------*/
		TextView tv_memo = (TextView) v.findViewById(R.id.textView2);
		
		tv_memo.setTextColor(Color.BLACK);
		tv_memo.setBackgroundColor(Color.WHITE);
		
		String memo = ti.getMemo();
		
		if (memo != null) {
			tv_memo.setText(memo);
			
		} else {//if (memo)
			
			tv_memo.setText("");
			
		}//if (memo)
		
		/*********************************
		 * 5-2. Get => last viewed
		 *********************************/
		
		
		/*----------------------------
		 * 6. Return
			----------------------------*/
		return v;
		
	}//private void move_mode_off()

}//public class TIListAdapter extends ArrayAdapter<TI>
