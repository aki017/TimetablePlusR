package info.aki017.TimetablePlusR.TimetableItem;


import java.io.Serializable;

import android.graphics.Color;

public class TimetableItem implements Serializable{
	private static final long serialVersionUID = 2L;
	private int time=-10000000;
	private int no = 0;
	private String station = "";
	private Direction direction = Direction.Minakusa;
	private Way way;

	/**
	 * アイテムを生成
	 */
	public TimetableItem() {
	}
	/**
	 * アイテムを生成
	 * @param time 時刻(0:00からの分数)
	 * @param direction Direction
	 * @param way Way
	 */
	public TimetableItem(int time,Direction direction,Way way,int no,String station) {
		this.time = time;
		this.direction = direction;
		this.way = way;
		this.no = no;
		this.station = station;
	}
	/**
	 * 時間を取得
	 * @return 時刻(0:00からの分数)
	 */
	public int getTime() { return time;}
	/**
	 * 時間を設定
	 * @param time 時刻(0:00からの分数)
	 */
	public void setTime(int time) { this.time = time;}
	/**
	 * 方向を取得
	 * @return Direction
	 */
	public Direction getDirection() { return direction;}
	/**
	 * 方向を設定
	 * @param direction Direction
	 */
	public void setDirection(Direction direction) { this.direction=direction;}
	/**
	 * 方向を設定
	 * @param text 有効そうな文字列
	 */
	public void setDirection(String text) {
		/*
		if (text.equals("南草津"))direction = Direction.Minakusa;
		if (text.equals("草津"))direction = Direction.Kusatu;
		if (text.equals("大津"))direction = Direction.Ootu;
		*/
		for (Direction test : Direction.values()) {
			if (test.getName().equals(text)) {if (test.getName().equals(text))direction = test;	}
		}
	}
	/**
	 * 道を取得
	 * @return Way
	 */
	public Way getWay() {return way;}
	/**
	 * 道を設定
	 * @param text 有効そうな文字列
	 */
	public void setWay(String text) {
		way = new Way(text);
	}
	/**
	 * 時刻データを文字列で取得
	 * @return "%2d時%2d分"
	 */
	public String getTimeText() {
		return String.format("%2d時%2d分", time/60,time%60);
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStation()
	{
		return this.station;
	}
	public void setStation(String station)
	{
		this.station = station;
	}
	
	public boolean isSameStation(String station)
	{
		return (this.station.equals(station)) ? true : false;
	}
	public boolean isSameDirection(Direction direction)
	{
		return (this.direction.equals(direction)) ? true : false;
	}
	public int getColor() {
		//TODO:　未実装
		return Color.argb(25, 16*(no%16), 16*(no/16%16), 16*(no/16/16%16));
	}
}
