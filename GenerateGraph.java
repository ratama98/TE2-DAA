import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// writeGraphToFile(graph, "graph_18.txt");
public class GenerateGraph {

    public static void main(String[] args) {
        int vertices = 20;
        int[][] graph = generateHamiltonianPathGraph(vertices);
        writeGraphToFile(graph, "graph_20.txt");

        // Print the generated adjacency matrix
        for (int i = 0; i < vertices; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }

    // Function to generate a graph with a Hamiltonian path
    public static int[][] generateHamiltonianPathGraph(int vertices) {
        int[][] graph = new int[vertices][vertices];

        // Connect vertices in a path
        for (int i = 0; i < vertices - 1; i++) {
            graph[i][i + 1] = 1;
            graph[i + 1][i] = 1;
        }

        // Connect the last and first vertices to form a cycle
        graph[vertices - 1][0] = 1;
        graph[0][vertices - 1] = 1;

        // Connect additional edges randomly to ensure connectivity
        connectRandomEdges(graph, vertices);

        return graph;
    }

    // Function to connect additional random edges to ensure connectivity
    private static void connectRandomEdges(int[][] graph, int vertices) {
        Random random = new Random();

        // Connect approximately half of the remaining edges randomly
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 2; j < vertices; j++) {
                if (random.nextBoolean()) {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }
    }

    private static void writeGraphToFile(int[][] graph, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < graph.length; i++) {
                writer.write(Arrays.toString(graph[i]));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the graph to the console
        printGraph(graph);
    }

    private static void printGraph(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }
}