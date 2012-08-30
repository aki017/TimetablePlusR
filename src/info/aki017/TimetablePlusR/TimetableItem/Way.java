package info.aki017.TimetablePlusR.TimetableItem;

public enum Way {
	Panasonic("パナソニック東口"),
	Kasayama("笠山"),
	Kagayaki("かがやき通り"),
	Straight(""),
	Bypass("バイパス");

	private String way;

	private Way(String way) {
		this.way = way;
	}

	public String getName() {
		return this.way;
	}

}
