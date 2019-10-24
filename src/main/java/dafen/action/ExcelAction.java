package dafen.action;


import com.alibaba.druid.util.StringUtils;
import dafen.bean.ExcelData;
import dafen.bean.UserDO;
import dafen.constant.ExcelConstant;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.response.CommonResponse;
import dafen.service.DepartmentService;
import dafen.service.ScoreService;
import dafen.service.UserService;
import dafen.service.model.DepartmentModel;
import dafen.service.model.ScoreModel;
import dafen.service.model.UserModel;
import dafen.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Controller("excel")
@RequestMapping("/excel")
@Api(tags = "管理员导出信息表")
public class ExcelAction extends BaseAction {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScoreService scoreService;


    /**
     * 超级管理员下载内容，根据时间次数下载
     * 数据全部导出
     * 复杂了点
     *
     * @param response
     * @throws FinallyException
     */
    @ApiOperation(value = "管理员根据times标记，导出对应轮次的打分信息",notes = "超级管理员导出")
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    @ResponseBody
    public void exportByTimes(HttpServletResponse response,
                      @RequestParam(name = "times") int times) throws FinallyException {
        // 制作数据表
        List<UserModel> userModels = userService.getAllUser(); // 用户行
        List<DepartmentModel> list = departmentService.getDepartments();// 部门列
        List<Map<String, String>> scoreModels = scoreService.exportScoreByTimes(times);// 分数格

        // 制表内容
        String[][] table = new String[userModels.size() + 2][list.size() + 2];
        int j = 2;
        int k = 2;
        // 从第三行开始添加用户信息，第一个为用户编号，第二格为用户名字
        for (UserModel m : userModels) {
            int u_id = m.getId();
            table[j][0] = u_id + "";
            table[j][1] = m.getUserName();
            j++;
        }
        // 从第三行开始添加部门信息，第一格为部门编号，第二格为部门名称
        for (DepartmentModel model : list) {
            int dep_id = model.getId();
            table[0][k] = dep_id + "";
            table[1][k] = model.getdName();
            k++;
        }
        // 添加用户得分信息
        for (int b = 2; b < table.length; b++) {
            // 每个用户的内容
            for (int c = 2; c < table[0].length; c++) {
                // 用户每个部门的内容
                for (Map<String, String> o : scoreModels) {
                    // 找出坐标点的内容
                    if (StringUtils.equals(table[0][c], o.get("dep_id")) && StringUtils.equals(table[b][0], o.get("user_id"))) {
                        // 找到坐标点的内容
                        table[b][c] = o.get("score");
                    }
                }
            }
        }
        // 设置表格信息
        ExcelData data = new ExcelData();
        data.setName("page_one");
        // 表title信息
        List<String> titles = new ArrayList<>();
        titles.add("人员\\部门");
        for (int x = 2; x < table[0].length; x++) {
            titles.add(table[1][x]);
        }
        data.setTitles(titles);
        // 设置 table 信息
        List<List<Object>> rows = new ArrayList<>();
        for (int y = 2; y < table.length; y++) {
            List<Object> row = new ArrayList<>();
            for (int z = 1; z < table[0].length; z++) {
                row.add(table[y][z]);
            }
            rows.add(row);
        }
        data.setRows(rows);
        String file = new SimpleDateFormat("yyyyMMdd").format(new Date());
        try {
            ExcelUtils.exportExcel(response, file, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 直接下载
//     * 用户数据导出
//     * @param response
//     * @throws FinallyException
//     */
//    @RequestMapping("/export")
//    public void exportByUser(HttpServletResponse response, @RequestParam(name = "user_id")int user_id) throws FinallyException {
//        if(user_id < 1){
//            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
//        }
//        List<ScoreModel> userInfo = scoreService.getScoreByUserId(user_id);
//        List<DepartmentModel> list = departmentService.getDepartments();
//
//
//        ExcelData data = new ExcelData();
//        data.setName("hello");
//        List<String> titles = new ArrayList<>();
//        titles.add("ID");
//        titles.add("name");
//        data.setTitles(titles);
//
//        List<List<Object>> rows = new ArrayList<>();
//        for(int i = 0, length = list.size();i<length;i++){
//            DepartmentModel userInfo = list.get(i);
//            List<Object> row = new ArrayList<>();
//            row.add(userInfo.getId());
//            row.add(userInfo.getdName());
//            rows.add(row);
//        }
//        data.setRows(rows);
//        String file = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        try{
//            ExcelUtils.exportExcel(response,file,data);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    /**
     * 保存到本地
     * @param response
     * @throws FinallyException
     */
//    @RequestMapping("/test")
//    public CommonResponse test() throws FinallyException {
//        int rowIndex = 0;
//        List<DepartmentModel> list = departmentService.getDepartments();
//        ExcelData data = new ExcelData();
//        data.setName("hello");
//        List<String> titles = new ArrayList<>();
//        titles.add("ID");
//        titles.add("name");
//        data.setTitles(titles);
//
//        List<List<Object>> rows = new ArrayList<>();
//        for(int i = 0, length = list.size();i<length;i++){
//            DepartmentModel model = list.get(i);
//            List<Object> row = new ArrayList<>();
//            row.add(model.getId());
//            row.add(model.getdName());
//            rows.add(row);
//        }
//        data.setRows(rows);
//        try{
//            rowIndex = ExcelUtils.generateExcel(data, ExcelConstant.FILE_PATH + ExcelConstant.FILE_NAME);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
////        return RetResponse.makeOKRsp(Integer.valueOf(rowIndex));
//        return CommonResponse.create("返回结果："+Integer.valueOf(rowIndex));
//    }
}
