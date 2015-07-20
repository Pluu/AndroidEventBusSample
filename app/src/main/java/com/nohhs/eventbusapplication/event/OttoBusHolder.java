package com.nohhs.eventbusapplication.event;

import com.squareup.otto.Bus;

/**
 * Created by nohhs on 2015-07-17.
 */
public final class OttoBusHolder extends Bus {

	private static final Bus mBus = new OttoMainThreadBus();

	public static Bus get() {
		return mBus;
	}

}
