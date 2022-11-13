package ch.skazsi.profilepicture.repository;

import java.util.Objects;

public class Image {

	private final String name;
	private final String type;
	private final byte[] bytes;

	public Image(String name, String type, byte[] bytes) {
		this.name = Objects.requireNonNull(name);
		this.type = Objects.requireNonNull(type);
		this.bytes = Objects.requireNonNull(bytes);
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public byte[] getBytes() {
		return bytes;
	}
}
