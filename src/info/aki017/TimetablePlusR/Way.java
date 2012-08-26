package info.aki017.TimetablePlusR;

public enum Way {
	Panasonic("パナソニック"),
	Kasayama("笠山"),
	Kagayaki("かがやき通り");

	private String way;

	private Way(String way) {
		this.way = way;
	}

	public String getName() {
		return this.way;
	}

}
