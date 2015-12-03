package ta2.adapters;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import ta2.items.AudioMemo;
import ta2.main.R;
import ta2.utils.CONS;
import ta2.utils.Methods;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adp_AudioMemoList extends ArrayAdapter<AudioMemo> {

	/*--------------------------------------------------------
	 * Class fields
		--------------------------------------------------------*/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	String today;
	String before_1;
	String before_2;
	String before_1w;	// one week before
	
	int comp;	// compareTo() value
	int comp_1;	// compareTo() value, before today
	int comp_2;	// compareTo() value, 2 days before
	int comp_1w;	// compareTo() value, one week before
	
	String msg_Log;
	
	/*--------------------------------------------------------
	 * Constructor
		--------------------------------------------------------*/
	public Adp_AudioMemoList(Context con, int resourceId, List<AudioMemo> items) {
		// Super
		super(con, resourceId, items);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// Calendars
		_Init_Calendars();

	}//public TIListAdapter(Context con, int resourceId, List<TI> items)

	private void _Init_Calendars() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// Today

		////////////////////////////////
		Calendar c_Today = Calendar.getInstance();
		
		c_Today.setTimeInMillis(Methods.getMillSeconds_now());

		this.today = String.format("%d/%02d/%02d", 
						c_Today.get(Calendar.YEAR),
						c_Today.get(Calendar.MONTH) + 1,
						c_Today.get(Calendar.DATE)
				);
		
//		// Log
//		String msg_Log = "today => " + this.today;
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// before_1

		////////////////////////////////
		c_Today.add(Calendar.DATE, -1);
		
		this.before_1 = String.format("%d/%02d/%02d", 
				c_Today.get(Calendar.YEAR),
				c_Today.get(Calendar.MONTH) + 1,
				c_Today.get(Calendar.DATE)
		);
		
//		// Log
//		msg_Log = "before_1 => " + this.before_1;
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////
		
		// before_2
		
		////////////////////////////////
		c_Today.add(Calendar.DATE, -1);
		
		this.before_2 = String.format("%d/%02d/%02d", 
				c_Today.get(Calendar.YEAR),
				c_Today.get(Calendar.MONTH) + 1,
				c_Today.get(Calendar.DATE)
				);
		
//		// Log
//		msg_Log = "before_2 => " + this.before_2;
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////
		
		// before_1w
		
		////////////////////////////////
		c_Today.add(Calendar.DATE, -5);
		
		this.before_1w = String.format("%d/%02d/%02d", 
				c_Today.get(Calendar.YEAR),
				c_Today.get(Calendar.MONTH) + 1,
				c_Today.get(Calendar.DATE)
				);
		
//		// Log
//		msg_Log = "before_1w => " + this.before_1w;
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}

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

		// setup: positions

		////////////////////////////////
		CONS.ShowListActv.list_Pos_Prev = CONS.ShowListActv.list_Pos_Current;
		
		CONS.ShowListActv.list_Pos_Current = position;

//		// Log
//		msg_Log = String.format(
//						"pos prev = %d, pos current = %d", 
//						CONS.ShowListActv.list_Pos_Prev,
//						CONS.ShowListActv.list_Pos_Current);
//		
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
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
    	AudioMemo memo = getItem(position);
    	
    	////////////////////////////////

		// view

		////////////////////////////////
    	_Setup_Views(v, memo, position);
    	
//    	TextView tv = (TextView) v.findViewById(R.id.list_row);
//    	
//    	tv.setText(wp.getWord());
		
