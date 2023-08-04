package com.hgcode.bookstore.controller;

import com.hgcode.bookstore.entity.BookEntity;
import com.hgcode.bookstore.model.Book;
import com.hgcode.bookstore.service.IBookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BookController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class BookControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBookService bookService;

    BookEntity bookEntityOne;
    BookEntity bookEntityTwo;

    Book bookOne;
    Book bookTwo;
    List<Book> books = new ArrayList<>();

    @BeforeEach
    void setUp() {
        bookOne = new Book(1L,"Toan","sach toan",20000);
        bookTwo = new Book(2L,"Ly","sach Ly",30000);
        bookEntityOne = new BookEntity();
        BeanUtils.copyProperties(bookOne,bookEntityOne);
        bookEntityTwo = new BookEntity();
        BeanUtils.copyProperties(bookTwo,bookEntityTwo);
        books.add(bookOne);
        books.add(bookTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createBook() {
    }

    @Test
    void getAllBooks() {

    }

    @Test
    void deleteBook() {
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookOne);
        this.mockMvc.perform(get("/api/v1/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() {
    }
}