package dafen.action;


import com.alibaba.druid.util.StringUtils;
import dafen.action.View.UserVO;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.response.CommonResponse;
import dafen.service.UserService;
import dafen.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Controller("login")
@RequestMapping("/u")
public class LoginAction extends BaseAction{

    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    @ResponseBody
    public CommonResponse register(@RequestParam(name = "phone")String phone,
                                   @RequestParam(name = "password")String pwd,
                                   @RequestParam(name = "department")int department,
                                   @RequestParam(name = "isManager")int isManager,
                                   @RequestParam(name = "name")String name) throws FinallyException {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)
                || StringUtils.isEmpty(name)){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }

        UserModel userModel=new UserModel();
        userModel.setPhone(phone);
        userModel.setUserName(name);
        userModel.setDepartment(department);
        userModel.setUserRole(isManager);
        userModel.setUserPassword(pwd);
        userService.register(userModel);

        return CommonResponse.create("注册成功");
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonResponse login(@RequestParam(name = "phone")String phone,
                                   @RequestParam(name = "password")String pwd) throws FinallyException {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }

        UserModel userModel = userService.login(phone,pwd);
        return CommonResponse.create(convertByModel(userModel));
    }


    private UserVO convertByModel(UserModel userModel) throws FinallyException {
        if(userModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }


}
