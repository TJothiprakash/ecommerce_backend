package springboot_backend_ecommerce.paymentgateway.stripe.controller;

import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot_backend_ecommerce.paymentgateway.stripe.service.StripeService;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-payment")
    public String createPayment(@RequestParam int amount, @RequestParam String currency) throws StripeException {
        return stripeService.createPaymentIntent(amount, currency);
    }
}

