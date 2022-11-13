package ch.skazsi.profilepicture.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class ImageManager {

	private ImageProcessor imageProcessor = new ImageProcessor();

	public byte[] createProfilePictureAsPNG(byte[] inputImage) {
		BufferedImage inputBufferedImage = getBufferedImage(inputImage);
		BufferedImage outputBufferedImage = imageProcessor.process(inputBufferedImage);
		return getPNGByteArray(outputBufferedImage);
	}

	private BufferedImage getBufferedImage(byte[] inputImage) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(inputImage);
			return ImageIO.read(inputStream);
		} catch (Exception e) {
			throw new RuntimeException("Unable to get the buffered image", e);
		}
	}

	private byte[] getPNGByteArray(BufferedImage bufferedImage) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "PNG", outputStream);
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("Unable to convert the image to a byte array", e);
		}
	}
}
