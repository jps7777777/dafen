package dafen.action;

import com.alibaba.druid.util.StringUtils;
import dafen.service.model.DepartmentModel;
import dafen.service.model.UserModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelActionTest {

    @Test
    public void test2() {
        List<DepartmentModel> list = new ArrayList<>();
        List<Map<String, String>> scoreModels = new ArrayList<>();
        List<UserModel> userModels = new ArrayList<>();

        for(int i=0;i< 3;i++){
            DepartmentModel departmentModel = new DepartmentModel();
            departmentModel.setId(i+1);
            departmentModel.setdName("骨科"+i);
            UserModel userModel = new UserModel();
            userModel.setId(i+1);
            userModel.setUserName("金"+i);
            list.add(departmentModel);
            userModels.add(userModel);
            Map<String,String> map = new HashMap<>();
            map.put("id",(i+1)+"");
            map.put("score","3");
            map.put("user_id",(i+1)+"");
            map.put("dep_id",(i+1)+"");
            scoreModels.add(map);
        }
        String[][] table = new String[list.size()+1][userModels.size()+1];

        System.out.println("list="+list.size());
        System.out.println("models="+userModels.size());
        System.out.println("table.length="+table.length);
        System.out.println("table[0].length="+table[0].length);
        // 主要内容
        for (int b = 1; b < table[0].length; b++) {
            for (int c = 1; c < table.length; c++) {
                for (Map<String, String> o : scoreModels) {
                    if (StringUtils.equals(table[0][c], o.get("dep_id"))) {
                        table[b][c] = o.get("score");
                    }
                }
            }
        }
        System.out.println(table[1][1]);
        System.out.println(table[1][2]);
    }
}