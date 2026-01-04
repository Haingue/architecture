package com.haingue.tp1.CommunityBookstore.repository;

import com.haingue.tp1.CommunityBookstore.model.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, UUID> {
    Optional<Borrowing> findFirstByBookIsbnAndCustomerUuid(String bookIsbn, UUID customerUuid);

    Page<Borrowing> findAllByCustomerUuid(UUID customerUuid, Pageable pageable);

    Page<Borrowing> findAllByReturnDateIsNullOrderByBorrowingDate(Pageable page);
}
