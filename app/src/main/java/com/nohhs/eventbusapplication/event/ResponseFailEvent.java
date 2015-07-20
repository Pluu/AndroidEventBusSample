package com.nohhs.eventbusapplication.event;

/**
 * Created by nohhs on 2015-07-17.
 */
public final class ResponseFailEvent {
	private final Throwable throwable;

	public ResponseFailEvent(Throwable throwable) {
		this.throwable = throwable;
	}

	public Throwable getThrowable() {
		return throwable;
	}

}
