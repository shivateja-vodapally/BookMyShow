package MySpringBootProject.BookMyShow.Models;

import MySpringBootProject.BookMyShow.Models.Constants.BaseModel;
import MySpringBootProject.BookMyShow.Models.Constants.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    private int price;
    @ManyToOne
    @JoinColumn(name="show_id")
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus ShowSeatStatus;
}

/*
    ShowSeat  Show
        1      1
        M       1

      ShowSeat Show -> M : 1

      Seat  ShowSeat
      1     M
      1     1

      ShowSeat Seat -> M : 1
 */