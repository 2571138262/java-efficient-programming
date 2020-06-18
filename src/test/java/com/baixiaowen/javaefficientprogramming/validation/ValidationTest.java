package com.baixiaowen.javaefficientprogramming.validation;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 验证测试类
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationTest {

    // 验证器对象
    private Validator validator;
    // 待验证对象
    private UserInfo userInfo;
    // 验证结果集合
    private Set<ConstraintViolation<UserInfo>> set;
    // 验证结果集
    private Set<ConstraintViolation<UserInfoService>> otherSet;

    /**
     * 初始化操作
     */
    @BeforeAll
    public void init() {
        // 初始化验证器
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        // 初始化待验证对象 - 用户信息
        userInfo = new UserInfo();
//        userInfo.setUserId("Baixiaowen");
//        userInfo.setUserId("");

        userInfo.setUserName("Baixiaowen");
//        userInfo.setUserName("");
//        userInfo.setUserName(" ");

        userInfo.setPassword("123456");
//        userInfo.setPassword("");
//        userInfo.setPassword(" ");

//        userInfo.setEmail("2571138262@qq.com");

        userInfo.setAge(30);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        userInfo.setBirthday(calendar.getTime());

        userInfo.setPhone("18500000000");

        UserInfo friend = new UserInfo();
//        friend.setUserId("baixw");
        friend.setUserName("baixw");
//        friend.setEmail("2571138262@qq.com");
        friend.setPhone("18500000000");

//        userInfo.setFriends(new ArrayList(){{add(new UserInfo());}});
        userInfo.setFriends(Lists.newArrayList(friend));
    }

    /**
     * 结果打印
     */
    @AfterAll
    public void print() {
        set.forEach(item -> System.err.println(item.getMessage()));
//        otherSet.forEach(item -> System.err.println(item.getMessage()));
    }

    @Test
    public void nullValidation() {
        // 使用验证器对对象进行验证
        set = validator.validate(userInfo);
    }

    // TODO 中级约束注解使用
    /**
     * 级联验证测试
     */
    @Test
    public void graphValidation(){
        set = validator.validate(userInfo);
    }

    /**
     * 分组验证
     */
    @Test
    public void groupValidation(){
        // 验证注册组
//        set = validation.validate(userInfo, UserInfo.RegisterGroup.class);
        // 验证注册组， 登录组
        set = validator.validate(userInfo, UserInfo.RegisterGroup.class,
                UserInfo.LoginGroup.class);
    }

    /**
     * 组序列
     */
    @Test
    public void groupSequenceValidation(){
        set = validator.validate(userInfo, UserInfo.Group.class);
    }

    // TODO 高级约束注解使用
    /**
     * 对方法输入参数进行约束注解校验
     */
    @Test
    public void paramValidation() throws NoSuchMethodException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 待验证对象
        UserInfoService service = new UserInfoService();

        // 待验证方法
        Method method = service.getClass()
                .getMethod("setUserInfo", UserInfo.class);

        // 方法输入参数
        Object[] paramObjects = new Object[]{new UserInfo()};

        // 对方法的入参进行校验
        otherSet = executableValidator.validateParameters(
                service,
                method,
                paramObjects);
    }

    /**
     * 对方法赶回值进行约束校验
     */
    @Test
    public void returnValueValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 构造要验证的方法对象
        UserInfoService service = new UserInfoService();
        Method method = service.getClass().getMethod("getUserInfo");

        // 调用方法得到返回值
        Object returnValue = method.invoke(service);

        // 校验方法返回值是否符合约束
        otherSet = executableValidator.validateReturnValue(
                service,
                method,
                returnValue);
    }

    /**
     * 对构造函数输入参数进行校验
     */
    @Test
    public void constructorValidation() throws NoSuchMethodException {
        // 获取验证执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        // 获取构造函数
        Constructor<UserInfoService> constructor
                = UserInfoService.class.getConstructor(UserInfo.class);

        Object[] paramObjects = new Object[]{new UserInfo()};

        // 校验构造函数
        otherSet = executableValidator.validateConstructorParameters(
                constructor,
                paramObjects
        );
    }

}