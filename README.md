
##Jukebox-JavaApp



It configures the compiler to use UTF-8, Java 11

It includes SLF4J as logging API, logback (with ISO8601 timestamps) as logging backend, JUnit 5 for testing, and AssertJ for test assertions.

It copies the dependencies to target/lib, and configures the JAR file with a main class and a classpath,
so that the resulting JAR can be run with `java -jar`.




## Building and run

`cd` to go to the folder where the application is located.

Run into the folder which the application is located `mvn clean package` and check the `target` folder.

Now, run `java -jar target/jukebox-1.0-SNAPSHOT.jar ` +path of mp3 file, for example `java -jar target/jukebox-1.0-SNAPSHOT.jar papakia.mp3` if mp3 file is in the same folder as the application, else give the absolute path of the mp3 file.

Or, run `java -jar target/jukebox-1.0-SNAPSHOT.jar papakia.mp3 loop`, if you want to play mp3 file in loop.

if you want to play a m3u list in order, run for example `java -jar target/jukebox-1.0-SNAPSHOT.jar best.m3u` or `java -jar target/jukebox-1.0-SNAPSHOT.jar best.m3u order` if m3u file is in the same folder as the application, else give the absolute path of the m3u file.


if you want to play m3u list songs random , run for example `java -jar target/jukebox-1.0-SNAPSHOT.jar best.m3u random` if m3u file is in the same folder as the application, else give the absolute path of the m3u file.






