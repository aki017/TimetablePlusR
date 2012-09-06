package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.TimetableParser;
import info.aki017.TimetablePlusR.Timetable.XmlTimetableParser;
import info.aki017.TimetablePlusR.TimetableItem.Direction;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private MainPagerAdapter mPagerAdapter;
	private ViewPager mViewPager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		

		InputStream in = null;
		try {
			in = openFileInput("timetable.xml");
		} catch (FileNotFoundException e) {
			updateXmlfile();
			try {
				in = openFileInput("timetable.xml");
			} catch (FileNotFoundException e1) {
				//TODO : ファイルが存在しない場合
				e1.printStackTrace();
			}
		}
		if(in != null)
		{
		
		TimetableParser parser = new XmlTimetableParser();
		if (!parser.parse(in)) Trace.e("パース失敗");

	    mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
	    mViewPager = (ViewPager) findViewById(R.id.viewpager);
	    PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
	    mViewPager.setAdapter(mPagerAdapter);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.DKGRAY);

        mPagerAdapter.addTab("すべて",null,"立命館大学");
        mPagerAdapter.addTab("南草津駅",Direction.Minakusa,"立命館大学");
        mPagerAdapter.addTab("草津駅",Direction.Kusatu,"立命館大学");
        mPagerAdapter.addTab("大津駅",Direction.Ootu,"立命館大学");
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		updateXmlfile();
		return super.onOptionsItemSelected(item);
	}
	/**
	 * 時刻表のデータを取得する
	 * ただし、非同期で実行するため正しく取得できたかわからない
	 */
	public void updateXmlfile()
	{
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
	}
}
