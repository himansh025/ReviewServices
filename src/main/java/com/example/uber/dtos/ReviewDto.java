package com.example.uber.dtos;
import com.example.uber.models.Booking;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

        private  Long id;
        private Long bookingId;
        private  String  content;
        private  Double rating;
        private Date createdAt;
        private Date updatedAt;


    }

