package com.nohhs.eventbusapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nohhs.eventbusapplication.event.EventBusListener;
import com.nohhs.eventbusapplication.event.ResponseEvent;
import com.nohhs.eventbusapplication.event.ResponseFailEvent;
import com.nohhs.eventbusapplication.network.EventBusTestNetwork;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class EventBusActivityFragment extends Fragment {

	private final String TAG = EventBusActivityFragment.class.getSimpleName();

	@Bind(R.id.textView)
	TextView textView;

	private final EventBusListener listener = new EventBusListener();

	public EventBusActivityFragment() { }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_detail, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		EventBus.getDefault().register(this);
		EventBus.getDefault().register(listener);
	}

	@Override
	public void onPause() {
		EventBus.getDefault().unregister(this);
		EventBus.getDefault().unregister(listener);
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}

	@OnClick(R.id.button)
	public void clickNetworkCall() {
		new EventBusTestNetwork().callType1();
	}

	@OnClick(R.id.button2)
	public void clickLocalCall() {
		EventBus.getDefault().post(new ResponseEvent("Local Message Send"));
	}

	@OnClick(R.id.button3)
	public void clickLocalCallListener() {
		EventBus.getDefault().post("300");
	}

	public void onEventMainThread(ResponseEvent result) {
		textView.setText(result.getObj());
	}

	public void onEventMainThread(ResponseFailEvent result) {
		Toast.makeText(getActivity(), result.getThrowable().getMessage(), Toast.LENGTH_SHORT)
			 .show();
	}

	public void onEventMainThread(String result) {
		Log.d(TAG, result);
		Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT)
			 .show();
	}

}
