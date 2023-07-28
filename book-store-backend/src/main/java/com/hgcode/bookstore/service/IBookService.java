package com.hgcode.bookstore.service;

import com.hgcode.bookstore.model.Book;

import java.util.List;

public interface IBookService {
    Book createBook(Book book);

    List<Book> getAllBooks();

    Boolean deleteBook(Long id);

    Book getBookById(Long id);

    Book updateBook(Long id, Book book);
}
