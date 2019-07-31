package com.example.invok.service.iml;

import com.example.invok.bean.User;
import com.example.invok.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserbyName(String name) {
        User user = new User();
        user.setName(name);
        user.setAge(25);
        user.setEmail("abc@163.com");
        return user;
    }

    @Override
    public User getUserByName2(String name2) {
        User user = new User();
        user.setName(name2 + "2");
        user.setAge(25);
        user.setEmail("abc@163.com");
        return user;
    }
}
