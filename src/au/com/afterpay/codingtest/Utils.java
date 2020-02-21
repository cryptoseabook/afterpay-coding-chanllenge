package au.com.afterpay.codingtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static Date parseDateStr(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //2014-04-29T13:15:54
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date format is not supported");
        }
    }

    public static boolean isInTimeWindowInclusive(Date startTime, Date finisTime, Date currTime) {
        return ( currTime == startTime || currTime.after(startTime) ) && (currTime == finisTime || currTime.before(finisTime));
    }

    public static Date timeAfter24HourWindow(Date startTime) {
        return timeAfterHourWindow(startTime, 24);
    }

    public static Date timeAfterHourWindow(Date startTime, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.HOUR, hours);
        Date dt = c.getTime();
        return dt;
    }
}
