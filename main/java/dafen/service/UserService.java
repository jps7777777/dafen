package dafen.service;

import dafen.exception.FinallyException;
import dafen.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    void register(UserModel userModel) throws FinallyException;


    UserModel login(String phone,String password) throws FinallyException;



    String updatePwd(String phone,String password);

    UserModel getUserInfoByPhone(String phone);
    UserModel getUserInfoById(int id);


}
