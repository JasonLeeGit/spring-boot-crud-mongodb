package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class IDeleteServiceImpl implements IDeleteService {

	@Autowired
	ArtistRepository artistRepository;

	@Override
	public void deleteArtistById(Integer id) {
		artistRepository.deleteById(id);
	}
}
