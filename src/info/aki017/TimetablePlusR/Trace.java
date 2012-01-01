package info.aki017.TimetablePlusR;

import java.util.Stack;

import android.util.Log;

public class Trace {
	private Trace(){}
	public static void v(String str){ $log(android.util.Log.VERBOSE,	str);	}
	public static void d(String str){ $log(android.util.Log.DEBUG,	str);	}
	public static void i(String str){ $log(android.util.Log.INFO,	str);	}
	public static void w(String str){ $log(android.util.Log.WARN,	str);	}
	public static void e(String str){ $log(android.util.Log.ERROR,	str);	}
	

	public static void v(String tag,String str){ $log(android.util.Log.VERBOSE,	tag,	str);	}
	public static void d(String tag,String str){ $log(android.util.Log.DEBUG,	tag,	str);	}
	public static void i(String tag,String str){ $log(android.util.Log.INFO,		tag,	str);	}
	public static void w(String tag,String str){ $log(android.util.Log.WARN,		tag,	str);	}
	public static void e(String tag,String str){ $log(android.util.Log.ERROR,	tag,	str);	}

	public static void e(Exception error)
	{
		StackTraceElement e = error.getStackTrace()[1];
		log(android.util.Log.ERROR,e.getClassName()+"."+e.getMethodName(),error.getMessage());
	}
	public static void $log(int level,String str)
	{
		StackTraceElement e = new Exception().getStackTrace()[2];
		log(level,e.getClassName()+"."+e.getMethodName(),str);
	}
	
	public static void $log(int level,String tag,String str)
	{
		StackTraceElement e = new Exception().getStackTrace()[2];
		log(level,e.getClassName()+"."+e.getMethodName()+":"+tag,str);
	}
	public static void log(int level,String tag,String str)
	{
		Log.println(level, tag, str);
	}
}
