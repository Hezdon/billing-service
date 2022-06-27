package com.blusalt.assessment.billingservice.repo;

import com.blusalt.assessment.billingservice.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepo extends JpaRepository<Fund, Long> {
}
