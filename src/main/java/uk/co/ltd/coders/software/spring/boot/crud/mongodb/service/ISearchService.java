package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;

@Service
public interface ISearchService {
	
	List<Artist> searchAllArtists();
	
	Artist searchArtist(String artistName);
}
