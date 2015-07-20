package com.nohhs.eventbusapplication.event;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * Created by nohhs on 2015-07-20.
 */
public final class GuavaBusHolder {

	private static Handler handler = new Handler(Looper.getMainLooper());

	private static final EventBus mBus = new AsyncEventBus(new Executor() {
		@Override
		public void execute(Runnable command) {
			handler.post(command);
		}
	});

	public static EventBus get() {
		return mBus;
	}

}
