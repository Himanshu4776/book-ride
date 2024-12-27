package com.bookRide.app.Service.impl;

import com.bookRide.app.Repositories.ReviewRepository;
import com.bookRide.app.Service.ReviewService;
import com.bookRide.app.entities.Review;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    /**
     * @param id
     * @return review if review is found else return null
     */
    @Override
    public Optional<Review> findReviewById(Long id) throws EntityNotFoundException {
        Optional<Review> review;
        try {
            review = reviewRepository.findById(id);
            if (review.isEmpty()) {
                throw new EntityNotFoundException("Review with id " + id + " is not present");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException("Review with id " + id + " not found", id);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", id);
        }
        return review;
    }

    /**
     * @return All reviews List
     */
    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * @param id
     * @return true value if entry is deleted else false.
     */
    @Override
    public boolean deleteReviewById(Long id) {
        Optional<Review> review = findReviewById(id);
        if (review.isPresent()) {
            try {
                reviewRepository.delete(review.get());
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * @param review
     * @return Review when the data is created successfully.
     */
    @Transactional
    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * @param id
     * @param reviewData
     * @return Review when the data is updated successfully.
     */
    @Override
    public Review updateReview(Long id, Review reviewData) {
        Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(reviewData.getRating() != null){
            review.setRating(reviewData.getRating());
        }
        if(reviewData.getContent() != null){
            review.setContent(reviewData.getContent());
        }
        return this.reviewRepository.save(review);
    }
}
