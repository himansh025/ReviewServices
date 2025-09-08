package com.example.uber.adapters;

import com.example.uber.dtos.CreateReviewDto;
import com.example.uber.models.Review;

public interface CreateReviewDtoToReviewAdapter {


    public Review ConverDto(CreateReviewDto createReviewDto);

}
