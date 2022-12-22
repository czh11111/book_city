package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    public int saveOrder(Order order);
}
