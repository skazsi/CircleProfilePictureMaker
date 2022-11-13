package ch.skazsi.profilepicture.repository;

import static org.assertj.core.api.BDDAssertions.catchException;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void constructor_fails_with_null_name() {
		// When
		Exception exception = catchException(() -> new Image(null, "type", new byte[] {}));

		// Then
		then(exception).isInstanceOf(NullPointerException.class);
	}

	@Test
	void constructor_fails_with_null_type() {
		// When
		Exception exception = catchException(() -> new Image("name", null, new byte[] {}));

		// Then
		then(exception).isInstanceOf(NullPointerException.class);
	}

	@Test
	void constructor_fails_with_null_bytes() {
		// When
		Exception exception = catchException(() -> new Image("name", "type", null));

		// Then
		then(exception).isInstanceOf(NullPointerException.class);
	}
}
