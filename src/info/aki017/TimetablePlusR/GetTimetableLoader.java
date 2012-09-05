package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.TimetableItem.Direction;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class GetTimetableLoader extends AsyncTaskLoader<List<TimetableItem>> {
	private Direction[] directions = null;
	private String[] stations = null;
	private List<TimetableItem> result = new LinkedList<TimetableItem>();
	public GetTimetableLoader(Context context,Direction[] directions,String[] stations) {
		super(context);
		this.directions = directions;
		this.stations = stations;
	}
	@Override
	public List<TimetableItem> loadInBackground() {
		List<TimetableItem> list = new LinkedList<TimetableItem>();
		list.clear();
		for(TimetableItem item:Timetable.getInstance())
		{
			if(isSameStation(item) && isSameDirection(item)) list.add(item);
		}
		return list;
	}
	private boolean isSameStation(TimetableItem item)
	{
		for (int i = 0; i < stations.length; i++) {
			if(item.isSameStation(stations[i]))return true;
		}
		return false;
	}
	private boolean isSameDirection(TimetableItem item)
	{
		for (int i = 0; i < directions.length; i++) {
			if(item.isSameDirection(directions[i]))return true;
		}
		return false;
	}
	@Override
	public void deliverResult(List<TimetableItem> data) {
		if (isReset()) {
			if (this.result != null) {
				this.result = null;
			}
			return;
		}

		this.result = data;

		if (isStarted()) {
			super.deliverResult(data);
		}
	}
	
	@Override
	protected void onStartLoading() {
		if (this.result != null) {
			deliverResult(this.result);
		}
		if (takeContentChanged() || this.result == null) {
			forceLoad(); // これをやっておくとonCreateLoaderで開始処理をしなくてよくなる
		}
	}
	
	@Override
	protected void onStopLoading() {
		super.onStopLoading();
		cancelLoad();
	}

	@Override
	protected void onReset() {
		super.onReset();
		onStopLoading();
	}
	
	@Override
	public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
	    super.dump(prefix, fd, writer, args);
	    writer.print(prefix); writer.print("result="); writer.println(this.result);
	}
}
