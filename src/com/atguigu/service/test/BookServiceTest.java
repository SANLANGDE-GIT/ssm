package com.atguigu.service.test;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author ru
 * @create 2020-11-10-14:28
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
    @Autowired
    BookService bookService;
    @Test
    public void saveBook() {
        bookService.saveBook(new Book(null,"1234","453123",new BigDecimal("1290"),9999,1110));
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(21,"傲慢与偏见","简·奥斯汀",new BigDecimal("999"),8888,6666));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(3);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void queryTotalCount(){
        System.out.println(bookService.queryTotalCount());
    }

    @Test
    public void queryBookForPage(){
        bookService.queryBookForPage(6,5).forEach(System.out::println);
    }
}