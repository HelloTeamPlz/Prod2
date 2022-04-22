# Insurance Application

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project contains two parts: a data generator that creates a csv file with random data in it. It also contains an analysis file that takes information from a csv and analyzes the information as well as some functions to take the data in the csv and graph it.
	
## Technologies
Project is created with:
* java.io._
* scala.io.Source
* java.util.Properties
* scala.util.Random
* scala.collection.JavaConverters._
	
## Setup
To run this project clone it from git hub and follow these steps:
Make sure you set how large of a csv file you want to create in the generator.scala
```
for(i <- 1 until 5000)
```
will create a CSV with 5,000 lines

To run generator.scala
Step 1:
```
cd Prod2/insurance
```
Step 2(Start SBT):
```
sbt
```
Step 3(Run/Compile/Package):
```
run
compile 
package
```
