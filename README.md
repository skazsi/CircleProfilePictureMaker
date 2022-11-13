# Profile Picture Maker

A small utility tool and Java library for making circle shaped transparent profile pictures out of any rectangular pictures. Can be used as a Java command line tool as well as a Java library.

The pictures are cropped to square at first using center alignment. Then resized to the desired size, cropped to a circle, and finally decorated with a small border. The following samples represent the applied changes.

| Original | Result with 100px size configuration |
| --- | --- |
| ![Lena](src/test/resources/Lenna.png) | ![Lena](src/test/resources/Lenna_profile.png) |
| ![Mona Lisa](src/test/resources/Mona-lisa.jpg) | ![Lena](src/test/resources/Mona-lisa_profile.png) |

