package com.hgcode.bookstore.service.ipml;

import com.hgcode.bookstore.entity.BookEntity;
import com.hgcode.bookstore.model.Book;
import com.hgcode.bookstore.repository.IBookRepository;
import com.hgcode.bookstore.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Book> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        List<Book> books = bookEntities.stream()
                .map(book -> new Book(
                        book.getId(),book.getName(),book.getDescription(),book.getPrice()
                )).collect(Collectors.toList());
        return books;
    }

    @Override
    public Boolean deleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).get();
        bookRepository.delete(bookEntity);
        return true;
    }

    @Override
    public Book getBookById(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).get();
        Book book = new Book();
        BeanUtils.copyProperties(bookEntity,book);
        return book;
    }
}
