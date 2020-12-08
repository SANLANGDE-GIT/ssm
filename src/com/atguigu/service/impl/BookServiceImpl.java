package com.atguigu.service.impl;

import com.atguigu.mapper.BookMapper;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.BookExample;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ru
 * @create 2020-11-10-14:19
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public void saveBook(Book book) {
        bookMapper.insertSelective(book);
        //测试事务
//        int num=3/0;
//        bookMapper.insertSelective(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateByPrimaryKeySelective(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Book> queryBooks() {

        return bookMapper.selectByExample(null);
    }

    @Override
    public int queryTotalCount() {
        return bookMapper.countByExample(null);
    }

    @Override
    public List<Book> queryBookForPage(Integer begin, Integer pageNo) {
        BookExample bookExample=new BookExample();
        bookExample.setOffset(begin);
        bookExample.setLimit(pageNo);
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Integer totalCount=bookMapper.countByExample(null);
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        page.setPageTotalCount(totalCount);
        Integer total=totalCount/pageSize;
        if(totalCount%pageSize>0){
            total+=1;
        }
        page.setPageTotal(total);
        page.setPageNo(pageNo);
        Integer begin=(page.getPageNo()-1)*pageSize;
        List<Book> book=queryBookForPage(begin,pageSize);

        page.setItems(book);

        return page;
    }
}
