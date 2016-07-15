package base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static void main(String[] args) throws ParseException {
		/*SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		Date sdate = sdf2.parse("2016-07-05 09:00:00");
		System.out.println("处理后的时间:" + sdate);*/
		
		
		System.out.println(DateUtils.formatDate(new Date(), "HH:mm"));
	}
}
