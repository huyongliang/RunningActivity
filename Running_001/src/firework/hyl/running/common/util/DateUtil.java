package firework.hyl.running.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static boolean isSameDay(Date date1,Date date2){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String d1=dateFormat.format(date1);
		String d2=dateFormat.format(date2);
		return d1.equals(d2);
	}
	
	public static void main(String[] args) {
		System.out.println(isSameDay(new Date(), new Date()));
	}
}
