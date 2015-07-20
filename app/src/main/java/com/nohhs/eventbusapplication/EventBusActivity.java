package com.nohhs.eventbusapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.nohhs.eventbusapplication.event.ResponseEvent;
import de.greenrobot.event.EventBus;

public class EventBusActivity extends AppCompatActivity {

	@Bind(R.id.textView)
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_bus);
		ButterKnife.bind(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onPause() {
		EventBus.getDefault().unregister(this);
		super.onPause();
	}

	public void onEventMainThread(ResponseEvent result){
		textView.setText(result.getObj());
	}

}
