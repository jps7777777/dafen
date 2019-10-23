package dafen.service.impl;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ScoreServiceImplTest {

    @Test
    public void getMasterMonkey(){
        int n = 8;
        int m = 3;
        List<String> strArr = new ArrayList<>();
        for(int i=1;i<=n;i++){
            strArr.add("monkey"+i);
        }
        System.out.println(strArr);
        System.out.println(strArr.size());
        for(int x=0,y=1;strArr.size() > 1;){
            // 判断x值是否越界，越界重头开始
            if(x == strArr.size()){
                x = 0;
            }
            System.out.println("x="+x+","+strArr.get(x));
            if(y == m){
                System.out.println("当x="+x+"时，删掉了"+strArr.get(x));
                strArr.remove(x);
                y=1;
                continue;
            }else{
                y++;
            }
            if(x < strArr.size()-1){
                x++;
            }else{
                x = 0;
            }
        }
        System.out.println(strArr);
    }




    private String[] getLast(String[] map,int m){
        System.out.println("hello");
        for (int i=0;i< m;){
            for(int y=0;y<map.length;y++){
                if(map[y] == ""){
                    continue;
                }else {
                    map[y] = "";
                    break;
                }
            }
            m++;
        }
        return map;
    }








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
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        // 第几周
//        calendar.get(Calendar.WEEK_OF_YEAR);
//        int times = Integer.valueOf(calendar.getWeekYear()+""+calendar.get(Calendar.WEEK_OF_YEAR));
//        System.out.println("结果为："+times);
    }
}