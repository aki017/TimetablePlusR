package info.aki017.TimetablePlusR;

public class TimetableItem {
	private int time=-10000000;
	private Direction direction = Direction.Minakusa;
	private Way way = Way.Kasayama;
	public int getTime() { return time;}
	public void setTime(int t) { this.time = t;}
	public Direction getDirection() { return direction;}
	public void setDirection(Direction d) { this.direction=d;}
	public Way getWay() {return way;}
	public String getTimeText() {
		return String.format("%2d時%2d分", time/60,time%60);
	}
	public void setDirection(String text) {
		if (text.equals("南草津"))direction = Direction.Minakusa;
		if (text.equals("草津"))direction = Direction.Kusatu;
		if (text.equals("大津"))direction = Direction.Ootu;
		if (text.equals("南草津"))direction = Direction.Minakusa;
		/*for (Direction test : Direction.values()) {
			if (test.getName().equals(text)) {
				direction = Direction.valueOf(test.name());
			}
		}*/
	}
	public void setWay(String text) {
		for (Way test : Way.values()) {
			if (test.getName().equals(text))way = test;
		}
	}
}
