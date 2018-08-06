package com.engineerproject.recommendationsystem;

import com.engineerproject.recommendationsystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
