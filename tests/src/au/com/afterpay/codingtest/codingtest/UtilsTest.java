package au.com.afterpay.codingtest.codingtest;


import au.com.afterpay.codingtest.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class UtilsTest {


    @Test
    public void parseDateStrPositive() {
        Date date = Utils.parseDateStr("2014-04-29T13:15:54");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        Assert.assertEquals(2014, year);
        Assert.assertEquals(3, month);
        Assert.assertEquals(29, day);
        Assert.assertEquals(13, hour);
        Assert.assertEquals(15, minute);
        Assert.assertEquals(54, second);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDateStrNegative() {
        Utils.parseDateStr("2014-04-2913:15:54");
    }

    @Test
    public void isInTimeWindowInclusive() {
        Date startTime = new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime();
        Date finishTime =  new GregorianCalendar(2020, Calendar.FEBRUARY, 13).getTime();
        Date currTime =  new GregorianCalendar(2020, Calendar.FEBRUARY, 12).getTime();

        boolean result = Utils.isInTimeWindowInclusive(startTime, finishTime, currTime);
        Assert.assertTrue(result);

        currTime =  new GregorianCalendar(2020, Calendar.FEBRUARY, 15).getTime();
        result = Utils.isInTimeWindowInclusive(startTime, finishTime, currTime);
        Assert.assertFalse(result);
    }

    @Test
    public void timeAfter24HourWindow() {
        Date startTime = new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime();
        Date expected = new GregorianCalendar(2020, Calendar.FEBRUARY, 12).getTime();

        Date date = Utils.timeAfter24HourWindow(startTime);
        Assert.assertEquals(expected, date);
    }

    @Test
    public void timeAfterHourWindow() {
        Date startTime = new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime();
        Date expected = new GregorianCalendar(2020, Calendar.FEBRUARY, 13).getTime();

        Date date = Utils.timeAfterHourWindow(startTime, 48);
        Assert.assertEquals(expected, date);
    }
}
