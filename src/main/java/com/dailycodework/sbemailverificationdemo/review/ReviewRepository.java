package com.dailycodework.sbemailverificationdemo.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.courseName = :courseName")
    List<Review> findByCourseName(@Param("courseName") String courseName);

    @Query("SELECT p FROM Review p WHERE p.professorName = :professorName")
    List<Review> findByProfessorName(@Param("professorName") String professorName);

    
} 
