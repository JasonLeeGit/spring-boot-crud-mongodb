package uk.co.ltd.coders.software.spring.boot.crud.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ArtistCollection")
public class Artist {

	@Transient
	public static final String SEQUENCE_NAME = "database_sequence";

	@Id
	@Min(value = 0)
	private int id;

	@NotEmpty(message = "artist name cannot be null or empty")
	private String artistName;

	@NotEmpty(message = "album cannot be empty")
	private List<@Valid Album> albums;
}
