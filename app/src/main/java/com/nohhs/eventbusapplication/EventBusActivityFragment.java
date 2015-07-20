package com.nohhs.eventbusapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nohhs.eventbusapplication.event.ResponseEvent;
import com.nohhs.eventbusapplication.event.ResponseFailEvent;
import com.nohhs.eventbusapplication.network.EventBusTestNetwork;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class EventBusActivityFragment extends Fragment {

	@Bind(R.id.textView)
	TextView textView;

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
	}

	@Override
	public void onPause() {
		EventBus.getDefault().unregister(this);
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

	public void onEventMainThread(ResponseEvent result){
		textView.setText(result.getObj());
	}

	public void onEventMainThread(ResponseFailEvent result){
		Toast.makeText(getActivity(), result.getThrowable().getMessage(), Toast.LENGTH_SHORT)
			 .show();
	}

}
