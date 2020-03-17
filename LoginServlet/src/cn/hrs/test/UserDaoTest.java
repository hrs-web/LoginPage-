package cn.hrs.test;


import cn.hrs.dao.UserDao;
import cn.hrs.domain.User;
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
