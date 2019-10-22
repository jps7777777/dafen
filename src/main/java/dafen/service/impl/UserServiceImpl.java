package dafen.service.impl;

import com.alibaba.druid.util.StringUtils;
import dafen.bean.UserDO;
import dafen.dao.UserDOMapper;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.service.UserService;
import dafen.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public void register(UserModel userModel) throws FinallyException {
        if(userModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        // 验证唯一性
        UserDO userDO_old = userDOMapper.selectByPhone(userModel.getPhone());
        if(userDO_old != null){
            throw new FinallyException(EnumException.PARAMS_ERROR,"电话号码已存在");
        }

        UserDO userDO = convertByModel(userModel);
        userDOMapper.insertSelective(userDO);
        if(userDO.getId()<1){
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
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
    public void updatePwd(String phone, String password) throws FinallyException {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        UserDO userDO = userDOMapper.selectByPhone(phone);
        if(userDO == null){
            throw new FinallyException(EnumException.DATA_EMPTY,"用户不存在");
        }
        userDO.setUserPassword(password);
        int row = userDOMapper.updateByPrimaryKeySelective(userDO);
        if(row < 1){
            throw new FinallyException(EnumException.PARAMS_ERROR,"密码未做修改，请重置。");
        }
    }

    @Override
    public UserModel getUserInfoByPhone(String phone) {
        return null;
    }

    @Override
    public UserModel getUserInfoById(int id) {
        return null;
    }

    /**
     * 返回所用用户信息
     * @return
     * @throws FinallyException
     */
    @Override
    public List<UserModel> getAllUser() throws FinallyException {
        List<UserDO> userDOS = userDOMapper.selectAll();
        if(userDOS.size() < 1){
            throw new FinallyException(EnumException.DATA_EMPTY);
        }
        List<UserModel> models = new ArrayList<>();
        for (UserDO d:userDOS
             ) {
            models.add(convertByDO(d));
        }
        return models;
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
