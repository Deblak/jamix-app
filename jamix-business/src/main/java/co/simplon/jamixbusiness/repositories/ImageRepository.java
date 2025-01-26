package co.simplon.jamixbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.jamixbusiness.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
