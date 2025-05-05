package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.ArtistRepository;

@Service
public class IDeleteServiceImpl implements IDeleteService {

	@Autowired
	private ArtistRepository artistRepository;

	@Override
	@Transactional
	public void deleteArtistById(Integer id) {
		artistRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void deleteArtistByArtistName(String artistName) {
		artistRepository.deleteByArtistName(artistName);
	}
}
