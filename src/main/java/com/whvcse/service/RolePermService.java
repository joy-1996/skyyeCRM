package com.whvcse.service;

import com.whvcse.pojo.RolePerm;
import java.util.List;

public interface RolePermService {
    /*给角色分配权限*/
    Integer addPermissionToRole( List<RolePerm> rolePerms);
    /*删除角色已有的权限*/
    Integer deleteHasPermission(Integer r_id);
    /*选中已经有的权限*/
    List<RolePerm> selectPermissionByEmpId(Integer r_id);
}
