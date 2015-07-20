package com.nohhs.eventbusapplication.network;

import java.io.IOException;

import com.nohhs.eventbusapplication.event.GuavaBusHolder;
import com.nohhs.eventbusapplication.event.ResponseEvent;
import com.nohhs.eventbusapplication.event.ResponseFailEvent;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by nohhs on 2015-07-17.
 */
public class GuavaTestNetwork implements Callback {

	private final OkHttpClient client = new OkHttpClient();

	public void callType1() {
		Request request = new Request.Builder()
			.url("http://sampleapi.dev-khan.net/someApi/0/1")
			.build();

		client.newCall(request).enqueue(this);
	}

	@Override
	public void onFailure(Request request, IOException e) {
		GuavaBusHolder.get().post(new ResponseFailEvent(e));
	}

	@Override
	public void onResponse(Response response) throws IOException {
		GuavaBusHolder.get().post(new ResponseEvent(response.body().string()));
	}
}
