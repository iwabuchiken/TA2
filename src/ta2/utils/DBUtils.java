package ta2.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ta2.items.Memo;
import ta2.items.WordPattern;
import ta2.main.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
//import android.view
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/****************************************
 * Copy & pasted from => C:\WORKS\WORKSPACES_ANDROID\ShoppingList\src\shoppinglist\main\DBUtils.java
 ****************************************/
public class DBUtils extends SQLiteOpenHelper{

	/*****************************************************************
	 * Class fields
	 *****************************************************************/
	 // DB name
	String dbName = null;
//	static String dbName = null;
	
	// Activity
	Activity activity;
	
	//
	Context context;

	/*********************************
	 * DB
	 *********************************/
	// Database
	SQLiteDatabase db = null;

	/*****************************************************************
	 * Constructor
	 *****************************************************************/
	public DBUtils(Context context, String dbName) {
		super(context, dbName, null, 1);
		
		// Initialize activity
		this.activity = (Activity) context;
		
		this.context = context;
		
		this.dbName = dbName;
		
	}//public DBUtils(Context context)

	/*******************************************************
	 * Methods
	 *******************************************************/
	@Override
	public void 
	onCreate
	(SQLiteDatabase db) {
		
		// Log
		String msg_Log = "onCreate";
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//public void onCreate(SQLiteDatabase db)

	@Override
	public void 
	onUpgrade
	(SQLiteDatabase arg0, int arg1, int arg2) {
		
		// Log
		String msg_Log = "onUpgrade";
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	/****************************************
	 * createTable_generic()
	 * 
	 * <Caller> 
	 * 1. 
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public boolean createTable
	(SQLiteDatabase db, String tableName, 
			String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		
		//
//		if (!tableExists(db, tableName)) {
		if (tableExists(db, tableName)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		/*----------------------------
		 * 2. Build sql
			----------------------------*/
		//
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
							" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		// created_at, modified_at
		sb.append("created_at TEXT, modified_at TEXT, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
		/*----------------------------
		 * 3. Exec sql
			----------------------------*/
		//
		try {
//			db.execSQL(sql);
			db.execSQL(sb.toString());
			
			// Log
			Log.d(this.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			
			return true;
		} catch (SQLException e) {
			// Log
			Log.e(this.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean createTable(SQLiteDatabase db, String tableName)

	/******************************
		createTable()
		
		@param columns, types => use non-full version
		@return true => Table created or exists
	 ******************************/
	public boolean createTable
	(Activity actv, String tableName, String[] columns, String[] types)
	{
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
//		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase wdb = this.getWritableDatabase();
//		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		//
		//if (!tableExists(db, tableName)) {
		if (tableExists(wdb, tableName)) {
			// Log
			Log.i("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			// debug
			String msg_Toast = "Table exists => " + tableName;
			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			
			return true;
//			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		/*----------------------------
		 * 2. Build sql
			----------------------------*/
		//
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
							" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		// created_at, modified_at
		sb.append("created_at TEXT, modified_at TEXT, ");
//		sb.append("created_at INTEGER, modified_at INTEGER, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
		/*----------------------------
		 * 3. Exec sql
			----------------------------*/
		//
		try {
		//	db.execSQL(sql);
			wdb.execSQL(sb.toString());
			
			// Log
			Log.d(this.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			wdb.close();
			
			return true;
			
		} catch (SQLException e) {
			
			// Log
			Log.e(this.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			wdb.close();
			
			return false;
			
		}//try

	}//public boolean createTable(SQLiteDatabase db, String tableName)

	/******************************
		createTable()
		
		@param columns, types => use non-full version
		@return
				-1	Table exists<br>
				-2	Exception in executing the sql<br>
				1	Table created<br>
	 ******************************/
	public static int 
	createTable
	(Activity actv, 
		String dbName, String tableName, 
		String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		////////////////////////////////

		// validate: table exists

		////////////////////////////////
		if (DBUtils.tableExists(actv, dbName, tableName)) {
			// Log
			Log.i("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			wdb.close();
			
			return -1;

		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////

		// Build sql

		////////////////////////////////
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		// created_at, modified_at
		sb.append("created_at TEXT, modified_at TEXT, ");
//		sb.append("created_at INTEGER, modified_at INTEGER, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
		////////////////////////////////

		// Exec sql

		////////////////////////////////
		try {
			//	db.execSQL(sql);
			wdb.execSQL(sb.toString());
			
			// Log
			Log.d(actv.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			wdb.close();
			
			return 1;
			
		} catch (SQLException e) {
			
			// Log
			Log.e(actv.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			wdb.close();
			
			return -2;
			
		}//try
		
	}//createTable_static
	
	public boolean tableExists(SQLiteDatabase db, String tableName) {
		// The table exists?
		Cursor cursor = db.rawQuery(
									"SELECT * FROM sqlite_master WHERE tbl_name = '" + 
									tableName + "'", null);
		
		((Activity) context).startManagingCursor(cursor);
//		actv.startManagingCursor(cursor);
		
		// Judge
		if (cursor.getCount() > 0) {
			return true;
		} else {//if (cursor.getCount() > 0)
			return false;
		}//if (cursor.getCount() > 0)
	}//public boolean tableExists(String tableName)
	
	public static boolean 
	tableExists
	(Activity actv, String dbName, String tableName) {
		// The table exists?
		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		Cursor cursor = rdb.rawQuery(
				"SELECT * FROM sqlite_master WHERE tbl_name = '" + 
						tableName + "'", null);
		
		actv.startManagingCursor(cursor);
//		actv.startManagingCursor(cursor);
		
		// Judge
		if (cursor.getCount() > 0) {
		
			rdb.close();
			return true;
			
		} else {//if (cursor.getCount() > 0)
			
			rdb.close();
			return false;
			
		}//if (cursor.getCount() > 0)
		
	}//public boolean tableExists(String tableName)

	public boolean dropTable(Activity actv, SQLiteDatabase db, String tableName) {
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Starting: dropTable()");
		
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = tableExists(db, tableName);
		
		if (tempBool == true) {
		
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);

		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);

			return false;
		}//if (tempBool == true)

		/*------------------------------
		 * Drop the table
		 *------------------------------*/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			db.execSQL(sql);
			
			// Vacuum
			db.execSQL("VACUUM");
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
			// Return
			return true;
			
		} catch (SQLException e) {
			// TODO ?��?��?��?��?��?��?��?��?��?��?��ꂽ catch ?��u?��?��?��b?��N
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
			// debug
			Toast.makeText(actv, 
						"DROP TABLE => failed(table=" + tableName, 
						3000).show();
			
			// Return
			return false;
		}//try

	}//public boolean dropTable(String tableName) 

	public boolean drop_table(Activity actv, SQLiteDatabase db, String tableName) {
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Starting: dropTable()");
		
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = tableExists(db, tableName);
		
		if (tempBool == true) {
		
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);

		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);

			return false;
		}//if (tempBool == true)

		/*------------------------------
		 * Drop the table
		 *------------------------------*/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			db.execSQL(sql);
			
			// Vacuum
			db.execSQL("VACUUM");
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
			// Return
			return true;
			
		} catch (SQLException e) {
			// TODO ?��?��?��?��?��?��?��?��?��?��?��ꂽ catch ?��u?��?��?��b?��N
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
			// debug
			Toast.makeText(actv, 
						"DROP TABLE => failed(table=" + tableName, 
						3000).show();
			
			// Return
			return false;
		}//try

	}//public boolean dropTable(String tableName) 

	public boolean insertData(SQLiteDatabase db, String tableName, 
												String[] columnNames, String[] values) {
		
////		String sql = "SELECT * FROM TABLE " + DBUtils.table_name_memo_patterns;
//		String sql = "SELECT * FROM " + DBUtils.table_name_memo_patterns;
//		
//		Cursor c = db.rawQuery(sql, null);
//		
//		
//		
//		// Log
//		Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount() => " + c.getCount() + " / " +
//				"c.getColumnCount() => " + c.getColumnCount());
//		
//		c.close();
		
		
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		try {
			// Start transaction
			db.beginTransaction();
			
			// ContentValues
			ContentValues val = new ContentValues();
			
			// Put values
			for (int i = 0; i < columnNames.length; i++) {
				val.put(columnNames[i], values[i]);
			}//for (int i = 0; i < columnNames.length; i++)
			
			// Insert data
			db.insert(tableName, null, val);
			
			// Set as successful
			db.setTransactionSuccessful();
			
			// End transaction
			db.endTransaction();
			
			// Log
//			Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Data inserted => " + "(" + columnNames[0] + " => " + values[0] + 
//				" / " + columnNames[3] + " => " + values[3] + ")");
			
			return true;
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Exception! => " + e.toString());
			
			return false;
		}//try
		
//		//debug
//		return false;
		
	}//public insertData(String tableName, String[] columnNames, String[] values)

	/******************************
		public boolean dropTable
		
		@return
			-1	Table doesnt exist<br>
			-2	SQLException<br>
			1	Table dropped<br>
	 ******************************/
	public int dropTable
	(Activity actv, String tableName) {
		/***************************************
		 * Setup: DB
		 ***************************************/
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = tableExists(wdb, tableName);
		
		if (tempBool == true) {
		
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);

		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
			// debug
			String msg_Toast = "Table doesn't exist: " + tableName;
			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();

			return -1;
			
		}//if (tempBool == true)

		/*------------------------------
		 * Drop the table
		 *------------------------------*/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			wdb.execSQL(sql);
			
			// Vacuum
			wdb.execSQL("VACUUM");
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
//			// debug
//			String msg_Toast = "The table dropped => " + tableName;
//			Toast.makeText(actv, msg_Toast, Toast.LENGTH_SHORT).show();
			
			wdb.close();
			
			// Return
			return 1;
			
		} catch (SQLException e) {
			// TODO ?��?��?��?��?��?��?��?��?��?��?��ꂽ catch ?��u?��?��?��b?��N
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
//			// debug
//			Toast.makeText(actv, 
//						"DROP TABLE => failed(table=" + tableName, 
//						Toast.LENGTH_LONG).show();
			
			wdb.close();
			
			// Return
			return -2;
		}//try

	}//public boolean dropTable(String tableName) 

	public boolean 
	insertData(SQLiteDatabase db, String tableName, 
											String[] columnNames, long[] values) {
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		try {
			// Start transaction
			db.beginTransaction();
			
			// ContentValues
			ContentValues val = new ContentValues();
			
			// Put values
			for (int i = 0; i < columnNames.length; i++) {
				val.put(columnNames[i], values[i]);
			}//for (int i = 0; i < columnNames.length; i++)
			
			// Insert data
			db.insert(tableName, null, val);
			
			// Set as successful
			db.setTransactionSuccessful();
			
			// End transaction
			db.endTransaction();
			
			// Log
			Log.d("DBUtils.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", "Data inserted => " + "(" + columnNames[0] + " => " + values[0] + "), and others");
			
			return true;
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", "Exception! => " + e.toString());
			
			return false;
		}//try
	}//public insertData(String tableName, String[] columnNames, String[] values)
	
	public boolean deleteData(Activity actv, SQLiteDatabase db, String tableName, long file_id) {
		/*----------------------------
		 * Steps
		 * 1. Item exists in db?
		 * 2. If yes, delete it
			----------------------------*/
		/*----------------------------
		 * 1. Item exists in db?
			----------------------------*/
		boolean result = isInDB_long(db, tableName, file_id);
		
		if (result == false) {		// Result is false ==> Meaning the target data doesn't exist
											//							in db; Hence, not executing delete op
			
			// debug
			Toast.makeText(actv, 
					"Data doesn't exist in db: " + String.valueOf(file_id), 
					2000).show();
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
					"Data doesn't exist in db => Delete the data (file_id = " + String.valueOf(file_id) + ")");
			
			return false;
			
		} else {//if (result == false)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
					"Data exists in db" + String.valueOf(file_id) + ")");
			
		}//if (result == false)
		
		
		String sql = 
						"DELETE FROM " + tableName + 
						" WHERE file_id = '" + String.valueOf(file_id) + "'";
		
		try {
			db.execSQL(sql);
			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data deleted => file id = " + String.valueOf(file_id));
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Sql executed: " + sql);
			
			return true;
			
		} catch (SQLException e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
			
		}//try
		
	}//public boolean deleteData(SQLiteDatabase db, String tableName, long file_id)

	public boolean deleteData_ai(Activity actv,
						SQLiteDatabase db, String tableName, long db_id) {
		/*----------------------------
		 * Steps
		 * 1. Item exists in db?
		 * 2. If yes, delete it
			----------------------------*/
		/*----------------------------
		 * 1. Item exists in db?
			----------------------------*/
		boolean result = DBUtils.isInDB_long_ai(db, tableName, db_id);
		
		if (result == false) {		// Result is false ==> Meaning the target data doesn't exist
											//							in db; Hence, not executing delete op
			
			// debug
			Toast.makeText(actv, 
					"Data doesn't exist in db: " + String.valueOf(db_id), 
					2000).show();
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
					"Data doesn't exist in db => Delete the data (db_id = " + String.valueOf(db_id) + ")");
			
			return false;
			
		} else {//if (result == false)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
					"Data exists in db" + String.valueOf(db_id) + ")");
			
		}//if (result == false)
		
		
		String sql = 
						"DELETE FROM " + tableName
						+ " WHERE " + android.provider.BaseColumns._ID
						+ " = "
						+ String.valueOf(db_id);
		
		try {
			db.execSQL(sql);
			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data deleted => file id = " + String.valueOf(db_id));
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Sql executed: " + sql);
			
			return true;
			
		} catch (SQLException e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "sql=" + sql);
			
			return false;
			
		}//try
		
	}//public boolean deleteData_ai(Activity actv, SQLiteDatabase db, String tableName, long db_id)

	/****************************************
	 *
	 * 
	 * <Caller> 
	 * 1. deleteData(Activity actv, SQLiteDatabase db, String tableName, long file_id)
	 * 
	 * <Desc> 
	 * 1. REF=> http://stackoverflow.com/questions/3369888/android-sqlite-insert-unique
	 * 
	 * <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static boolean 
	isInDB_long
	(SQLiteDatabase db, String tableName, long file_id) {
		
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE file_id = '" +
						String.valueOf(file_id) + "'";
		
		long result = DatabaseUtils.longForQuery(db, sql, null);
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "result => " + String.valueOf(result));
		
		if (result > 0) {

			return true;
			
		} else {//if (result > 0)
			
			return false;
			
		}//if (result > 0)
		
//		return false;
		
	}//public boolean isInDB_long(SQLiteDatabase db, String tableName, long file_id)

	public static boolean isInDB_long_ai(
						SQLiteDatabase db, String tableName, long db_id) {
		
		String sql = "SELECT COUNT(*) FROM " + tableName
					+ " WHERE " + android.provider.BaseColumns._ID + " = "
					+ String.valueOf(db_id);
		
		long result = DatabaseUtils.longForQuery(db, sql, null);
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "result => " + String.valueOf(result));
		
		if (result > 0) {

			return true;
			
		} else {//if (result > 0)
			
			return false;
			
		}//if (result > 0)
		
//		return false;
		
	}//public static boolean isInDB_long_ai

	/******************************
		@return
			-1	Query exception<br>
			-2	Query => null<br>
			-3	No entry in the table<br>
			-4	Unknown result<br>
			1	Entry exists<br>
	 ******************************/
	public static int 
	isInDB_Pattern
	(SQLiteDatabase db, String word) {
		
		Cursor c = null;
		
		String where = CONS.DB.col_names_Patterns[0] + " = ?";
		String[] args = new String[]{
				
							word
							
						};
		
		try {
			
			c = db.query(
					
					CONS.DB.tname_Patterns,			// 1
					CONS.DB.col_names_Patterns,	// 2
//					null, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {

			// Log
			String msg_Log = "Exception";
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
//			String msg = "Query exception";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return -1;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query => null";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);

			return -2;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);

			return -3;
			
		} else if (c.getCount() >= 1) {//if (c == null)
			
			// Log
			String msg_Log = "Entry exists => " + c.getCount();
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return 1;
			
		} else {//if (c == null)
			
			// Log
			String msg_Log = "Unknown result";
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -4;
			
		}//if (c == null)
		
//		String sql = "SELECT * FROM " + CONS.DB.tname_Patterns
//				+ " WHERE " + CONS.DB.col_names_Patterns[0] + " = "
//				+ "'" + word + "'";
//		
//		// Log
//		String msg_Log = "sql => " + sql;
//		Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		long result = DatabaseUtils.longForQuery(db, sql, null);
//		
//		// Log
//		Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "result => " + String.valueOf(result));
//		
//		if (result > 0) {
//			
//			return true;
//			
//		} else {//if (result > 0)
//			
//			return false;
//			
//		}//if (result > 0)
		
//		return false;
		
	}//public static boolean isInDB_long_ai
	
	public boolean insert_data_refresh_history(SQLiteDatabase wdb,
			String tableName, long[] data) {
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
			ContentValues val = new ContentValues();
			
//			// Put values
//			for (int i = 0; i < columnNames.length; i++) {
//				val.put(columnNames[i], values[i]);
//			}//for (int i = 0; i < columnNames.length; i++)
			
//			"last_refreshed", "num_of_items_added"
			
			val.put("last_refreshed", data[0]);
			
			val.put("num_of_items_added", data[1]);
			
			// Insert data
			wdb.insert(tableName, null, val);
			
			// Set as successful
			wdb.setTransactionSuccessful();
			
			// End transaction
			wdb.endTransaction();
			
			// Log
//			Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Data inserted => " + "(" + columnNames[0] + " => " + values[0] + 
//				" / " + columnNames[3] + " => " + values[3] + ")");
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Exception! => " + e.toString());
			
			return false;
			
		}//try
		
	}//public boolean insert_data_refresh_history

