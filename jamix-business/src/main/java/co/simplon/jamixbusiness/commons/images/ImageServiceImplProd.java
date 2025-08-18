package co.simplon.jamixbusiness.commons.images;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.StorageClass;

/**
 * Service to manage images. Using Scaleway Object Storage (S3-compatible)
 */
@Profile("prod")
@Service
public class ImageServiceImplProd implements ImageService {

    @Value("${jamix.url.images}")
    private String baseUrl;

    @Value("${scw.accessKey}")
    private String accessKey;

    @Value("${scw.secretKey}")
    private String secretKey;

    @Value("${scw.region}")
    private String region;

    @Value("${scw.bucket}")
    private String bucket;

    private S3Client s3Client;

    /**
     * Do not remove. Called automatically by Spring after dependency injection.
     */
    @PostConstruct
    private void initClientCws() {
	AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, secretKey);
	this.s3Client = S3Client.builder().region(Region.of(region))
		.credentialsProvider(StaticCredentialsProvider.create(creds))
		.endpointOverride(URI.create("https://s3." + region + ".scw.cloud")).build();
    }

    /**
     * Uploads a new image to the "images/" folder of the Scaleway bucket.
     *
     * Uses ONEZONE_IA (free up to 75 GB), sets ACL to public-read and adds content
     * type for browser display (e.g. image/jpeg).
     *
     * @param file Multipart file to upload
     * @return generated image ID (UUID + file extension)
     */
    @Override
    public String store(MultipartFile file) {
	String imageId = buildImageId(file);
	try {
	    PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key("images/" + imageId)
		    .storageClass(StorageClass.ONEZONE_IA).acl("public-read").contentType(file.getContentType())
		    .build();
	    s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
	} catch (Exception ex) {
	    throw new RuntimeException("Failed to upload image", ex);
	}
	return imageId;
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

    /**
     * Deletes the image from Scaleway bucket.
     *
     * @param imageId the image filename to delete
     */
    @Override
    public void delete(String imageId) {
	try {
	    DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucket).key("images/" + imageId).build();
	    s3Client.deleteObject(request);
	} catch (Exception e) {
	    throw new RuntimeException("Failed to delete image", e);
	}
    }

    @Profile("!prod")
    @Override
    public Resource load(String imageId) {
	throw new UnsupportedOperationException("Local image loading not supported anymore. Using bucket now");
    }
}
