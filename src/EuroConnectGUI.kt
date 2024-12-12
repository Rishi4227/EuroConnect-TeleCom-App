
import java.awt.BorderLayout
import java.awt.Font
import java.awt.FlowLayout
import java.io.File
import javax.swing.*

// Main class for the EuroConnect GUI
class EuroConnectGUI {
    // declare variables for the graph, MST algorithm, file handler, and result display area
    private var graph: Graph? = null        // The graph object to hold the data
    private val mstAlgorithm = MSTAlgorithm()       // MST algorithm instance
    private val fileHandler = FileHandler()     // File handler for loading and saving
    private val resultArea = JTextArea(10, 30).apply { isEditable = false }     // Text area for displaying results


    // Initialization block to set up the GUI
    init {
        // Create main frame
        val frame = JFrame("EuroConnect MST Generator").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE    // Set close operation
            setSize(600, 400)
        }

        // Create components
        val titleLabel = JLabel("EuroConnect MST Generator", JLabel.CENTER).apply {
            font = Font("San Francisco", Font.BOLD, 20) // Set font for the title
        }

        // Buttons for different actions
        val loadFileButton = JButton("Load Distance File")  // Button to load a file
        val generateMSTButton = JButton("Generate MST")  // Button to generate MST
        val exportResultsButton = JButton("Export Results")  // Button to export results


        // Add components to layout
        val buttonPanel = JPanel(FlowLayout()).apply {
            add(loadFileButton)  // Add "Load File" button
            add(generateMSTButton)  // Add "Generate MST" button
            add(exportResultsButton)  // Add "Export Results" button
        }

        val scrollPane = JScrollPane(resultArea)    // Scroll pane for the result area (in case of long output)

        // Set main layout
        frame.layout = BorderLayout()
        frame.add(titleLabel, BorderLayout.NORTH)  // Add title label at the top
        frame.add(buttonPanel, BorderLayout.CENTER)  // Add button panel in the center
        frame.add(scrollPane, BorderLayout.SOUTH)  // Add scrollable result area at the bottom
        // Button actions
        loadFileButton.addActionListener { loadFile() }  // Action for "Load File"
        generateMSTButton.addActionListener { generateMST() }  // Action for "Generate MST"
        exportResultsButton.addActionListener { exportResults() }  // Action for "Export Results"

        // Display frame
        frame.isVisible = true
    }

    private fun loadFile() {
        val fileChooser = JFileChooser()  // Open file chooser dialog
        val result = fileChooser.showOpenDialog(null)  // Show open dialog
        if (result == JFileChooser.APPROVE_OPTION) {  // If the user selects a file
            val file = fileChooser.selectedFile  // Get the selected file
            try {
                // Load the graph from the selected file
                graph = fileHandler.loadFile(file.absolutePath)
                resultArea.text = "File loaded successfully!\n"  // Notify success
                resultArea.append("Graph contains ${graph?.nodes?.size} nodes and ${graph?.edges?.size} edges.\n")  // Display graph details
            } catch (e: Exception) {
                resultArea.text = "Error loading file: ${e.message}"  // Display error message
            }
        }
    }

    // Method to generate and display the Minimum Spanning Tree (MST)
    private fun generateMST() {
        if (graph == null) {  // Check if a graph has been loaded
            resultArea.text = "Please load a file first!"  // Prompt user to load a file
            return
        }
        try {
            // Use Kruskal's algorithm to generate the MST
            val (mstEdges, totalDistance) = mstAlgorithm.kruskal(graph!!)
            resultArea.text = "Minimum Spanning Tree:\n"  // Display header
            mstEdges.forEach { edge ->
                resultArea.append("${edge.capital1} - ${edge.capital2}: ${edge.distance} km\n")  // List MST edges
            }
            resultArea.append("Total Cable Length: $totalDistance km\n")  // Display total cable length
        } catch (e: Exception) {
            resultArea.text = "Error generating MST: ${e.message}"  // Display error message
        }
    }

    // Method to export MST results to a file
    private fun exportResults() {
        if (graph == null) {  // Check if a graph has been loaded
            resultArea.text = "Please load a file first!"  // Prompt user to load a file
            return
        }
        val fileChooser = JFileChooser()  // Open save file dialog
        val result = fileChooser.showSaveDialog(null)  // Show save dialog
        if (result == JFileChooser.APPROVE_OPTION) {  // If the user selects a file
            val file = fileChooser.selectedFile  // Get the selected file path
            try {
                // Use Kruskal's algorithm to get the MST
                val (mstEdges, totalDistance) = mstAlgorithm.kruskal(graph!!)
                // Save the results to the specified file
                fileHandler.saveResults(mstEdges, totalDistance, file.absolutePath)
                resultArea.text = "Results exported successfully to ${file.absolutePath}.\n"  // Notify success
            } catch (e: Exception) {
                resultArea.text = "Error exporting results: ${e.message}"  // Display error message
            }
        }
    }
}

// Main function to run the GUI
fun main() {
    SwingUtilities.invokeLater { EuroConnectGUI() }  // Start the GUI on the event-dispatch thread
}


