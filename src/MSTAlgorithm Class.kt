// Implements Kruskal's algorithm to calculate the Minimum Spanning Tree
class MSTAlgorithm {

    fun kruskal(graph: Graph): Pair<List<Edge>, Int> {
        val mstEdges = mutableListOf<Edge>()
        val disjointSet = DisjointSet(graph.nodes)

        // Sort edges by distance
        val sortedEdges = graph.edges.sortedBy { it.distance }
        var totalDistance = 0

        // Iterate through edges
        for (edge in sortedEdges) {
            if (disjointSet.union(edge.capital1, edge.capital2)) {
                mstEdges.add(edge)
                totalDistance += edge.distance
            }
        }

        return Pair(mstEdges, totalDistance)
    }

    // Helper class: Manages disjoint sets for cycle detection
    private class DisjointSet(nodes: Set<String>) {
        private val parent = mutableMapOf<String, String>()

        init {
            for (node in nodes) {
                parent[node] = node
            }
        }

        // Finds the root of a node
        fun find(node: String): String {
            if (parent[node] != node) {
                parent[node] = find(parent[node]!!)
            }
            return parent[node]!!
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
