# Blockchain

A toy project that creates blockchains.

Author: Thomas Hawes, with code snippets obtained from the [JetBrains Academy](https://hyperskill.org/tracks)
learning platform.

This code is being developed as a solution to the Blockchain project for the
Java Core track of the educational platform [JetBrains Academy](https://hyperskill.org/tracks).
The main purpose is to showcase the author's software capabilities and
knowledge.


## Contents

[No licence](#no-licence)

[Running the app](#running-the-app)


## No licence

**This repository deliberately contains no licence.** This is because it contains
code samples taken from the JetBrains Academy platform, which are provided for
non-commercial use. References to the origin of such code samples are made in
the relevant source code files.

You are permitted to view, fork, download and run the software in this
repository, so long as these activities are for non-commercial purposes.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


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
This will generate the file in `./app/build/libs/app-0.0.5.jar`. This can then
be run in the usual way:
```shell
java -jar app/build/libs/app-0.0.5.jar
```


### Generate a distribution with run script

A third option is to generate a distribution of the application and use the
accompanying shell (or batch) script that Gradle produces. Generate a
distribution by running
```shell
./gradlew assemble
```
This will generate archives `app-0.0.5.tar` and `app-0.0.5.zip` of the
application in the directory `./app/build/distributions`. Unpack the archive of
your choice and then run the `bin/app` shell script (or `bin\app.batch` script)
within. 
