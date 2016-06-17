package crawler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	// Wed, 28 May 2014 14:18:49
	// 2010-04-10 21:27:19
	// 2014-07-12T07:50:00.000Z
	// 2014年07月23日 22:41
	// 07月24日 01:05
	// Wed, Jun 11
	// Tue 12:01AM EDT
	// 07-30 14:54
	private static final String[] DATE_FORMATS = new String[] {
			"EEE, dd MMM yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy年MM月dd日 HH:mm",
			"MM月dd日 HH:mm", "EEE, MMM dd", "EEE HH:mma z", "MM-dd HH:mm" };

	public static long convertToTime(String text) throws ParseException {
		for (int i = 0; i < DATE_FORMATS.length; i++) {
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMATS[i],
					Locale.US);
			try {
				Date date = format.parse(text);
				// some date string do not contain YEAR

				if (!hasYear(date)) {
					Calendar cal = Calendar.getInstance();
					Calendar now = Calendar.getInstance();
					cal.setTime(date);
					cal.set(Calendar.YEAR, now.get(Calendar.YEAR));
					date = cal.getTime();
				}

				return date.getTime();
			} catch (Exception e) {
				// do nothing but try another format
			}
		}

		throw new ParseException("Unparseable date: " + text, 0);
	}

	private static boolean hasYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) > 1970;
	}
}
