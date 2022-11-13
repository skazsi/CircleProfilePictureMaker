package ch.skazsi.profilepicture;

import ch.skazsi.profilepicture.image.ImageManager;
import ch.skazsi.profilepicture.repository.DirectoryListingImageRepository;
import ch.skazsi.profilepicture.repository.Image;
import ch.skazsi.profilepicture.repository.ImageRepository;

public class ProfilePictureMaker {

	public static void main(String[] args) {

		ImageRepository imageRepository = new DirectoryListingImageRepository("c:\\Downloads\\ProfilePictures");
		ImageManager imageManager = new ImageManager();

		while (imageRepository.hasNext()) {
			Image image = imageRepository.next();
			byte[] profilePictureAsPNGBytes = imageManager.createProfilePictureAsPNG(image.getBytes());
			Image profileImage = new Image(image.getName() + "_profile", "png", profilePictureAsPNGBytes);
			imageRepository.save(profileImage);
		}
	}
}
