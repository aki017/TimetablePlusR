package info.aki017.TimetablePlusR;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InputStream in = null;
		try {
			in = openFileInput("xtimetable.xml");
		} catch (FileNotFoundException e) {
				try {
					OutputStream out = openFileOutput("timetable.xml",MODE_PRIVATE);
					XmlTimetableParser.getXmlFromInternet(new URL("http://www.aki017.info/TimetablePlusR/timetable.xml"), out);
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
		parser.getTimetable(in);
		
		// TabHostのインスタンスを取得
		TabHost tabs = getTabHost();
		addTab(tabs,"すべて",getTimetable(),ListActivity.class);
		addTab(tabs,"南草津",getTimetable().getTimetable(Direction.Kusatu),ListActivity.class);
		addTab(tabs,"草津",getTimetable().getTimetable(Direction.Minakusa),ListActivity.class);
		addTab(tabs,"大津",getTimetable().getTimetable(Direction.Tobishima),ListActivity.class);
		// 初期表示のタブ設定
		tabs.setCurrentTab(0);
	}
	private void addTab(TabHost host,String name,Timetable timetable,Class<ListActivity> myclass)
	{
		TabSpec tab = host.newTabSpec(name);
		Intent intent = new Intent();
		intent.setClass(this,myclass);
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
	
	
	private Timetable getTimetable()
	{
		Timetable timetable = new Timetable();
		for(int i = 0;i<=100;i++)
		{
			TimetableItem item = new TimetableItem();
			switch((int)(Math.random()*3)){
			case 0:	item.setDirection(Direction.Kusatu); break;
			case 1:	item.setDirection(Direction.Minakusa); break;
			case 2:	item.setDirection(Direction.Tobishima); break;
			default:item.setDirection(Direction.Kusatu); break;
			}
			item.setTime((int)(Math.random()*60*24));
			timetable.add(item);
		}
		timetable.sort(new TimetableItemComparator());
		return timetable;
	}
}
