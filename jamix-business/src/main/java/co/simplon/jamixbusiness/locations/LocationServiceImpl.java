package co.simplon.jamixbusiness.locations;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;
    private final RestTemplate restTemplate;

    @Value("${jamix.api.geo}")
    private String apiUrl;

    protected LocationServiceImpl(LocationRepository repository, RestTemplate restTemplate) {
	this.repository = repository;
	this.restTemplate = new RestTemplate();
    }

    @Override
    public Boolean isReal(LocationViewDto locationDto) {
	if (locationDto.city() == null || locationDto.zipCode() == null) {
	    return false;
	}
	String zipCode = locationDto.zipCode().trim();
	String inputCity = locationDto.city().trim().toLowerCase();
	String url = apiUrl + "?codePostal=" + zipCode;

	try {
	    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	    if (response.getBody() == null || response.getBody().isEmpty()) {
		return false;
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    List<Map<String, Object>> communes = mapper.readValue(response.getBody(), new TypeReference<>() {
	    });
	    for (Map<String, Object> commune : communes) {
		String communeName = ((String) commune.get("nom")).trim().toLowerCase();
		if (communeName.equals(inputCity)) {
		    return true;
		}
	    }
	    return false;
	} catch (Exception e) {
	    System.err.println("API validation failed: " + e.getMessage());
	    return false;
	}
    }

    @Override
    public Boolean isExist(String city, String zipCode) {
	return repository.findByCityIgnoreCaseAndZipCode(city, zipCode).isPresent();
    }

    @Override
    public LocationViewDto create(LocationCreateDto dto) {
	if (dto == null || dto.city() == null || dto.zipCode() == null) {
	    throw new IllegalArgumentException("French city or zip code is void.");
	}
	String sanitizedCity = dto.city().trim();
	String sanitizedZip = dto.zipCode().trim();

	LocationViewDto locationDto = new LocationViewDto(null, sanitizedCity, sanitizedZip);
	if (!isReal(locationDto)) {
	    throw new IllegalArgumentException("French city or zip code is fake.");
	}

	Optional<Location> existing = repository.findByCityIgnoreCaseAndZipCode(sanitizedCity, sanitizedZip);
	if (existing.isPresent()) {
	    return LocationMapper.toViewDto(existing.get());
	}
	Location location = new Location(sanitizedCity, sanitizedZip);
	repository.save(location);

	return LocationMapper.toViewDto(location);
    }

    @Override
    public List<Location> getAllLocation() {
	return repository.findAll();
    }

}
