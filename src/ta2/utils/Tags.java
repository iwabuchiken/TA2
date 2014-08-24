package ta2.utils;

public class Tags {

	public enum SwipeTags {
		
		ACTV_MAIN, ACTV_MEMO,

	}

	public static enum ButtonTags {
		// actv_main
		ACTV_MAIN_MEMO, LIST, LOGIN, LOGOUT,
		ACTV_MAIN_SHOWLIST,
		
		// actv_twt
		BACK, SEND_TWEET, PATTERN,
		
		// actv_memo
		ACTV_MEMO_BACK, ACTV_MEMO_CLEAR, ACTV_MEMO_SAVE,
		
		// actv: ShowList
		ACTV_SHOWLIST_BACK,
		
	}
	
	public static enum ListTags {
		// MainActivity.java
		actv_main_lv,
		
		// Main
		admin_adapter,
		
		actv_main_lv_locs,
		
		// MemoActv
		ACTV_MEMO_LV_1, ACTV_MEMO_LV_2, ACTV_MEMO_LV_3,
		
	}//public static enum ListTags

	public static enum 
	DialogTags {
		// Generics
		GENERIC_DISMISS, GENERIC_DISMISS_SECOND_DIALOG,
		GENERIC_DISMISS_THIRD_DIALOG,
		
		GENERIC_CLEAR_SECOND_DIALOG,
		
		dlg_generic_dismiss,
		
		dlg_generic_dismiss_second_dialog,
		
		dlg_generic_dismiss_third_dialog,
		
		dlg_edit_locs_btn_ok,
		
		dlg_register_patterns_register,
		
		dlg_Delete_PatternsItem_OK,
		
		// dlg_filter_timeline
		dlg_Filter_Timeline_OK, dlg_Filter_Timeline_Reset,
		
		// dlg: import db => ok
		DLG_CONF_IMPORT_DB_OK,
		
		// dlg: import patterns => ok
		DLG_CONF_IMPORT_PATTERNS_OK,
		
		// dlg: create/drop table: patterns
		DLG_CONF_CREATE_TABLE_PATTERNS_OK, DLG_CONF_DROP_TABLE_PATTERNS_OK,
		
		// dlg: conf: restore db
		DLG_CONF_RESTORE_DB_OK,
		
		DLG_CONF_CLEAR_VIEW_OK,
		
		DLG_CONF_CREATE_TABLE_MEMOS_OK,
		DLG_CONF_DROP_TABLE_MEMOS_OK,
		
	}//public static enum DialogTags

	public static enum DialogItemTags {
		// dlg_db_admin.xml
		ACTV_MAIN_ADMIN_LV, ACTV_MAIN_ADMIN_LV_OPS,
		
		GV_Tweet,
		
		GV_Filter_Timeline,
		
		AdminPatterns_LV,
		
		AdminPatterns_Item_LV,
		
		HoriLV_TwtActv,
		
	}//public static enum DialogItemTags

}
