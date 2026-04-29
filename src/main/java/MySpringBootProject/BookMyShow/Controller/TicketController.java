package MySpringBootProject.BookMyShow.Controller;

import MySpringBootProject.BookMyShow.DTO.BookTicketRequestDTO;
import MySpringBootProject.BookMyShow.DTO.TicketResponseDTO;
import MySpringBootProject.BookMyShow.Exception.ShowSeatAlreadyBookedException;
import MySpringBootProject.BookMyShow.Models.ShowSeat;
import MySpringBootProject.BookMyShow.Service.CityService;
import MySpringBootProject.BookMyShow.Service.TicketService;
import MySpringBootProject.BookMyShow.Models.City;
import MySpringBootProject.BookMyShow.Models.Ticket;
import MySpringBootProject.BookMyShow.Models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private CityService cityService;

    @GetMapping("/BMS/City")
    public List<City> getCities()
    {
        return cityService.getCityList();
    }

    @GetMapping("/BMS/City/{cityId}")
    public ResponseEntity<City> displayMovies(@PathVariable int cityId)
    {
        City city= cityService.getTheatreList(cityId);
        return ResponseEntity.ok(city);
    }

    @PostMapping("/ticket")
    public ResponseEntity createTicket(@RequestBody BookTicketRequestDTO bookTicketRequestDTO) throws ShowSeatAlreadyBookedException {
        Ticket ticket = ticketService.bookTicket(bookTicketRequestDTO.getShowSeatIds(), bookTicketRequestDTO.getUserId());
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        Show show=ticket.getShow();
        ticketResponseDTO.setAuditoriumName(show.getAuditorium().getName());
        ticketResponseDTO.setMovieName(show.getMovie().getName());
        ticketResponseDTO.setTotalAmount(ticket.getTotalAmount());
        ticketResponseDTO.setTimeOfShow(show.getStartTime());
        List<String> seatNumbers=new ArrayList<>();
        for(ShowSeat showSeat:ticket.getShowSeats())
        {
            seatNumbers.add(showSeat.getSeat().getSeatNumber());
        }
        ticketResponseDTO.setSeatNumbers(seatNumbers);
        return ResponseEntity.ok(ticketResponseDTO);
    }


}
