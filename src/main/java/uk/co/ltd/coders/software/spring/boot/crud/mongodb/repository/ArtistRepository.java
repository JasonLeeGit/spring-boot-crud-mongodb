package uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;

@Repository("artistRepository")
public interface ArtistRepository extends MongoRepository<Artist, Integer> {
	
	Artist findByArtistName(String artistName);

}
