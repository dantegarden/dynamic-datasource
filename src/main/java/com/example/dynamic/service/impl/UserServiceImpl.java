package com.example.dynamic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dynamic.dao.SysUserMapper;
import com.example.dynamic.model.SysUser;
import com.example.dynamic.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: lij
 * @create: 2020-02-14 21:58
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService  {
}
