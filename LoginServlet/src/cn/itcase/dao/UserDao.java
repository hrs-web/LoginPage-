package cn.itcase.dao;

import cn.itcase.domain.User;
import cn.itcase.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中User表的类
 */
public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate  template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     */
    public User login(User LoginUser){
        try {
            //1、编写sql语句
            String sql = "select * from user where username = ? and password = ?";
            //2、调用query查询方法
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),
                    LoginUser.getUsername(),LoginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace(); //记录日记
            return null;
        }
    }

}
