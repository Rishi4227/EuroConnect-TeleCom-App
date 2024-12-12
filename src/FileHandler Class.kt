/*
import java.io.File

// Handles file input and output operations
class FileHandler {

    // Loads a graph from a CSV file
    fun loadFile(filePath: String): Graph {
        val graph = Graph()
        File(filePath).forEachLine { line ->
            val parts = line.split(",")
            if (parts.size == 3) {
                val capital1 = parts[0].trim()
                val capital2 = parts[1].trim()
                val distance = parts[2].trim().toInt()
                graph.addEdge(capital1, capital2, distance)
            }
        }
        return graph
    }

    // Saves MST results to a file
    fun saveResults(edges: List<Edge>, totalDistance: Int, filePath: String) {
        File(filePath).printWriter().use { out ->
            out.println("Edge List:")
            edges.forEach { edge ->
                out.println("${edge.capital1} - ${edge.capital2}: ${edge.distance} km")
            }
            out.println("Total Distance: $totalDistance km")
        }
    }
}
*/


import java.io.File

// Handles file input and output operations
class FileHandler {

    // Loads a graph from a CSV file
    fun loadFile(filePath: String): Graph {
        val graph = Graph()  // Create an empty graph object
        File(filePath).forEachLine { line ->  // Read each line from the file
            val parts = line.split(",")  // Split the line by commas
            if (parts.size == 3) {  // Ensure the line has exactly three components
                val capital1 = parts[0].trim()  // First capital
                val capital2 = parts[1].trim()  // Second capital
                val distance = parts[2].trim().toInt()  // Distance between the capitals
                graph.addEdge(capital1, capital2, distance)  // Add the edge to the graph
            }
        }
        return graph  // Return the populated graph
    }

    // Saves MST results to a file
    fun saveResults(edges: List<Edge>, totalDistance: Int, filePath: String) {
        File(filePath).printWriter().use { out ->  // Open the file for writing and also (use) block to ensure it close after writing
            out.println("Edge List:")  // Header for the edge list
            edges.forEach { edge ->  // Write each edge to the file
                out.println("${edge.capital1} - ${edge.capital2}: ${edge.distance} km")
            }
            out.println("Total Distance: $totalDistance km")  // Write the total distance
        }
    }
}
