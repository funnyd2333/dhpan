package com.dh.dhpan.DAO;

import com.dh.dhpan.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 检查用户名是否存在
    boolean existsByUsername(String username);
    Optional<Object> findByUsername(@NotBlank(message = "用户名不能为空") String username);
    User findByUsernameAndPassword(String username, String password);//通过用户名uname和密码查找用户
}
