package com.atguigu.controller;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author ru
 * @create 2020-11-10-16:31
 */
@ControllerAdvice
@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookService bookService;

    /**
     * @ExceptionHandler 注解表示异常处理方法 <br/>
     * 参数是接收抛出的异常对象<br/>
     */
    @ExceptionHandler
    public String exception(Exception e){
        System.out.println("Exception =>"+e);
        return "error/error500";
    }
    @ExceptionHandler
    public String exception(RuntimeException e){
        System.out.println("RuntimeException =>"+e);
        return "error/error500";
    }
    @ExceptionHandler
    public String exception(ArithmeticException e){
        System.out.println("ArithmeticException =>"+e);
        return "error/error500";
    }

    @RequestMapping(value = "/list")
    public String list(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        Page<Book> page=bookService.page(pageNo == null ? 1 : pageNo, pageSize != null ? pageSize : Page.PAGE_SIZE);
        page.setUrl("book/list");
        map.put("page", page);
        return "bookList";
    }

    @RequestMapping(value = "/add")
    public String addBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/book/list?pageNo="+Integer.MAX_VALUE;
    }

    @RequestMapping(value = "/update")
    public String updateBook(Book book,Integer pageNo){
        bookService.updateBook(book);
        return "redirect:/book/list?pageNo="+pageNo;
    }

    @RequestMapping(value = "/queryBook")
    public String queryBook(Model model, Integer id){
        model.addAttribute("book",bookService.queryBookById(id));
        return "bookEdit";
    }

    @RequestMapping(value = "/delete")
    public String deleteBook(Integer id,Integer pageNo){
        bookService.deleteBookById(id);
        return "redirect:/book/list?pageNo="+pageNo;
    }


}
