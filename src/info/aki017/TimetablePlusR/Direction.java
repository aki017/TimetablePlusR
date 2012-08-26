package info.aki017.TimetablePlusR;

public enum Direction {
	Kusatu("草津"),
	Seta("瀬田"),
	Minakusa("南草津"),
	Ootu("大津"),
	Tobishima("飛島グリーンヒル"),
	Hukusi("福祉センター");
	
	private String to;
	private String way;
	private Direction(String to,String way)
	{
		this.to=to;
		this.way=way;
	}
	private Direction(String to)
	{
		this.to=to;
		this.way="";
	}
	public String getName()
	{
		return this.to;
	}
	public String getWay()
	{
		return this.way;
	}
	public void setWay(String way)
	{
		this.way = way;
	}
}
