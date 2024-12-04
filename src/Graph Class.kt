// Represents a graph with nodes and edges
class Graph {
    val edges = mutableListOf<Edge>() // List of edges
    val nodes = mutableSetOf<String>() // Unique set of nodes

    // Adds an edge to the graph
    fun addEdge(edge: Edge) {
        edges.add(edge)
        nodes.add(edge.capital1)
        nodes.add(edge.capital2)
    }

    // Adds an edge using node names and distance
    fun addEdge(capital1: String, capital2: String, distance: Int) {
        addEdge(Edge(capital1, capital2, distance))
    }
}
