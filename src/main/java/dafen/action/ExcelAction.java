package dafen.action;


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
import dafen.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Controller("excel")
@RequestMapping("/excel")
public class ExcelAction extends BaseAction {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScoreService scoreService;

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

    /**
     * 直接下载
     * 数据全部导出
     * @param response
     * @throws FinallyException
     */
    @RequestMapping("/export")
    @ResponseBody
    public CommonResponse test2(HttpServletResponse response,
                      @RequestParam(name = "times")int times) throws FinallyException {
        ExcelData data = new ExcelData();
        data.setName("page_one");
        List<DepartmentModel> list = departmentService.getDepartments();
        List<String> titles = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();

        List<Object> scoreModels = scoreService.exportScoreByTimes(times);

        return CommonResponse.create(scoreModels);

//        titles.add("人员\\部门");
//        for (DepartmentModel model :list ) {
//            int dep_id = model.getId();
//            titles.add(model.getdName());
//        }
//
//        titles.add("ID");
//
//        data.setTitles(titles);
//
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

}
