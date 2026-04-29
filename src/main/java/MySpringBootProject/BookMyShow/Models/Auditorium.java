package MySpringBootProject.BookMyShow.Models;

import MySpringBootProject.BookMyShow.Models.Constants.AuditoriumFeature;
import MySpringBootProject.BookMyShow.Models.Constants.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {
    private String name;
    private int capacity;
    @OneToMany(mappedBy = "auditorium")
    private List<Show> shows;
    @ManyToOne
    @JoinColumn(name = "theatre_id", insertable = false, updatable = false)
    private Theatre theatre;
    @OneToMany
    @JoinColumn(name="audi_id")
    private List<Seat> seats;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeature> auditoriumFeatures;
}
