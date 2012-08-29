package info.aki017.TimetablePlusR;

import java.io.InputStream;

interface TimetableParser {
	/**
	 * パースする
	 * @param in InputStream
	 * @return 成功可否
	 */
	boolean parse(InputStream in);
}
