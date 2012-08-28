package info.aki017.TimetablePlusR;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class DetailActivity extends Activity {
		Timer   mTimer   = null;
		Handler mHandler = new Handler();
		//UIの更新する間隔(ms)　残り時間用
		final static int INTERVAL = 1000;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.detail);
			
			final TimetableItem timetableItem = (TimetableItem) getIntent().getExtras().getSerializable("TimeTableItem");
			TextView mTitle = ((TextView) findViewById(R.id.detail_title));
			mTitle.setText(""+timetableItem.getTimeText());
			mTimer = new Timer(true);
			mTimer.schedule( new TimerTask(){
		        @Override
		        public void run() {
		            // UIスレッド以外では描画を更新できないので
		            mHandler.post( new Runnable() {
		                public void run() {
		                }
		            });
		        }
		    }, 1000, 60000);
			
		}
		
		
	}
