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

//TODO : 自分でも読めない
//TODO : 後で書く
public class XmlTimetableParser implements TimetableParser {
	private static String TAG = "PullParser";

	public XmlTimetableParser() {
	}

	@Override
	public Timetable getTimetable(InputStream in) {
		Timetable timetable = new Timetable();
		XmlPullParser xmlPullParser = Xml.newPullParser();
		try {
			xmlPullParser.setInput(in, "UTF-8");
			// xmlPullParser.setInput(new
			// StringReader("<Timetable><item><direction>南草津</direction><way>笠山</way><time>6:50</time></item></Timetable>"));
		} catch (XmlPullParserException e) {
			Log.e(TAG,"InputError");
			e.printStackTrace();
		}

		try {
			int eventType;
			eventType = xmlPullParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					Log.d(TAG, "Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					Log.d(TAG, "End document");
				} else if (eventType == XmlPullParser.START_TAG) {
					if (xmlPullParser.getName().equalsIgnoreCase("Timetable")) {
						Log.d(TAG+"_","Start Timetable :"
										+ xmlPullParser.getAttributeValue(
												xmlPullParser.getNamespace(),
												"Station"));
						if(xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(),"Station").replaceAll(" ", "").equals("立命館大学"))
						{
							timetable = getTimetable(xmlPullParser);
						}
					}
				} else if (eventType == XmlPullParser.END_TAG) {
				} else if (eventType == XmlPullParser.TEXT) {
				}
				eventType = xmlPullParser.next();
			}
		} catch (Exception e) {
			Log.d(TAG + "_", "Error");
		}
		return timetable;
	}

	private Timetable getTimetable(XmlPullParser xmlPullParser)
			throws XmlPullParserException, IOException {
		Log.d(TAG + "_", "getTimetable" + xmlPullParser.getDepth());
		Timetable timetable = new Timetable();

		int eventType;
		eventType = xmlPullParser.next();
		while (eventType != XmlPullParser.END_TAG) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xmlPullParser.getName().equalsIgnoreCase("ITEM")) {
					timetable.add(getItemData(xmlPullParser));
				}
				//Log.d(TAG, "Start tag " + xmlPullParser.getName());
			} else if (eventType == XmlPullParser.END_TAG) {
				if (xmlPullParser.getName().equalsIgnoreCase("Timetable"))
					return timetable;
			}
			eventType = xmlPullParser.next();
		}
		return timetable;
	}

	private TimetableItem getItemData(XmlPullParser xmlPullParser)
			throws XmlPullParserException, IOException {
		Log.d(TAG + "_", "getItemData" + xmlPullParser.getDepth());
		TimetableItem timetableItem = new TimetableItem();
		int next;
		// Itemが閉じるまで
		while (((next = xmlPullParser.next()) != XmlPullParser.END_TAG)) {
			Log.d(TAG + "_", "getItemData_" + xmlPullParser.getDepth());
			// Itemの要素
			if (next == XmlPullParser.START_TAG) {
				Log.d(TAG + "_", "getItemData_ " + xmlPullParser.getName());
				if (xmlPullParser.getName().equalsIgnoreCase("DIRECTION")) {
					if (xmlPullParser.next() == XmlPullParser.TEXT)
						timetableItem.setDirection(xmlPullParser.getText());
						Log.w(TAG+"_",""+xmlPullParser.getText()+" -> "+timetableItem.getDirection().getName()+"");
				} else if (xmlPullParser.getName().equalsIgnoreCase("WAY")) {
					if (xmlPullParser.next() == XmlPullParser.TEXT) {
						timetableItem.setWay(xmlPullParser.getText().replaceAll(" ", ""));
						Log.w(TAG+"_",""+xmlPullParser.getText()+" -> "+timetableItem.getWay().getName()+"");
					}
				} else if (xmlPullParser.getName().equalsIgnoreCase("TIME")) {
					if (xmlPullParser.next() == XmlPullParser.TEXT) {
						String[] t = xmlPullParser.getText().replaceAll(" ", "").split(":");
						timetableItem.setTime(Integer.parseInt(t[0]) * 60
								+ Integer.parseInt(t[1]));
					}
				} else {
					Log.d(TAG + "_", "getItemData_ " + xmlPullParser.getName());
					xmlPullParser.next();
				}
				while (xmlPullParser.next() != XmlPullParser.END_TAG) {
				}

			}
		}

		Log.d(TAG, timetableItem.getDirection().getName());
		Log.d(TAG, timetableItem.getWay().getName());
		Log.d(TAG, timetableItem.getTimeText());
		return timetableItem;
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
