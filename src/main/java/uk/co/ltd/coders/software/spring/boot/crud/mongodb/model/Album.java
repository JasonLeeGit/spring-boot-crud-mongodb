package uk.co.ltd.coders.software.spring.boot.crud.mongodb.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

	@NotEmpty(message = "album cannot be empty")
	private String albumName;
}
