package ta2.utils;

public class Tags {

	public enum SwipeTags {
		
		ACTV_MAIN, ACTV_MEMO, ACTV_SHOWLIST_BASE, ACTV_BM,

	}

	public static enum ButtonTags {
		// actv_main
		ACTV_MAIN_MEMO, LIST, LOGIN, LOGOUT,
		ACTV_MAIN_SHOWLIST,
		ACTV_MAIN_VOICE, 
		
		
		// actv_twt
		BACK, SEND_TWEET, PATTERN,
		
		// actv_memo
		ACTV_MEMO_BACK, ACTV_MEMO_CLEAR, ACTV_MEMO_SAVE,
		
		// actv: ShowList
		ACTV_SHOWLIST_BACK, 
		ACTV_SHOWLIST_TOP, 
		ACTV_SHOWLIST_UP, 
		ACTV_SHOWLIST_DOWN, 
		ACTV_SHOWLIST_BOTTOM, 
		
		ACTV_MEMO_EDIT_SAVE, 
		ACTV_MEMO_EDIT_BACK, 
		
		ACTV_REC_REC, 
		ACTV_REC_STOP, 
		ACTV_REC_BACK, 
		
		ACTV_PLAY_PLAY, 
		ACTV_PLAT_STOP, 
		ACTV_PLAY_BACK, 
		ACTV_PLAY_SAVE, 
		ACTV_PLAY_CLEAR, 
		ACTV_PLAY_TV, 
		
		ACTV_MAIN_PHOTO, 
		
		image_activity_next, 
		image_activity_back, 
		image_activity_prev, 
		
		ACTV_PHOTO_BACK, 
		ACTV_PHOTO_TOP, 
		ACTV_PHOTO_UP, 
		ACTV_PHOTO_DOWN, 
		ACTV_PHOTO_BOTTOM, ACTV_IMAGE_BACK, ACTV_SHOWLOG_IB_BACK, ACTV_SHOWLOG_IB_TOP, ACTV_SHOWLOG_IB_BOTTOM, ACTV_SHOWLOG_IB_DOWN, ACTV_SHOWLOG_IB_UP, ACTV_PLAYER_PLAY, ACTV_PLAYER_STOP, ACTV_PLAYER_BACK, ACTV_PLAYER_TV, ACTV_IMPORT_BACK, ACTV_IMPORT_TOP, ACTV_IMPORT_UP, ACTV_IMPORT_DOWN, ACTV_IMPORT_BOTTOM, ACTV_PLAYER_BT_SEE_BM, ACTV_PLAYER_BT_ADD_BM, actv_bm_bt_back, actv_bm_ib_bottom, actv_bm_ib_top, actv_bm_ib_down, actv_bm_ib_up,
		
	}
	
	public static enum ListTags {
		// MainActivity.java
		actv_main_lv,
		
		// Main
		admin_adapter,
		
		actv_main_lv_locs,
		
		// MemoActv
		ACTV_MEMO_LV_1, ACTV_MEMO_LV_2, ACTV_MEMO_LV_3,
		
		// ShowListActv
		ACTV_SHOWLIST_LV, 
		
		ACTV_REC_LV_1, 
		ACTV_REC_LV_2, 
		ACTV_REC_LV_3, 
		
		ACTV_PHOTO_LV, 
		
		ACTV_IMPORTACTV_LV, ACTV_BM_LV,
		
	}//public static enum ListTags

	public static enum 
	DialogTags {
		// Generics
		GENERIC_DISMISS, GENERIC_DISMISS_SECOND_DIALOG,
		GENERIC_DISMISS_THIRD_DIALOG,
		GENERIC_DISMISS_4TH_DIALOG, 
		
		GENERIC_CLEAR_SECOND_DIALOG,
		
		dlg_generic_dismiss,
		
		dlg_generic_dismiss_second_dialog,
		
		dlg_generic_dismiss_third_dialog,
		
		dlg_edit_locs_btn_ok,
		
		dlg_register_patterns_register,
		
		dlg_Delete_PatternsItem_OK,
		
		// dlg_filter_timeline
		DLG_FILTER_SHOWLIST_OK, DLG_FILTER_SHOWLIST_CLEAR,
		
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
		
		DLG_FILTER_SHOWLIST_RESET,
		
		// register patterns
		DLG_REGISTER_PATTERNS_OK,
		
		//
		DLG_CONF_DELETE_MEMO_OK, 
		DLG_CONF_DELETE_PATTERN_OK, 
		DLG_CONF_UPLOAD_DB_OK, 
		
		DLG_CONF_ADD_COLUMN_USED_OK, 
		
		DLG_CONF_DROP_CREATE_TABLE_ADMIN_OK, 
		
		DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK, 
		
//<<<<<<< HEAD
		DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK,
//=======
		DLG_EDIT_MEMOS_BT_OK, 
		DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK, 
		DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK, 
		
		DLG_CONF_DROP_CREATE_TABLE_UPLOAD_HISTORY_OK, 
		
		DLG_CONF_UPLOAD_AUDIO_OK, 
		DLG_CONF_DROP_CREATE_TABLE_UPLOAD_HISTORY_AUDIO_OK, 
		
		DLG_FILTER_SHOWLOGACTV_OK, 
		DLG_FILTER_SHOWLIST_SHOWLOGACTV_CLEAR, 
		DLG_FILTER_SHOWLIST_SHOWLOGACTV_RESET, 
		
		DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_SHOWLOG_OK, 
		DLG_CONF_DROP_CREATE_TABLE_AUDIO_FILES_OK, 
		
		DLG_EDIT_MEMOS_BT_OK__PLAYER_ACTV, 
		
		DLG_FILTER_ACTV_IMPORT_OK, 
		DLG_FILTER_ACTV_IMPORT_CLEAR, 
		DLG_FILTER_ACTV_IMPORT_RESET, DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_AUDIOMEMO_OK, DLG_CONF_DROP_CREATE_TABLE_BM_OK,
		
		
//>>>>>>> master
		
	}//public static enum DialogTags

	public static enum SpinnerTag {
		
		SP_MEMOLIST_SIZE,
		
	}
	
	public static enum DialogItemTags {
		// dlg_db_admin.xml
		ACTV_MAIN_ADMIN_LV, ACTV_MAIN_ADMIN_LV_OPS,
		
		GV_Tweet,
		
		GV_FILTER_SHOWLIST,
		
		AdminPatterns_LV,
		
		AdminPatterns_Item_LV,
		
		HoriLV_TwtActv,
		
		// MemoActv
		ACTV_MEMO_ADMIN_PATTERNS,
		
		//
		ACTV_SHOWLIST_LV, 
		
		ACTV_MEMO_ADMIN_PATTERNS_GV, 
		
		ACTV_IMAGE_ADD_MEMO_LV_1, 
		ACTV_IMAGE_ADD_MEMO_LV_2, 
		ACTV_IMAGE_ADD_MEMO_LV_3, 
		
		ACTV_PLAY_ADD_MEMO_LV_1, 
		ACTV_PLAY_ADD_MEMO_LV_2, 
		ACTV_PLAY_ADD_MEMO_LV_3, ACTV_SHOWLIST_FILTER_HISTORY_LV, GV_FILTER_SHOWLOG, ACTV_IMPORT_LV,
		
	}//public static enum DialogItemTags

}
