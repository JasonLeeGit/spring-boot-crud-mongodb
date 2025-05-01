package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

	@Autowired
	public IUpdateService updateService;

	@PutMapping("/update/artist")
	public ResponseEntity<Optional<Artist>> updateArist(@RequestBody @Valid Artist artist) {
		Optional<Artist> updatedArtist = updateService.updateArtist(artist);
		if (updatedArtist.isPresent()) {
			return ResponseEntity.ok(updatedArtist);
		} else {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Error message", "Error failed to update artist");
			return new ResponseEntity<Optional<Artist>>(headers, HttpStatus.BAD_REQUEST);
		}
	}
}
