package MySpringBootProject.BookMyShow.Service.Payment;

import MySpringBootProject.BookMyShow.DTO.TicketEmailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TicketEmailConsumer {
    private final JavaMailSender mailSender;

    public TicketEmailConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "ticket-booked", groupId = "bms-email-service")
    public void sendTicketEmail(TicketEmailDTO dto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(dto.getEmail());
            mailMessage.setSubject("BookMyShow Ticket Confirmed - Ticket ID " + dto.getTicketId());

            mailMessage.setText(
                    "🎬 Movie: " + dto.getMovieName() + "\n" +
                            "🏢 Theatre: " + dto.getTheatreName() + "\n" +
                            "⏰ Show Time: " + dto.getShowTime() + "\n" +
                            "💺 Seats: " + String.join(", ", dto.getSeatNumbers()) + "\n" +
                            "💰 Amount: ₹" + dto.getAmount() + "\n" +
                            "🎟 Ticket ID: " + dto.getTicketId()
            );

            mailSender.send(mailMessage);

        } catch (Exception exception) {
            System.out.println("Could not send ticket email: " + exception.getMessage());
        }
    }
}
