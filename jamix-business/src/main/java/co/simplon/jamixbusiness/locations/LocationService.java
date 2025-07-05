package co.simplon.jamixbusiness.locations;

import java.util.List;

public interface LocationService {
    Boolean isReal(LocationViewDto locationDto);

    Boolean isExist(String city, String zipCode);

    LocationViewDto create(LocationCreateDto dto);

    List<Location> getAllLocation();

}
