package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class WriteServiceImpl implements IWriteService {
	
	@Autowired
	ArtistRepository artistRepository;

	@Override
	public long isRepositoryPopulated() {
		return artistRepository.count();
	}

	@Override
	public void saveAndFlush(List<Artist> artists) {
		List<Artist> savedArtists = artistRepository.saveAll(artists);
		if(savedArtists.size() == artists.size()) {
			System.out.println("Success all artists saved");
		} else {
			//rollback somehow?
		}
	}

	
	@Override
	public void save(Artist artist) {
		Artist savedArtist = artistRepository.save(artist);
		if(savedArtist!=null) {
			System.out.println("Success all artists saved");
		} else {
			//rollback somehow?
		}
	}
}
