package com.kuma2x.xauth.serivce;

import com.kuma2x.xauth.entity.SysMenu;

import java.util.List;

public interface MenuService {
    public int deleteMenu(int id);
    public int insertMenu(SysMenu menu);
    public int updateMenu(SysMenu menu);
    public List<SysMenu> selectAllMenu();
    public List<SysMenu> selectMenuByUsername(String username);
}
