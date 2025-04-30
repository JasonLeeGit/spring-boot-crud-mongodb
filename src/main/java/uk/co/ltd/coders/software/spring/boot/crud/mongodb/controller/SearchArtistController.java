package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Artist> searchArtistByName(@RequestParam String artistName) {
		return ResponseEntity.ok(searchService.searchArtist(artistName));
	}

	@GetMapping("/search/artist/albums/by/name/ordered/desc")
	public ResponseEntity<List<String>> searchArtistAlbumsInDescendingOrder(@RequestParam String artistName) {
		
		return ResponseEntity.ok(searchService.searchArtist(artistName)
				.getAlbums()
				.stream()
				.map(album -> album.getAlbumName())
				//.sorted() asc but already sorted asc on DB upload
				.sorted(Collections.reverseOrder())
				.collect(Collectors.toList()));
	}	
}
