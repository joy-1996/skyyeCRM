package com.whvcse.dao;


import com.whvcse.pojo.RolePerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermDao {
    /*给角色分配权限*/
    Integer addPermissionToRole(@Param("rolePerms") List<RolePerm> rolePerms);
    /*删除角色已有的权限*/
    Integer deleteHasPermission(Integer r_id);
    /*选中已经有的权限*/
    List<RolePerm> selectPermissionByEmpId(Integer r_id);
}
