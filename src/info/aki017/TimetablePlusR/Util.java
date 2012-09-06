package info.aki017.TimetablePlusR;

public class Util {
	private Util(){}
	public static String setStringWidth(String str,int i)
	{
		while(strlen(str) != i)
		{
			if(strlen(str) < i) str += " ";
			if(strlen(str) > i) str.substring(0,-1);
		}
		return str;
	}
	public static int strlen(String str)
	{
		int len = 0;
        for( int i=0; i<str.length(); i++ ) {
            char c = str.charAt( i );
            if( ( c<='\u007e' )|| // 英数字
                ( c=='\u00a5' )|| // \記号
                ( c=='\u203e' )|| // ~記号
                ( c>='\uff61' && c<='\uff9f' ) // 半角カナ
            )
            	len ++;
            else
                len+=2;
        }
        return len;
    }
}
