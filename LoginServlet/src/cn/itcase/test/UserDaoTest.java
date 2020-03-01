package cn.itcase.test;


import cn.itcase.dao.UserDao;
import cn.itcase.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void test(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        UserDao dao = new UserDao();
        User login = dao.login(user);
        System.out.println(login);
    }
}
