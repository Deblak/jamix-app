package co.simplon.jamixbusiness.services.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.entities.Image;
import co.simplon.jamixbusiness.repositories.ImageRepository;
import co.simplon.jamixbusiness.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    protected ImageServiceImpl(ImageRepository repository) {
	this.repository = repository;
    }

    @Override
    public Image saveImage(MultipartFile imageFile) {
	try {

	    Image image = new Image();
	    image.setType(imageFile.getContentType());
	    image.setImageData(imageFile.getBytes());

	    return repository.save(image);
	} catch (IOException e) {
	    throw new RuntimeException("Failed to store image", e);
	}

    }

    @Override
    public Optional<Image> getImageById(Long id) {
	return repository.findById(id);
    }

}
