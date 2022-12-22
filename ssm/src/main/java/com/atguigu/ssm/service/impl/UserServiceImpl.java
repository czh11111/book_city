package com.atguigu.ssm.service.impl;


import com.atguigu.ssm.mapper.UserMapper;
import com.atguigu.ssm.pojo.User;
import com.atguigu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public void registUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public User login(User user) {
        /**
         * 咱们的登录，只是需要查询数据库即可！！！
         *
         * QQ登录 ！！！
         * 1、查询用户名和密码
         * 2、查询天气信息
         * 3、查询QQ邮箱几封未读邮件
         * 4、查询QQ空间留言数
         * 5、还要查询QQ游戏 有没有什么更新
         * 6、查询QQ的黄钻，什么什么钻，的皮肤信息。
         * 7、还要查询个个好友的登录IP和登录位置信息
         * 8、发QQ好友登录提示音
         */
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {

        if (userMapper.queryUserByUsername(username) == null) {
           // 等于null,说明没查到，没查到表示可用
           return false;
        }

        return true;

    }
}
