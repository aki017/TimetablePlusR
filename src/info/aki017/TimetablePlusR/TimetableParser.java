package info.aki017.TimetablePlusR;

import java.io.InputStream;

interface TimetableParser {
	Timetable getTimetable(InputStream in);
}
