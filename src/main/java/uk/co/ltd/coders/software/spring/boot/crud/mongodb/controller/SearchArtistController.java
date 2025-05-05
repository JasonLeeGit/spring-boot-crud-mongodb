package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(SearchArtistController.class);
	@Autowired
	private ISearchService searchService;	
	
	@GetMapping("/search/all")
	public ResponseEntity<List<Artist>> allArtists() {
		logger.info("SearchArtistController - Searching all artists");
		List<Artist> artists = searchService.searchAllArtists();
		if(artists.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}else {
			return ResponseEntity.ok(artists);			
		}
	}
	
	@GetMapping("/search/artist/by/name")
	public ResponseEntity<Artist> searchArtistByName(@RequestParam String artistName) {
		logger.info("SearchArtistController - Searching for artist "+ artistName);
			return searchService.searchArtist(artistName)
					.map(artist -> ResponseEntity.ok(artist))
					.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search/artist/albums/by/name/ordered/desc")
	public ResponseEntity<List<String>> searchArtistAlbumsInDescendingOrder(@RequestParam String artistName) {
		logger.info("SearchArtistController - Searching for artist albums in DESC order for "+ artistName);
		return searchService.searchArtist(artistName)
				.map(artist -> ResponseEntity.ok(
						artist.getAlbums()
							.stream()
							.map(album -> album.getAlbumName())
							.sorted(Collections.reverseOrder())
							.collect(Collectors.toList())))
				.orElse(ResponseEntity.notFound().build());
	}		   
}




