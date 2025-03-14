package springboot_backend_ecommerce.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<String> sayHello(Principal principal) {
        return ResponseEntity.ok("Hello, " + principal.getName() + "! Welcome to your dashboard.");
    }
}

