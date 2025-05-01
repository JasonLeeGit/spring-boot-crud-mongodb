package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

	@Autowired
	private ICreateService createService;

	@Autowired
	private ISearchService searchService;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	@PostMapping("/create/artist")
	public ResponseEntity<Artist> createNewArtist(@RequestBody @Valid Artist artist) {
		Artist insertedArtist = null;
		StringBuffer errorMessage = new StringBuffer();		
		errorMessage.append("Error failed to save new artist");

		if (doesArtistAlreadyExist(artist)) {
			artist.setId(sequenceGenerator.generateSequence(Artist.SEQUENCE_NAME));
			insertedArtist = createService.insertArtist(artist);
		} else {
			errorMessage.append(", artist already exists in the database");
		}

		if (insertedArtist != null) {
			return ResponseEntity.ok(insertedArtist);
		} else {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Error message", errorMessage.toString());
			return new ResponseEntity<Artist>(headers, HttpStatus.BAD_REQUEST);
		}
	}

	private boolean doesArtistAlreadyExist(Artist artist) {
		return searchService.searchArtist(artist.getArtistName()).isEmpty();
	}
}
