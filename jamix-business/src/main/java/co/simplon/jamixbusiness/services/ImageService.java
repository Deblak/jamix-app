package co.simplon.jamixbusiness.services;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.jamixbusiness.entities.Image;

public interface ImageService {
    Image saveImage(MultipartFile imageFile);

    Optional<Image> getImageById(Long id);
}
