package info.aki017.TimetablePlusR.TimetableItem;

import java.io.Serializable;

public class Way implements Serializable{
	private String way;
	public Way(String str)
	{
		way = str;
	}
	public String getName()
	{
		return way;
	}
}
