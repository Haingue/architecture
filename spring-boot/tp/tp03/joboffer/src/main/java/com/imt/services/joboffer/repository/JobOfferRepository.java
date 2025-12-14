package com.imt.services.joboffer.repository;

import com.imt.services.joboffer.entity.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {

    List<JobOffer> findAllByTitleLikeOrderByCreationTimestampDesc(String titleRegex);

    Page<JobOffer> searchAllByTitleLikeIgnoreCase(String title, Pageable pageable);
}
