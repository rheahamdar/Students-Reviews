package com.dailycodework.sbemailverificationdemo.registration;

import org.hibernate.annotations.NaturalId;


public record RegistrationRequest(
         String userName,
         String email,
         String password,
         String role) {
}
