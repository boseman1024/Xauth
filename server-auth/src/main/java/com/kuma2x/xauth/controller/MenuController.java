package com.kuma2x.xauth.controller;

import com.google.gson.Gson;
import com.kuma2x.xauth.entity.SysMenu;
import com.kuma2x.xauth.serivce.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class MenuController {
    @Resource
    private MenuService menuService;

    @RequestMapping("/menu/getall")
    public List<SysMenu> getAllMenu(){
        return menuService.selectAllMenu();
    }

    @RequestMapping("/menu/usermenu")
    public List<SysMenu> getUserMenu(@RequestParam String username){
        return menuService.selectMenuByUsername(username);
    }

    @PostMapping("/menu/add")
    public int addMenu(@RequestBody SysMenu menu){
        int result = menuService.insertMenu(menu);
        return result;
    }

    @PostMapping("/menu/update")
    public int updateMenu(@RequestBody SysMenu menu){
        int result = menuService.updateMenu(menu);
        return result;
    }

    @RequestMapping("/menu/delete")
    public int deleteMenu(@RequestParam int id){
        int result = menuService.deleteMenu(id);
        return result;
    }

}
