package MySpringBootProject.BookMyShow.Events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookedEvent {
    private long ticketId;
    private String email;
    private double amount;
}
