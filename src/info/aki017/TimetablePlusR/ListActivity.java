package info.aki017.TimetablePlusR;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class ListActivity extends Activity implements OnItemClickListener{
	Timer   mTimer   = null;
	Handler mHandler = new Handler();
	//UIの更新する間隔(ms)　残り時間用
	final static int INTERVAL = 60000;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		
		Direction direction = (Direction) getIntent().getExtras().getSerializable("Direction");
		final Timetable timetable = Timetable.getInstance();
		timetable.update();
		final TimetableAdapter adapter = new TimetableAdapter(getApplicationContext(),getItemByDirection(direction));
		ListView listview = ((ListView) findViewById(R.id.list_1));
				 listview.setAdapter(adapter);
				// リストビューのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
			    listview.setOnItemClickListener(this);
			    
		Calendar calendar = Calendar.getInstance();
		int second = calendar.get(Calendar.SECOND);
        mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
	        @Override
	        public void run() {
	            // UIスレッド以外では描画を更新できないので
	            mHandler.post( new Runnable() {
	                public void run() {
	                	timetable.update();
	            		adapter.notifyDataSetChanged();
	                }
	            });
	        }
	    }, (60-second)*1000, 60000);
	}
	private Timetable getItemByDirection(Direction direction) {
		Timetable timetable = Timetable.getData();
		if(direction != null)
		{
			for (Iterator<TimetableItem> iterator = timetable.iterator(); iterator.hasNext();) {
				TimetableItem timetableItem = (TimetableItem) iterator.next();
				if(! timetableItem.getDirection().equals(direction))iterator.remove();
			}
		}
		return timetable;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        ListView listView = (ListView) parent;
        // 選択されたアイテムを取得します
        TimetableItem item = (TimetableItem) listView.getItemAtPosition(position);

		Intent intent = new Intent();
		intent.setClass(this, DetailActivity.class);
		intent.putExtra("TimeTableItem", item);
		startActivity(intent);
	}
}
