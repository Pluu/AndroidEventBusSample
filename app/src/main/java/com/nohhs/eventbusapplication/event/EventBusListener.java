package com.nohhs.eventbusapplication.event;

import android.util.Log;

/**
 * Created by nohhs on 2015-07-21.
 */
public class EventBusListener {
	private final String TAG = EventBusListener.class.getSimpleName();

	public void onEvent(String text) {
		Log.d(TAG, text);
	}

}
