package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookMapper {
    //List<Book> getAllBooks();
    int addBook(Book book) ;
    int deleteBookById(@Param("id") Integer id);
    int updateBook(Book book);
    Book queryBookById(@Param("id") Integer id);
    List<Book> queryBooks();
    List<Book> queryBooksByPrice(@Param("min") int min,@Param("max") int max);
}
