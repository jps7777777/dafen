package dafen.action;


import com.alibaba.druid.util.StringUtils;
import dafen.action.View.UserVO;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.response.CommonResponse;
import dafen.service.UserService;
import dafen.service.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;


@RestController
@Controller("login")
@RequestMapping("/u")
@Api(value="用户controller",tags={"用户操作接口"})
public class LoginAction extends BaseAction{

    @Autowired
    private UserService userService;

    @ApiOperation(value="创建用户" ,notes="创建用户时的注意事项")
    @PostMapping("/register")
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
        userModel.setUserPassword(entryptPassword(pwd));
        userService.register(userModel);

        return CommonResponse.create("注册成功");
    }

    @ApiOperation(value="用户登录" ,notes="用户时传递参数")
    @PostMapping("/login")
    @ResponseBody
    public CommonResponse login(@RequestParam(name = "phone")String phone,
                                @RequestParam(name = "password")String pwd) throws FinallyException {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        UserModel userModel = userService.login(phone,entryptPassword(pwd));
        return CommonResponse.create(convertByModel(userModel));
    }

    @ApiOperation(value="修改用户密码" ,notes="用户修改密码时使用接口")
    @PostMapping("/reset")
    @ResponseBody
    public CommonResponse resetPwd(@RequestParam(name = "phone")String phone,
                                   @RequestParam(name = "password")String pwd) throws FinallyException {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        userService.updatePwd(phone,entryptPassword(pwd));
        return CommonResponse.create("密码修改成功");
    }

    /**
     * 使用md5对密码进行加密
     * @param password 密码
     * @return String
     */
    private String entryptPassword(String password) throws FinallyException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            byte[] md5s = MessageDigest.getInstance("MD5").digest(password.getBytes("utf-8"));
            for (byte b : md5s) {
                stringBuilder.append(String.format("%02x", new Integer(b & 0xff)));
            }
        } catch (Exception e) {
            throw new FinallyException(EnumException.PARAMS_ERROR.setErrMsg("密码设置错误"));
        }
        return stringBuilder.toString();
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


