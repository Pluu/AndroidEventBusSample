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
import com.nohhs.eventbusapplication.event.OttoBusHolder;
import com.nohhs.eventbusapplication.event.ResponseEvent;
import com.nohhs.eventbusapplication.event.ResponseFailEvent;
import com.nohhs.eventbusapplication.network.OttoTestNetwork;
import com.squareup.otto.Subscribe;

/**
 * A placeholder fragment containing a simple view.
 */
public class OttoActivityFragment extends Fragment {

	@Bind(R.id.textView)
	TextView textView;

	public OttoActivityFragment() { }

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
		OttoBusHolder.get().register(this);
	}

	@Override
	public void onPause() {
		OttoBusHolder.get().unregister(this);
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}

	@OnClick(R.id.button)
	public void clickNetworkCall() {
		new OttoTestNetwork().callType1();
	}

	@OnClick(R.id.button2)
	public void clickLocalCall() {
		OttoBusHolder.get().post(new ResponseEvent("Local Message Send"));
	}

	@Subscribe
	public void responseNetwork(ResponseEvent result) {
		textView.setText(result.getObj());
	}

	@Subscribe
	public void responseFailNetwork(ResponseFailEvent result) {
		Toast.makeText(getActivity(), result.getThrowable().getMessage(), Toast.LENGTH_SHORT)
			 .show();
	}

}
