package ch.skazsi.profilepicture.repository;

import static org.assertj.core.api.BDDAssertions.catchException;
import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;

class DirectoryListingImageRepositoryTest {

	@Nested
	static class Constructor {

		@Test
		void constructor_fails_with_null_directory() throws IOException {
			// When
			Exception exception = catchException(() -> new DirectoryListingImageRepository(null));

			// Then
			then(exception).isInstanceOf(NullPointerException.class);
		}
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	static class Iterating_through_a_directory {

		private static DirectoryListingImageRepository underTest;

		@TempDir
		static Path tempDir;

		@BeforeAll
		static void create_test_directory_structure() throws IOException {
			Files.createFile(tempDir.resolve("irrelevant_contains_zero_dots"));
			Files.createFile(tempDir.resolve("relevant.jpg"));
			Files.createFile(tempDir.resolve("relevant.with.multiple.dots.gif"));
			Files.createFile(tempDir.resolve("irrelevant_extension.txt"));
			Files.createDirectory(tempDir.resolve("irrelevant_directory"));
		}

		@BeforeAll
		static void init_test_instance() {
			underTest = new DirectoryListingImageRepository(tempDir.toString());
		}

		@Test
		@Order(1)
		void hasNext_returns_true_at_first() throws IOException {
			// When
			boolean hasNext = underTest.hasNext();

			// Then
			then(hasNext).isTrue();
		}

		@Test
		@Order(2)
		void next_returns_the_first_image() throws IOException {
			// When
			Image image = underTest.next();

			// Then
			then(image.getName()).isEqualTo("relevant");
			then(image.getType()).isEqualTo("jpg");
		}

		@Test
		@Order(3)
		void hasNext_returns_true_at_second() throws IOException {
			// When
			boolean hasNext = underTest.hasNext();

			// Then
			then(hasNext).isTrue();
		}

		@Test
		@Order(4)
		void next_returns_the_second_image() throws IOException {
			// When
			Image image = underTest.next();

			// Then
			then(image.getName()).isEqualTo("relevant.with.multiple.dots");
			then(image.getType()).isEqualTo("gif");
		}

		@Test
		@Order(5)
		void hasNext_returns_finally_false() throws IOException {
			// When
			boolean hasNext = underTest.hasNext();

			// Then
			then(hasNext).isFalse();
		}
	}

	@Nested
	static class Iterating_through_an_empty_directory {

		private static DirectoryListingImageRepository underTest;

		@TempDir
		static Path tempDir;

		@BeforeAll
		static void init_test_instance() {
			underTest = new DirectoryListingImageRepository(tempDir.toString());
		}

		@Test
		void hasNext_returns_false() throws IOException {
			// When
			boolean hasNext = underTest.hasNext();

			// Then
			then(hasNext).isFalse();
		}
	}

	@Nested
	static class Saving {

		private static DirectoryListingImageRepository underTest;

		@TempDir
		static Path tempDir;

		@BeforeAll
		static void init_test_instance() {
			underTest = new DirectoryListingImageRepository(tempDir.toString());
		}

		@Test
		void save() throws IOException {
			// Given
			Image image = new Image("name", "type", new byte[] {1,2,3});
			
			// When
			underTest.save(image);

			// Then
			Path path = Path.of(tempDir.toString(), image.getName()+ "."+image.getType());
			then(Files.readAllBytes(path)).isEqualTo(new byte[] {1,2,3});
		}
	}
}
