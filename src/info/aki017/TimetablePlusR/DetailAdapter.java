package info.aki017.TimetablePlusR;

import info.aki017.TimetablePlusR.Timetable.Timetable;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;

import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DetailAdapter extends ArrayAdapter<TimetableItem>{
	private LayoutInflater mInflater;
	private TextView mDirection;
	private TextView mWay;
	private TextView mCountDown;
	private TextView mTimeData;
	public DetailAdapter(Context context, Timetable objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.detail_row, null);
		}
		final TimetableItem item = this.getItem(position);
		
		if(item != null){
			//行き先表示
			mDirection = (TextView)convertView.findViewById(R.id.detail_direction);
			mDirection.setText(String.format("%s", item.getStation()) );

			//運行時間表示
			mTimeData = (TextView)convertView.findViewById(R.id.detail_time);
			mTimeData.setText(item.getTimeText());
			
			//カウントダウン表示
			mCountDown = (TextView)convertView.findViewById(R.id.detail_cost);
			Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			int time = hour*60*60+minute*60+second;
			mCountDown.setText(
					(item.getTime()*60>time)
							?
								(item.getTime()*60-time >= 100)
									?
										String.format("後%s分" ,item.getTime()-time/60)
									:
										String.format("後%s秒" ,item.getTime()*60-time)
							:
								""
					);

		}
		return convertView ;
	}
}