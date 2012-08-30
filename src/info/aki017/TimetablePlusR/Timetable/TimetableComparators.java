package info.aki017.TimetablePlusR.Timetable;

import info.aki017.TimetablePlusR.TimetableItem.TimetableItem;
import info.aki017.TimetablePlusR.TimetableItem.TimetableItemComparator;

import java.util.Comparator;

public enum TimetableComparators{
	Time(new TimetableItemComparator());
	
	private Comparator<TimetableItem> comparator;

	TimetableComparators(Comparator<TimetableItem> comparator) {
		this.comparator = comparator;
	}
}
