package com.atguigu.test;

import com.atguigu.mapper.BookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author ru
 * @create 2020-11-09-21:28
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    BookMapper bookMapper;

    @Test
    public void test1() throws SQLException {
        System.out.println(dataSource.getConnection());
        System.out.println(bookMapper);
    }

}