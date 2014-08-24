package ta2.adapters;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ta2.items.Memo;
import ta2.items.WordPattern;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Adp_MemoList extends ArrayAdapter<Memo> {

	/*--------------------------------------------------------
	 * Class fields
		--------------------------------------------------------*/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	
	/*--------------------------------------------------------
	 * Constructor
		--------------------------------------------------------*/
	public Adp_MemoList(Context con, int resourceId, List<Memo> items) {
		// Super
		super(con, resourceId, items);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TIListAdapter(Context con, int resourceId, List<TI> items)

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

		// vars

		////////////////////////////////
    	String msg_Log;
    	
    	////////////////////////////////

		// View

		////////////////////////////////
    	View v = null;

    	if (convertView != null) {

    		v = convertView;
    		
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row_showlist, null);

		}//if (convertView != null)
		
    	////////////////////////////////

		// get: item

		////////////////////////////////
    	Memo memo = getItem(position);
    	
    	////////////////////////////////

		// view

		////////////////////////////////
    	_Setup_Views(v, memo);
    	
//    	TextView tv = (TextView) v.findViewById(R.id.list_row);
//    	
//    	tv.setText(wp.getWord());
		
//    	return null;
		return v;
    }//public View getView(int position, View convertView, ViewGroup parent)

	private void 
	_Setup_Views
	(View v, Memo memo) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// text

		////////////////////////////////
		TextView tv_Memo = (TextView) v.findViewById(R.id.list_row_showlist_tv_text);
		
		tv_Memo.setText(memo.getText());
		
		////////////////////////////////

		// created at

		////////////////////////////////
		TextView tv_CreatedAt = 
				(TextView) v.findViewById(R.id.list_row_showlist_tv_created_at);
		
		String[] tokens = memo.getCreated_at().split(" ");
		
		String[] tokens_Date = tokens[0].split("/");
		
		String new_DateLabel = StringUtils.join(
						new String[]{
						
								tokens_Date[1],
								tokens_Date[2]
										
						}, 
						"/");
		
		tv_CreatedAt.setText(new_DateLabel);
//		tv_CreatedAt.setText(memo.getCreated_at());
		
		////////////////////////////////
		
		// modified at
		
		////////////////////////////////
		TextView tv_ModifiedAt = 
				(TextView) v.findViewById(R.id.list_row_showlist_tv_modified_at);

		tokens = memo.getCreated_at().split(" ");
		
		String[] tokens_Time = tokens[1].split("\\.");
		
		String new_TimeLabel = tokens_Time[0];
		
		tv_ModifiedAt.setText(new_TimeLabel);
//		tv_ModifiedAt.setText(memo.getModified_at());
		
	}//_Setup_Views

}//public class TIListAdapter extends ArrayAdapter<TI>
