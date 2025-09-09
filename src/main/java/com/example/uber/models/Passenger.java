package com.example.uber.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Passenger extends BaseModel {


    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "passenger") //here we have to define to whom this booking is pass or driver
    private List<Booking> bookings = new ArrayList<>();


}
