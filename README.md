# CS361_P3
# Project 3: Turing Machine Simulator

* Author: Flynn Hoare & Nick Bortz
* Class: CS361 Section 2
* Semester: Spring 2025

## Overview

This program simulates a bi-infinite Turing Machine. It reads a machine encoding and input string from a file, then simulates how the TM processes the input according to its transition rules. The goal is to accurately and efficiently model TM behavior.

## Reflection
### Flynn
This project was a change of pace with no starter files to go off of. The project as a whole made sense after some thinking, and wasn't too difficult. The part I struggled with most was understanding the input. I got a bit stuck with the way that the input files were structured and understanding how everything needed to be parsed. I enjoyed this project in particular as it was more complicated and pushed my thinking a bit more than the others.  

### Nick
This project was a little more challenging than previous projects, since this one had no
Java files to start with. It was up to us to write every interface and class as efficiently as
possible. I really enjoyed the challenge of trying to make the most efficient algorithm as possible.
I spent many hours trying to squeeze as much performance out of this program as possible, and I believe
it paid off. I had some trouble with parsing the input at first, and I was trying to overcomplicate the
programming with an input string for the tape before I realized that wasn't part of the specifications.
This project was especially interesting, in that it pushed me to be as efficient as possible with my 
programming.

## Compiling and Using

To compile, execute the following command in the main project directory:
```
javac tm/*.java
```
To run the program with a test input file use:
```
java tm.TMSimulator <input txt file>
```
User input required

## Sources used

N/A