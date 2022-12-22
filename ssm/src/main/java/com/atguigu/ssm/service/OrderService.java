package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
