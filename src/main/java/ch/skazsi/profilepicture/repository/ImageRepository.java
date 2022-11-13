package ch.skazsi.profilepicture.repository;

import java.util.Iterator;

public interface ImageRepository extends Iterator<Image> {

	void save(Image image);
}
