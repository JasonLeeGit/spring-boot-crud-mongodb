package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class SearchServiceImpl implements ISearchService {

	@Autowired
	ArtistRepository artistRepository;
	
	@Override
	public List<Artist> searchAllArtists() {
		return artistRepository.findAll();
	}
	
	@Override
	public Artist searchArtist(String artistName) {
		return artistRepository.findByArtistName(artistName);
	}
}
