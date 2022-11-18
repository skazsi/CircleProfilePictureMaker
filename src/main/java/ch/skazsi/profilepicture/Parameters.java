package ch.skazsi.profilepicture;

public class Parameters {

	private String directory = System.getProperty("user.dir");
	private int imageSize = 150;

	public Parameters(String... args) {
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("Invalid argument set");
		}

		for (int i = 0; i < args.length; i = i + 2) {
			switch (args[i]) {
			case "-dir":
				directory = args[i + 1];
				break;
			case "-imageSize":
				imageSize = Integer.parseInt(args[i + 1]);
				break;
				default: throw new IllegalArgumentException("Invalid key argument: " + args[i]);
			}
		}
	}

	public String getDirectory() {
		return directory;
	}

	public int getImageSize() {
		return imageSize;
	}
}
