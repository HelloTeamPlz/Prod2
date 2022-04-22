# Insurance Application

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is a command line interface paper trader written in python. It takes real time stock data from two diffent free api's [finnhub](https://finnhub.io/) to get real time stock data. [Alphavantage](https://www.alphavantage.co) to get historical data.
	
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