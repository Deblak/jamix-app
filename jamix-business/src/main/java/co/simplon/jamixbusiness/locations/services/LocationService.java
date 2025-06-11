package co.simplon.jamixbusiness.locations.services;

import java.util.List;

import co.simplon.jamixbusiness.locations.Location;
import co.simplon.jamixbusiness.locations.dtos.LocationCreateDto;
import co.simplon.jamixbusiness.locations.dtos.LocationViewDto;

public interface LocationService {
    Boolean isReal(LocationViewDto locationDto);

    Boolean isExist(String city, String zipCode);

    LocationViewDto create(LocationCreateDto dto);

    List<Location> getAllLocation();

}
