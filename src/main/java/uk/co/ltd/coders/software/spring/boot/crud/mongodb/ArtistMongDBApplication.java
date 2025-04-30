package uk.co.ltd.coders.software.spring.boot.crud.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * To run open a terminal, cd to src/main/resources and enter docker-compose up -d  
 * data stored in mongoDBCompass localhost:28017 -> ArtistWithAlbumsMongoDatabase -> ArtistCollection
 */

@SpringBootApplication
public class ArtistMongDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtistMongDBApplication.class, args);
	}

}
