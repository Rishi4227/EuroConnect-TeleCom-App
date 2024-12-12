# EuroConnect MST Generator

## Overview
This project is a telecommunications application developed as part of COMP1815 coursework. It calculates the Minimum Spanning Tree (MST) for connecting European capitals using Kruskal's Algorithm and provides a graphical user interface (GUI) for ease of use.

The application was developed in Kotlin and designed to be easy to run. This README provides instructions on how to set up, run, and use the application.

---

## How to Run the Code

### Option 1: Run Directly from Unzipped Folder
1. **Download and Unzip the Folder**:
   - Download the project folder provided to you.
   - Unzip the folder into a directory of your choice.

2. **Run the GUI File**:
   - Navigate to the unzipped folder.
   - Locate the `EuroConnectGUI.kt` file.
   - Open the file in IntelliJ IDEA (or another Kotlin-supported IDE).
   - Run the file directly to start the application.

---

### Option 2: Clone the Repository
1. **Clone the Repository**:
   - Use the following command to clone the repository:
     ```bash
     git clone <https://rp9977b@dev.azure.com/rp9977b/Team_CW/_git/Team_CW>
     ```


2. **Open the Project**:
   - Open the project in IntelliJ IDEA (or another Kotlin-supported IDE).
   - Make sure the project dependencies (if any) are resolved.

3. **Run the GUI File**:
   - Navigate to `src/main/kotlin`.
   - Locate the `EuroConnectGUI.kt` file.
   - Run the file to start the application.

---

## Application Features
1. **Load Distance File**:
   - Load a file containing distances between European capitals.
2. **Generate MST**:
   - Calculate the Minimum Spanning Tree (MST) and display the results.
3. **Export Results**:
   - Save the MST results to a file for future use.

---

## Prerequisites
- **Java Development Kit (JDK)**:
  - Ensure that JDK 11 or above is installed on your system.
- **Kotlin Compiler**:
  - A Kotlin-supported IDE (e.g., IntelliJ IDEA) is required to run the application.

---

## Folder Structure
- `src/`: Contains all source files, including:
  - `Graph.kt`: Defines the graph structure and edges.
  - `Edge.kt`: Represents edges in the graph.
  - `FileHandler.kt`: Handles file loading and parsing.
  - `MSTAlgorithm.kt`: Implements Kruskal's Algorithm for MST calculation.
  - `EuroConnectGUI.kt`: The main GUI application file.



