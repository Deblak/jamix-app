package co.simplon.jamixbusiness.commons.images;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${jamix.uploads.destination}")
    private String uploadsDest;

    @Value("${jamix.uploads.url}")
    private String baseUrl;

    @Override
    public String store(MultipartFile file) {
	String imageId = buildImageId(file);
	String dest = "%s/%s".formatted(uploadsDest, imageId);
	try {
	    file.transferTo(new File(dest));
	} catch (Exception ex) {
	    throw new RuntimeException("Failed to store image", ex);
	}
	return imageId;
    }

    @Override
    public Resource load(String imageId) {
	Path path = Path.of(uploadsDest, imageId);
	if (!Files.exists(path)) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
	}
	return new FileSystemResource(path);
    }

    @Override
    public String getUrl(String imageId) {
	return "%s/%s".formatted(baseUrl, imageId);
    }

    private String buildImageId(MultipartFile image) {
	UUID uuid = UUID.randomUUID();
	String name = image.getOriginalFilename();
	String ext = name.substring(name.lastIndexOf('.'));
	return uuid + ext;
    }

    @Override
    public void delete(String imageId) {
	Path path = Path.of(uploadsDest, imageId);
	try {
	    Files.deleteIfExists(path);
	} catch (IOException e) {
	    throw new UncheckedIOException("Failed to delete image", e);
	}
    }
}
