
package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
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

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.ArtistMongDBApplication;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Album;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.util.ITestHelper;

@Testcontainers
@SpringBootTest(classes = ArtistMongDBApplication.class)
public class SearchArtistControllerTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;
	@SuppressWarnings("resource")
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0").withExposedPorts(27017);

	private MockMvc mockMvc;
	private Artist artist;

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
	public void searchAllArtistsWhenNoneFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/artist/service/search/all"))
				.andExpect(status().isNotFound());	
	}

	@Test
	public void searchAllArtistsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/artist/service/create/artist")
				.content(ITestHelper.asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());		

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/artist/service/search/all"))
				.andExpect(status().isOk());	
	}
	
	@Test
	public void searchForArtistByName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/artist/service/create/artist")
				.content(ITestHelper.asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/artist/service/search/artist/by/name?artistName=TestArtist")
				.content(ITestHelper.asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void searchForArtistByNameWhenNoneFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/artist/service/search/artist/by/name?artistName=TestArtist")
				.content(ITestHelper.asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@AfterEach
	public void tearDown() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/artist/service/delete/artist/by/name?artistName=TestArtist")
				.content(ITestHelper.asJsonString(artist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
}
