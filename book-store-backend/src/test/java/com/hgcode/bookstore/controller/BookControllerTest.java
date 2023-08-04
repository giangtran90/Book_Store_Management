package com.hgcode.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void testCreateBook() throws Exception {
        String requestJson = writeValueAsString(bookOne);
        when(bookService.createBook(bookOne)).thenReturn(bookOne);
        this.mockMvc.perform(post("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(books);
        this.mockMvc.perform(get("/api/v1/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBook() throws Exception{
        when(bookService.deleteBook(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/api/v1/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookOne);
        this.mockMvc.perform(get("/api/v1/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBook() throws Exception {
        Book bookUpdate = new Book(1L,"Hoa","sach Hoa", 25000);
        String requestJson = writeValueAsString(bookUpdate);
        when(bookService.updateBook(1L,bookUpdate)).thenReturn(bookUpdate);
        this.mockMvc.perform(put("/api/v1/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String writeValueAsString(Book book) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);

        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(book);
    }
}