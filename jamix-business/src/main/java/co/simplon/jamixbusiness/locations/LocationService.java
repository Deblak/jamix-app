package co.simplon.jamixbusiness.locations;

import java.util.List;

import co.simplon.jamixbusiness.locations.entities.Location;

public interface LocationService {
    Boolean isReal(LocationViewDto locationDto);

    Boolean isExist(String city, String zipCode);

    LocationViewDto create(LocationCreateDto dto);

    List<Location> getAllLocation();

}
