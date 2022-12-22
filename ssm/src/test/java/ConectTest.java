import com.atguigu.ssm.mapper.*;
import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.Order;
import com.atguigu.ssm.pojo.OrderItem;
import com.atguigu.ssm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ConectTest {
    @Autowired
    OrderItemMapper OrderItemMapper;
    @Test
    public void connectTest(){
        OrderItem orderItem = new OrderItem(12,"aaa",3,new BigDecimal(30),new BigDecimal(90),"12");
        OrderItemMapper.saveOrderItem(orderItem);
        //BookMapper.addBook(book);


    }
}
