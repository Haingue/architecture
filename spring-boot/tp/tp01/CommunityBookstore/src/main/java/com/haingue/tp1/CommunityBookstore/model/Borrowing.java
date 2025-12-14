package com.haingue.tp1.CommunityBookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table
public class Borrowing {
    @Id
    private UUID uuid;

    @ManyToOne
    private User customer;
    @ManyToOne
    private Book book;

    private Instant borrowingDate;
    private Instant returnDate;
}
