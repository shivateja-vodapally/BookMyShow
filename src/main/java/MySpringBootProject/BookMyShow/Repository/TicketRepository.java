package MySpringBootProject.BookMyShow.Repository;

import MySpringBootProject.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface TicketRepository extends JpaRepository<Ticket, Integer>{}

