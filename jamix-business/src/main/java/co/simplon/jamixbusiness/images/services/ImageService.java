package co.simplon.jamixbusiness.images.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String store(MultipartFile file);

    Resource load(String imageId);

    String getUrl(String imageId);

    void delete(String imageId);

}
