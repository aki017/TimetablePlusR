package info.aki017.TimetablePlusR;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InputStream in = null;
		try {
			in = openFileInput("timetable.xml");
		} catch (FileNotFoundException e) {

			Toast.makeText(this, "データをダウンロードします", Toast.LENGTH_LONG).show();
			try {
				deleteFile("timetable.xml");
				OutputStream out = openFileOutput("timetable.xml", MODE_PRIVATE);
				XmlTimetableParser.getXmlFromInternet(new URL(
						"http://www.aki017.info/TimetablePlusR/timetable.xml"), out);
				in = openFileInput("timetable.xml");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		TimetableParser parser = new XmlTimetableParser();
		Timetable timetable = parser.getTimetable(in);
		Log.w("Timetablenum", "" + timetable.size());
		timetable.sort(new TimetableItemComparator());
		// TabHostのインスタンスを取得
		TabHost tabs = getTabHost();
		addTab(tabs, "すべて", timetable, ListActivity.class);
		addTab(tabs, "南草津", timetable.getTimetable(Direction.Minakusa),
				ListActivity.class);
		addTab(tabs, "草津", timetable.getTimetable(Direction.Kusatu),
				ListActivity.class);
		addTab(tabs, "大津", timetable.getTimetable(Direction.Ootu),
				ListActivity.class);
		// 初期表示のタブ設定
		tabs.setCurrentTab(0);
	}

	private void addTab(TabHost host, String name, Timetable timetable,
			Class<ListActivity> myclass) {
		TabSpec tab = host.newTabSpec(name);
		Intent intent = new Intent();
		intent.setClass(this, myclass);
		intent.putExtra("TimeTable", timetable);
		tab.setIndicator(name);
		tab.setContent(intent);
		host.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		Toast.makeText(this, "更新します", Toast.LENGTH_LONG).show();
		final Context context = getApplicationContext();
		final Handler handler = new Handler();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = openFileOutput("timetable.xml",MODE_PRIVATE);
					XmlTimetableParser.getXmlFromInternet(new URL("http://www.aki017.info/TimetablePlusR/timetable.xml"),out);
					handler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "更新した", Toast.LENGTH_LONG)
									.show();
						}
					});

				} catch (Exception e1) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(context, "更新失敗", Toast.LENGTH_LONG)
									.show();
						}
					});
				}

			}
		}).start();
		return super.onOptionsItemSelected(item);
	}
}
