package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2022/7/11
 * Author:ybc
 * Description:
 * 查询所有的员工信息-->/employee-->get
 * 查询员工的分页信息-->/employee/page/1-->get
 * 根据id查询员工信息-->/employee/1-->get
 * 跳转到添加页面-->/to/add-->get
 * 添加员工信息-->/employee-->post
 * 修改员工信息-->/employee-->put
 * 删除员工信息-->/employee/1-->delete
 */
@Controller
public class ClientBookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/client/bookServlet",params={"action=page"})
    public String page(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                       Model model
    ){
        //1 获取请求的参数 pageNo 和 pageSize

        //2 调用BookService.page(pageNo，pageSize)：Page对象
        PageInfo<Book> page = bookService.getBookPage(pageNo);
        model.addAttribute("url","client/bookServlet?action=page");
        model.addAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        return "forward:/pages/client/index.jsp";
    }

    @RequestMapping(value = "/client/bookServlet",params={"action=pageByPrice"})
    public String pageByPrice(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                              Integer min,
                              Integer max,
                              Model model){
        // 如果有最小价格的参数,追加到分页条的地址参数中
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (min != null) {
            sb.append("&min=").append(min);
        }
        else
            min = 0;
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (max !=null) {
            sb.append("&max=").append(max);
        }
        else
            max = 9999;
        // 调用BookService.page(pageNo，pageSize)：Page对象
        PageInfo<Book> page = bookService.getBookPageByPrice(pageNo,min,max);
        model.addAttribute("url",sb);
        model.addAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        return "forward:/pages/client/index.jsp";
    }

}
