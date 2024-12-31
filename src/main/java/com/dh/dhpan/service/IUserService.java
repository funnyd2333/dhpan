package com.dh.dhpan.service;

import com.dh.dhpan.model.DTO.UserDTO;
import com.dh.dhpan.model.entity.User;

public interface IUserService {
    //注册
    User register(UserDTO user);
    //登录
    User login(UserDTO user);

}
