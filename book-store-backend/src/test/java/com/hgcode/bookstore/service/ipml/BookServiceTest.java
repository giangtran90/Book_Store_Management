package com.hgcode.bookstore.service.ipml;

import com.hgcode.bookstore.entity.BookEntity;
import com.hgcode.bookstore.model.Book;
import com.hgcode.bookstore.repository.IBookRepository;
import com.hgcode.bookstore.service.IBookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @Mock
    private IBookRepository bookRepository;

    private IBookService bookService;

    AutoCloseable autoCloseable;

    BookEntity bookEntity;

    Book book;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository);
        book = new Book(1L,"Toan","Sach toan",20000);
        bookEntity = new BookEntity();
        BeanUtils.copyProperties(book,bookEntity);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateBook() {
        mock(BookEntity.class);
        mock(IBookRepository.class);

        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        assertThat(bookService.createBook(book)).isEqualTo(book);
    }

    @Test
    void testGetAllBooks() {
        mock(BookEntity.class);
        mock(IBookRepository.class);

        when(bookRepository.findAll()).thenReturn(
                new ArrayList<BookEntity>(Collections.singleton(bookEntity))
        );

        assertThat(bookService.getAllBooks().get(0).getName())
                .isEqualTo(bookEntity.getName());
    }

    @Test
    void deleteBook() {
    }

    @Test
    void testGetBookById() {
        mock(BookEntity.class);
        mock(IBookRepository.class);
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(bookEntity));

        assertThat(bookService.getBookById(1L).getName()).isEqualTo(bookEntity.getName());
    }

    @Test
    void updateBook() {
    }
}