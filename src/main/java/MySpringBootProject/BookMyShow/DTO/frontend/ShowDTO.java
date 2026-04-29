package MySpringBootProject.BookMyShow.DTO.frontend;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowDTO {
    private long showId;
    private LocalDateTime startTime;
    private String displayTime;
}
