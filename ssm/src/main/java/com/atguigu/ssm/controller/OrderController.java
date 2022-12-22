package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.Cart;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.pojo.User;
import com.atguigu.ssm.service.EmployeeService;
import com.atguigu.ssm.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/orderServlet")
    public String createOrder(HttpSession session){
        // 先获取Cart购物车对象
        Cart cart = (Cart)session.getAttribute("cart");
        // 获取Userid
        User loginUser = (User)session.getAttribute("user");
        if (loginUser == null) {
            return "forward:/pages/user/login.jsp";
        }

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);

//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        session.setAttribute("orderId",orderId);
        return "redirect:/pages/cart/checkout.jsp";
        //resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
