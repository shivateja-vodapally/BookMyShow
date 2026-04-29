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
public class City extends BaseModel {
    @Column(name="CityName")
    private String name;
    @OneToMany(mappedBy = "city", fetch= FetchType.LAZY)
    private List<Theatre> theatres;
}
