package ch.skazsi.profilepicture.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryListingImageRepository implements ImageRepository{

	private static final Set<String> SUPPORTED_EXTENSIONS = new HashSet<>(Arrays.asList("jpg", "jpeg", "gif", "png"));
	
	private String directory;
	private List<Path> images;
	
	public DirectoryListingImageRepository(String directory) {
		this.directory = Objects.requireNonNull(directory);
		
		try (Stream<Path> stream = Files.list(Path.of(directory))) {
	        images = stream
	          .filter(file -> !Files.isDirectory(file))
	          .filter(this::isSupported)
	          .sorted()
	          .collect(Collectors.toList());

	    } catch (IOException e) {
			throw new RuntimeException("Unable to list directory of " + directory, e);
		}
	}

	private boolean isSupported(Path path) {
		String filename = path.toString();
		String extension = getExtension(filename);
		return SUPPORTED_EXTENSIONS.contains(extension);
	}

	private String getName(String filename) {
		if (filename.contains(".")) {
			return filename.substring(0, filename.lastIndexOf("."));
		}
		return null;
	}

	private String getExtension(String filename) {
		if (filename.contains(".")) {
			return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		return images.size() > 0;
	}

	@Override
	public Image next() {
		try {
			Path path = images.remove(0);
			String filename = path.getFileName().toString();
			String name = getName(filename);
			String type = getExtension(filename);
			return new Image(name, type, Files.readAllBytes(path));
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error during taking the next image", e);
		}
	}

	@Override
	public void save(Image image) {
		try {
			Path path = Path.of(directory, image.getName() + "." + image.getType());
			Files.write(path, image.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error during saving image", e);
		}
	}

}
