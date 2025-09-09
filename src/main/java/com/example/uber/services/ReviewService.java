package com.example.uber.services;

import com.example.uber.dtos.ReviewDto;
import com.example.uber.models.Review;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewService {


    public List<Review> findAllReviews();

    public Optional<Review> findReviewById(Long id);

   public Boolean deleteReviewById(Long reviewId);

    public Review updateReviewById(Review review, Long reviewId);

    public List<Review> findAllByCreatedAtBefore(Date date);

    public List<Review> findAllReviewsRatingLessThanEqual(Integer givenRating);

    public Integer countAllRatingLessThanEqual(Integer givenRating);

//    public  Review findReviewByBookingId(Long bookingId);

    public Review postReview( Review review);

}
