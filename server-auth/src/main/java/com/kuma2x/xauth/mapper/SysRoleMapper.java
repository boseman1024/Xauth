package com.kuma2x.xauth.mapper;

import com.kuma2x.xauth.entity.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRoleMapper {

    @Select("select * from sys_role,sys_user_role where sys_user_role.rid = sys_role.id and sys_user_role.uid = #{uid}")
    @Results({
            @Result(property="id",column="id"),
            @Result(property="name",column="name"),
            @Result(property="namezh",column="namezh"),
            @Result(property="menus",column="id",javaType=List.class,
                    many=@Many(select="com.kuma2x.xauth.mapper.SysMenuMapper.selectMenuByRid"))
    })
    List<SysRole> selectRoleByUId(int uid);

    @Select("select * from sys_role")
    @Results({
            @Result(property="id",column="id"),
            @Result(property="name",column="name"),
            @Result(property="namezh",column="namezh"),
            @Result(property="menus",column="id",javaType=List.class,
                    many=@Many(select="com.kuma2x.xauth.mapper.SysMenuMapper.selectMenuByRid"))
    })
    List<SysRole> selectAllRole();

    @Insert("insert into sys_role(name,namezh) values(#{name},#{namezh})")
    int insertRole(SysRole role);

    @Delete("delete from sys_role where id=#{id}")
    int deleteRole(int id);

    @Update("update sys_role set name = #{name},namezh = #{namezh} where id = #{id}")
    int updateRole(SysRole role);

    @Delete("delete from sys_role_menu where rid = #{id}")
    int deleteRoleMenu(int id);

    @Insert({
            "<script>"+
            "insert into sys_role_menu(rid,mid) values" +
            "<foreach collection='menus' item='mid' separator=','> "+
            "(#{id},#{mid})"+
            "</foreach></script>"
    })
    int insertRoleMenu(@Param("id")int id,@Param("menus") List<Integer> menus);
}