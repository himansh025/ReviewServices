package com.example.uber.controllers;

import com.example.uber.adapters.CreateReviewDtoToReviewAdapter;
import com.example.uber.dtos.CreateReviewDto;
import com.example.uber.dtos.ReviewDto;
import com.example.uber.models.Review;
import com.example.uber.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class ReviewController {


    private final ReviewService reviewService;
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;


    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @GetMapping("/allReview")
    public ResponseEntity<List<Review>> findAll() {
        List<Review> reviews = this.reviewService.findAllReviews();
        return ResponseEntity.ok(reviews);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Optional<Review> reviews = this.reviewService.findReviewById(id);
            return new ResponseEntity<>(reviews, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody CreateReviewDto review, @PathVariable Long id) {
        try {
            Review review1= this.createReviewDtoToReviewAdapter.ConverDto(review);
        Review updatedReview = reviewService.updateReviewById(review1, id);
        return new  ResponseEntity<>(updatedReview,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{bookingId}")
//    public ResponseEntity<Review>findReviewByBookingId ( @PathVariable Long bookingId) {
//        Review updatedReview = reviewService.findReviewByBookingId( bookingId);
//        return ResponseEntity.ok(updatedReview);
//    }

    @GetMapping("/before")
    public ResponseEntity<List<Review>> findReviewCreatedBefore(@RequestBody Date date) {
        List<Review> reviewCreatedBefore = reviewService.findAllByCreatedAtBefore(date);
        return ResponseEntity.ok(reviewCreatedBefore);
    }

    @GetMapping("/rating-less")
    public ResponseEntity<List<Review>> findReviewRatingLessThanEqual(@RequestBody Integer rating) {
        List<Review> reviewRatingLessThanEqual = reviewService.findAllReviewsRatingLessThanEqual(rating);
        return ResponseEntity.ok(reviewRatingLessThanEqual);
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> countAllRatingLessThanEqual(@RequestBody Integer rating) {
        Integer totalCount = reviewService.countAllRatingLessThanEqual(rating);
        return ResponseEntity.ok(totalCount);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted= this.reviewService.deleteReviewById(reviewId);
            if(!isDeleted)return new  ResponseEntity<>("enable to delete this review",HttpStatus.INTERNAL_SERVER_ERROR);
            return  new ResponseEntity<>("Review Deleted Succesfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> CreateReview(@RequestBody CreateReviewDto review) {
        Review incomingReview = this.createReviewDtoToReviewAdapter.ConverDto(review);
        System.out.println(incomingReview);
        if (incomingReview == null) {
            return new ResponseEntity<>("Invalid Arguments", HttpStatus.BAD_REQUEST);
        }
        Review newreview = this.reviewService.postReview(incomingReview);
        ReviewDto response = ReviewDto.builder()
                .id(newreview.getId())
                .bookingId(newreview.getBooking().getId())
                .content(newreview.getContent())
                .rating(newreview.getRating())
                .createdAt(newreview.getCreatedAt())
                .updatedAt(newreview.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}
