package info.aki017.TimetablePlusR;

import java.util.Timer;
import java.util.TimerTask;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.Timetable.TimetableAdapter;
import info.aki017.TimetablePlusR.TimetableItem.Direction;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItemComparator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TimetableFragment extends Fragment implements OnItemClickListener{
	private TimetableAdapter mAdapter;
	private Timer mTimer;
	Handler mHandler = new Handler();

	public TimetableFragment()
	{
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.list_1, container, false);
    	if(getArguments().containsKey("Direction"))
    	{
		Direction direction = (Direction) getArguments().getSerializable("Direction");
		String station = getArguments().getString("Station");

		Timetable timetable = Timetable.getInstance().getTimetableByDirection(direction).getTimetableByStation(station);
		timetable.update();
		timetable.sort(new TimetableItemComparator());
		/*Trace.e(""
				+" : "+Timetable.getInstance().getTimetableByDirection(direction).size()
				+" : "+Timetable.getInstance().getTimetableByDirection(direction).getTimetableByStation(station).size()
				+" : "+Timetable.getInstance().getTimetableByStation(station).size()
				+" : "+direction.getName()
				+" : "+station
				+" : "+timetable.size()
				+" : "+timetable.get(0).getDirection().getName()
		);*/

		mAdapter = new TimetableAdapter(getActivity(),timetable);
		mAdapter.notifyDataSetChanged();
        ((ListView)view.findViewById(R.id.list_1)).setAdapter(mAdapter);
        ((ListView)view.findViewById(R.id.list_1)).setOnItemClickListener(this);
    	}
    	
    	mTimer = new Timer(true);
		mTimer.schedule( new TimerTask(){
	        @Override
	        public void run() {
	            // UIスレッド以外では描画を更新できないので
	            mHandler.post( new Runnable() {
	                public void run() {
	                	mAdapter.notifyDataSetChanged();
	                }
	            });
	        }
	    }, 1000, 1000);
		
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        ListView listView = (ListView) parent;
        showDetails(listView,position);
	}
	
	void showDetails(ListView listView,int index) {
		TimetableItem item = (TimetableItem) listView.getItemAtPosition(index);
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}