package MySpringBootProject.BookMyShow.Controller;

import MySpringBootProject.BookMyShow.DTO.frontend.HomePageResponseDTO;
import MySpringBootProject.BookMyShow.DTO.frontend.SeatDTO;
import MySpringBootProject.BookMyShow.Service.HomePageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bms")
public class HomePageController {
    private final HomePageService homePageService;

    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping("/home/{cityName}")
    public HomePageResponseDTO getHomePage(@PathVariable String cityName) {
        return homePageService.getHomePage(cityName);
    }

    @GetMapping("/shows/{showId}/seats")
    public List<SeatDTO> getSeats(@PathVariable long showId) {
        return homePageService.getSeatsForShow(showId);
    }
}
