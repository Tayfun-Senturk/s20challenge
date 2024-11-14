package com.workintech.s20challenge.service;


import com.workintech.s20challenge.dto.ReviewDTO;
import com.workintech.s20challenge.entity.Product;
import com.workintech.s20challenge.entity.Review;
import com.workintech.s20challenge.entity.User;
import com.workintech.s20challenge.repository.ProductRepository;
import com.workintech.s20challenge.repository.ReviewRepository;
import com.workintech.s20challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(ReviewDTO reviewDTO) {
        User user = userRepository.findById(reviewDTO.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(reviewDTO.productId()).orElseThrow(() -> new RuntimeException("Product not found"));
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(reviewDTO.rating());
        review.setComment(reviewDTO.comment());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewByProductId(Long id) {
        return reviewRepository.findByProductId(id);
    }
}

