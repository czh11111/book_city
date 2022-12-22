package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.Cart;
import com.atguigu.ssm.pojo.CartItem;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.service.BookService;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class CartController {

    @Autowired
    private BookService bookService;
    @RequestMapping(value = "/cartServlet",params={"action=updateCount"})
    public String updateCount(int id, int count,HttpSession session,
                              @RequestHeader(value="Referer") String referer
    ){
        Cart cart =(Cart)session.getAttribute("cart");
        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id,count);
            // 重定向回原来购物车展示页面
            //resp.sendRedirect(req.getHeader("Referer"));
        }
        String uri = "redirect:"+referer;
        return uri;
    }

    @RequestMapping(value = "/cartServlet",params={"action=clear"})
    public String clear(HttpSession session,
                        @RequestHeader(value="Referer") String referer){
        // 1 获取购物车对象
        Cart cart =(Cart)session.getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面
            //resp.sendRedirect(req.getHeader("Referer"));

        }
        String uri = "redirect:"+referer;
        return uri;
    }
    @RequestMapping(value = "/cartServlet",params={"action=deleteItem"})
    public String deleteItem(int id,HttpSession session,
                             @RequestHeader(value="Referer") String referer){
        Cart cart =(Cart)session.getAttribute("cart");
        if (cart != null) {
            // 删除 了购物车商品项
            cart.deleteItem(id);
            // 重定向回原来购物车展示页面
            //resp.sendRedirect(req.getHeader("Referer"));

        }
        String uri = "redirect:"+referer;
        return uri;
        //return "redirect:/";

    }



    @RequestMapping(value = "/cartServlet",params={"action=addItem"})
    public String addItem(int id, HttpSession session,
                          @RequestHeader(value = "referer") String referer) {
        Book book = bookService.queryBookById(id);
        System.out.println(book);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("请求头Referer的值：" + referer);
        // 最后一个添加的商品名称
        session.setAttribute("lastName", cartItem.getName());

        // 重定向回原来商品所在的地址页面
        //resp.sendRedirect(req.getHeader("Referer"));
        String uri = "redirect:"+referer;
        return uri;
    }
}
