import java.awt.BorderLayout
import java.awt.Font
import java.awt.FlowLayout
import java.io.File
import javax.swing.*

class EuroConnectGUI {
    private var graph: Graph? = null
    private val mstAlgorithm = MSTAlgorithm()
    private val fileHandler = FileHandler()
    private val resultArea = JTextArea(10, 30).apply { isEditable = false }

    init {
        // Create main frame
        val frame = JFrame("EuroConnect MST Generator").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            setSize(600, 400)
        }

        // Create components
        val titleLabel = JLabel("EuroConnect MST Generator", JLabel.CENTER).apply {
            font = Font("San Francisco", Font.BOLD, 20)
        }

        val loadFileButton = JButton("Load Distance File")
        val generateMSTButton = JButton("Generate MST")
        val exportResultsButton = JButton("Export Results")

        // Add components to layout
        val buttonPanel = JPanel(FlowLayout()).apply {
            add(loadFileButton)
            add(generateMSTButton)
            add(exportResultsButton)
        }

        val scrollPane = JScrollPane(resultArea)

        // Set main layout
        frame.layout = BorderLayout()
        frame.add(titleLabel, BorderLayout.NORTH)
        frame.add(buttonPanel, BorderLayout.CENTER)
        frame.add(scrollPane, BorderLayout.SOUTH)

        // Button actions
        loadFileButton.addActionListener { loadFile() }
        generateMSTButton.addActionListener { generateMST() }
        exportResultsButton.addActionListener { exportResults() }

        // Display frame
        frame.isVisible = true
    }

    private fun loadFile() {
        val fileChooser = JFileChooser()
        val result = fileChooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val file = fileChooser.selectedFile
            try {
                graph = fileHandler.loadFile(file.absolutePath)
                resultArea.text = "File loaded successfully!\n"
                resultArea.append("Graph contains ${graph?.nodes?.size} nodes and ${graph?.edges?.size} edges.\n")
            } catch (e: Exception) {
                resultArea.text = "Error loading file: ${e.message}"
            }
        }
    }

    private fun generateMST() {
        if (graph == null) {
            resultArea.text = "Please load a file first!"
            return
        }
        try {
            val (mstEdges, totalDistance) = mstAlgorithm.kruskal(graph!!)
            resultArea.text = "Minimum Spanning Tree:\n"
            mstEdges.forEach { edge ->
                resultArea.append("${edge.capital1} - ${edge.capital2}: ${edge.distance} km\n")
            }
            resultArea.append("Total Cable Length: $totalDistance km\n")
        } catch (e: Exception) {
            resultArea.text = "Error generating MST: ${e.message}"
        }
    }

    private fun exportResults() {
        if (graph == null) {
            resultArea.text = "Please load a file first!"
            return
        }
        val fileChooser = JFileChooser()
        val result = fileChooser.showSaveDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val file = fileChooser.selectedFile
            try {
                val (mstEdges, totalDistance) = mstAlgorithm.kruskal(graph!!)
                fileHandler.saveResults(mstEdges, totalDistance, file.absolutePath)
                resultArea.text = "Results exported successfully to ${file.absolutePath}.\n"
            } catch (e: Exception) {
                resultArea.text = "Error exporting results: ${e.message}"
            }
        }
    }
}

fun main() {
    SwingUtilities.invokeLater { EuroConnectGUI() }
}


