package com.bookRide.app.Adapter;

import com.bookRide.app.DTOs.AddReviewDto;
import com.bookRide.app.Repositories.BookingRepository;
import com.bookRide.app.entities.Booking;
import com.bookRide.app.entities.Review;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConvertReviewDtoToReviewAdapter {
    private BookingRepository bookingRepository;

    public Review convertDto(AddReviewDto createReviewDto) {
        Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());
        return booking.map(value -> Review.builder()
                .rating(createReviewDto.getRating())
                .booking(value)
                .content(createReviewDto.getContent())
                .build()).orElse(null);
    }
}
