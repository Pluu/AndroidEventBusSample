package com.nohhs.eventbusapplication.event;

import android.util.Log;

import com.google.common.eventbus.Subscribe;

/**
 * Created by nohhs on 2015-07-21.
 */
public class GuavaEventListener {

	private final String TAG = GuavaEventListener.class.getSimpleName();

	@Subscribe
	public void listen(String text) {
		Log.d(TAG, text);
	}

}