	/******************************
		@return true => update successful
	 ******************************/
	public static boolean
	update_Data_AI
	(Activity actv, String dbName, String tableName,
			long db_id, String col_name, String value) {
		/*********************************
		 * memo
		 *********************************/
		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		
//		String sql = "UPDATE " + CONS.tname_main + " SET " + 
	
		String sql = "UPDATE " + tableName + " SET " + 
//				"last_viewed_at='" + Methods.getMillSeconds_now() + "' " +

				col_name + " = '" + value + "' "
				+ " WHERE " + android.provider.BaseColumns._ID + " = '"
				+ db_id + "'";
		
		
		//			"file_id", 		"file_path", "file_name", "date_added", "date_modified"
		//static String[] cols = 
		//{"file_id", 		"file_path", "file_name", "date_added",
		//"date_modified", "memos", "tags"};
		
		
		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "sql => Done: " + sql);
			
			//Methods.toastAndLog(actv, "Data updated", 2000);
			
			wdb.close();
			
			return true;
			
			
		} catch (SQLException e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString() + " / " + "sql: " + sql);
			
			wdb.close();
			
			return false;
		}
		
	}//public static boolean update_data_ai()

	
	public void updateData_aiLength(Activity actv, String table_name,
			long db_id, int length) {
		
		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		
		String sql = "UPDATE " + table_name + " SET " + 
				"length" + " = " + length + " "
				+ " WHERE " + android.provider.BaseColumns._ID + " = '"
				+ db_id + "'";
				
		// Log
		Log.d("DBUtils.java" + "["
		+ Thread.currentThread().getStackTrace()[2].getLineNumber()
		+ "]", "sql=" + sql);

		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", "Exec sql => Done");
			
		} catch (SQLException e) {

			// Log
			Log.e("DBUtils.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", "Exception => " + e.toString());

		}//try
		
		// Close
		wdb.close();
		
	}//public void updateData_aiLength

	public boolean
	updateData_generic
	(Activity actv, String tableName, long dbId, String colName, String colValue) {

		/***************************************
		 * Setup: DB
		 ***************************************/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		/***************************************
		 * Build SQL
		 ***************************************/
		String sql = "UPDATE " + tableName + " SET "
//				+ colName + "='" + colValue + "', "
				+ colName + "='" + colValue + "'"
				+ " WHERE " + android.provider.BaseColumns._ID + " = '" + dbId + "'";
				
		/***************************************
		 * Exec: Query
		 ***************************************/
		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "sql => Done: " + sql);
			
		//	Methods.toastAndLog(actv, "Data updated", 2000);

			wdb.close();
			
			return true;
			
			
		} catch (SQLException e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString() + " / " + "sql: " + sql);
			
			wdb.close();
			
			return false;
		}

	}//updateData_generic()

	public int
	getNumOfEntries(Activity actv, String table_name) {
		/*********************************
		 * memo
		 *********************************/
//		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase rdb = this.getReadableDatabase();

		String sql = "SELECT * FROM " + table_name;
		
		Cursor c = null;
		
		try {
			
			c = rdb.rawQuery(sql, null);
			
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return -1;
		}
		
		int num_of_entries = c.getCount();
		
		rdb.close();

		return num_of_entries;
		
	}//public int getNumOfEntries(Activity actv, String table_name)

	public int
	getNumOfEntries_BM(Activity actv, String table_name, long aiDbId) {
		/*********************************
		 * memo
		 *********************************/
//		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase rdb = this.getReadableDatabase();

		String sql = "SELECT * FROM " + table_name
					+ " WHERE "
					+ "ai_id = "
					+ aiDbId;
		
		Cursor c = null;
		
		try {
			
			c = rdb.rawQuery(sql, null);
			
		} catch (Exception e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return -1;
		}
		
		int num_of_entries = c.getCount();
		
		rdb.close();

		return num_of_entries;
		
	}//public int getNumOfEntries_BM(Activity actv, String table_name, long aiDbId)

	
	
	
	
	
	
