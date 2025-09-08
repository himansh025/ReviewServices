package com.example.uber.dtos;

import com.example.uber.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateReviewDto {

    private  String  content;
    private Double rating;
    private Booking booking;
}
