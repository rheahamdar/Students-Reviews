package com.dailycodework.sbemailverificationdemo.registration;



public record RegistrationRequest(
         String userName,
         String email,
         String password,
         String role) {
}
