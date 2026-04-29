package MySpringBootProject.BookMyShow.DTO.frontend;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HomePageResponseDTO {
    private String cityName;
    private List<TheatreDTO> theatres = new ArrayList<>();
}
