package com.haingue.tp1.CommunityBookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private boolean available;

}
