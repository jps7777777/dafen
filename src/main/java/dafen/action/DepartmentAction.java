package dafen.action;

import com.alibaba.druid.util.StringUtils;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.response.CommonResponse;
import dafen.service.DepartmentService;
import dafen.service.model.DepartmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller("department")
@RequestMapping("/d")
public class DepartmentAction extends BaseAction {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加部门
     * @param name
     * @return
     * @throws FinallyException
     */
    @RequestMapping("/add")
    @ResponseBody
    public CommonResponse insertDepartment(@RequestParam(name = "name")String name) throws FinallyException {
        if(StringUtils.isEmpty(name)){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setdName(name);
        departmentService.addDepartment(departmentModel);
        return CommonResponse.create("部门添加成功");
    }

    /**
     * 根据部门编号获取部门的名称
     * @param id
     * @return
     * @throws FinallyException
     */
    @RequestMapping("/dep")
    @ResponseBody
    public CommonResponse getDepartment(@RequestParam(name = "department_id")int id) throws FinallyException {
        if(id < 1){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        DepartmentModel departmentModel = departmentService.getDepartmentById(id);
        return CommonResponse.create(departmentModel);
    }

    /**
     * 获取所有的部门信息
     * @return
     * @throws FinallyException
     */
    @RequestMapping("/list")
    @ResponseBody
    public CommonResponse getList() throws FinallyException {
        List<DepartmentModel> list = departmentService.getDepartments();
        return CommonResponse.create(list);
    }

    /**
     * 删除部门信息
     * @return
     * @throws FinallyException
     */
    @RequestMapping("/del")
    @ResponseBody
    public CommonResponse delDepartment(@RequestParam(name = "department_ids")List<Integer> dep_ids) throws FinallyException {
        if(dep_ids.size() < 1){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        departmentService.delDepartment(dep_ids);
        return CommonResponse.create("删除成功");
    }


}
