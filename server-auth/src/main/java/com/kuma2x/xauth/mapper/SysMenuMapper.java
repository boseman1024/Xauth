package com.kuma2x.xauth.mapper;

import com.kuma2x.xauth.entity.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysMenuMapper {

    @Select("select mid from sys_menu,sys_role_menu where sys_menu.id = sys_role_menu.mid and sys_role_menu.rid = #{rid} and sys_menu.url=1")
    List<Integer> selectMenuByRid(int rid);

    @Select("select distinct sys_menu.id,sys_menu.name,sys_menu.url,sys_menu.path,sys_menu.component,sys_menu.iconCls,sys_menu.parentId " +
            "from sys_user,sys_role,sys_user_role,sys_menu,sys_role_menu " +
            "where sys_user_role.rid=sys_role.id and sys_user_role.uid=sys_user.id " +
            "and sys_role_menu.rid=sys_role.id and sys_role_menu.mid=sys_menu.id " +
            "and username=#{username} " +
            "order by sys_menu.id")
    List<SysMenu> selectMenuByUsername(String username);

    @Select("select * from sys_menu")
    List<SysMenu> selectAllMenu();

    @Select("delete from sys_menu where parentid = #{id}")
    SysMenu deleteMenuByPid(int id);

    @Insert("insert into sys_menu(name,url,path,component,iconcls,parentid) values(#{name},#{url},#{path},#{component},#{iconcls},#{parentid})")
    int insertMenu(SysMenu menu);

    @Delete("delete from sys_menu where id=#{id}")
    int deleteMenu(int id);

    @Update("update sys_menu set name = #{name},url = #{url},path = #{path},component = #{component},iconcls = #{iconcls} where id = #{id}")
    int updateMenu(SysMenu menu);
}