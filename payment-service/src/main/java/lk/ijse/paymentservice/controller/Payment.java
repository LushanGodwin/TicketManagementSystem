package lk.ijse.paymentservice.controller;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class Payment {

    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    @GetMapping("/health")
    public String healthCheck() {
        return "Payment Service is running successfully";
    }

    @PostMapping
    public ResponseEntity<String> savePayment(@RequestBody PaymentDTO paymentDTO) {


        try {
            String ticketId = paymentDTO.getTicket_id();
            System.out.println(ticketId);

            String url = "http://ticket-service/api/v1/ticket/ticketExists?ticketId=" + ticketId;
            System.out.println(url);
            Boolean ticketExists = restTemplate.getForObject(url, Boolean.class);
            if (!ticketExists) {
                return ResponseEntity.badRequest().body("Ticket Not Found");
            } else {
                paymentService.save(paymentDTO);

                return ResponseEntity.ok("Payment saved successfully");
            }


        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Payment saved failed\n" + exception.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<String> updatePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            String ticketId = paymentDTO.getTicket_id();


            String url = "http://ticket-service/api/v1/ticket/ticketExists?ticketId=" + ticketId;
            Boolean ticketExists = restTemplate.getForObject(url, Boolean.class);
            if (Boolean.FALSE.equals(ticketExists)) {
                return ResponseEntity.badRequest().body("Ticket Not Found");
            }
            paymentService.update(paymentDTO);
            return ResponseEntity.badRequest().body("Payment Update Success");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Payment Update failed\n" + exception.getMessage());
        }
    }

}
