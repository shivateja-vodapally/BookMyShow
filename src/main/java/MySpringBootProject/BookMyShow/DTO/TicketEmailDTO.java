package MySpringBootProject.BookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketEmailDTO {
    private Long ticketId;
    private String email;
    private String movieName;
    private String theatreName;
    private String showTime;
    private List<String> seatNumbers;
    private Double amount;
}
