package com.kuma2x.xauth.serivce;

import com.kuma2x.xauth.entity.SysRole;

import java.util.List;


public interface RoleService {
    public int deleteRole(int id);
    public int insertRole(SysRole role);
    public int updateRole(SysRole role);
    public List<SysRole> selectAllRole();
    public int updateRoleMenu(int id,List<Integer> menus);
}
