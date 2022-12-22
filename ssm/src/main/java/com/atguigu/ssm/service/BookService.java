package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.Book;
import com.github.pagehelper.PageInfo;
//import com.atguigu.ssm.pojo.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    PageInfo<Book> getBookPage(Integer pageNum);
    PageInfo<Book> getBookPageByPrice(Integer pageNum,int min, int max);
}
