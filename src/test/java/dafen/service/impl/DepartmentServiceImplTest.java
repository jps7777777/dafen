package dafen.service.impl;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DepartmentServiceImplTest {

    @Test
    public void serviceTest(){
        int[] arr1= new int[4];
        int[][] arr2 = new int[3][4];
        int arr3[] = new int[5];
        System.out.println(arr1.length);
        System.out.println(arr2.length);
        System.out.println(arr2[0].length);
        System.out.println(arr3.length);


    }



    @Test
    public void delDepartment() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println("时间1："+calendar.getTime());
        String dateName = df.format(calendar.getTime());

        Date date = new Date();
        System.out.println("时间2"+date.toString());
        System.out.println(date.getTime());

        System.out.println("时间3："+df.format(date));

    }
}