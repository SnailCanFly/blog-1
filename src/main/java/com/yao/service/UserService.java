package com.yao.service;

import com.yao.po.User;

public interface UserService {
    User userCheck(String username, String password);

}
