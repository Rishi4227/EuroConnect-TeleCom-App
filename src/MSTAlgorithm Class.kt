
// Implements Kruskal's algorithm to calculate the Minimum Spanning Tree
class MSTAlgorithm {

    fun kruskal(graph: Graph): Pair<List<Edge>, Int> {
        val mstEdges = mutableListOf<Edge>()
        val disjointSet = DisjointSet(graph.nodes) // Disjoint set to manage connected components

        // Sort edges by distance
        val sortedEdges = graph.edges.sortedBy { it.distance }
        var totalDistance = 0

        // Iterate through edges
        for (edge in sortedEdges) {
            if (disjointSet.union(edge.capital1, edge.capital2)) {  // Add edge if it does not form a cycle
                mstEdges.add(edge) // Add the edge to the MST
                totalDistance += edge.distance
            }
        }

        return Pair(mstEdges, totalDistance)
    }

    // Helper class: Manages disjoint sets for cycle detection
    private class DisjointSet(nodes: Set<String>) {
        private val parent = mutableMapOf<String, String>()

        // Initialize the disjoint set
        init {
            for (node in nodes) {
                parent[node] = node
            }
        }

        // Finds the root of a node
        fun find(node: String): String {
            if (parent[node] != node) { // If the node is not its own parent
                parent[node] = find(parent[node]!!) // Recursively find and update the root
            }
            return parent[node]!! // Return the root of the node
        }

        // Unites two nodes and returns true if they were not already connected
        fun union(node1: String, node2: String): Boolean {
            val root1 = find(node1)
            val root2 = find(node2)

            if (root1 == root2) return false
            parent[root1] = root2
            return true
        }
    }
}