//	private static ContentValues 
//	_insert_Data_Patterns__ContentValues
//	(String pattern) {
//		// TODO Auto-generated method stub
//		ContentValues val = new ContentValues();
//		
////		android.provider.BaseColumns._ID,		// 0
////		"created_at", "modified_at",			// 1,2
////		"word",									// 3
////		"table_name"							// 4		
//		
//		val.put(
//				CONS.DB.col_names_MemoPatterns_full[2],		// modified_at 
//				Methods.conv_MillSec_to_TimeLabel(
//						Methods.getMillSeconds_now()));
//		
//		val.put(
//				CONS.DB.col_names_MemoPatterns_full[3],		// word
//				pattern);
//		
//		return val;
//		
//	}//_insert_Data_Patterns__ContentValues

	/******************************
		@return
			-1 => Unknown sql type<br>
			-2 => Exception<br>
			1 => Sql done<br>
	 ******************************/
	public static int 
	exec_Sql
	(Activity actv, String sql_Type) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// setup

		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();		
		
		String sql = null;

		////////////////////////////////

		// dispatch

		////////////////////////////////
//		if (sql_Type.equals(
//				CONS.DB.Sqls._20140817_154650_addCol_IFM11_UpdatedAt_TITLE)) {
//			
//			sql = CONS.DB.Sqls._20140817_154650_addCol_IFM11_UpdatedAt_SQL;
//			
//		} else {
//
//			wdb.close();
//			
//			// Log
//			String msg_Log = "Unknown sql type => " + sql_Type;
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			return -1;
//			
//		}
		
		try {
			
			wdb.execSQL(sql);
			
			// Log
			String msg_Log = "sql done => " + sql;
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (Exception e) {
			// TODO: handle exception

			// Log
			String msg_Log = "Exception => " + e.toString();
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			wdb.close();
			
			return -2;
			
		}
		
		
		////////////////////////////////

		// close

		////////////////////////////////
		wdb.close();
		
		return 1;
		
	}//exec_Sql

	/******************************
		@return -1 => Table doesn't exist<br>
	 ******************************/
	public static int 
	insert_Data_Patterns
	(Activity actv, List<String> patterns_List) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		////////////////////////////////
	
		// validate: table exists
	
		////////////////////////////////
		if (!DBUtils.tableExists(
					actv, CONS.DB.dbName, CONS.DB.tname_Patterns)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + CONS.DB.tname_Patterns);
			
