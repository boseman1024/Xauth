package com.kuma2x.xauth.controller;

import com.google.gson.Gson;
import com.kuma2x.xauth.entity.SysRole;
import com.kuma2x.xauth.serivce.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping("/role/getall")
    public List<SysRole> getAllRoles(){
        return roleService.selectAllRole();
    }

    @PostMapping("/role/add")
    public int addRole(@RequestBody SysRole role){
        return roleService.insertRole(role);
    }

    @RequestMapping("/role/delete")
    public int deleteRole(@RequestParam int id){
        return roleService.deleteRole(id);
    }

    @PostMapping("/role/update")
    public int updateRole(@RequestBody SysRole role){
        return roleService.updateRole(role);
    }

    @RequestMapping("/role/updatemenu")
    public int updateRoleMenu(@RequestParam int id,@RequestParam List<Integer> menus){
        return roleService.updateRoleMenu(id,menus);
    }
}
