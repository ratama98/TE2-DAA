import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateGraph {

    public static void main(String[] args) {
        int vertices = 18;
        int[][] graph = generateHamiltonianPathGraph(vertices);
        writeGraphToFile(graph, "graph_18.txt");

        for (int i = 0; i < vertices; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }

    public static int[][] generateHamiltonianPathGraph(int vertices) {
        int[][] graph = new int[vertices][vertices];

        List<Integer> vertexOrder = generateRandomVertexOrder(vertices);

        for (int i = 0; i < vertices - 1; i++) {
            int currentVertex = vertexOrder.get(i);
            int nextVertex = vertexOrder.get(i + 1);
            graph[currentVertex][nextVertex] = 1;
            graph[nextVertex][currentVertex] = 1;
        }

        graph[vertexOrder.get(vertices - 1)][vertexOrder.get(0)] = 1;
        graph[vertexOrder.get(0)][vertexOrder.get(vertices - 1)] = 1;

        connectRandomEdges(graph, vertices);

        return graph;
    }

    private static List<Integer> generateRandomVertexOrder(int vertices) {
        List<Integer> order = new ArrayList<>();
        for (int i = 1; i < vertices - 1; i++) {
            order.add(i);
        }

        Collections.shuffle(order);

        order.add(0, 0); 
        order.add(vertices - 1); 

        return order;
    }

    private static void connectRandomEdges(int[][] graph, int vertices) {
        Random random = new Random();

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

        printGraph(graph);
    }

    private static void printGraph(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }
}