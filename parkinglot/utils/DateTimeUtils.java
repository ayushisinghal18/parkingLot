package parkinglot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

	// calculates how much time a vehicle was parked for
	public static int parseDateIntoTime(Date issuedAt, Date paidAt) throws ParseException {
		String format = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		long diff = paidAt.getTime() - issuedAt.getTime();

		int diffInHours = (int) (diff / (60 * 60 * 1000) % 24);

		return (diffInHours < 1 ? 1 : diffInHours);
	}
}
