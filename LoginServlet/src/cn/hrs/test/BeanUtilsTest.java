package cn.hrs.test;

import cn.hrs.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTest {
    @Test
    public void test(){
        User user = new User();
        try {
            BeanUtils.setProperty(user,"hehe","risheng");
            System.out.println(user);
            String hehe = BeanUtils.getProperty(user, "hehe");
            System.out.println(hehe);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
