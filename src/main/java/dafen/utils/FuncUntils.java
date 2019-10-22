package dafen.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FuncUntils {

    /**
     * 返回时间字符串
     * @param date
     * @param format
     * @return
     */
    public static String getTimeString(Date date,String format){
        if(date == null){
            date = new Date();
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 返回今年及本周组成的期数
     * @return
     */
    public static int getTimesWithWeek(){
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(new Date());
        return Integer.valueOf(calendar.getWeekYear()+""+calendar.get(Calendar.WEEK_OF_YEAR));
    }




}
