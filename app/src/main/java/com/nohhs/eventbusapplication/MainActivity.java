package com.nohhs.eventbusapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
	implements AdapterView.OnItemClickListener {

	private final String PATH = "com.pluusystem.eventbus.SAMPLE_CODE";
	private static final String TAG_TITLE = "title";

	@Bind(R.id.listView)
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		Intent intent = getIntent();
		String path = intent.getStringExtra(PATH);

		if (path == null) {
			path = "";
		}

		listView.setAdapter(new SimpleAdapter(this, getData(path),
											  android.R.layout.simple_list_item_1,
											  new String[]{TAG_TITLE},
											  new int[]{android.R.id.text1}));
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(this);
	}

	protected List<Map<String, Object>> getData(String prefix) {
		List<Map<String, Object>> myData = new ArrayList<>();

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(PATH);

		PackageManager pm = getPackageManager();
		List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

		if (null == list)
			return myData;

		String[] prefixPath;
		String prefixWithSlash = prefix;

		if (prefix.equals("")) {
			prefixPath = null;
		} else {
			prefixPath = prefix.split("/");
			prefixWithSlash = prefix + "/";
		}

		int len = list.size();

		Map<String, Boolean> entries = new HashMap<>();

		for (int i = 0; i < len; i++) {
			ResolveInfo info = list.get(i);
			CharSequence labelSeq = info.loadLabel(pm);
			String label = labelSeq != null
				? labelSeq.toString()
				: info.activityInfo.name;

			if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

				String[] labelPath = label.split("/");

				String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

				if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
					addItem(myData, nextLabel, activityIntent(
						info.activityInfo.applicationInfo.packageName,
						info.activityInfo.name));
				} else {
					if (entries.get(nextLabel) == null) {
						addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
						entries.put(nextLabel, true);
					}
				}
			}
		}

		Collections.sort(myData, sDisplayNameComparator);

		return myData;
	}

	private final static Comparator<Map<String, Object>> sDisplayNameComparator =
		new Comparator<Map<String, Object>>() {
			private final Collator collator = Collator.getInstance();

			public int compare(Map<String, Object> map1, Map<String, Object> map2) {
				return collator.compare(map1.get(TAG_TITLE), map2.get(TAG_TITLE));
			}
		};

	protected Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();
		result.setClassName(pkg, componentName);
		return result;
	}

	protected Intent browseIntent(String path) {
		Intent result = new Intent();
		result.setClass(this, MainActivity.class);
		result.putExtra(PATH, path);
		return result;
	}

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Map<String, Object> map = (Map<String, Object>)parent.getItemAtPosition(position);

		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}

}
