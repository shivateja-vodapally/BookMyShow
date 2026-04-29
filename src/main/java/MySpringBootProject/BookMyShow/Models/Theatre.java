package MySpringBootProject.BookMyShow.Models;

import MySpringBootProject.BookMyShow.Models.Constants.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {
    private String name;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    @JsonIgnore
    private City city;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="theatre_id")
    @JsonIgnore
    private List<Auditorium> auditoriums;
}
