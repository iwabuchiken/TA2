package ta2.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import ta2.items.FilterHistory;
import ta2.items.ListItem;
import ta2.main.R;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Adp_FHs extends ArrayAdapter<FilterHistory> {

	/*--------------------------------------------------------
	 * Class fields
		--------------------------------------------------------*/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	private TextView tv;
	
	/*--------------------------------------------------------
	 * Constructor
		--------------------------------------------------------*/
	public Adp_FHs(Context con, int resourceId, List<FilterHistory> fh) {
		// Super
		super(con, resourceId, fh);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TIListAdapter(Context con, int resourceId, List<TI> items)

	/*--------------------------------------------------------
	 * Methods
		--------------------------------------------------------*/
    @Override
    public View 
    getView
    (int position, View convertView, ViewGroup parent) {
    	/*----------------------------
		 * Steps
		 * 0. View
		 * 1. Set layout
		 * 2. Get view
		 * 3. Get item
		 * 4. Get bitmap
		 * 5. Get memo, or, file name
			----------------------------*/
    	/*----------------------------
		 * 0. View
			----------------------------*/
    	View v = null;

    	if (convertView != null) {

    		v = convertView;
    		
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row_filter_history, null);
//			v = inflater.inflate(R.layout.list_row_simple_iv_1, null);

		}//if (convertView != null)
		
    	////////////////////////////////

		// get: item

		////////////////////////////////
    	FilterHistory fh = getItem(position);
    	
    	////////////////////////////////

		// setup: view

		////////////////////////////////
		_Setup_Views(v, fh);
		
//		// Log
//		String msg_Log = "v.getHeight() => " + v.getHeight();
//		Log.d("Adp_FHs.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		return v;
		
    }//public View getView(int position, View convertView, ViewGroup parent)

	private void 
	_Setup_Views
	(View v, FilterHistory fh) {
		// TODO Auto-generated method stub
		
		
		////////////////////////////////
		
		// view: text
		
		////////////////////////////////
		_Setup_Views__TV_ModifiedAt_Date(v, fh);
		
		_Setup_Views__TV_ModifiedAt_Time(v, fh);
		
		////////////////////////////////

		// keywords

		////////////////////////////////
		this._Setup_Views__TV_Keywords(v, fh);
		
		////////////////////////////////
		
		// op label
		
		////////////////////////////////
		this._Setup_Views__TV_OpLabel(v, fh);
		
//		////////////////////////////////
//		
//		// view: icon
//		
//		////////////////////////////////
//		_Setup_Views__IV(v, li);
		
	}//_Setup_Views

	private void 
	_Setup_Views__TV_ModifiedAt_Date
	(View v, FilterHistory fh) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// created at

		////////////////////////////////
		TextView tv_Created = 
				(TextView) v.findViewById(R.id.list_row_filter_history_tv_created_at);

		String[] tokens = fh.getModified_at().split(" ");
		
		String[] tokens_Date = tokens[0].split("/");
		
		String new_DateLabel = StringUtils.join(
						new String[]{
						
								tokens_Date[1],
								tokens_Date[2]
										
						}, 
						"/");

		tv_Created.setText(new_DateLabel);
		
//		tv.setTextColor(((Activity)con).getResources().getColor(li.getTextColor_ID()));
		
//		LinearLayout.LayoutParams params =
//				new LinearLayout.LayoutParams(
//								LayoutParams.FILL_PARENT,
//								LayoutParams.WRAP_CONTENT);
//		
//		//REF margin http://stackoverflow.com/questions/3416087/how-to-set-margin-of-imageview-using-code-not-xml answered Aug 5 '10 at 15:19
//		params.setMargins(5, 10, 10, 10);
//		
//		tv_Created.setLayoutParams(params);
		
	}//_Setup_Views__TV

	private void 
	_Setup_Views__TV_ModifiedAt_Time
	(View v, FilterHistory fh) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// created at
		
		////////////////////////////////
		TextView tv_Time = 
				(TextView) v.findViewById(R.id.list_row_filter_history_tv_modified_at);
		
		String[] tokens = fh.getModified_at().split(" ");
		
		String[] tokens_Time = tokens[1].split("\\.");
		
		String new_TimeLabel = tokens_Time[0];

		
		tv_Time.setText(new_TimeLabel);
		
//		tv.setTextColor(((Activity)con).getResources().getColor(li.getTextColor_ID()));
		
//		LinearLayout.LayoutParams params =
//				new LinearLayout.LayoutParams(
//								LayoutParams.FILL_PARENT,
//								LayoutParams.WRAP_CONTENT);
//		
//		//REF margin http://stackoverflow.com/questions/3416087/how-to-set-margin-of-imageview-using-code-not-xml answered Aug 5 '10 at 15:19
//		params.setMargins(5, 10, 10, 10);
//		
//		tv_Created.setLayoutParams(params);
		
	}//_Setup_Views__TV_ModifiedAt_Time
	
	private void 
	_Setup_Views__TV_Keywords
	(View v, FilterHistory fh) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// created at
		
		////////////////////////////////
		TextView tv_KWs = 
				(TextView) v.findViewById(R.id.list_row_filter_history_tv_keywords);
		
		tv_KWs.setText(fh.getKeywords());
		
//		tv.setTextColor(((Activity)con).getResources().getColor(li.getTextColor_ID()));
		
//		LinearLayout.LayoutParams params =
//				new LinearLayout.LayoutParams(
//								LayoutParams.FILL_PARENT,
//								LayoutParams.WRAP_CONTENT);
//		
//		//REF margin http://stackoverflow.com/questions/3416087/how-to-set-margin-of-imageview-using-code-not-xml answered Aug 5 '10 at 15:19
//		params.setMargins(5, 10, 10, 10);
//		
//		tv_Created.setLayoutParams(params);
		
	}//_Setup_Views__TV_Keywords

	private void
	_Setup_Views__TV_OpLabel
	(View v, FilterHistory fh) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// created at
		
		////////////////////////////////
		TextView tv_OpLabel = 
				(TextView) v.findViewById(R.id.list_row_filter_history_tv_operator);
		
		tv_OpLabel.setText(fh.getOp_label());
		
//		tv.setTextColor(((Activity)con).getResources().getColor(li.getTextColor_ID()));
		
//		LinearLayout.LayoutParams params =
//				new LinearLayout.LayoutParams(
//								LayoutParams.FILL_PARENT,
//								LayoutParams.WRAP_CONTENT);
//		
//		//REF margin http://stackoverflow.com/questions/3416087/how-to-set-margin-of-imageview-using-code-not-xml answered Aug 5 '10 at 15:19
//		params.setMargins(5, 10, 10, 10);
//		
//		tv_Created.setLayoutParams(params);
		
	}//_Setup_Views__TV_OpLabel
	
}//public class TIListAdapter extends ArrayAdapter<TI>
