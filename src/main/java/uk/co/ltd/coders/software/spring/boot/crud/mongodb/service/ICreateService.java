package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;

@Service
public interface ICreateService {

	Optional<Artist> insertArtist(Artist artist);
}
