package dafen.service;

import dafen.exception.FinallyException;
import dafen.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 用户注册
     * @param userModel
     * @throws FinallyException
     */
    void register(UserModel userModel) throws FinallyException;

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     * @throws FinallyException
     */
    UserModel login(String phone,String password) throws FinallyException;

    /**
     * 修改密码
     * @param phone
     * @param password
     * @return
     */
    void updatePwd(String phone,String password) throws FinallyException;

    UserModel getUserInfoByPhone(String phone);
    UserModel getUserInfoById(int id);


}