//			String msg = "Table doesn't exist => " + CONS.DB.tname_Patterns;
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
	
		// Iteration
	
		////////////////////////////////
		int counter = 0;
		
		ContentValues val = null;
	//	
		for (String pattern : patterns_List) {
			
			////////////////////////////////
			
			// prep: content values
			
			////////////////////////////////
			val = _insert_Data_Patterns__ContentValues(pattern);
			
			try {
				// Start transaction
				wdb.beginTransaction();
				
				// Insert data
				long res = wdb.insert(CONS.DB.tname_Patterns, null, val);
	//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
			
				if (res == -1) {
					
					// Log
					String msg_Log = "insertion => failed: " + pattern;
					Log.e("DBUtils.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
	
				} else {
					
	//				// Log
	//				String msg_Log = "insertion => done";
	//				Log.d("DBUtils.java"
	//						+ "["
	//						+ Thread.currentThread().getStackTrace()[2]
	//								.getLineNumber() + "]", msg_Log);
					
					counter += 1;
					
					// Set as successful
					wdb.setTransactionSuccessful();
					
				}
				
	//			// Set as successful
	//			wdb.setTransactionSuccessful();
				
				// End transaction
				wdb.endTransaction();
				
			} catch (Exception e) {
				
				// Log
				// Log
				String msg_Log = String.format(
									"Exception(%s) => %s", 
									pattern, e.toString());
				Log.e("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//try
			
		}//for (String pattern : patterns_List)
	
		////////////////////////////////
	
		// close
	
		////////////////////////////////
		wdb.close();
	
		////////////////////////////////
	
		// return
	
		////////////////////////////////
		return counter;
		
	}//insert_Data_Patterns

	private static ContentValues 
	_insert_Data_Patterns__ContentValues
	(String pattern) {
		// TODO Auto-generated method stub
		ContentValues val = new ContentValues();
		
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		"word",									// 3
//		"table_name"							// 4
		
		val.put(
				"created_at", 
				Methods.conv_MillSec_to_TimeLabel(
								Methods.getMillSeconds_now()));
		
		val.put(
				"modified_at", 
				Methods.conv_MillSec_to_TimeLabel(
						Methods.getMillSeconds_now()));
		
		val.put("word", pattern);
		
//		val.put("table_name", CONS.DB.tname_IFM11);

		return val;
		
	}//_insert_Data_Patterns__ContentValues

	/******************************
		@return
		
	 ******************************/
	public static List<WordPattern> 
	find_All_WP
	(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate: DB file exists?

		////////////////////////////////
		File dpath_DBFile = actv.getDatabasePath(CONS.DB.dbName);

		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + CONS.DB.dbName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		////////////////////////////////

		// DB

		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		boolean res = dbu.tableExists(rdb, CONS.DB.tname_Patterns);
//		boolean res = dbu.tableExists(rdb, tableName);

		if (res == false) {
			
			String msg = "No such table: " + CONS.DB.tname_Patterns;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}

		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
//		String where = CONS.DB.col_names_IFM11[8] + " = ?";
//		String[] args = new String[]{
//				
//							tableName
//						};
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_Patterns,			// 1
					CONS.DB.col_names_Patterns_full,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {

			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			String msg = "Query exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query failed";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);

			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);

			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		"word",									// 3
		
		List<WordPattern> list_WP = new ArrayList<WordPattern>();
		
		while(c.moveToNext()) {
			
			WordPattern wp = new WordPattern.Builder()

					.setDb_Id(c.getLong(0))
					.setCreated_at(c.getString(1))
					.setModified_at(c.getString(2))
					
					.setWord(c.getString(3))
					
					.build();
			
			list_WP.add(wp);
			
		}

		rdb.close();
		
		return list_WP;
		
	}//find_All_WP

	/******************************
		@return
			null => 
				1. No DB file<br>
				2. No such file<br>
				3. Query exception<br>
				4. Query failed<br>
				5. No entry in the table<br>
	 ******************************/
	public static List<Memo> 
	find_All_Memos
	(Activity actv, CONS.Enums.SortOrder order) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		File dpath_DBFile = actv.getDatabasePath(CONS.DB.dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + CONS.DB.dbName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_TA2;
		boolean res = dbu.tableExists(rdb, tname);
//		boolean res = dbu.tableExists(rdb, tableName);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		String orderBy = null;
		
		if (order == CONS.Enums.SortOrder.ASC) {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_ASC;
			
		} else if (order == CONS.Enums.SortOrder.DESC) {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_DESC;
			
		} else {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_ASC;

		}
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_names_TA2_full,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					orderBy);		// 7
//			orderBy,			// 7
//			null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			String msg = "Query exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query failed";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7		
		
		List<Memo> list_Memos = new ArrayList<Memo>();
		
		while(c.moveToNext()) {
			
			Memo wp = new Memo.Builder()
			
			.setDb_Id(c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))

			.setText(c.getString(3))
			
			.setUploaded_at(c.getString(4))
			
			.setTwted_at(c.getString(5))
			.setTwt_Id(c.getLong(6))
			.setTwt_created_at(c.getString(7))
			
			.build();
			
			list_Memos.add(wp);
			
		}
		
		rdb.close();
		
		return list_Memos;
		
	}//find_All_WP
	
	/******************************
		@return
			null => 
				1. No DB file<br>
				2. No such file<br>
				3. Query exception<br>
				4. Query failed<br>
				5. No entry in the table<br>
	 ******************************/
	public static List<Memo> 
	find_All_Memos
	(Activity actv, 
		CONS.Enums.SortOrder order, int limit) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		File dpath_DBFile = actv.getDatabasePath(CONS.DB.dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + CONS.DB.dbName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_TA2;
		boolean res = dbu.tableExists(rdb, tname);
//		boolean res = dbu.tableExists(rdb, tableName);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		String orderBy = null;
		
		if (order == CONS.Enums.SortOrder.ASC) {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_ASC;
			
		} else if (order == CONS.Enums.SortOrder.DESC) {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_DESC;
			
		} else {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_ASC;
			
		}
		
		String limit_Memos = String.valueOf(limit);
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_names_TA2_full,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					orderBy,
					limit_Memos);		// 7
