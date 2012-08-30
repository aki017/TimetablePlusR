package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends Activity implements OnItemClickListener{
		Timer   mTimer   = null;
		Handler mHandler = new Handler();
		//UIの更新する間隔(ms)　残り時間用
		final static int INTERVAL = 1000;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.detail);
			
			//Intent間の受け渡し
			final TimetableItem timetableItem = (TimetableItem) getIntent().getExtras().getSerializable("TimeTableItem");
			
			//タイトル設定
			TextView mTitle = ((TextView) findViewById(R.id.detail_title));
			mTitle.setText(timetableItem.getDirection().getName());
			
			
			Timetable timetable = Timetable.getData().getTimetableByNo(timetableItem.getDirection(),timetableItem.getNo());
			final DetailAdapter adapter = new DetailAdapter(getApplicationContext(),timetable);
			ListView listview = ((ListView) findViewById(R.id.detail_list));
					 listview.setAdapter(adapter);
				     listview.setOnItemClickListener(this);	
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
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
	        ListView listView = (ListView) parent;
	        // 選択されたアイテムを取得します
	        TimetableItem item = (TimetableItem) listView.getItemAtPosition(position);


	        // インテントのインスタンス生成
	        Intent intent = new Intent();
			// インテントにアクション及び位置情報をセット
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("geo:0,0?q="+item.getStation()));
			// Googleマップ起動
			startActivity(intent);
			startActivity(intent);
		}
		
	}
