package base.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.icu.util.Calendar;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        final Date startDate = sdf.parse("2016-04-27 03:00:00");
	        final Date endDate = sdf.parse("2016-07-06 03:00:00");
	        
	        System.out.println(startDate);
	        System.out.println(endDate);
	        
	        
	        Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);
	        //比当前月份少1
	        int month = cal.get(Calendar.MONTH);
	        //date表示日期，day表示天数，所以date与day_of_month相同
	        int date = cal.get(Calendar.DATE);
	        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	        //表示本周的第几天，从周日开始计算
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
	        //12小时制
	        int hour = cal.get(Calendar.HOUR);
	        //24小时制
	        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE);
	        int second = cal.get(Calendar.SECOND);
	        int millisecond = cal.get(Calendar.MILLISECOND);
	        int maxDate = cal.getActualMaximum(Calendar.DATE);
	        System.out.println("现在的年份为:" + year);
	        System.out.println("现在的月份为:" + month);
	        System.out.println("现在的号为:" + date);
	        System.out.println("现在的号为:" + dayOfMonth);
	        System.out.println("现在是星期:" + dayOfWeek);
	        System.out.println("现在过了的天数为:" + dayOfYear);
	        System.out.println("现在几点:" + hour);
	        System.out.println("现在几点:" + hourOfDay);
	        System.out.println("现在几分:" + minute);
	        System.out.println("现在几秒:" + second);
	        System.out.println("现在几毫秒:" + millisecond);
	        System.out.println("本月最后一天是:" + maxDate);
	        
	        cal.set(2000, 0, 30, 0, 0, 0);
	        System.out.println(cal.getTime());
	        cal.setLenient(false);
	        cal.set(2000, 2, 31, 0, 0, 0);
	        System.out.println(cal.getTime());
	        
	        Calendar calendar = Calendar.getInstance();
	     // 从一个Calendar对象中获取Date对象
	     Date date1 = calendar.getTime();
	     System.out.println(date1);
	     // 将 Date 对象反应到一个 Calendar 对象中，
	     // Calendar/GregorianCalendar没有构造函数可以接受Date对象，所以我们必需先获得一个实例，然后设置Date 对象
	     calendar.setTime(date1);
	}

}
