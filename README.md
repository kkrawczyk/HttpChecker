#HttpChecker

##Introduction

Java application to checking website response status. It can format output in various ways (provided by OutputFormatters) so it can be used in example as Nagios plugin.

##Compiling and building jar package
To compile sources you'll need installed java jdk 1.7 and maven2 on your system.
Build package command:

```bash
mvn clean install assembly:single
```

Above command produces target directory with two jars:
* HttpChecker-[version].jar jar without dependencies
* HttpChecker-[version]-jar-with-dependencies.jar jar with dependencies

To generate javadoc in target/javadoc use command:

```bash
mvn javadoc:aggregate
```

##Basic usage
HttpChecker in version 1.0 is compiled with NagiosOutputFormatter and acts as a nagios plugin. It can be executed with one url provided in command line or with url filelist (each url has to be in separate line).

Run command:

```bash
java -jar HttpChecker-<version>-jar-with-dependencies.jar [options]
```

|Option|Function|
| ----------| ----------:|
|-h | prints help|
|-u [url] | checks one url|
|-f [filename] | checks all urls from the file|


