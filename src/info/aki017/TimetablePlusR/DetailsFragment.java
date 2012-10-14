package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment implements OnItemClickListener{
	Timer   mTimer   = null;
	Handler mHandler = new Handler();
	//UIの更新する間隔(ms)　残り時間用
	final static int INTERVAL = 1000;
    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static DetailsFragment newInstance(TimetableItem item) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putSerializable("item", item);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
        View view = inflater.inflate(R.layout.detail, container, false);
        
      //Intent間の受け渡し

		final TimetableItem timetableItem = (TimetableItem) getArguments().getSerializable("item");
		
		//タイトル設定
		TextView mTitle = ((TextView) view.findViewById(R.id.detail_title));
		mTitle.setText(timetableItem.getDirection().getName());

		
		Timetable timetable = Timetable.getData().getTimetableByNo(timetableItem.getDirection(),timetableItem.getNo());
		final DetailAdapter adapter = new DetailAdapter(getActivity(),timetable);
		ListView listview = ((ListView) view.findViewById(R.id.detail_list));
				 listview.setAdapter(adapter);
			     listview.setOnItemClickListener(this);	
		mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
	        @Override
	        public void run() {
	            // UIスレッド以外では描画を更新できないので
	            mHandler.post( new Runnable() {
	                public void run() {
	                	adapter.notifyDataSetChanged();
	                }
	            });
	        }
	    }, 1000, 1000);
		
		
        return view;
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
