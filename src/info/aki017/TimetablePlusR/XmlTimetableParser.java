package info.aki017.TimetablePlusR;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class XmlTimetableParser implements TimetableParser {
	public XmlTimetableParser()
	{

		Log.v("te","init");
	}
	@Override
	public Timetable getTimetable(InputStream in) {
		Log.v("te","te");
		XmlPullParser xmlPullParser = Xml.newPullParser();
		try {
			xmlPullParser.setInput(new StringReader(
					"<Timetable><item><direction>南草津</direction><way>笠山</way><time>650</time></item></Timetable>"));
		} catch (XmlPullParserException e) {
			Log.d("XmlPullParserSample", "Error");
		}

		try {
			int eventType;
			eventType = xmlPullParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					Log.d("XmlPullParserSample", "Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					Log.d("XmlPullParserSample", "End document");
				} else if (eventType == XmlPullParser.START_TAG) {
					Log.d("XmlPullParserSample",
							"Start tag " + xmlPullParser.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					Log.d("XmlPullParserSample",
							"End tag " + xmlPullParser.getName());
				} else if (eventType == XmlPullParser.TEXT) {
					Log.d("XmlPullParserSample",
							"Text " + xmlPullParser.getText());
				}
				eventType = xmlPullParser.next();
			}
		} catch (Exception e) {
			Log.d("XmlPullParserSample", "Error");
		}
		return null;
	}

	public static void getXmlFromInternet(URL url, OutputStream out)
			throws IOException {
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}

		out.close();
		in.close();
	}

}
