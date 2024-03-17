package com.dailycodework.sbemailverificationdemo.review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "professor_name")
    private String professorName;
    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;
    @Column(name = "course_rating")
    private int courseRating;
    @Column(name = "uni_name")
    private String uniName;
    @Column(name="user_name")
    private String userName;

  
   
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUniName() {
        return uniName;
    }
    public void setUniName(String uniName) {
        this.uniName = uniName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getProfessorName() {
        return professorName;
    }
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
    public String getReviewText() {
        return reviewText;
    }
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    public int getCourseRating() {
        return courseRating;
    }
    public void setCourseRating(int courseRating) {
        this.courseRating = courseRating;
    }
   


   
}