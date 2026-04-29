package MySpringBootProject.BookMyShow.DTO.frontend;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentRequestDTO {
    private List<Integer> showSeatIds;
    private Integer userId;
    private String upiId;
    private String email;
}
