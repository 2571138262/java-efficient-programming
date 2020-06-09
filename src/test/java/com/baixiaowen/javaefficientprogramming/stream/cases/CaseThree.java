package com.baixiaowen.javaefficientprogramming.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例三 ：【扁平化】- 权限管理功能模块。查询某用户所有角色下所包含的权限名称
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaseThree {

    /**
     * 角色
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Role {
        /**
         * 权限列表
         */
        private List<Permission> permissions;
    }
    /**
     * 权限
     */
    @Data
    @AllArgsConstructor
    class Permission {
        /**
         * 权限名称
         */
        private String name;
    }

    /**
     * 用户角色列表
     */
    List<Role> roleList;

    @BeforeAll
    public void init() {
        roleList = new ArrayList();

        Role adminRole = new Role();
        List<Permission> adminPermissionList = Lists.newArrayList(
                new Permission("删除"),
                new Permission("查看"),
                new Permission("导出"));
        adminRole.setPermissions(adminPermissionList);

        Role userRole = new Role();
        List<Permission> userPermissionList = Lists.newArrayList(
                new Permission("新建"),
                new Permission("修改"),
                new Permission("删除"),
                new Permission("查看"));
        userRole.setPermissions(userPermissionList);

        roleList.add(adminRole);
        roleList.add(userRole);
    }

    @Test
    public void findPermission(){
        roleList.stream()
                // TODO 扁平化MAP 获取对象中的集合类属性，组成一个新的流，供后续处理
                .flatMap(role -> role.getPermissions().stream())
                .peek(permission -> System.out.println("新的流元素" + permission))
                .distinct()
                .forEach(System.err::println);
    }
}
