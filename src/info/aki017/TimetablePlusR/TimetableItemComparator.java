package info.aki017.TimetablePlusR;

import java.util.Comparator;

/**
 * 時刻を基準にソート
 */
public class TimetableItemComparator implements Comparator<TimetableItem> {

	@Override
	public int compare(TimetableItem a, TimetableItem b) {
		return a.getTime() - b.getTime();
	}

}