//			orderBy);		// 7
//			orderBy,			// 7
//			null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			String msg = "Query exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query failed";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7		
		
		List<Memo> list_Memos = new ArrayList<Memo>();
		
		while(c.moveToNext()) {
			
			Memo wp = new Memo.Builder()
			
			.setDb_Id(c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setText(c.getString(3))
			
			.setUploaded_at(c.getString(4))
			
			.setTwted_at(c.getString(5))
			.setTwt_Id(c.getLong(6))
			.setTwt_created_at(c.getString(7))
			
			.build();
			
			list_Memos.add(wp);
			
		}
		
		rdb.close();
		
		return list_Memos;
		
	}//find_All_WP
	
	/******************************
		@return
			null => 
				1. No DB file<br>
				2. No such file<br>
				3. Query exception<br>
				4. Query failed<br>
				5. No entry in the table<br>
	 ******************************/
	public static List<Memo> 
	find_All_Memos_conditions
	(Activity actv, 
		CONS.Enums.SortOrder order, String where, String[] args) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		File dpath_DBFile = actv.getDatabasePath(CONS.DB.dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + CONS.DB.dbName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_TA2;
		boolean res = dbu.tableExists(rdb, tname);
//		boolean res = dbu.tableExists(rdb, tableName);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		String orderBy = null;
		
		if (order == CONS.Enums.SortOrder.ASC) {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_ASC;
			
		} else if (order == CONS.Enums.SortOrder.DESC) {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_DESC;
			
		} else {
			
			orderBy = CONS.DB.col_names_TA2_full[0] + " " + CONS.DB.sortOrder_ASC;
			
		}
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_names_TA2_full,	// 2
					where, args,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					orderBy);		// 7
