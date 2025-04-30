package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IDeleteService;

@RestController
@RequestMapping("/v1/artist/service")
public class DeleteArtistController {
	
	@Autowired
	private IDeleteService deleteService;

	@DeleteMapping("/delete/artist/by/id")
	public void deleteArtistById(@RequestParam Integer id) {
		if (id != null && id >= 0) {
			deleteService.deleteArtistById(id);
		}
	}
}
