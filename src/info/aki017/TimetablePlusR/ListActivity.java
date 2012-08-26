package info.aki017.TimetablePlusR;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity{
	Timer   mTimer   = null;
	Handler mHandler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		final Timetable timetable = (Timetable) getIntent().getExtras().getSerializable("TimeTable");
		timetable.update();
		final TimetableAdapter adapter = new TimetableAdapter(getApplicationContext(),timetable);
		((ListView) findViewById(R.id.list_1)).setAdapter(adapter);
		
		Calendar calendar = Calendar.getInstance();
		int second = calendar.get(Calendar.SECOND);
        mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
	        @Override
	        public void run() {
	            // mHandlerを通じてUI Threadへ処理をキューイング
	            mHandler.post( new Runnable() {
	                public void run() {
	                	timetable.update();
	            		adapter.notifyDataSetChanged();
	                }
	            });
	        }
	    }, (60-second)*1000, 60000);
		
	}
	
	
}
