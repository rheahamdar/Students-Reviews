package com.dailycodework.sbemailverificationdemo.review;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.dailycodework.sbemailverificationdemo.user.User;
import com.dailycodework.sbemailverificationdemo.user.UserRepository;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public String createReview(@RequestBody Review review) {
        // Get the currently authenticated user's authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String email = user.getEmail();
            if (!email.toLowerCase().endsWith(".edu")) {
                return "Only users with email addresses ending with '.edu' are allowed to write reviews.";
            }
            review.setUserName(userName);
        }
        reviewRepository.save(review);
        return "Review submitted successfully";
    }

    @GetMapping("/by-course")
    public List<Review> getReviewsByCourse(@RequestParam String courseName) {
        return reviewRepository.findByCourseName(courseName);
    }

    // Method to get reviews by professor name
    @GetMapping("/by-professor")
    public List<Review> getReviewsByProfessor(@RequestParam String professorName) {
        return reviewRepository.findByProfessorName(professorName);
    }
}