package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IReadService;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IWriteService;

@RestController
@RequestMapping("/v1/artist/service")
public class CollateDataController {

	@Autowired
	private IReadService readService;
	
	@Autowired
	private IWriteService writeService;

	@GetMapping("/collate/data")
	public void collateData() {
		if (writeService.isRepositoryPopulated() <= 0) {
			System.out.println("Loading Data!");
			
			writeService.saveAndFlush(readService.directoryList());

//			readService.directoryList().forEach(artist -> {
//				artist.setId(sequenceGenerator.generateSequence(Artist.SEQUENCE_NAME));
//				writeService.save(artist);
//				}
//			);
		} else {
			System.out.println("Database already populated no new records inserted, record count is " + writeService.isRepositoryPopulated());
		}
	}
}
