package com.atguigu.ssm.service.impl;



import com.atguigu.ssm.mapper.BookMapper;
import com.atguigu.ssm.pojo.Book;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookMapper.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookMapper.queryBooks();
    }

    @Override
    public PageInfo<Book> getBookPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 4);
        //查询所有的员工信息
        List<Book> list = bookMapper.queryBooks();
        //获取分页相关数据
        PageInfo<Book> page = new PageInfo<Book>(list, 5);
        return page;
    }

    @Override
    public PageInfo<Book> getBookPageByPrice(Integer pageNum, int min, int max) {
        //开启分页功能
        PageHelper.startPage(pageNum, 4);
        //查询所有的员工信息
        List<Book> list = bookMapper.queryBooksByPrice(min,max);
        //获取分页相关数据
        PageInfo<Book> page = new PageInfo<Book>(list, 5);
        return page;
    }
}
