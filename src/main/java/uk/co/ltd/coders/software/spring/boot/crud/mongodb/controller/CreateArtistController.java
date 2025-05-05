package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.SequenceGeneratorService;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.ICreateService;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.ISearchService;

@RestController
@RequestMapping("/v1/artist/service")
@Validated
public class CreateArtistController {

	private static final Logger logger = LoggerFactory.getLogger(CreateArtistController.class);
	@Autowired
	private ICreateService createService;

	@Autowired
	private ISearchService searchService;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	@PostMapping("/create/artist")
	public ResponseEntity<Artist> createNewArtist(@RequestBody @Valid Artist artist) {
		if (artistDoesNotAlreadyExist(artist)) {
			logger.info("CreateArtistController - inserting new artist");
			
			artist.setId(sequenceGenerator.generateSequence(Artist.SEQUENCE_NAME));
			
			return createService.insertArtist(artist)
					.map(insertedArtist -> ResponseEntity.ok(insertedArtist))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logger.info("CreateArtistController - artist already exists in the database!");
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	private boolean artistDoesNotAlreadyExist(Artist artist) {
		return searchService.searchArtist(artist.getArtistName()).isEmpty();
	}
}
