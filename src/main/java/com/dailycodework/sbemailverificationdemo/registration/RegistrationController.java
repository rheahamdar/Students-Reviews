package com.dailycodework.sbemailverificationdemo.registration;

import com.dailycodework.sbemailverificationdemo.event.RegistrationCompleteEvent;
import com.dailycodework.sbemailverificationdemo.registration.token.VerificationToken;
import com.dailycodework.sbemailverificationdemo.registration.token.VerificationTokenRepository;
import com.dailycodework.sbemailverificationdemo.user.User;
import com.dailycodework.sbemailverificationdemo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        String email = registrationRequest.email();

        // Extract username from email
        String emailUsername = email.substring(0, email.indexOf('@'));
        
        // Check if the provided username matches the username from the email
        if (!registrationRequest.userName().equals(emailUsername)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided username does not match the username from the email.");
        }

        if (email != null && !email.endsWith("@mail.aub.edu")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email addresses not ending with '@mail.aub.edu' are not allowed for registration.");
        }
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return ResponseEntity.ok("Success! Please check your email to complete your registration.");
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }



    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
