package uk.co.ltd.coders.software.spring.boot.crud.mongodb.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ITestHelper {
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
