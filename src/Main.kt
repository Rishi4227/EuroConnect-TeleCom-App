import java.io.File
fun main() {
    print("Enter the file path: ")
    val filePath = readLine()
    if (!filePath.isNullOrBlank()) {
        loadFileAndCount(filePath.trim())
    } else {
        println("Invalid file path!")
    }
}



// Function to count nodes and edges from a file
fun loadFileAndCount(filePath: String) {

    val nodes = mutableSetOf<String>()
    var edgeCount = 0

    try {
        File(filePath).forEachLine { line ->
            val parts = line.split(",")
            if (parts.size == 3) {
                val capital1 = parts[0].trim()
                val capital2 = parts[1].trim()
                nodes.add(capital1)
                nodes.add(capital2)
                edgeCount++
            }
        }

        println("File loaded successfully!")
        println("The graph contains ${nodes.size} nodes and $edgeCount edges.")
    } catch (e: Exception) {
        println("Error loading file: ${e.message}")
    }
}