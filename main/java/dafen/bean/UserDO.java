package dafen.bean;

import java.util.Date;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.phone
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_password
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private String userPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_role
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private Integer userRole;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.update_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.delete_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private Integer deleteTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.department
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private Integer department;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_name
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    private String userName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.phone
     *
     * @return the value of user.phone
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.phone
     *
     * @param phone the value for user.phone
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_password
     *
     * @return the value of user.user_password
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_password
     *
     * @param userPassword the value for user.user_password
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_role
     *
     * @return the value of user.user_role
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_role
     *
     * @param userRole the value for user.user_role
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_time
     *
     * @return the value of user.create_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_time
     *
     * @param createTime the value for user.create_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.update_time
     *
     * @return the value of user.update_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.update_time
     *
     * @param updateTime the value for user.update_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.delete_time
     *
     * @return the value of user.delete_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public Integer getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.delete_time
     *
     * @param deleteTime the value for user.delete_time
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.department
     *
     * @return the value of user.department
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public Integer getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.department
     *
     * @param department the value for user.department
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setDepartment(Integer department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_name
     *
     * @return the value of user.user_name
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_name
     *
     * @param userName the value for user.user_name
     *
     * @mbg.generated Sun Oct 20 16:37:09 CST 2019
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}