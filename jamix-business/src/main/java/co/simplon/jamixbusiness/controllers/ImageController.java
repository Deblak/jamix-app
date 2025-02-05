package co.simplon.jamixbusiness.controllers;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Value("${jamix.uploads.destination}")
    private String uploadsDest;

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageId) {
	try {
	    Path path = Paths.get(uploadsDest).resolve(imageId);
	    Resource resource = new UrlResource(path.toUri());
	    if (!resource.exists()) {
		return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
	} catch (Exception e) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
    }

}
