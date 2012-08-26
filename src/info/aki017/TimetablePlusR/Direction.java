package info.aki017.TimetablePlusR;

public enum Direction {
	Kusatu("草津"),
	Seta("瀬田"),
	Minakusa("南草津"),
	Ootu("大津"),
	Tobishima("飛島グリーンヒル"),
	Hukusi("福祉センター");
	
	private String to;
	private Direction(String to)
	{
		this.to=to;
	}
	public String getName()
	{
		return this.to;
	}
}
