package com.kuma2x.xauth.mapper;

import com.kuma2x.xauth.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {

    @Select("select * from sys_user where username = #{username}")
    @Results({
            @Result(property="id",column="id"),
            @Result(property="username",column="username"),
            @Result(property="password",column="password"),
            @Result(property="name",column="name"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="enabled",column="enabled"),
            @Result(property="avatar",column="avatar"),
            @Result(property="role",column="id",javaType=List.class,
                    many=@Many(select="com.kuma2x.xauth.mapper.SysRoleMapper.selectRoleByUId"))
    })
    SysUser selectByUsername(String username);

    @Delete("delete from sys_user where username = #{username}")
    int deleteByUsername(String username);

    @Select("select id,username,name,phone,email,enabled,avatar from sys_user")
    @Results({
            @Result(property="id",column="id"),
            @Result(property="username",column="username"),
            @Result(property="name",column="name"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="enabled",column="enabled"),
            @Result(property="avatar",column="avatar"),
            @Result(property="role",column="id",javaType=List.class,
                    many=@Many(select="com.kuma2x.xauth.mapper.SysRoleMapper.selectRoleByUId"))
    })
    List<SysUser> selectAll();

    @Insert("insert into sys_user(username,password,name,phone,email,avatar) values(#{username},#{password},#{name},#{phone},#{email},#{avatar})")
    int insertUser(SysUser user);

    @Update("update sys_user set name=#{name},phone=#{phone},email=#{email},avatar=#{avatar} where username=#{username}")
    int updateUser(SysUser user);

    @Update("update sys_user set password=#{password} where username=#{username}")
    int updatePwd(String username,String password);

    @Delete("delete from sys_user_role where uid = #{uid}")
    int deleteUserRole(int uid);

    @Insert({
            "<script>"+
                    "insert into sys_user_role(uid,rid) values" +
                    "<foreach collection='roles' item='rid' separator=','> "+
                    "(#{uid},#{rid})"+
                    "</foreach></script>"
    })
    int insertUserRole(@Param("uid")int uid,@Param("roles") List<Integer> roles);
}