package info.aki017.TimetablePlusR;

import java.util.Comparator;

public enum TimetableComparators{
	Time(new TimetableItemComparator());
	
	private Comparator<TimetableItem> comparator;

	TimetableComparators(Comparator<TimetableItem> comparator) {
		this.comparator = comparator;
	}
}
