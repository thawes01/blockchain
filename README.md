# Blockchain

A toy project that creates blockchains.

This is being developed as a solution to the Blockchain project for the Java
Core track of [JetBrains Academy](https://hyperskill.org/tracks).

## Running the app

_Blockchain_ comes with a [Gradle](https://gradle.org) wrapper script to build it; see
the [Gradle docs on the wrapper script](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:using_wrapper)
for more information. There are several options for building and/or running the
application. Navigate to the Blockchain root directory and execute one of the
following commands. (Note: code is given for Unix systems; for Windows, replace
calls to the executable `gradlew` with the batch script `gradlew.bat` and change
the file path syntax).


### Gradle run task

The first option is to use the Gradle run task to run _Blockchain_:
```shell
./gradlew run
```


### Generate JAR file

The second option is to generate an executable JAR file and then run it with
a local Java distribution. Generate the JAR file using the following command:
```shell
./gradlew jar
```
This will generate the file in `./app/build/libs/app-0.0.1.jar`. This can then
be run in the usual way:
```shell
java -jar app/build/libs/app-0.0.1.jar
```


### Generate a distribution with run script

A third option is to generate a distribution of the application and use the
accompanying shell (or batch) script that Gradle produces. Generate a
distribution by running
```shell
./gradlew assemble
```
This will generate archives `app-0.0.1.tar` and `app-0.0.1.zip` of the
application in the directory `./app/build/distributions`. Unpack the archive of
your choice and then run the `bin/app` shell script (or `bin\app.batch` script)
within. 
