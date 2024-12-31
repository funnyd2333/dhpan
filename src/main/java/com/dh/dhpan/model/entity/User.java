package com.dh.dhpan.model.entity;

import jakarta.persistence.*;


@Table(name="pan_users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    @Column(name = "user_id")
    private Integer userid;
    private String username;
    private String password;

    public Integer getId() {
        return userid;
    }

    public void setId(Integer id) {
        this.userid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
