package base.date;

import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

public class Calender {
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
	        Date sdate = sdf.parse("2015-8-9 22:22:10");
	        
	        System.out.println("sdate:"+sdate);
		
		Calendar cal = Calendar.getInstance();
		//cal.setTime(new Date());
		cal.setTime(sdate);
		Date dateFriest=cal.getTime();
		String string=sdf.format(dateFriest);
		System.out.println("dateFriest:"+string);
		
		 cal.add(Calendar.MONTH, 1);
	     //cal.add(Calendar.WEEK,2);
		 cal.add(Calendar.MINUTE,-10);
		
		
		Date date = cal.getTime();
		System.out.println("修改原始:"+date);
		// 设置格式
		String strDate = sdf.format(date);
		System.out.println("strDate:"+strDate);
		
	}
}
