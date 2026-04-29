package MySpringBootProject.BookMyShow.Service;

import MySpringBootProject.BookMyShow.Exception.ShowSeatAlreadyBookedException;
import MySpringBootProject.BookMyShow.Models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws ShowSeatAlreadyBookedException;
}
