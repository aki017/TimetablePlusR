package info.aki017.TimetablePlusR.Timetable;

import java.io.InputStream;

public interface TimetableParser {
	/**
	 * パースする
	 * @param in InputStream
	 * @return 成功可否
	 */
	boolean parse(InputStream in);
}
