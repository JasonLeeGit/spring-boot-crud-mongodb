package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class UpdateServiceImpl implements IUpdateService {

	@Autowired
	ArtistRepository artistRepository;
	
	@Override
	public Optional<Artist> updateArtist(Artist artist) {
		return Optional.of(artistRepository.save(artist));
	}

}
