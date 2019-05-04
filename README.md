# Shuffler
A desktop application that uses a weighted random sampling algorithm to play random media files in the same directory as the app. Each time the shuffler plays a media file, the file's corresponding weight is reduced for future use.

To use the program, drag the [Shuffler.jar](out/artifacts/Shuffler_jar/Shuffler.jar) file into the directory with the media files you want to play.
Then, double click the jar file to play a random file. The first time the program is executed, you will be prompted to provide a file extension that allows the program to filter through the relevant files. The program will store its data locally in a file called shuffler.ser. To reset the program's memory, simply delete this file.

The weighted random sampling algorithm used is the linear scan from the article by Bruce Hill: [A Faster Weighted Random Choice](https://blog.bruce-hill.com/a-faster-weighted-random-choice)
