package com.example.uber.adapters;

import com.example.uber.dtos.CreateReviewDto;
import com.example.uber.models.Booking;
import com.example.uber.models.Review;
import com.example.uber.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImp implements CreateReviewDtoToReviewAdapter{


    private  final BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImp(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Review ConverDto(CreateReviewDto createReviewDto) {
        Optional<Booking> booking= bookingRepository.findById(createReviewDto.getBookingId());
        System.out.printf("booking"+booking);
        return booking.map(value -> Review.builder()
                .rating(createReviewDto.getRating())
                .content(createReviewDto.getContent())
                .booking(value)
                .build()).orElse(null);
    }
}
