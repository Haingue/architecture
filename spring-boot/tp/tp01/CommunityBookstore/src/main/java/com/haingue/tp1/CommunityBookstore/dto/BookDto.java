package com.haingue.tp1.CommunityBookstore.dto;

import java.time.LocalDate;

public record BookDto (
        String isbn,
        String title,
        String author,
        LocalDate publicationDate,
        boolean available
) {}
