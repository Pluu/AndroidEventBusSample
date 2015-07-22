package com.nohhs.eventbusapplication.event;

import android.util.Log;

import com.squareup.otto.Subscribe;

/**
 * Created by nohhs on 2015-07-21.
 */
public class OttoListener {

	private final String TAG = OttoListener.class.getSimpleName();

	@Subscribe
	public void listen(String text) {
		Log.d(TAG, text);
	}

}