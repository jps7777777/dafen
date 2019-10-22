package dafen.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FuncUntilsTest {

    @Test
    public void getTimesWithWeek() {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(new Date());
        int times = Integer.valueOf(calendar.getWeekYear()+""+calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println(times);
        System.out.println("now="+Calendar.WEEK_OF_YEAR);
        System.out.println("now="+calendar.get(Calendar.WEEK_OF_YEAR));
    }
}