package MySpringBootProject.BookMyShow.Repository;

import MySpringBootProject.BookMyShow.Models.City;
import MySpringBootProject.BookMyShow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Integer> {
    @Query("SELECT DISTINCT c from City c "+"JOIN FETCH c.theatres t "+
            "WHERE c.id= :cityId " + "AND t.city.id= :cityId" )
    Optional<City> findTheatresWithValidCityId(@Param("cityId") int cityId);
}
