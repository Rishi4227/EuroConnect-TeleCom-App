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