//    	return null;
		return v;
    }//public View getView(int position, View convertView, ViewGroup parent)

	private void 
	_Setup_Views
	(View v, AudioMemo memo, int inList_Pos) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// text

		////////////////////////////////
		TextView tv_AudioMemo = (TextView) v.findViewById(R.id.list_row_showlist_tv_text);
		
		tv_AudioMemo.setText(memo.getText());
		
		////////////////////////////////

		// background

		////////////////////////////////
		int pref_Pos = Methods.get_Pref_Int(
						(Activity)con,
						CONS.Pref.pname_ImportActv,
						CONS.Pref.pkey_ImportActv_Current_Position,
//						CONS.Pref.pname_ShowListActv,
//						CONS.Pref.pkey_ShowListActv_Current_Position,
		//				CONS.Pref.pkey_CurrentPosition,
						inList_Pos);

		// Log
		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"pref_Pos = %d / inList_Pos = %d", pref_Pos, inList_Pos
				);
		
		Log.i("Adp_AudioMemoList.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		if (pref_Pos != CONS.Pref.dflt_IntExtra_value) {
			
			if (pref_Pos == inList_Pos) {
				
				tv_AudioMemo.setBackgroundColor(
							((Activity)con).getResources().getColor(R.color.yellow_pale_dark));
//				((Activity)con).getResources().getColor(R.color.gold2));
				
			} else {
				
				tv_AudioMemo.setBackgroundColor(
						((Activity)con).getResources().getColor(R.color.white));
				
			}
			
		} else {//if (pref_Pos != CONS.Pref.dflt_IntExtra_value)
			
			tv_AudioMemo.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.white));
			
		}//if (pref_Pos != CONS.Pref.dflt_IntExtra_value)
		
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

		// compare

		////////////////////////////////
//		int tmp = this.today.compareTo(tokens[0]);
//		
//		// Log
//		String msg_Log = String.format(
//					"today = %s // tokens[0] = %s: compare => %d",
//					this.today, tokens[0], tmp);
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		String tokens_Date_s = String.format(
					"%s/%s/%s", 
					tokens_Date[0], tokens_Date[1], tokens_Date[2]);
		
		comp = tokens_Date_s.compareTo(this.today);
		comp_1 = tokens_Date_s.compareTo(this.before_1);
		comp_2 = tokens_Date_s.compareTo(this.before_2);
		comp_1w = tokens_Date_s.compareTo(this.before_1w);
//		comp = tokens[0].compareTo(this.today);
//		comp_1 = tokens[0].compareTo(this.before_1);
//		comp_2 = tokens[0].compareTo(this.before_2);
//		comp = this.today.compareTo(tokens[0]);
//		comp_1 = this.before_1.compareTo(tokens[0]);
//		comp_2 = this.before_2.compareTo(tokens[0]);

//		// Log
//		msg_Log = String.format(
//					"today = %s // tokens_Date = %s: " +
//					"comp => %d, comp_1 => %d, comp_2 => %d",
//					this.today, tokens_Date_s, 
//					comp, comp_1, comp_2);
//		
//		Log.d("Adp_AudioMemoList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);

		if (comp >= 0) {
//			if (!(comp < 0)) {
//			if ((this.today.compareTo(tokens[0])) == 0) {
			
			tv_CreatedAt.setBackgroundColor(
							((Activity)con).getResources().getColor(R.color.blue1));
			tv_CreatedAt.setTextColor(
					((Activity)con).getResources().getColor(R.color.white));
			
		} else if (comp_1 >= 0) {
			
			tv_CreatedAt.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.green4));
			tv_CreatedAt.setTextColor(
					((Activity)con).getResources().getColor(R.color.white));
			
		} else if (comp_2 >= 0) {
			
			tv_CreatedAt.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.gold2));
			
			tv_CreatedAt.setTextColor(
					((Activity)con).getResources().getColor(R.color.black));
			
		} else if (comp_1w >= 0) {
			
			tv_CreatedAt.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.purple4));
			tv_CreatedAt.setTextColor(
					((Activity)con).getResources().getColor(R.color.white));
			
		} else {
			
			tv_CreatedAt.setBackgroundColor(
					((Activity)con).getResources().getColor(R.color.gray1));
			tv_CreatedAt.setTextColor(
					((Activity)con).getResources().getColor(R.color.white));
			
		}
		
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
