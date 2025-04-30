package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;

@Service
public interface IUpdateService {

	public Artist updateArtist(Artist artist);
	
}
