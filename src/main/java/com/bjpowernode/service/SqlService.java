package com.bjpowernode.service;

import com.bjpowernode.bean.User;

import java.util.List;

public interface SqlService {
    int add(User user);
    List<User> selectAll();
}
