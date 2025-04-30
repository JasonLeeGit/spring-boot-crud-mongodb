package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IUpdateService;

@RestController
@RequestMapping("/v1/artist/service")
public class UpdateArtistController {

	@Autowired
	public IUpdateService updateService;

	@PutMapping("/update/artist")
	public ResponseEntity<Artist> updateArist(@RequestBody Artist artist) {
		Artist updatedArtist = updateService.updateArtist(artist);
		if (updatedArtist != null) {
			return ResponseEntity.ok(updatedArtist);
		} else {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Error message", "Error failed to update artist");
			return new ResponseEntity<Artist>(headers, HttpStatus.BAD_REQUEST);
		}
	}
}
