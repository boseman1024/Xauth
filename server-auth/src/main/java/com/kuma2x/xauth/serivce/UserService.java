package com.kuma2x.xauth.serivce;

import com.kuma2x.xauth.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    public String login(String username,String password);
    public SysUser getUserByUsername(String username);
    public int deleteByUsername(String username);
    public int insertUser(SysUser user);
    public int updateUser(SysUser user);
    public List<SysUser> getAllUser();
    public int updatePwd(String username,String oldPassword,String newPassword);
    public int changeRole(int uid,List<Integer> roles);
}
