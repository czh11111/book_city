package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserMapper {


    User queryUserByUsername(@Param("username") String username);

    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    int saveUser(User user);
}