//			orderBy,			// 7
//			null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			String msg = "Query exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query failed";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7		
		
		List<Memo> list_Memos = new ArrayList<Memo>();
		
		while(c.moveToNext()) {
			
			Memo wp = new Memo.Builder()
			
			.setDb_Id(c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setText(c.getString(3))
			
			.setUploaded_at(c.getString(4))
			
			.setTwted_at(c.getString(5))
			.setTwt_Id(c.getLong(6))
			.setTwt_created_at(c.getString(7))
			
			.build();
			
			list_Memos.add(wp);
			
		}
		
		rdb.close();
		
		return list_Memos;
		
	}//find_All_WP
	
	/******************************
		@return
			null => 
				1. No DB file<br>
				2. No such file<br>
				3. Query exception<br>
				4. Query failed<br>
				5. No entry in the table<br>
	 ******************************/
	public static List<Memo> 
	find_All_Memos
	(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		File dpath_DBFile = actv.getDatabasePath(CONS.DB.dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + CONS.DB.dbName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_TA2;
		boolean res = dbu.tableExists(rdb, tname);
//		boolean res = dbu.tableExists(rdb, tableName);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		String orderBy = CONS.DB.col_names_TA2_full[0] + " ASC";
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_names_TA2_full,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					orderBy);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			String msg = "Query exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query failed";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7		
		
		List<Memo> list_Memos = new ArrayList<Memo>();
		
		while(c.moveToNext()) {
			
			Memo wp = new Memo.Builder()
			
			.setDb_Id(c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setText(c.getString(3))
			
			.setUploaded_at(c.getString(4))
			
			.setTwted_at(c.getString(5))
			.setTwt_Id(c.getLong(6))
			.setTwt_created_at(c.getString(7))
			
			.build();
			
			list_Memos.add(wp);
			
		}
		
		rdb.close();
		
		return list_Memos;
		
	}//find_All_WP
	
	/******************************
		@return
			null => 
				1. No DB file<br>
				2. No such file<br>
				3. Query exception<br>
				4. Query failed<br>
				5. No entry in the table<br>
	 ******************************/
	public static Memo 
	find_Memo_From_Id
	(Activity actv, long db_Id) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		File dpath_DBFile = actv.getDatabasePath(CONS.DB.dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + CONS.DB.dbName;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_TA2;
		boolean res = dbu.tableExists(rdb, tname);
//		boolean res = dbu.tableExists(rdb, tableName);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		String where = CONS.DB.col_names_Patterns_full[0] + " = ?";
		String[] args = new String[]{
				
				String.valueOf(db_Id)
							
						};
		
//		try {
//			
//			c = db.query(
//					
//					CONS.DB.tname_Patterns,			// 1
//					CONS.DB.col_names_Patterns,	// 2
////					null, null,		// 3,4
//					where, args,		// 3,4
//					null, null,		// 5,6
//					null,			// 7
//					null);

//		String orderBy = CONS.DB.col_names_TA2_full[0] + " ASC";
//		db_Id
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_names_TA2_full,	// 2
//					null, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			String msg = "Query exception";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			String msg = "Query failed";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			String msg = "No entry in the table";
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"text",									// 3
//		"uploaded_at",							// 4
//		"twted_at",								// 5
//		
//		"twt_id",								// 6
//		"twt_created_at",						// 7		
		
//		List<Memo> list_Memos = new ArrayList<Memo>();
//		
//		while(c.moveToNext()) {
		
		c.moveToFirst();
			
			Memo memo = new Memo.Builder()
			
			.setDb_Id(c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setText(c.getString(3))
			
			.setUploaded_at(c.getString(4))
			
			.setTwted_at(c.getString(5))
			.setTwt_Id(c.getLong(6))
			.setTwt_created_at(c.getString(7))
			
			.build();
			
//			list_Memos.add(wp);
//			
//		}
		
		rdb.close();
		
		return memo;
		
	}//find_Memo_From_Id
	
	/******************************
		@return
		
	 ******************************/
	public static List<WordPattern> 
	find_All_WP_symbols
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get all WP

		////////////////////////////////
		List<WordPattern> list_WP = DBUtils.find_All_WP(actv);

		////////////////////////////////
		
		// prep: filter
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/1047342/how-to-run-a-query-with-regexp-in-android answered Jun 26 '09 at 5:25
		//REF http://ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/ "2.4. Extracting/Capturing"
		String regex = "[a-zA-Z]";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = null;
		
		////////////////////////////////

		// filter

		////////////////////////////////
		List<WordPattern> list_WP_filtered = 
								new ArrayList<WordPattern>();
		
		// If the word DOES NOT contain [a-zA-Z]
		//		=> then, put it into the new list
		for (WordPattern wp : list_WP) {
			
			m = p.matcher(wp.getWord());
			
			if (m.find()) {
				
				continue;
				
			}
			
			list_WP_filtered.add(wp);
			
		}
		
		
		return list_WP_filtered;
		
	}//find_All_WP_symbols
	
	/******************************
		@return
		
	 ******************************/
	public static List<WordPattern> 
	find_All_WP_tags
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// get all WP
		
		////////////////////////////////
		List<WordPattern> list_WP = DBUtils.find_All_WP(actv);
		
		////////////////////////////////
		
		// prep: filter
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/1047342/how-to-run-a-query-with-regexp-in-android answered Jun 26 '09 at 5:25
		//REF http://ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/ "2.4. Extracting/Capturing"
//		String regex = "^[:#]";
//		String regex = "^(:|\\#)";
//		String regex = "^(:|#)";
//		String regex = "^(:|#)[a-zA-Z]";
		String regex = "^[:#][a-zA-Z]";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = null;
		
		////////////////////////////////
		
		// filter
		
		////////////////////////////////
		List<WordPattern> list_WP_filtered = 
				new ArrayList<WordPattern>();
		
		// If the word DOES NOT contain [a-zA-Z]
		//		=> then, put it into the new list
		for (WordPattern wp : list_WP) {
			
			m = p.matcher(wp.getWord());
			
			if (m.find()) {
				
				list_WP_filtered.add(wp);
//				continue;
				
			}
			
//			// Log
//			String msg_Log = String.format("match => %s", wp.getWord());
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
//			list_WP_filtered.add(wp);
			
		}
		
		
		return list_WP_filtered;
		
	}//find_All_WP_tags
	
	/******************************
		@return
		
	 ******************************/
	public static List<WordPattern> 
	find_All_WP_literals
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// get all WP
		
		////////////////////////////////
		List<WordPattern> list_WP = DBUtils.find_All_WP(actv);
		
		////////////////////////////////
		
		// prep: filter
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/1047342/how-to-run-a-query-with-regexp-in-android answered Jun 26 '09 at 5:25
		//REF http://ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/ "2.4. Extracting/Capturing"
