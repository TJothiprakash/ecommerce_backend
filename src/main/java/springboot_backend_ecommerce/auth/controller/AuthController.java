package springboot_backend_ecommerce.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springboot_backend_ecommerce.auth.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        user.setVerified(false);
        userRepository.save(user);

        String verficationToken = verificationTokenService.generateVerificationToken(user);
        return ResponseEntity.ok("User registered successfully!");
    }


    /***
     * verify email endpoint
     *
     */
    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody String token) {
        if (verificationTokenService.verifyToken(token)) {
            return ResponseEntity.ok("Email verified! You can now log in.");
        }
        return ResponseEntity.badRequest().body("Invalid or expired token");
    }

    public ResponseEntity<String> forgotPassword(@RequestParam String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found !!! "));


        String token = passwordResetService.generateResetToken(user);
        emailService.sendVerificationEmail(user.getEmail(), token);
        return ResponseEntity.ok("Reset link sent! Check your email.");
    }

}

