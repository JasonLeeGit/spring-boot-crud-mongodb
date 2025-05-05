package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IUpdateService;

@RestController
@RequestMapping("/v1/artist/service")
@Validated
public class UpdateArtistController {

	
	private static final Logger logger = LoggerFactory.getLogger(UpdateArtistController.class);
	@Autowired
	private IUpdateService updateService;

	@PutMapping("/update/artist")
	public ResponseEntity<Artist> updateArist(@RequestBody @Valid Artist artist) {
		logger.info("UpdateArtistController - updating artist "+ artist.toString());
		return updateService.updateArtist(artist)
				.map(updatedArtist -> ResponseEntity.ok(updatedArtist))
				.orElse(ResponseEntity.notFound().build());
	}
}
