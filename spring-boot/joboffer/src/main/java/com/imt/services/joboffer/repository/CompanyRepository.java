package com.imt.services.joboffer.repository;

import com.imt.services.joboffer.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
