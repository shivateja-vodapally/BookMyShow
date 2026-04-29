package MySpringBootProject.BookMyShow.Service;

import MySpringBootProject.BookMyShow.DTO.frontend.HomePageResponseDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.SeatDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.ShowDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.TheatreDTO;
import MySpringBootProject.BookMyShow.Models.Show;
import MySpringBootProject.BookMyShow.Models.ShowSeat;
import MySpringBootProject.BookMyShow.Repository.ShowRepository;
import MySpringBootProject.BookMyShow.Repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomePageService {
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    public HomePageService(ShowRepository showRepository, ShowSeatRepository showSeatRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    public HomePageResponseDTO getHomePage(String cityName) {
        List<Object[]> rows = showRepository.findTheatreShowsForCity(cityName);
        HomePageResponseDTO response = new HomePageResponseDTO();
        response.setCityName(cityName);

        Map<Long, TheatreDTO> theatreMap = new LinkedHashMap<>();
        for (Object[] row : rows) {
            MySpringBootProject.BookMyShow.Models.Theatre theatre = (MySpringBootProject.BookMyShow.Models.Theatre) row[0];
            Show show = (Show) row[1];
            long theatreKey = theatre.getId();
            TheatreDTO theatreDTO = theatreMap.computeIfAbsent(theatreKey, id -> {
                TheatreDTO dto = new TheatreDTO();
                dto.setTheatreId(theatreKey);
                dto.setTheatreName(theatre.getName());
                dto.setMovieName(show.getMovie().getName());
                return dto;
            });

            ShowDTO showDTO = new ShowDTO();
            showDTO.setShowId(show.getId());
            showDTO.setStartTime(show.getStartTime());
            showDTO.setDisplayTime(show.getStartTime().format(TIME_FORMATTER));
            theatreDTO.getShows().add(showDTO);
        }

        response.getTheatres().addAll(theatreMap.values());
        return response;
    }

    public List<SeatDTO> getSeatsForShow(long showId) {
        return showSeatRepository.findByShowIdWithSeat(showId)
                .stream()
                .map(showSeat -> {
                    SeatDTO dto = new SeatDTO();
                    dto.setShowSeatId(showSeat.getId());
                    dto.setSeatNumber(showSeat.getSeat().getSeatNumber());
                    dto.setStatus(showSeat.getShowSeatStatus().name());
                    dto.setPrice(showSeat.getPrice());
                    return dto;
                })
                .toList();
    }
}
