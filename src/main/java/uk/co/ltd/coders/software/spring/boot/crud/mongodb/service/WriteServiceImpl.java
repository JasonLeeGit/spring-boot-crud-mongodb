package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller.CreateArtistController;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class WriteServiceImpl implements IWriteService {
	
	private static final Logger logger = LoggerFactory.getLogger(CreateArtistController.class);
	
	@Autowired
	private ArtistRepository artistRepository;

	@Override
	public long isRepositoryPopulated() {
		return artistRepository.count();
	}

	@Override
	@Transactional
	public void saveAndFlush(List<Artist> artists) {
		List<Artist> savedArtists = artistRepository.saveAll(artists);
		if(savedArtists.size() == artists.size()) {
			logger.info("Success all artists saved");
		}
	}
	
	@Override
	@Transactional
	public void save(Artist artist) {
		Artist savedArtist = artistRepository.save(artist);
		if(savedArtist!=null) {
			logger.info("Success all artists saved");
		} 
	}
}
