package com.engineerproject.recommendationsystem.infrastructure;

import com.engineerproject.recommendationsystem.infrastructure.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
