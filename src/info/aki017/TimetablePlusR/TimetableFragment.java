package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.Timetable.TimetableAdapter;
import info.aki017.TimetablePlusR.TimetableItem.Direction;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;

import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TimetableFragment extends Fragment implements OnItemClickListener{
	private TimetableAdapter mAdapter;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.list_1, container, false);

		Direction direction = (Direction) getArguments().getSerializable("Direction");
		String station = getArguments().getString("Station");

		Timetable timetable = Timetable.getInstance().getTimetableByDirection(direction).getTimetableByStation(station);
		timetable.update();
		Trace.e(""
				+" : "+Timetable.getInstance().getTimetableByDirection(direction).size()
				+" : "+Timetable.getInstance().getTimetableByDirection(direction).getTimetableByStation(station).size()
				+" : "+Timetable.getInstance().getTimetableByStation(station).size()
				+" : "+direction.getName()
				+" : "+station
				+" : "+timetable.size()
				+" : "+timetable.get(0).getDirection().getName()
		);

		mAdapter = new TimetableAdapter(getActivity(),timetable);
		mAdapter.notifyDataSetChanged();
        ((ListView)view.findViewById(R.id.list_1)).setAdapter(mAdapter);
        
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    //    outState.putInt("curChoice", mCurCheckPosition);
       // outState.putInt("shownChoice", mShownCheckPosition);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        ListView listView = (ListView) parent;
        // 選択されたアイテムを取得します
        TimetableItem item = (TimetableItem) listView.getItemAtPosition(position);
/*
		Intent intent = new Intent();
		intent.setClass(this, DetailActivity.class);
		intent.putExtra("TimeTableItem", item);
		startActivity(intent);*/
	}
}