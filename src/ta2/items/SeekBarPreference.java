package ta2.items;

import ta2.main.R;
import ta2.utils.CONS;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarPreference extends Preference implements
		OnSeekBarChangeListener {

	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		setWidgetLayoutResource(R.layout.preference_widget_seekbar);
		
	}

	@Override
	public void onProgressChanged(SeekBar seekbar, int progress, boolean arg2) {
		// TODO Auto-generated method stub

//		int progress = seekbar.getProgress();
        /* ユーザーが設定変更を行った後(内部的な値を設定する前)に呼び出す。 */
        CONS.Pref.currentProgress = (callChangeListener(progress))?progress:CONS.Pref.oldProgress;

        persistInt(CONS.Pref.currentProgress);
        CONS.Pref.oldProgress = CONS.Pref.currentProgress;
        
        // Log
		String msg_Log = "onStopTrackingTouch";
		Log.d("SeekBarPreference.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
        
		////////////////////////////////

		// update: summary

		////////////////////////////////
		this.setSummary("Current = " + String.valueOf(CONS.Pref.currentProgress));

	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

//		// Log
//		String msg_Log = "onStartTrackingTouch";
//		Log.d("SeekBarPreference.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekbar) {
		// TODO Auto-generated method stub

		int progress = seekbar.getProgress();
        /* ユーザーが設定変更を行った後(内部的な値を設定する前)に呼び出す。 */
        CONS.Pref.currentProgress = (callChangeListener(progress))?progress:CONS.Pref.oldProgress;

        persistInt(CONS.Pref.currentProgress);
        CONS.Pref.oldProgress = CONS.Pref.currentProgress;
        
        // Log
		String msg_Log = "onStopTrackingTouch";
		Log.d("SeekBarPreference.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
        
		////////////////////////////////

		// update: summary

		////////////////////////////////
		this.setSummary("Current = " + String.valueOf(CONS.Pref.currentProgress));
		
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		// TODO Auto-generated method stub
		
		return a.getInteger(index, CONS.Pref.DEFAULT_PROGRESS);
		
//		return super.onGetDefaultValue(a, index);
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue,
			Object defaultValue) {
		// TODO Auto-generated method stub
		
		if (restorePersistedValue) {
            CONS.Pref.currentProgress = getPersistedInt(CONS.Pref.currentProgress);
        } else {
        	CONS.Pref.currentProgress = (Integer) defaultValue;
            persistInt(CONS.Pref.currentProgress);
        }
		
		CONS.Pref.oldProgress = CONS.Pref.currentProgress;
		
		
//		super.onSetInitialValue(restorePersistedValue, defaultValue);
	}

	@Override
	protected void onBindView(View view) {
		// TODO Auto-generated method stub
		final SeekBar seekbar = (SeekBar) view.findViewById(R.id.pref_seekbar);
		
        if (seekbar != null) {
            seekbar.setProgress(CONS.Pref.currentProgress);
            seekbar.setMax(CONS.Pref.MAX_PROGRESS);
            seekbar.setOnSeekBarChangeListener(this);
            
            ////////////////////////////////

			// set: summary

			////////////////////////////////
    		this.setSummary("Current = " + String.valueOf(CONS.Pref.currentProgress));

        }
        
		super.onBindView(view);
	}

}
