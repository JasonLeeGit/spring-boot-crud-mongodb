package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.ISearchService;

@RestController
@RequestMapping("/v1/artist/service")
public class SearchArtistController {
	
	@Autowired
	private ISearchService searchService;

	@GetMapping("/search/all")
	public ResponseEntity<List<Artist>> allArtists() {
		return ResponseEntity.ok(searchService.searchAllArtists());
	}
	
	@GetMapping("/search/artist/by/name")
	public ResponseEntity<Optional<Artist>> searchArtistByName(@RequestParam String artistName) {
		StringBuffer errorMessage = new StringBuffer();		

		if (StringUtils.isNotBlank(artistName)) {
			return ResponseEntity.ok(searchService.searchArtist(artistName));
		} else {
			MultiValueMap<String, String> headers = new HttpHeaders();
			errorMessage.append("No artist name has been entered?");
			headers.add("Error message", errorMessage.toString());
			return new ResponseEntity<Optional<Artist>>(headers, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search/artist/albums/by/name/ordered/desc")
	public ResponseEntity<List<String>> searchArtistAlbumsInDescendingOrder(@RequestParam String artistName) {
		StringBuffer errorMessage = new StringBuffer();
		if(StringUtils.isNotBlank(artistName) ) {
			Optional<Artist> artist = searchService.searchArtist(artistName);
			if(artist.isPresent()) {
				return ResponseEntity.ok(artist.get()
						.getAlbums()
						.stream()
						.map(album -> album.getAlbumName())
						.sorted(Collections.reverseOrder())
						.collect(Collectors.toList()));
			} else {
				MultiValueMap<String, String> headers = new HttpHeaders();
				errorMessage.append("No artist found for "+artistName);
				headers.add("Error message", errorMessage.toString());
				return new ResponseEntity<List<String>>(headers, HttpStatus.BAD_REQUEST);
			}	
		} else {
			MultiValueMap<String, String> headers = new HttpHeaders();
			errorMessage.append("No artist name has been entered?");
			headers.add("Error message", errorMessage.toString());
			return new ResponseEntity<List<String>>(headers, HttpStatus.BAD_REQUEST);
		}		
	}	
}
