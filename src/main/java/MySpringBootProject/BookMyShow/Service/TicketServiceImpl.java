package MySpringBootProject.BookMyShow.Service;

import MySpringBootProject.BookMyShow.Exception.ShowSeatAlreadyBookedException;
import MySpringBootProject.BookMyShow.Models.*;
import MySpringBootProject.BookMyShow.Models.Constants.ShowSeatStatus;
import MySpringBootProject.BookMyShow.Models.Constants.TicketStatus;
import MySpringBootProject.BookMyShow.Repository.ShowSeatRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws ShowSeatAlreadyBookedException {
        double amount=0;
        Ticket ticket=new Ticket();
        List<ShowSeat> seats=new ArrayList<>();
        for(Integer showSeatId: showSeatIds)
        {
            ShowSeat seat=showSeatRepository.findById(showSeatId)
                    .orElseThrow(() -> new RuntimeException("ShowSeat not found with ID: " + showSeatId));;
            if(!seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))
                throw new ShowSeatAlreadyBookedException("Show seat is booked by someone else");
        }

        for(Integer showSeatId: showSeatIds)
        {
            ShowSeat seat=showSeatRepository.findById(showSeatId).get();
            seat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            seats.add(seat);
            ticket.setShow(seat.getShow());
            amount=amount+seat.getPrice();
            showSeatRepository.save(seat);
        }
        ticket.setShowSeats(seats);
        ticket.setTotalAmount(amount);
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticket.setTimeOfBooking(LocalDateTime.now());
        return ticket;
    }

}
