package MySpringBootProject.BookMyShow.DTO.frontend;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TheatreDTO {
    private long theatreId;
    private String theatreName;
    private String movieName;
    private List<ShowDTO> shows = new ArrayList<>();
}
