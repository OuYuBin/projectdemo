package aop;

import java.util.Date;

public class Logger {
	public static void logging(Level level, String context) {
		if (Level.DEBUG.equals(level)) {
			System.out.println("DEBUG\t" + new Date().toString() + "\t"
					+ context);
		} else if (Level.INFO.equals(level)) {
			System.out.println("INFO\t" + new Date().toString() + "\t"
					+ context);
		}
	}
}
