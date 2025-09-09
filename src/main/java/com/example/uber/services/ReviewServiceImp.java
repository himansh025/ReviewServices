package com.example.uber.services;

import com.example.uber.dtos.ReviewDto;
import com.example.uber.models.Review;
import com.example.uber.repositories.BookingRepository;
import com.example.uber.repositories.DriverRepository;
import com.example.uber.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReviewServiceImp implements ReviewService {
    private final DriverRepository driverRepository;
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public ReviewServiceImp(DriverRepository driverRepository, ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.driverRepository = driverRepository;
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Review> findAllReviews() {
        try {
            return reviewRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        try {
            return reviewRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public Boolean deleteReviewById(Long reviewId) {
        try {
            Optional<Review> review = this.reviewRepository.findById(reviewId);
            if (review == null) throw new RuntimeException("review not found");
            this.reviewRepository.deleteById(reviewId);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting review: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Review updateReviewById(Review review, Long reviewId) {
        try {
            Optional<Review> oldReview = this.reviewRepository.findById(reviewId);
            if (oldReview == null) throw new RuntimeException("review not found in db");
            Review updated = oldReview.get();
            updated.setRating(review.getRating());
            updated.setContent(review.getContent());
            System.out.println(review);
            this.reviewRepository.save(updated);
            return updated;
        } catch (Exception e) {
            System.out.println("Error deleting review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Review> findAllByCreatedAtBefore(Date date) {
        try {
            return reviewRepository.findAllByCreatedAtBefore(date);
        } catch (Exception e) {
            System.out.println("Error createdAtBefore review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> findAllReviewsRatingLessThanEqual(Integer givenRating) {
        try {
            return reviewRepository.findAllByRatingIsLessThanEqual(givenRating);
        } catch (Exception e) {
            System.out.println("Error findAllByRatingIsLessThanEqual review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer countAllRatingLessThanEqual(Integer givenRating) {
        try {
            return reviewRepository.countAllByRatingIsLessThanEqual(givenRating);
        } catch (Exception e) {
            System.out.println("Error countAllByRatingIsLessThanEqual review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional
    public Review postReview(Review review) {
        try {
            return reviewRepository.save(review);
        } catch (Exception e) {
            System.out.println("error during the posting the review" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}