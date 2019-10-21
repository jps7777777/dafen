package dafen.service.impl;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ScoreServiceImplTest {

    @Test
    public void getScoreByCondition() {
        // 将字符串格式化
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//         第几周
//        int rs = calendar.get(Calendar.WEEK_OF_YEAR);
//        System.out.println("get result = "+rs);


        // 查询times列表信息
//        if(type == 3 ){
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            times = Integer.valueOf(calendar.getWeekYear()+""+calendar.get(Calendar.WEEK_OF_YEAR));
//        }

        // 将字符串格式化
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 第几周
        calendar.get(Calendar.WEEK_OF_YEAR);
        int times = Integer.valueOf(calendar.getWeekYear()+""+calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("结果为："+times);
    }
}