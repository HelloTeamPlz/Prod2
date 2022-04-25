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
To run this project clone it from git hub and follow these steps:\
Make sure you set how large of a csv file you want to create in the generator.scala\
From prod2 folder copy the followoing files to your unix machine
```
scp -P 2222 ./insurance.csv maria_dev@sandbox-hdp.hortonworks.com:/home/maria_dev
scp -P 2222 ./names.txt maria_dev@sandbox-hdp.hortonworks.com:/home/maria_dev
scp -P 2222 ./states maria_dev@sandbox-hdp.hortonworks.com:/home/maria_dev
scp -P 2222 ./date.txt maria_dev@sandbox-hdp.hortonworks.com:/home/maria_dev
```
to set the amount of lines created in your csv change the value in the for loop in genorator.scala
```
for(i <- 1 until 5000)
```
will create a CSV with 5,000 lines\
To run generator.scala\
To Run the genorator Follow these steps:\
Step 1(start sbt):
```
cd insurance
sbt
Compile
Package
```
Step 2(Copy the JAR file to your vm)):
```
scp -P 2222 ./insurance_2.11-0.1.0-SNAPSHOT.jar maria_dev@sandbox-hdp.hortonworks.com:/home/maria_dev
```
Step 3(Run on the VM):
```
spark-submit --deploy-mode client ./insurance_2.11-0.1.0-SNAPSHOT.jar --class example.genData
```
