package ch.skazsi.profilepicture;

import ch.skazsi.profilepicture.image.ImageManager;
import ch.skazsi.profilepicture.repository.DirectoryListingImageRepository;
import ch.skazsi.profilepicture.repository.Image;
import ch.skazsi.profilepicture.repository.ImageRepository;

public class ProfilePictureMaker {

	private static final String PROFILE_PICTURE_SUFFIX = "_profile";
	
	public static void main(String[] args) {

		Parameters parameters = new Parameters(args);
		System.out.println("Using parameters:");
		System.out.println("\tdirectory: " + parameters.getDirectory());
		System.out.println("\timageSize: " + parameters.getImageSize());
		
		ImageRepository imageRepository = new DirectoryListingImageRepository(parameters.getDirectory());
		ImageManager imageManager = new ImageManager(parameters.getImageSize());

		while (imageRepository.hasNext()) {
			Image image = imageRepository.next();
			if (image.getName().endsWith(PROFILE_PICTURE_SUFFIX)) {
				System.out.println("Skipping image of "+image.getName()+"."+image.getType());
				continue;
			}
			System.out.println("Processing image of "+image.getName()+"."+image.getType());
			
			byte[] profilePictureAsPNGBytes = imageManager.createProfilePictureAsPNG(image.getBytes());
			Image profileImage = new Image(image.getName() + PROFILE_PICTURE_SUFFIX, "png", profilePictureAsPNGBytes);
			imageRepository.save(profileImage);
		}
	}
}
