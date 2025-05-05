package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IDeleteService;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.ISearchService;

@RestController
@RequestMapping("/v1/artist/service")
public class DeleteArtistController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeleteArtistController.class);
	@Autowired
	private IDeleteService deleteService;
	
	@Autowired
	private ISearchService searchService;

	@DeleteMapping("/delete/artist/by/id")
	public ResponseEntity<Object> deleteArtistById(@RequestParam Integer id) {

		if (id != null && id >= 0) {
			logger.info("DeleteArtistController - deleting artist for ID "+ id);
			deleteService.deleteArtistById(id);
		}
		 
		return searchService.searchById(id)
				.map(a -> ResponseEntity.badRequest().build())
				.orElse(ResponseEntity.ok().build());
	}
	
	@DeleteMapping("/delete/artist/by/name")
	public ResponseEntity<Object> deleteArtistByArtistName(@RequestParam String artistName) {

		if (artistName != null) {
			logger.info("DeleteArtistController - deleting artist for artistName"+ artistName);
			deleteService.deleteArtistByArtistName(artistName);
		}
		
		return searchService.searchArtist(artistName)
				.map(a -> ResponseEntity.badRequest().build())
				.orElse(ResponseEntity.ok().build());
	}
}
