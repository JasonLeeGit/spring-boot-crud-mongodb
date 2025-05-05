package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.ArtistMongDBApplication;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Album;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;

@Testcontainers
@SpringBootTest(classes = ArtistMongDBApplication.class)
public class CreateArtistControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;

	private Artist artist;
	
    //@Container known bug fails with premature end of stream, fix is start container maually as below mongoDBContainer.start();
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0").withExposedPorts(27017);
  
    @DynamicPropertySource
    static void containersProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
    }
	
	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		artist = new Artist();
		artist.setArtistName("TestArtist");
		artist.setAlbums(List.of(new Album("AlbumName1"), new Album("AlbumName2"), new Album("AlbumName3")));
	}

	@Test
	public void createNewArtistTest() throws Exception {	
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/artist/service/create/artist")
				.content(asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void createNewArtistForExistingTest() throws Exception {	
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/artist/service/create/artist")
				.content(asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnprocessableEntity());
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
