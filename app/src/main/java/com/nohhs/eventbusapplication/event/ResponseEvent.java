package com.nohhs.eventbusapplication.event;

/**
 * Created by nohhs on 2015-07-17.
 */
public final class ResponseEvent {

	private final String obj;

	public ResponseEvent(String obj) {this.obj = obj;}

	public String getObj() {
		return obj;
	}
}
