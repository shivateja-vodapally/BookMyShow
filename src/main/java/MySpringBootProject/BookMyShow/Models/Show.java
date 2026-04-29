package MySpringBootProject.BookMyShow.Models;

import MySpringBootProject.BookMyShow.Models.Constants.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
    @ManyToOne
    private Auditorium auditorium;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;
}
