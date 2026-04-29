package MySpringBootProject.BookMyShow.Controller;

import MySpringBootProject.BookMyShow.DTO.frontend.PaymentRequestDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.PaymentResponseDTO;
import MySpringBootProject.BookMyShow.Service.Payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bms/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/upi")
    public ResponseEntity<PaymentResponseDTO> completeUpiPayment(@RequestBody PaymentRequestDTO request) throws Exception {
        return ResponseEntity.ok(paymentService.completeUpiPayment(request));
    }
}
