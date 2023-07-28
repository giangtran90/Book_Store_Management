package com.hgcode.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", length = 45)
    private Long id;
    @Column(name = "book_name", length = 255)
    private String name;
    @Column(name = "book_description")
    private String description;
    @Column(name = "book_price")
    private Integer price;
}
