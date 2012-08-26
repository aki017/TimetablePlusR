package info.aki017.TimetablePlusR;

public class TimetableItem {
	private int time=-10000000;
	private Direction direction = Direction.Minakusa;
	public int getTime() { return time;}
	public void setTime(int t) { this.time = t;}
	public Direction getDirection() { return direction;}
	public void setDirection(Direction d) { this.direction=d;}
	public String getTimeText() {
		return String.format("%2d時%2d分", time/60,time%60);
	}
}
