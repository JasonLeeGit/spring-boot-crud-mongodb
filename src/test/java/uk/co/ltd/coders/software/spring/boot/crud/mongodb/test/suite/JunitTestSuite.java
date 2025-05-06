package uk.co.ltd.coders.software.spring.boot.crud.mongodb.test.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ActiveProfiles;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller.CollateDataControllerTest;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller.CreateArtistControllerTest;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller.DeleteArtistControllerTest;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller.SearchArtistControllerTest;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.controller.UpdateArtistControllerTest;

@ActiveProfiles("test")
@Suite
@SelectClasses({ 
	CollateDataControllerTest.class,
	CreateArtistControllerTest.class,
	DeleteArtistControllerTest.class,
	SearchArtistControllerTest.class,
	UpdateArtistControllerTest.class
})
public class JunitTestSuite {}
