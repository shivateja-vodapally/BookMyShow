package MySpringBootProject.BookMyShow.Service;

import MySpringBootProject.BookMyShow.Models.City;
import MySpringBootProject.BookMyShow.Repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCityList()
    {
        return cityRepository.findAll();
    }

    public City getTheatreList(int cityId)
    {
        return cityRepository.findTheatresWithValidCityId(cityId)
                .orElseThrow(() ->new RuntimeException("No city found with valid theatres"));
    }
}
