package info.aki017.TimetablePlusR.TimetableItem;

public enum Direction {
	Kusatu("草津駅"),
	Seta("瀬田"),
	Minakusa("南草津駅"),
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
