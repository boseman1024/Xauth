package com.kuma2x.xauth.serivce.Impl;

import com.kuma2x.xauth.entity.SysRole;
import com.kuma2x.xauth.mapper.SysRoleMapper;
import com.kuma2x.xauth.serivce.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int deleteRole(int id) {
        return sysRoleMapper.deleteRole(id);
    }

    @Override
    public int insertRole(SysRole role) {
        return sysRoleMapper.insertRole(role);
    }

    @Override
    public int updateRole(SysRole role) {
        return sysRoleMapper.updateRole(role);
    }

    @Override
    public List<SysRole> selectAllRole() {
        return sysRoleMapper.selectAllRole();
    }

    @Override
    public int updateRoleMenu(int id, List<Integer> menus) {
        sysRoleMapper.deleteRoleMenu(id);
        if(menus.isEmpty()){
            return 1;
        }
        return sysRoleMapper.insertRoleMenu(id,menus);
    }
}