//		String regex = "^[:#]";
//		String regex = "^(:|\\#)";
//		String regex = "^(:|#)";
//		String regex = "^(:|#)[a-zA-Z]";
//		String regex = "^[:#][a-zA-Z]";
		String reg1 = "^[:#][a-zA-Z]";
		String reg2 = "^[^\\w]+$";
		
		Pattern p1_tags = Pattern.compile(reg1);
		Pattern p2_symbols = Pattern.compile(reg2);
		
		Matcher m1_tags = null;
		Matcher m2_symbols = null;
		
		////////////////////////////////
		
		// filter
		
		////////////////////////////////
		List<WordPattern> list_WP_filtered = 
				new ArrayList<WordPattern>();
		
		// If the word DOES NOT contain [a-zA-Z]
		//		=> then, put it into the new list
		for (WordPattern wp : list_WP) {
			
			m1_tags = p1_tags.matcher(wp.getWord());
			m2_symbols = p2_symbols.matcher(wp.getWord());
			
			// Neither tag, nor symbol
			if (!m1_tags.find() && !m2_symbols.find()) {
				
				list_WP_filtered.add(wp);
//				continue;
				
			}
			
//			// Log
//			String msg_Log = String.format("match => %s", wp.getWord());
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
//			list_WP_filtered.add(wp);
			
		}
		
		
		return list_WP_filtered;
		
	}//find_All_WP_literals

	/******************************
		@return
			-1	insertion => failed<br>
			-2	Exception<br>
			1	text => inserted<br>
	 ******************************/
	public static int 
	save_Memo
	(Activity actv, String text) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		////////////////////////////////
		
		// prep: content values
		
		////////////////////////////////
		ContentValues val = _build_Values__Memo(actv, text);
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// Insert data
			long res = wdb.insert(CONS.DB.tname_TA2, null, val);
