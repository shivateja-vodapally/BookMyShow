package MySpringBootProject.BookMyShow.Repository;

import MySpringBootProject.BookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query("SELECT t, s FROM Theatre t " +
            "JOIN t.city c " +
            "JOIN t.auditoriums a " +
            "JOIN a.shows s " +
            "JOIN FETCH s.movie " +
            "WHERE c.name = :cityName")
    List<Object[]> findTheatreShowsForCity(@Param("cityName") String cityName);
}
