package com.nohhs.eventbusapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.common.eventbus.Subscribe;
import com.nohhs.eventbusapplication.event.GuavaBusHolder;
import com.nohhs.eventbusapplication.event.ResponseEvent;

public class GuavaActivity extends AppCompatActivity {

	@Bind(R.id.textView)
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guava);
		ButterKnife.bind(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		GuavaBusHolder.get().register(this);
	}

	@Override
	public void onPause() {
		GuavaBusHolder.get().unregister(this);
		super.onPause();
	}

	@Subscribe
	public void responseNetwork(ResponseEvent result) {
		textView.setText(result.getObj());
	}

}
