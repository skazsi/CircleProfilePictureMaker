# Profile Picture Maker

A small utility tool and Java library for making circle shaped transparent profile pictures out of any rectangular pictures. Can be used as a Java command line tool as well as a Java library.

The pictures are cropped to square at first using center alignment. Then resized to the desired size, cropped to a circle, and finally decorated with a small border. The following samples represent the applied changes.

| Original | Result with 100px size configuration |
| --- | --- |
| ![Lena](src/test/resources/Lenna.png) | ![Lena](src/test/resources/Lenna_profile.png) |
| ![Mona Lisa](src/test/resources/Mona-lisa.jpg) | ![Lena](src/test/resources/Mona-lisa_profile.png) |

## Command Line Usage

For using the command line tool Java 11 or higher JVM is required. It supports the following parameters:
* `-dir ../any/valid/directory` that specifies a directory for looking for images. Defaults to the current directory.
* `-imageSize 200` that specifies the desired profile picture size assuming 200 pixels. Defaults to 150 pixels.

The command line tool looks for any _jpg_, _jpeg_, _gif_ and _png_ files in the specified directory and saves the created profile pictures with a _profile_ filename suffix and _png_ type.

Example execution assuming _Lenna.png_ and _Mona-lisa.jpg_ in the _/some/dir_ directory will create the respective _Lenna_profile.png_ and _Mona-lis_profile.png_ files in the same directory.
```
> java -jar ProfilePictureMaker-0.0.1-SNAPSHOT.jar -dir /some/dir -imageSize 200
Using parameters:
        directory: /some/dir
        imageSize: 200
Processing image of Lenna.png
Processing image of Mona-lisa.jpg
```
