package cn.itcase.web.servlet;

import cn.itcase.dao.UserDao;
import cn.itcase.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置编码格式
        req.setCharacterEncoding("utf-8");

        /*
        //2、获取username和password的值
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //3、将获取的值封装到User对象中
        User LoginUser = new User();
        LoginUser.setUsername(username);
        LoginUser.setPassword(password);
        */
        //2、获取所有请求参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        //3.1、创建User对象
        User LoginUser = new User();
        //3.2、使用BeanUtils封装
        try {
            BeanUtils.populate(LoginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4、调用UserDao的login方法
        UserDao dao = new UserDao();
        User user = dao.login(LoginUser);
        //5、判断user
        if (user == null){
            //登录失败，获取请求转发器（getRequestDispatcher）
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //登录成功
            //存储数据
            req.setAttribute("user",user);
            //转发
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
