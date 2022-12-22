import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.User;
import com.atguigu.ssm.service.BookService;
import com.atguigu.ssm.service.OrderService;
import com.atguigu.ssm.service.UserService;
import com.atguigu.ssm.service.impl.UserServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLOutput;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class serviceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Test
    public void userServiceImplTest(){
        Boolean b=userService.existsUsername("admin");
        System.out.println(b);
    }
    @Test
    public void userServiceImplTest1(){
        User user= userService.login(new User(null,"admin","admin",null));
        user.setUsername("admin2");
        userService.registUser(user);
        //System.out.println(user);
    }
    @Test
    public void bookServiceTest1(){
        //List<Book> books = bookService.queryBooks();
        PageInfo<Book> page = bookService.getBookPageByPrice(4, 0, 50);
        System.out.println(page);
//        for (Book book : books) {
//            System.out.println(book);
//        }

    }
    @Test
    public void orderServiceTest1(){
        //orderService.

    }

}
