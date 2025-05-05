package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IReadService;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IWriteService;

@RestController
@RequestMapping("/v1/artist/service")
public class CollateDataController {

	private static final Logger logger = LoggerFactory.getLogger(CollateDataController.class);
	@Autowired
	private IReadService readService;
	
	@Autowired
	private IWriteService writeService;

	@GetMapping("/collate/data")
	public ResponseEntity<String> collateData() {
		long existingRecordCount = writeService.isRepositoryPopulated();
		if (existingRecordCount <= 0) {
			logger.info("CollateDataController - Writing data to database");
			writeService.saveAndFlush(readService.directoryList());
			logger.info("CollateDataController - writing data conpleted");
			return  ResponseEntity.ok().body("CollateDataController - writing data conpleted");
		} else {
			logger.info("Database already populated no new records inserted, existing record count is " + existingRecordCount);
			return  ResponseEntity.unprocessableEntity().body("Database already populated no new records inserted, existing record count is " + existingRecordCount); 
		}
	}
}
