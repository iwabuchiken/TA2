package ta2.main;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import ta2.items.MyView;
import ta2.listeners.LOI_LCL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class ImageActv extends Activity {

	//
	public static Vibrator vib;

	//
	long file_Id;
	long db_Id;
	
	//
	public static Bitmap bm;

	/*********************************
	 * Views
	 *********************************/
	public static MyView v;

	public static LinearLayout LL;
	
	public static TextView tv_file_name;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		----------------------------*/
		super.onCreate(savedInstanceState);

		//

//		setContentView(R.layout.image_activity);
		setContentView(R.layout.actv_image_with_myview);
//		setContentView(R.layout.image_activity_for_myview);

		this.setTitle(this.getClass().getName());
		
		//
		vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		
//		initial_setup();
		
	}//public void onCreate(Bundle savedInstanceState)

	private void _Setup_Initial() {
		/*----------------------------
		 * Steps
		 * 1. Get intent and data
		 * 2. Prepare image
		 * 3. Set image to the view
		 * 
		 * 3-2
		 * 
		 * 4. Set file name to the view
		 * 
		 * 5. Set listeners
			----------------------------*/
		//
		Intent i = getIntent();
		
//		String file_path = i.getStringExtra("file_path");
//		file_Id = i.getLongExtra("file_id", -1);
//		db_Id = i.getLongExtra("db_id", -1);
//		String file_name = i.getStringExtra("file_name");
//		
//		////////////////////////////////
//
//		// get: TI
//
//		////////////////////////////////
//		CONS.IMageActv.ti = Methods.find_TI_From_TIList(this, db_Id);
////		CONS.IMageActv.ti = DBUtils.get_TI_From_DbId(this, db_Id);
//		
//		/*----------------------------
//		 * 2. Prepare image
//			----------------------------*/
//		set_image(file_path);
//		
//		/*----------------------------
//		 * 4. Set file name to the view
//			----------------------------*/
////		TextView tv_file_name = (TextView) findViewById(R.id.image_activity_tv_message);
//		tv_file_name = (TextView) findViewById(R.id.image_activity_tv_message);
//		
//		tv_file_name.setText(file_name);
//		
//		/*----------------------------
//		 * 5. Set listeners
//			----------------------------*/
//		set_listeners();
//		
		////////////////////////////////

		// update: last_viewed_at

		////////////////////////////////
		
		
//		Methods.toastAndLog(this, file_path, 2000);
		
	}//private void initial_setup()

	private void set_image(String file_path) {
		
		// Log
		Log.d("ImageActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "file_path=" + file_path);
		
//		Bitmap bm = BitmapFactory.decodeFile(file_path);
		bm = BitmapFactory.decodeFile(file_path);
		
		
		Bitmap bm_modified = set_image_1_modify_bitmap(bm);
		
		/*----------------------------
		 * 3. Set image to the view
			----------------------------*/
		// MyView
//		MyView v = new MyView(this);
		v = new MyView(this);
		
		// Set image
//		v.setImageBitmap(bm);
		v.setImageBitmap(bm_modified);
		
		//
//		LinearLayout LL = (LinearLayout) findViewById(R.id.image_activity_LL_image);
		LL = (LinearLayout) findViewById(R.id.image_activity_LL_image);
		
		LL.addView(v);
		
	}//private void set_image()
	
	private Bitmap set_image_1_modify_bitmap(Bitmap bm) {
		/*********************************
		 * memo
		 *********************************/
		int bm_w = bm.getWidth();
		int bm_h = bm.getHeight();
		
		Display disp=((WindowManager)getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		int w;
		int h;
		
		if (bm_w > bm_h) {
			
			h = disp.getHeight();
			
			w = (int) (h * ((float) bm_w / bm_h));
			
//			w = disp.getWidth();
//			
//			h = (int) (w * ((float) bm_h / bm_w));
			
		} else {//if (bm_w > bm_h)
			
			w = disp.getWidth();
			
			h = (int) (w * ((float) bm_h / bm_w));
			
//			h = disp.getHeight();
//			
//			w = (int) (h * ((float) bm_w / bm_h));
			
		}//if (bm_w > bm_h)
		
		
//		int w=disp.getWidth();
//		int h=disp.getHeight();

		return Bitmap.createScaledBitmap(bm, w, h, false);
		
	}//private Bitmap set_image_1_modify_bitmap(Bitmap bm)

	private void set_listeners() {
		/*----------------------------
		 * Steps
		 * 1. "Back" button
		 * 		1.1. OnTouch
		 * 		1.2. OnClick
		 * 
		 * 2. "Prev" button
		 * 3. "Next" button
			----------------------------*/
		ImageButton ib_back = (ImageButton) findViewById(R.id.actv_image_with_myview_ib_back);
		
		ib_back.setTag(Tags.ButtonTags.image_activity_back);
		
		ib_back.setOnTouchListener(new BO_TL(this));
		ib_back.setOnClickListener(new BO_CL(this));
		
		/*********************************
		 * 2. "Prev" button
		 *********************************/
		ImageButton ib_prev = (ImageButton) findViewById(R.id.actv_image_with_myview_ib_prev);
		
		ib_prev.setImageResource(R.drawable.ifm8_back);
		
		ib_prev.setTag(Tags.ButtonTags.image_activity_prev);
		
		ib_prev.setOnTouchListener(new BO_TL(this));
		ib_prev.setOnClickListener(new BO_CL(this));
		
		/*********************************
		 * 3. "Next" button
		 *********************************/
		ImageButton ib_next = (ImageButton) findViewById(R.id.actv_image_with_myview_ib_next);
		
		ib_next.setImageResource(R.drawable.ifm8_forward);
		
		ib_next.setTag(Tags.ButtonTags.image_activity_next);
		
		ib_next.setOnTouchListener(new BO_TL(this));
		ib_next.setOnClickListener(new BO_CL(this));
		
	}//private void set_listeners()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_actv_image, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
//		case R.id.image_actv_menu_add_memo://------------------------------------
//
//			_case_AddMemo();
//			
//			break;
//			
		case R.id.image_actv_menu_add_patterns://------------------------------------
			
//			Methods.dlg_register_patterns(this);
			
//			Methods_dlg.dlg_patterns(this);
			
			break;
			
		case R.id.image_actv_menu_add_memo://------------------------------------
			
			if (CONS.IMageActv.memo == null) {
				
				Methods_dlg.dlg_EditMemo_ImageActv(this);
				
			} else {
				
				Methods_dlg.dlg_EditMemo_ImageActv_From_ShowList(this);

			}
//			Methods_dlg.dlg_EditMemo_ImageActv(this);
//			Methods_dlg.dlg_EditMemo_ImageActv(this);
//			Methods.dlg_register_patterns(this);
			
//			Methods_dlg.dlg_patterns(this);
			
			break;
			
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

	private void 
	_case_AddMemo() {
		// TODO Auto-generated method stub
		Log.d("ImageActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "file_id => " + file_Id);
		Log.d("ImageActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db_id => " + db_Id);

		////////////////////////////////

		// get: current path

		////////////////////////////////
		String currentPath = Methods.get_Pref_String(
				this, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_CurrentPath, 
				null);

		/******************************
			validate: null
		 ******************************/
		if (currentPath == null) {
			
			String msg = "Can't get the current path";
			Methods_dlg.dlg_ShowMessage(this, msg);
			
			return;
			
		}

		////////////////////////////////

		// add memo

		////////////////////////////////
		// Log
		String msg_Log = "showing dialog...";
		Log.d("ImageActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		Methods_dlg.dlg_AddMemo(
//						this, 
//						db_Id);
//		file_Id);

	}//_case_AddMemo()

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void 
	onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
		
		boolean res;
		
		////////////////////////////////

		// get: TI

		////////////////////////////////
		res = this._Setup_Get_TI();
		
		if (res == false) {
			
			return;
			
		}
		
		////////////////////////////////

		// set: image

		////////////////////////////////
		res = this._Setup_Set_Image();
		
		if (res == false) {
			
			return;
			
		}
		
		////////////////////////////////

		// setup

		////////////////////////////////
		_Setup_Initial();
//		initial_setup();
		
		////////////////////////////////

		// last_viewed_at

		////////////////////////////////
		_Setup_Update_LastViewed();

		////////////////////////////////

		// listeners

		////////////////////////////////
		this._Setup_Listeners();
		
		////////////////////////////////

		// tests

		////////////////////////////////
		do_test();
		
	}//onStart

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub

		////////////////////////////////
		
		// IB: memo
		
		////////////////////////////////
		ImageButton bt_Back = 
				(ImageButton) this.findViewById(R.id.actv_image_with_myview_ib_back);
		
		bt_Back.setTag(Tags.ButtonTags.ACTV_IMAGE_BACK);
		
		bt_Back.setOnTouchListener(new BO_TL(this));
		
		bt_Back.setOnClickListener(new BO_CL(this));

		
	}//_Setup_Listeners

	private boolean 
	_Setup_Set_Image() {
		// TODO Auto-generated method stub
		
		String fpath = StringUtils.join(
				new String[]{
						
						CONS.IMageActv.ti.getFile_path(),
						CONS.IMageActv.ti.getFile_name()
				}, 
				File.separator);
		
		// Log
		String msg_Log = "fpath => " + fpath;
		Log.d("ImageActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// validate: file exists

		////////////////////////////////
		File f = new File(fpath);
		
		if (!f.exists()) {
			
			String msg = "file => doesn't exist: " + fpath;
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
//		// Log
//		Log.d("ImageActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "file_path=" + file_path);
//		
////		Bitmap bm = BitmapFactory.decodeFile(file_path);
		CONS.IMageActv.bm = BitmapFactory.decodeFile(fpath);
//		
//		
		Bitmap bm_modified = set_image_1_modify_bitmap(CONS.IMageActv.bm);
		
		/*----------------------------
		 * 3. Set image to the view
			----------------------------*/
		// MyView
//		MyView v = new MyView(this);
		v = new MyView(this);
		
		// Set image
//		v.setImageBitmap(bm);
		v.setImageBitmap(bm_modified);
		
		//
//		LinearLayout LL = (LinearLayout) findViewById(R.id.image_activity_LL_image);
		LL = (LinearLayout) findViewById(R.id.image_activity_LL_image);
		
		LL.addView(v);

		////////////////////////////////

		// return

		////////////////////////////////
		return true;
		
	}//_Setup_Set_Image

	
	private void 
	do_test() {
		// TODO Auto-generated method stub
		
		_test_Start_ImageActv();
		
	}//do_test
	

	private void 
	_test_Start_ImageActv() {
		// TODO Auto-generated method stub
		
		
		
		
	}//_test_Start_ImageActv
	
	private boolean 
	_Setup_Get_TI() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// dispatch: From ShowListActv

		////////////////////////////////
		if (CONS.IMageActv.ti != null) {
			
			// Log
			String msg_Log = "CONS.IMageActv.ti => not null";
			Log.d("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return true;
			
		}
		
		////////////////////////////////

		// build: TI

		////////////////////////////////
		Intent i = null;
		
		try {
			
			i = getIntent();
			
			// Log
			String msg_Log = "intent => not null";
			Log.d("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (Exception e) {

			// Log
			String msg_Log = "Exception";
			Log.e("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e.printStackTrace();
			
			String msg = "Can't get intent";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		
//		Intent i = getIntent();
		
		String file_path = i.getStringExtra("file_path");
		file_Id = i.getLongExtra("file_id", -1);
		db_Id = i.getLongExtra("db_id", -1);
		String file_name = i.getStringExtra("file_name");
		
		////////////////////////////////

		// get: TI

		////////////////////////////////
		CONS.IMageActv.ti = Methods.find_TI_From_TIList(this, db_Id);
//		CONS.IMageActv.ti = DBUtils.get_TI_From_DbId(this, db_Id);
		
		if (CONS.IMageActv.ti != null) {
			
			// Log
			String msg_Log = "CONS.IMageActv.ti => "
							+ CONS.IMageActv.ti.getFile_name();
			Log.d("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return true;
			
		} else {

			// Log
			String msg_Log = "CONS.IMageActv.ti => null";
			Log.d("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
	}//_Setup_Get_TI

	private void 
	_Setup_Update_LastViewed() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// validate

		////////////////////////////////
		if (CONS.IMageActv.ti == null) {
			
			// Log
			String msg_Log = "CONS.IMageActv.ti => null";
			Log.e("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////

		// update

		////////////////////////////////
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		"file_id", "file_path", "file_name",	// 3,4,5
//		"date_added", "date_modified",			// 6,7
//		"memos", "tags",						// 8,9
//		"last_viewed_at",						// 10
//		"table_name",							// 11
//		"uploaded_at",							// 12
		
//		int res = DBUtils.update_TI(
//						this, 
//						CONS.IMageActv.ti, 
//						CONS.DB.col_names_IFM11_full[10],
//						Methods.conv_MillSec_to_TimeLabel(
//								Methods.getMillSeconds_now())
//						);
//		
//		String msg = null;
//		
//		switch(res) {
////			-1 => Insertion failed
////			-2 => Exception
////			1 => Insertion done
//		case -1:
//			
//			msg = "last_viewed_at => not inserted";
//			
//			break;
//			
//		case -2:
//			
//			msg = "last_viewed_at => Exception";
//			
//			break;
//			
//		case 1:
//			
//			msg = "Insertion done";
//			
//			break;
//			
//		default:
//			msg = "Unknown result => " + res;
//			
//			break;
//			
//		}
//		
//		// Log
//		Log.d("ImageActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg);
		
	}//_Setup_Update_LastViewed

	@Override
	protected void 
	onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
		
		////////////////////////////////

		// nullify: CONS.IMageActv.memo

		////////////////////////////////
		if (CONS.IMageActv.memo != null) {
			
			CONS.IMageActv.memo = null;
			
			// Log
			String msg_Log = "CONS.IMageActv.memo => nullified";
			Log.d("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////
		
		// nullify: CONS.IMageActv.ti
		
		////////////////////////////////
		if (CONS.IMageActv.ti != null) {
			
			CONS.IMageActv.ti = null;
			
			// Log
			String msg_Log = "CONS.IMageActv.ti => nullified";
			Log.d("ImageActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// recycle

		////////////////////////////////
		CONS.IMageActv.bm.recycle();
		
		// Log
		String msg_Log = "CONS.IMageActv.bm.recycle() => recycled";
		Log.d("ImageActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//onStop

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
		
		// REF=> http://ameblo.jp/yolluca/entry-10725668557.html
		if (CONS.IMageActv.bm != null) {
			CONS.IMageActv.bm.recycle();
//			if (bm != null) {
//				bm.recycle();
			
			// Log
			Log.d("ImageActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "CONS.IMageActv.bm => recycled");
			
			
		}//if (bm != null)
		
	}

}//public class ImageActivity extends Activity
