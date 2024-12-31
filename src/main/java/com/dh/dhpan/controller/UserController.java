package com.dh.dhpan.controller;


import com.dh.dhpan.model.DTO.UserDTO;
import com.dh.dhpan.model.Resp.ResponseMessage;
import com.dh.dhpan.model.entity.User;
import com.dh.dhpan.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dh.dhpan.model.Resp.ResponseMessage.success;

@RestController //接口方法返回对象，转换成json文本
@RequestMapping("/user")
@SessionAttributes
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody UserDTO user) {
        User newUser= userService.register(user);
        if(newUser==null) {
            return ResponseMessage.success("注册成功");
        }
        return ResponseMessage.error("注册失败，用户已存在");
    }
    //登录
    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @PostMapping("/login")
    public ResponseMessage login(@RequestBody UserDTO user, HttpSession httpSession) {
        User newUser= userService.login(user);
        if(newUser != null) {
            httpSession.setAttribute("userid", newUser.getId());
            return ResponseMessage.success("登录成功!当前id:",newUser.getId());
        }else{
            return ResponseMessage.error("账号或密码错误");
        }
    }

    //修改账号密码

    //退出登录

}
