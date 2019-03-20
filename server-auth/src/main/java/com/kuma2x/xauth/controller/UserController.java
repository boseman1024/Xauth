package com.kuma2x.xauth.controller;

import com.kuma2x.xauth.entity.SysUser;
import com.kuma2x.xauth.serivce.UserService;
import com.kuma2x.xauth.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/auth/login")
    public Map<String,String> Login(@RequestBody SysUser user){
        System.out.println(user.getUsername()+"====="+user.getPassword());
        String token = userService.login(user.getUsername(),user.getPassword());
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        return tokenMap;
    }

    @RequestMapping("/user/userinfo")
    public Map<String,SysUser> getUserInfo(@RequestParam String username){
        SysUser user = userService.getUserByUsername(username);
        Map<String,SysUser> userInfo = new HashMap<>();
        userInfo.put("user",user);
        return userInfo;
    }

    @RequestMapping("/user/all")
    public List<SysUser> getAll(){
        return userService.getAllUser();
    }

    @RequestMapping("/user/delete")
    public int deleteUser(@RequestParam String username){
        int result = userService.deleteByUsername(username);
        return result;
    }

    @PostMapping("/user/add")
    public int addUser(@RequestBody SysUser user){
        int result = userService.insertUser(user);
        return result;
    }

    @PostMapping("/user/update")
    public int updateUser(@RequestBody SysUser user){
        int result = userService.updateUser(user);
        return result;
    }

    @RequestMapping("/user/changerole")
    public int changeUserRole(@RequestParam int uid,@RequestParam List<Integer> roles){
        return userService.changeRole(uid,roles);
    }
}
