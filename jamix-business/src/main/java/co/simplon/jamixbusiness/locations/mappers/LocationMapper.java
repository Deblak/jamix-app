package co.simplon.jamixbusiness.locations.mappers;

import co.simplon.jamixbusiness.locations.Location;
import co.simplon.jamixbusiness.locations.dtos.LocationCreateDto;
import co.simplon.jamixbusiness.locations.dtos.LocationViewDto;

public final class LocationMapper {

    private LocationMapper() {
    }

    public Location createEntityFromDto(LocationCreateDto dto) {
	Location location = new Location();
	location.setCity(dto.city());
	location.setZipCode(dto.zipCode());
	return location;
    }

    public static LocationViewDto toViewDto(Location location) {
	if (location == null) {
	    return null;
	}
	return new LocationViewDto(location.getId(), location.getCity(), location.getZipCode());
    }

}