//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
			
			if (res == -1) {
				
				// Log
				String msg_Log = "insertion => failed";
				Log.e("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				wdb.endTransaction();
				wdb.close();
				
				return -1;
				
			} else {
				
				// Log
				String msg_Log = "insertion => done";
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
			// Set as successful
			wdb.setTransactionSuccessful();
			
			// End transaction
			wdb.endTransaction();
			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data inserted => " + "(" + columnNames[0] + " => " + values[0] + "), and others");
			
			wdb.close();
			
			return 1;
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception! => " + e.toString());
			
			wdb.close();
			
			return -2;
			
		}//try
		
	}//save_Memo

	private static ContentValues 
	_build_Values__Memo
	(Activity actv, String text) {
		// TODO Auto-generated method stub
		ContentValues val = new ContentValues();
		
		
		val.put("created_at",
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		val.put("modified_at",
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put("text", text);
		
		return val;
		
	}//_build_Values__TI

	private static ContentValues 
	_build_Values__Pattern
	(Activity actv, String word) {
		// TODO Auto-generated method stub
		ContentValues val = new ContentValues();
		
		
		val.put("created_at",
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		val.put("modified_at",
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put("word", word);
		
		return val;
		
	}//_build_Values__TI
	
	/******************************
		@return
			-1	insertion => failed<br>
			-2	Exception<br>
			-3	pattern already in DB<br>
			1	Inserted<br>
	 ******************************/
	public static int 
	save_Pattern
	(Activity actv, String new_Word) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		////////////////////////////////

		// validate: exists?

		////////////////////////////////
		int res_i = DBUtils.isInDB_Pattern(wdb, new_Word);
		
		if (res_i == 1) {
			
			// Log
			String msg_Log = "pattern already in DB => " + new_Word;
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			wdb.close();
			
			return -3;
			
		}
		
		////////////////////////////////
		
		// prep: content values
		
		////////////////////////////////
		ContentValues val = _build_Values__Pattern(actv, new_Word);
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// Insert data
			long res = wdb.insert(CONS.DB.tname_Patterns, null, val);
//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
			
			if (res == -1) {
				
				// Log
				String msg_Log = "insertion => failed";
				Log.e("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				wdb.endTransaction();
				wdb.close();
				
				return -1;
				
			} else {
				
				// Log
				String msg_Log = "insertion => done";
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
			// Set as successful
			wdb.setTransactionSuccessful();
			
			// End transaction
			wdb.endTransaction();
			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data inserted => " + "(" + columnNames[0] + " => " + values[0] + "), and others");
			
			wdb.close();
			
			return 1;
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception! => " + e.toString());
			
			wdb.close();
			
			return -2;
			
		}//try
		
	}//save_Pattern

	/******************************
		@return
			-1	Table doesn't exist<br>
			-2	Deletion => failed<br>
			> 1	Deletion => done<br>
	 ******************************/
	public static int 
	delete_Memo
	(Activity actv, Memo memo) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		////////////////////////////////

		// validate: table exists

		////////////////////////////////
		if (!DBUtils.tableExists(actv, CONS.DB.dbName, CONS.DB.tname_TA2)) {
			// Log
			Log.i("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + CONS.DB.tname_TA2);
			
			wdb.close();
			
			return -1;

		}//if (!tableExists(SQLiteDatabase db, String tableName))

		////////////////////////////////

		// delete

		////////////////////////////////
		////////////////////////////////

		// Query

		////////////////////////////////
		String where = CONS.DB.col_names_TA2_full[0] + " = ?";
//		String where = CONS.DB.col_names_IFM11[1] + " = ?";
		
		String[] args = new String[]{
				
							String.valueOf(memo.getDb_Id())
							
						};
 
		int res_int = wdb.delete(CONS.DB.tname_TA2, where, args);
		
		// Log
		String msg_Log = "res_int => " + res_int;
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// report

		////////////////////////////////
		switch(res_int) {
		
		case 0:
			
			// Log
			msg_Log = "deletion => failed";
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			wdb.close();
			
			return -2;
			
		default:
			
			wdb.close();
			
			return res_int;
				
		}
		
	}//delete_Memo
	
	/******************************
		@return
			-1	Table doesn't exist<br>
			-2	Deletion => failed<br>
			> 1	Deletion => done<br>
	 ******************************/
	public static int 
	delete_Pattern
	(Activity actv, WordPattern wp) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		////////////////////////////////
		
		// validate: table exists
		
		////////////////////////////////
		String tname = CONS.DB.tname_Patterns;
				
		if (!DBUtils.tableExists(actv, CONS.DB.dbName, tname)) {
			// Log
			Log.i("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			wdb.close();
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
		
		// delete
		
		////////////////////////////////
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		String where = CONS.DB.col_names_Patterns_full[0] + " = ?";
//		String where = CONS.DB.col_names_IFM11[1] + " = ?";
		
		String[] args = new String[]{
				
				String.valueOf(wp.getDb_Id())
				
		};
		
		int res_int = wdb.delete(tname, where, args);
		
		// Log
		String msg_Log = "res_int => " + res_int;
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		switch(res_int) {
		
		case 0:
			
			// Log
			msg_Log = "deletion => failed";
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			wdb.close();
			
			return -2;
			
		default:
			
			wdb.close();
			
			return res_int;
			
		}
		
	}//delete_Memo
	
}//public class DBUtils

