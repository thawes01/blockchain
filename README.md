# Blockchain

A toy project for creating blockchains.

Author: Thomas Hawes, with code snippets obtained from the
[JetBrains Academy](https://hyperskill.org/tracks) learning platform.


## Contents

* [Overview](#overview)
  - [Purpose of the project](#purpose-of-the-project)
  - [Skills demonstrated](#skills-demonstrated)
  - [Example output](#example-output)

* [No licence](#no-licence)

* [Running the app](#running-the-app)


## Overview

### Purpose of the project

This code is being developed as a solution to the Blockchain project for the
Java Core track of the educational platform [JetBrains Academy](https://hyperskill.org/tracks).
The main purpose is to showcase the author's software capabilities and
knowledge.

### Skills demonstrated

* Test driven development was used to develop the code.
* Unit testing with Junit 5. Some tests also feature mocking with Mockito.
* Basic use of Gradle for automated builds and dependency management.


### Example output

The _Blockchain_ application generates blockchains, printing information about
them to standard output. Each block in the blockchain has an ID, a timestamp
representing the time the block was created, the SHA-256 hash of the previous
block in the chain and the hash of the block itself. An example of the output
is as follows:

```text
Block:
Id: 0
Timestamp: 1645649962310
Hash of the previous block:
0
Hash of the block:
4c23fd2ca2878297a485de75aff812261678be3696d9a1c7b01482b434e7944f

Block:
Id: 1
Timestamp: 1645649962331
Hash of the previous block:
4c23fd2ca2878297a485de75aff812261678be3696d9a1c7b01482b434e7944f
Hash of the block:
7db091e487c1b68b46c2c5e29a52087859ff146075ad8f47927a3d25736def17

Block:
Id: 2
Timestamp: 1645649962332
Hash of the previous block:
7db091e487c1b68b46c2c5e29a52087859ff146075ad8f47927a3d25736def17
Hash of the block:
0139dc5e1bca0a56e7c00019e426e09d9f6b9828c0bb3630108f586286975104

Block:
Id: 3
Timestamp: 1645649962332
Hash of the previous block:
0139dc5e1bca0a56e7c00019e426e09d9f6b9828c0bb3630108f586286975104
Hash of the block:
0922bfc06b59ca5822070a807195ed784a7919a0aa9d51434bce56f7907e29a6

Block:
Id: 4
Timestamp: 1645649962332
Hash of the previous block:
0922bfc06b59ca5822070a807195ed784a7919a0aa9d51434bce56f7907e29a6
Hash of the block:
3ee148b0b67ca5c1856dc95307faea0fa110d7ea6561b3db155357176921abab

```

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


### Run with Gradle

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
This will generate the file in `./app/build/libs/app-0.0.12.jar`, which can be
run in the usual way:
```shell
java -jar app/build/libs/app-0.0.12.jar
```


### Generate a distribution with executable

A third option is to generate a distribution of the application and use the
accompanying executable shell (or batch) script that Gradle produces. Generate a
distribution by running
```shell
./gradlew assemble
```
This will generate archives `app-0.0.12.tar` and `app-0.0.12.zip` of the
application in the directory `./app/build/distributions`. Unpack the archive of
your choice and then run the `bin/app` shell script (or `bin\app.batch` script)
within. 
