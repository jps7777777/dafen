package dafen.service.impl;

import com.alibaba.druid.util.StringUtils;
import dafen.action.View.UserVO;
import dafen.bean.UserDO;
import dafen.dao.UserDOMapper;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.service.UserService;
import dafen.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public void register(UserModel userModel) throws FinallyException {
        if(userModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        UserDO userDO = convertByModel(userModel);
        userDOMapper.insertSelective(userDO);
        if(userDO.getId()<1){
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
        System.out.println("添加数据成功，id="+userDO.getId());
        System.out.println("添加分数");
    }

    @Override
    public UserModel login(String phone, String password) throws FinallyException {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }

        UserDO res_user = userDOMapper.selectByPhone(phone);
        if(res_user == null){
            throw new FinallyException(EnumException.USER_NOT_SAVED);
        }
        if(!StringUtils.equals(res_user.getUserPassword(),password)){
            throw new FinallyException(EnumException.USER_ERROR_ACCOUNT_OR_PASSWORD);
        }

        return convertByDO(res_user);
    }

    @Override
    public String updatePwd(String phone, String password) {
        return null;
    }

    @Override
    public UserModel getUserInfoByPhone(String phone) {
        return null;
    }

    @Override
    public UserModel getUserInfoById(int id) {
        return null;
    }




    private UserDO convertByModel(UserModel userModel) throws FinallyException {
        if(userModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }


    private UserModel convertByDO(UserDO userDO) throws FinallyException {
        if(userDO == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        return userModel;
    }



}
