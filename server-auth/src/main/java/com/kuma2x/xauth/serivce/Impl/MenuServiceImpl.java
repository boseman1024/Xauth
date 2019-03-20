package com.kuma2x.xauth.serivce.Impl;

import com.kuma2x.xauth.entity.SysMenu;
import com.kuma2x.xauth.mapper.SysMenuMapper;
import com.kuma2x.xauth.serivce.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int deleteMenu(int id) {
        sysMenuMapper.deleteMenuByPid(id);
        return sysMenuMapper.deleteMenu(id);
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return sysMenuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return sysMenuMapper.updateMenu(menu);
    }

    @Override
    public List<SysMenu> selectAllMenu() {
        return sysMenuMapper.selectAllMenu();
    }

    @Override
    public List<SysMenu> selectMenuByUsername(String username) {
        return sysMenuMapper.selectMenuByUsername(username);
    }
}
