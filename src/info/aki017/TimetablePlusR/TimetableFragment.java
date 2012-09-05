package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.Timetable.TimetableAdapter;
import info.aki017.TimetablePlusR.TimetableItem.Direction;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TimetableFragment extends FragmentActivity implements OnItemClickListener,LoaderCallbacks<List<TimetableItem>>{
	private Timer   mTimer   = null;
	private Handler mHandler = new Handler();
	private TimetableAdapter mAdapter;
	
	//UIの更新する間隔(ms)　残り時間用
	final static int INTERVAL = 60000;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);


		Direction direction = (Direction) getIntent().getExtras().getSerializable("Direction");
		String station = getIntent().getExtras().getString("Station");

		final Timetable timetable = Timetable.getInstance().getTimetableByDirection(direction).getTimetableByStation(station);
		timetable.update();
		mAdapter = new TimetableAdapter(getApplicationContext(),timetable);
		ListView listview = ((ListView) findViewById(R.id.list_1));
				 listview.setAdapter(mAdapter);
				// リストビューのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
			    listview.setOnItemClickListener(this);
			    
			 // ローダーを初期化
				Bundle args = new Bundle(1); //onCreateLoaderに渡したい値はここへ
				args.putSerializable("Directions", direction);
				args.putString("Stations", station);
				getSupportLoaderManager().initLoader(0, args, this);
	}
	/**
	 * ローダーを初期化した際に呼ばれる。
	 */
	@Override
	public Loader<List<TimetableItem>> onCreateLoader(int id, Bundle args) {
		// オブジェクト作って返すだけ
		Direction[] directions = {(Direction) args.getSerializable("Directions")};
		String[] stations = {args.getString("Stations")};
		GetTimetableLoader loader = new GetTimetableLoader(getApplication(),directions,stations);
		return loader;
	}
	/**
	 * データ読み込みが完了したときに呼ばれる
	 * @param data AsyncTaskLoader#loadInBackgroundで返した値
	 */
	@Override
	public void onLoadFinished(Loader<List<TimetableItem>> loader, List<TimetableItem> data) {
		for(TimetableItem s: data){
			mAdapter.add(s);
		}
		mAdapter.notifyDataSetChanged();
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
	@Override
	public void onLoaderReset(Loader<List<TimetableItem>> arg0) {
		// TODO Auto-generated method stub
		
	}
}
