package uk.co.ltd.coders.software.spring.boot.crud.mongodb.service;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.ltd.coders.software.spring.boot.crud.mongodb.helper.ConfigPropertiesHelper;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Album;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.model.Artist;
import uk.co.ltd.coders.software.spring.boot.crud.mongodb.repository.SequenceGeneratorService;

@Service
public class ReadServiceImpl implements IReadService {

	private static final Logger logger = LoggerFactory.getLogger(ReadServiceImpl.class);
	@Autowired
	private ConfigPropertiesHelper properties;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Override
	public List<Artist> directoryList() {

		List<Artist> artistsList = new ArrayList<>();
		try {
			List<String> artistNames = Files.list(new File(properties.getMusicPath()).toPath())
					.map(path -> path.getFileName().toString()).collect(toList());

			Collections.sort(artistNames, String.CASE_INSENSITIVE_ORDER);

			artistNames.forEach(artistName -> {
				try {
					if (isValidFileType(artistName)) {
						Artist artist = new Artist();
						artist.setArtistName(artistName);
						
						artist.setId(sequenceGenerator.generateSequence(Artist.SEQUENCE_NAME));

						List<String> albumNames = Files.list(new File(properties.getMusicPath() + artistName).toPath())
								.map(p -> p.getFileName().toString()).collect(toList());

						List<Album> albumList = new ArrayList<>();
						albumNames.forEach(albumName -> {

							if (isValidFileType(albumName)) {
								Album album = new Album();
								album.setAlbumName(albumName);
								albumList.add(album);
							}

						});
						artist.setAlbums(albumList);

						if (artist != null) {
							artistsList.add(artist);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("ReadServiceImpl - Artist results, ");
		artistsList.forEach(a -> logger.info(a.getArtistName()));
		
		return artistsList;
	}

	public String cleanNameOfSingleQuotes(String name) {
		String cleanName = "";
		for (int i = 0; i < name.length(); ++i) {
			char nameCharacter = name.charAt(i);
			int isNameCharacterSingleQuote = (int) nameCharacter;
			if (isNameCharacterSingleQuote != 39) {
				cleanName = cleanName + nameCharacter;
			}
		}
		return cleanName;
	}

	public boolean isValidFileType(String value) {
		return !value.contains(".jpg") && !value.contains(".JPG") && !value.contains(".jpeg") && !value.contains(".ini")
				&& !value.contains(".pdf") && !value.contains(".avi") && !value.contains(".txt")
				&& !value.contains(".bmp") && !value.contains(".url") && !value.contains(".png")
				&& !value.contains(".gif") && !value.contains(".ini") && !value.contains(".db")
				&& !value.contains(".mp3") && !value.contains("iTunes");
	}
}
