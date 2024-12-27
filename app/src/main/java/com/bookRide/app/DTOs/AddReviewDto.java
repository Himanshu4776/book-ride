package com.bookRide.app.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddReviewDto {
    private String content;

    private Double rating;

    private Long bookingId;
}
