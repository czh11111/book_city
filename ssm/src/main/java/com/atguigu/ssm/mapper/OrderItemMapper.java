package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int saveOrderItem(OrderItem orderItem);

}
