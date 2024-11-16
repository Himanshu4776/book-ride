package com.bookRide.app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking_review")
public class Review extends BaseEntity {
    @Column(nullable = false)
    private String content;

    private Double rating;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking; // we have defined a 1:1 relationship between booking and review

    @Override
    public String toString() {
        return "Review: " + this.content + " " + this.rating + " " + " booking: " + this.booking.getId() + " " + this.createdAt;
    }
}
