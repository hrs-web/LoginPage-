package cn.hrs.web.servlet;

import cn.hrs.dao.UserDao;
import cn.hrs.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置编码格式
        req.setCharacterEncoding("utf-8");

        // 2.先获输入的和生成的验证码
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        // 清除session中存储的验证码
        session.removeAttribute("checkCode_session");
        // 3.获取所有请求参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 3.1.创建User对象
        User LoginUser = new User();
        // 3.2.使用BeanUtils封装
        try {
            BeanUtils.populate(LoginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 4.调用UserDao的login方法，如果有些用户就返回user对象
        UserDao dao = new UserDao();
        User user = dao.login(LoginUser);

        // 5.先判断验证码是否正确
        if (checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)){
            // 验证码正确
            // 6.判断用户名和密码是否正确
            if (user != null){
                // 登录成功
                // 存储用户信息
                session.setAttribute("user",user.getUsername());
                // 重定向到success.jsp
                resp.sendRedirect(req.getContextPath()+"/success.jsp");
            }else{
                // 用户名或密码错误
                req.setAttribute("login_error","用户名或密码错误");
                // 转发到登录界面
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }else{
            // 验证码不一致
            // 存储提示信息到request
            req.setAttribute("cc_error","验证码错误");
            //转发到登录界面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
