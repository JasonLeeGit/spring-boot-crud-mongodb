package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class ICreateServiceImpl implements ICreateService {

	@Autowired
	ArtistRepository artistRepository;

	@Override
	public Artist insertArtist(Artist artist) {
		return artistRepository.insert(artist);
	}

}
