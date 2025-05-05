package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import org.springframework.stereotype.Service;

@Service
public interface IDeleteService {
	
	void deleteArtistById(Integer id);

	void deleteArtistByArtistName(String artistName);

}
