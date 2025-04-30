package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.SequenceGeneratorService;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.ICreateService;

@RestController
@RequestMapping("/v1/artist/service")
public class CreateArtistController {

	@Autowired
	private ICreateService createService;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/create/artist")
	public ResponseEntity<Artist> createNewArtist(@RequestBody Artist artist) { // TODO @Valid request and return Artist response
		
		artist.setId(sequenceGenerator.generateSequence(Artist.SEQUENCE_NAME));
		
		Artist insertedArtist = createService.insertArtist(artist);
		
		if (insertedArtist != null) {	
			return ResponseEntity.ok(insertedArtist);
		} else {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Error message", "Error failed to save new artist");
			return new ResponseEntity<Artist>(headers, HttpStatus.BAD_REQUEST);
		}
	}
}
