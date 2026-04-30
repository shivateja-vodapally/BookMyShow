package MySpringBootProject.BookMyShow.Service.Payment;

import MySpringBootProject.BookMyShow.DTO.TicketEmailDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.PaymentRequestDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.PaymentResponseDTO;
import MySpringBootProject.BookMyShow.Models.Constants.PaymentMethod;
import MySpringBootProject.BookMyShow.Models.Constants.ShowSeatStatus;
import MySpringBootProject.BookMyShow.Models.Constants.Status;
import MySpringBootProject.BookMyShow.Models.Payment;
import MySpringBootProject.BookMyShow.Models.ShowSeat;
import MySpringBootProject.BookMyShow.Models.Ticket;
import MySpringBootProject.BookMyShow.Repository.PaymentRepository;
import MySpringBootProject.BookMyShow.Repository.ShowSeatRepository;
import MySpringBootProject.BookMyShow.Repository.TicketRepository;
import MySpringBootProject.BookMyShow.Service.TicketService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final ShowSeatRepository showSeatRepository;
    private final KafkaTemplate<String, TicketEmailDTO> kafkaTemplate;

    public PaymentService(TicketService ticketService,
                          TicketRepository ticketRepository,
                          PaymentRepository paymentRepository,
                          ShowSeatRepository showSeatRepository,
                          KafkaTemplate<String, TicketEmailDTO> kafkaTemplate)
    {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
        this.showSeatRepository = showSeatRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public PaymentResponseDTO completeUpiPayment(PaymentRequestDTO request) throws Exception {
        validateUpiId(request.getUpiId());

        Ticket ticket = ticketService.bookTicket(request.getShowSeatIds(), request.getUserId());
        Ticket savedTicket = ticketRepository.save(ticket);

        for (ShowSeat showSeat : savedTicket.getShowSeats()) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatRepository.save(showSeat);
        }

        Payment payment = new Payment();
        payment.setAmount(savedTicket.getTotalAmount());
        payment.setPaymentMethod(PaymentMethod.UPI);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setReferenceId("UPI-" + UUID.randomUUID());
        payment.setStatus(Status.AVAILABLE); // treating AVAILABLE as successful payment because current enum has no SUCCESS value
        payment.setTicket(savedTicket);
        paymentRepository.save(payment);

        TicketEmailDTO dto = new TicketEmailDTO();

        dto.setTicketId(ticket.getId());
        dto.setAmount(ticket.getTotalAmount());

// Movie
        dto.setMovieName(ticket.getShow().getMovie().getName());

// Theatre
        dto.setTheatreName(ticket.getShow().getAuditorium().getTheatre().getName());

// Time
        dto.setShowTime(ticket.getShow().getStartTime().toString());

// Seats
        List<String> seats = ticket.getShowSeats()
                .stream()
                .map(s -> s.getSeat().getSeatNumber())
                .toList();

        dto.setSeatNumbers(seats);
//Email
        dto.setEmail(request.getEmail());
        kafkaTemplate.send("ticket-booked", dto);

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setStatus("SUCCESS");
        response.setTicketId(savedTicket.getId());
        response.setAmount(savedTicket.getTotalAmount());
        response.setMessage("Sample UPI payment completed. Ticket confirmation event published to Kafka.");
        System.out.println("Local Branch: Payment flow executed successfully");
        return response;
    }

    private void validateUpiId(String upiId) {
        if (upiId == null || !upiId.matches("^[a-zA-Z0-9._-]{2,}@[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid UPI ID. Example: shiva@upi");
        }
    }
}
