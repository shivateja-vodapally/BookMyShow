package MySpringBootProject.BookMyShow.DTO.frontend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {
    private long showSeatId;
    private String seatNumber;
    private String status;
    private int price;
}
