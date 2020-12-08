package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @author ru
 * @create 2020-11-10-14:16
 */
public interface BookService {

    public void saveBook(Book book);

    public void updateBook(Book book);

    public void deleteBookById(Integer id);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public int queryTotalCount();

    public List<Book> queryBookForPage(Integer begin,Integer pageNo);

    public Page<Book> page(int pageNo, int pageSize);
}
