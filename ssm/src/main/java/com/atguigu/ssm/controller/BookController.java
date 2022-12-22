package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.service.BookService;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
public class BookController {

    @Autowired
    private BookService bookService;
    @RequestMapping(value = "/manager/bookServlet",params={"action=page"})
    public String page(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                       Model model
    ){
        //1 获取请求的参数 pageNo 和 pageSize

        //2 调用BookService.page(pageNo，pageSize)：Page对象
        PageInfo<Book> page = bookService.getBookPage(pageNo);
        model.addAttribute("url","manager/bookServlet?action=page");
        model.addAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        return "forward:/pages/manager/book_manager.jsp";
    }
    @RequestMapping(value = "/manager/bookServlet",params={"action=add"})
    public String add(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                      Book book) {
        pageNo+=1;
//        2、调用BookService.addBook()保存图书
        bookService.addBook(book);
//        3、跳到图书列表页面
//                /manager/bookServlet?action=list
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        String uri="redirect:/manager/bookServlet?action=page&pageNo=" + pageNo;
        return uri;

    }


    @RequestMapping(value = "/manager/bookServlet",params={"action=delete"})
    public String delete(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                         int id) {
//        2、调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
//        3、重定向回图书列表管理页面
//                /book/manager/bookServlet?action=list
        String uri = "redirect:/manager/bookServlet?action=page&pageNo="+ pageNo;
        return uri;
    }


    @RequestMapping(value = "/manager/bookServlet",params={"action=update"})
    public String update(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                         Book book) {
//        2、调用BookService.updateBook( book );修改图书
        bookService.updateBook(book);
//        3、重定向回图书列表管理页面
//        地址：/工程名/manager/bookServlet?action=list
        String uri = "redirect:/manager/bookServlet?action=page&pageNo="+ pageNo;
        return uri;
    }


    @RequestMapping(value = "/manager/bookServlet",params={"action=getBook"})
    public String getBook(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                          int id,
                          Model model) {
        //2 调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到Request域中
        model.addAttribute("book", book) ;
        //4 请求转发到。pages/manager/book_edit.jsp页面
        return "forward:/pages/manager/book_edit.jsp";
    }


    @RequestMapping(value = "/manager/bookServlet",params={"action=list"})
    public String list(Model model) {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        model.addAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        return "forward:/pages/manager/book_manager.jsp";
    }


}
