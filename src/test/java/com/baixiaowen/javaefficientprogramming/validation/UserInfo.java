package com.baixiaowen.javaefficientprogramming.validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象实体类
 * 用户信息类
 */
public class UserInfo {

    // 分组验证标识
    // 登录场景
    public interface LoginGroup {
    }

    // 注册场景
    public interface RegisterGroup {
    }

    // 组排序场景
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class
    })
    public interface Group {
    }


    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空",
            // 登录组需要验证的属性
            groups = LoginGroup.class)
    private String userId;

    /**
     * 用户姓名
     *
     * @NotEmpty 不会去掉前后空格
     */
    @NotEmpty(message = "用户名称不能为空")
    private String userName;

    /**
     * 用户密码
     *
     * @NotBlank 自动去掉字符串前后空格后验证是否为空
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20, message = "密码不能少于6位， 多于20位")
    private String password;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱必须为有效邮箱")
    @NotNull(message = "邮箱不能为空",
            // 注册组需要验证的属性
            groups = RegisterGroup.class)
    private String email;

    /**
     * 用户电话
     */
    @Phone(message = "手机号不是185后头随便·")
    private String phone;

    /**
     * 年龄
     */
    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 60, message = "年龄不能大于60岁")
    private Integer age;

    /**
     * 用户生日
     */
    @Past(message = "生日不能为未来时间点")
//    @Future(message = "生日不能为过去时间")
    private Date birthday;

    /**
     * 好友列表
     */
    @Size(min = 1, message = "不能少于1个好友")
    private List<@Valid UserInfo> friends;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserInfo> friends) {
        this.friends = friends;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}