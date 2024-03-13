package com.dailycodework.sbemailverificationdemo.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/create")
    public String createReview(@RequestBody Review review) {
        reviewRepository.save(review);
        return "Review submitted successfully";
    }
}