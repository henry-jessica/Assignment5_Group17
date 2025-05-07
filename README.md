# Airport Flight Network Optimisation – Dijkstra and A* Algorithms
**COMP47500 – Advanced Data Structures in Java**  
**Group 17 – Jessica Henry, John Hughes, Ritika Verma**

## Overview
This project implements a graph-based optimisation system for computing efficient flight routes between airports. Using Dijkstra’s and A* algorithms, the system finds shortest paths in a directed, weighted graph representing a flight network. The project showcases both theoretical analysis and practical implementation with empirical testing on graphs of various sizes.

## Features
- Custom implementation of Graph and MinHeap structures in Java
- Implementation of Dijkstra's Algorithm for shortest path computation
- Implementation of A* Algorithm with heuristic support
- Adjacency list-based graph representation
- Defensive programming techniques for robustness
- Experimental benchmarking for time complexity and performance
- UML class, state, and activity diagrams for system architecture
- Scalability tests up to 500 nodes and 5000 edges

## Technologies
- Java 17
- JUnit (for testing)
- Custom MinHeap and Graph data structures

## Setup Instructions
1. Clone the repository:
    ```bash
    git clone https://github.com/henry-jessica/Assignment5_Group17
    cd Assignment5_Group17
    ```

2. Compile the Java source files:
    ```bash
    javac src/*.java
    ```

3. Run the program:
    ```bash
    java Main
    ```

4. Run tests:
    ```bash
    java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore GraphTest
    ```

## Usage
The application reads a set of airports and weighted flights, then computes and prints the optimal path using both Dijkstra’s and A* algorithms. Modify the main class to test different graphs or change the heuristic.

## Results
- A* consistently visits 30–40% fewer nodes than Dijkstra for sparse, goal-directed graphs.
- Performance scales linearly with input size.
- Heuristic quality significantly impacts A* performance.

## Contributors
- Jessica Henry – jessica.henry@ucdconnect.ie
- John Hughes – john.hughes@ucdconnect.ie
- Ritika Verma – ritika.verma@ucdconnect.ie

## License
This project is licensed for academic use under UCD’s coursework submission guidelines.
