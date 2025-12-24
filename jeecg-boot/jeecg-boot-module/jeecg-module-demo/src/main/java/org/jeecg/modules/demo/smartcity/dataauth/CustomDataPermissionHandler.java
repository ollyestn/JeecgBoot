package org.jeecg.modules.demo.smartcity.dataauth;

//public class CustomDataPermissionHandler {
//
//}

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomDataPermissionHandler {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 获取数据权限条件
     */
    public <T> QueryWrapper<T> getDataPermissionWrapper(Class<T> entityClass) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();

        // 获取当前登录用户
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            return wrapper;
        }

        // 获取用户角色
        List<String> roleCodes = sysUserService.getRole(sysUser.getUsername());

        // 判断是否是管理员角色
        boolean isAdmin = roleCodes.contains("sysadmin");

        if (isAdmin) {
            /**
             * 管理员权限逻辑：
             * 1. 可以查看status=1的所有数据（无论谁创建的）
             * 2. 同时可以查看自己创建的所有数据（无论status值）
             */
            wrapper.and(wq -> wq
                    .eq("status", 1)  // 条件1：status=1的所有数据
                    .or()
                    .eq("create_by", sysUser.getUsername())  // 条件2：自己创建的所有数据
            );
        }
        else
        {
            // 其他用户只能查看自己的数据
            wrapper.eq("create_by", sysUser.getUsername());
        }

        return wrapper;
    }

    public Boolean isSysAdmin(String username) {
        // 获取用户角色
        List<String> roleCodes = sysUserService.getRole(username);

        // 判断是否是管理员角色
        return roleCodes.contains("sysadmin");
    }
}
