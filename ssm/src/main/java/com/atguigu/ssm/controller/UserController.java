package com.atguigu.ssm.controller;

import com.atguigu.ssm.mapper.UserMapper;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.pojo.User;
import com.atguigu.ssm.service.EmployeeService;
import com.atguigu.ssm.service.UserService;
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
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/111")
    public String index(){

        return "forward:/pages/user/login.jsp";
    }

    @RequestMapping(value = "/userServlet",params={"action=login"})
    public  String login(User user, Model model, HttpSession session){
        User loginUser = userService.login(user);
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            model.addAttribute("msg", "用户或密码错误！");
            model.addAttribute("username", user.getUsername());
            //   跳回登录页面
            //req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return "forward:/pages/user/login.jsp";
        } else {
            // 登录 成功
            // 保存用户登录的信息到Session域中
            session.setAttribute("user", loginUser);
            //跳到成功页面login_success.html
            //req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            return "forward:/pages/user/login_success.jsp";
        }
    }


    @RequestMapping(value = "/userServlet",params={"action=regist"})
    public String regist(String code,User user, Model model, HttpSession session){
        // 获取Session中的验证码
        String token = (String)session.getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);

//        2、检查 验证码是否正确
        if (token!=null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(user.getUsername())) {
                System.out.println("用户名[" + user.getUsername() + "]已存在!");

                // 把回显信息，保存到Request域中
                model.addAttribute("msg", "用户名已存在！！");
                model.addAttribute("username", user.getUsername());
                model.addAttribute("email", user.getEmail());

//        跳回注册页面
                return "forward:/pages/user/regist.jsp";
            } else {
                //      可用
//                将注册的用户信息保存到数据库
                userService.registUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                return "forward:/pages/user/regist_success.jsp";
            }
        } else {
            // 把回显信息，保存到Request域中
            model.addAttribute("msg", "验证码错误！！");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());

            System.out.println("验证码[" + code + "]错误");
            return "forward:/pages/user/regist.jsp";
        }

    }
    @RequestMapping(value = "/userServlet",params={"action=logout"})
    public String logout(HttpSession session){
//        1、销毁Session中用户登录的信息（或者销毁Session）
        session.invalidate();
//        2、重定向到首页（或登录页面）。
        //resp.sendRedirect(req.getContextPath());
        return "redirect:/";
    }


}
