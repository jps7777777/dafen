package dafen.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(description="用户的实体对象")
public class UserModel {
    @ApiModelProperty(value="用户id",name="id",required=true)
    private int id;
    @ApiModelProperty(value="用户名",name="userName",required=true)
    @NotNull
    private String userName;
    @NotNull
    private String phone;
    @NotNull
    private String userPassword;

    /**
     *
     */
    @NotNull
    private Integer userRole;

    /**
     *
     */
    private Integer department;



//    /**
//     *
//     */
//    private Date createTime;
//    /**
//     *
//     */
//    private Integer deleteTime = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Integer getDeleteTime() {
//        return deleteTime;
//    }
//
//    public void setDeleteTime(Integer deleteTime) {
//        this.deleteTime = deleteTime;
//    }
}
