package ch.skazsi.profilepicture;

import static org.assertj.core.api.BDDAssertions.catchException;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParametersTest {

	@Test
	void constructor_with_no_arguments() {
		// When
		Parameters underTest = new Parameters();
		
		// Then
		then(underTest)
			.returns(System.getProperty("user.dir"), Parameters::getDirectory)
			.returns(150, Parameters::getImageSize);
	}

	@Test
	void constructor_with_directory_argument() {
		// When
		Parameters underTest = new Parameters("-dir", "some_dir");
		
		// Then
		then(underTest)
			.returns("some_dir", Parameters::getDirectory)
			.returns(150, Parameters::getImageSize);
	}

	@Test
	void constructor_with_imageSize_argument() {
		// When
		Parameters underTest = new Parameters("-imageSize", "300");
		
		// Then
		then(underTest)
			.returns(System.getProperty("user.dir"), Parameters::getDirectory)
			.returns(300, Parameters::getImageSize);
	}

	@Test
	void constructor_with_imageSize_fails_with_not_a_number_value() {
		// When
		Exception exception = catchException(() -> new Parameters("-imageSize", "not_a_number"));

		// Then
		then(exception).isInstanceOf(NumberFormatException.class).hasMessageEndingWith("\"not_a_number\"");
	}

	@Test
	void constructor_fails_with_wrong_argument_size() {
		// When
		Exception exception = catchException(() -> new Parameters("-imagesize"));

		// Then
		then(exception).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid argument set");
	}

	@ParameterizedTest
	@ValueSource(strings = {"imageSize", "-unkown"})
	void constructor_fails_with_invalid_key_argument(String invalidKeyArgument) {
		// When
		Exception exception = catchException(() -> new Parameters(invalidKeyArgument, "150"));

		// Then
		then(exception).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid key argument: " + invalidKeyArgument);
	}
}