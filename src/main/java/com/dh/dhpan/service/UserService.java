package com.dh.dhpan.service;

import com.dh.dhpan.DAO.UserRepository;
import com.dh.dhpan.exception;
import com.dh.dhpan.model.DTO.UserDTO;
import com.dh.dhpan.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements IUserService {
    @Autowired
    private UserRepository userRepository;
    //注册
    @Override
    public User register(UserDTO user) {
        User newUser = new User();
        //判断用户是否存在
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new exception.UsernameExistsException("Username already exists");
        }
        // 检查用户名和密码是否为空
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    @Override
    public User login(UserDTO user) {
        User newUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (newUser != null) {
            newUser.setPassword(null);
            return newUser;
        }
        return null;
    }
}
