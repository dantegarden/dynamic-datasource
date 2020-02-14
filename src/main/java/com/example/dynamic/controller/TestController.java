package com.example.dynamic.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.dynamic.config.dds.DataSource;
import com.example.dynamic.config.dds.DynamicDataSourceContextHolder;
import com.example.dynamic.dao.SysUserMapper;
import com.example.dynamic.model.SysUser;
import com.example.dynamic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lij
 * @create: 2020-02-14 22:00
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @DataSource("master")
    @RequestMapping("/master")
    public ResponseEntity master(){
        SysUser sysUser = new SysUser();
        sysUser.setName(RandomUtil.randomString(8)).setPassword("123123");
        sysUserMapper.insert(sysUser);
        return ResponseEntity.ok(true);
    }

    @DataSource("slave")
    @RequestMapping("/slave")
    public ResponseEntity slave(){
        SysUser sysUser = new SysUser();
        sysUser.setName(RandomUtil.randomString(8)).setPassword("123123");
        userService.save(sysUser);
        return ResponseEntity.ok(true);
    }

    @RequestMapping("/{tenant}/addUser")
    public ResponseEntity addUser(@PathVariable String tenant){
        if(!DynamicDataSourceContextHolder.containDataSourceKey(tenant)){
            return ResponseEntity.ok("没有这个租户");
        }
        DynamicDataSourceContextHolder.setDataSourceKey(tenant);
        SysUser sysUser = new SysUser();
        sysUser.setName(RandomUtil.randomString(8)).setPassword("123123");
        userService.save(sysUser);
        DynamicDataSourceContextHolder.clearDataSourceKey();
        return ResponseEntity.ok(true);
    }
}
