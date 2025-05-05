package uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.ArtistMongDBApplication;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.service.IWriteService;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(classes = ArtistMongDBApplication.class)
public class CollateDataControllerTest {
	
	private MockMvc mockMvc;
	@Autowired
	IWriteService writeService;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;

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
	}
	
	@Test
	public void createNewArtistForExistingTest() throws Exception {	
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/artist/service/collate/data"))
					.andExpect(status().isOk());
		
		assert(writeService.isRepositoryPopulated() == 2);

	}
}
