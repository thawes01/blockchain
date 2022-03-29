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

* [Dependencies](#dependencies)

* [Running the app](#running-the-app)

  - [Run with Gradle](#run-with-gradle)
  - [Generate JAR file](#generate-jar-file)
  - [Generate a distribution with executable](#generate-a-distribution-with-executable)


## Overview

### Purpose of the project

This code is being developed as a solution to the Blockchain project for the
Java Core track of the educational platform [JetBrains Academy](https://hyperskill.org/tracks).
The main purpose is to showcase the author's software capabilities and
knowledge.

### Skills demonstrated

* The author is currently learning test driven development and elements of this
  practice have been applied in the development of the code.
* Unit testing with Junit 5. Some tests also feature mocking with Mockito.
* Basic use of Gradle for automated builds and dependency management.


### Example output

The _Blockchain_ application generates blockchains, printing information about
them to standard output. Each block in the blockchain has an ID, a timestamp
representing the time the block was created (as the number of milliseconds since
the standard Java epoch 1970-01-01 00:00:00), the SHA-256 hash of the previous
block in the chain and the hash of the block itself.

A _proof of work_ number is supplied by the user at the beginning of the
application. This is the number of zeros each block's hash should begin with, in
order to prove the work done to find a block to add to the blockchain. Each
block has a _magic number_ – a number found to make the block's hash
satisfy this proof of work criteria. The time taken to generate the block is
also displayed.

An example of the output is as follows:

```text
Enter how many zeros the hash must start with: 5

Block:
Id: 0
Timestamp: 1648548985747
Magic number: 110846
Hash of the previous block:
0
Hash of the block:
00000b866187b67ecc142c9bfe6317cc8e05c53605ca9db2f44e42ae6129703d
Block was generating for 0 seconds

Block:
Id: 1
Timestamp: 1648548985936
Magic number: 1094612
Hash of the previous block:
00000b866187b67ecc142c9bfe6317cc8e05c53605ca9db2f44e42ae6129703d
Hash of the block:
00000d41f9da43e9588c5aceda599a01f55a98f2bea232c51db80e357c981ebf
Block was generating for 1 seconds

Block:
Id: 2
Timestamp: 1648548987125
Magic number: 261068
Hash of the previous block:
00000d41f9da43e9588c5aceda599a01f55a98f2bea232c51db80e357c981ebf
Hash of the block:
00000b42eff5ca8e2fd83fdbe9ebe199ad7966be18a80eb0b0c03e5118a325c0
Block was generating for 0 seconds

Block:
Id: 3
Timestamp: 1648548987405
Magic number: 679056
Hash of the previous block:
00000b42eff5ca8e2fd83fdbe9ebe199ad7966be18a80eb0b0c03e5118a325c0
Hash of the block:
00000f7db971a0350aa8f8ffff4711772c2d5d12295bb89b24217a0d1c6dfa44
Block was generating for 1 seconds

Block:
Id: 4
Timestamp: 1648548988129
Magic number: 2423074
Hash of the previous block:
00000f7db971a0350aa8f8ffff4711772c2d5d12295bb89b24217a0d1c6dfa44
Hash of the block:
000008fba38ef646ca7a90db6d200cd35d3c52a4645898fe79f65949c7818482
Block was generating for 3 seconds
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


## Dependencies

A local installation of a Java Development Kit is required to run _Blockchain_.


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
This will generate the file in `./app/build/libs/app-0.2.0-dev.jar`, which can be
run in the usual way:
```shell
java -jar app/build/libs/app-0.2.0-dev.jar
```


### Generate a distribution with executable

A third option is to generate a distribution of the application and use the
accompanying executable shell (or batch) script that Gradle produces. Generate a
distribution by running
```shell
./gradlew assemble
```
This will generate archives `app-0.2.0-dev.tar` and `app-0.2.0-dev.zip` of the
application in the directory `./app/build/distributions`. Unpack the archive of
your choice and then run the `bin/app` shell script (or `bin\app.batch` script)
within. 
