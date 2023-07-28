package com.hgcode.bookstore.service.ipml;

import com.hgcode.bookstore.entity.BookEntity;
import com.hgcode.bookstore.model.Book;
import com.hgcode.bookstore.repository.IBookRepository;
import com.hgcode.bookstore.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    @Override
    public Book createBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(book,bookEntity);
        bookRepository.save(bookEntity);
        return book;
    }
}
