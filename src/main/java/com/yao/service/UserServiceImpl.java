package com.yao.service;

import com.yao.dao.UserRepository;
import com.yao.po.User;
import com.yao.util.MD5Utils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserRepository userRepository;

    @Override
    public User userCheck( String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));

        return user;
    }
}
