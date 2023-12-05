import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GenerateAdjMatrix {

    public static void main(String[] args) {
        int vertices = 20;
        int[][] adjacencyMatrix = generateHamiltonianPath(vertices);

        // Output the matrix to a text file
        writeMatrixToFile(adjacencyMatrix, "adjacency_matrix_18.txt");

        // Print the matrix to the console
        printMatrix(adjacencyMatrix);
    }

    private static int[][] generateHamiltonianPath(int vertices) {
        int[][] adjacencyMatrix = new int[vertices-1][vertices];

        for (int i = 0; i < vertices - 2; i++) {
            adjacencyMatrix[i][i + 1] = 1;
            adjacencyMatrix[i + 1][i] = 1;
        }

        adjacencyMatrix[vertices - 2][0] = 1;
        adjacencyMatrix[0][vertices - 1] = 1;

        return adjacencyMatrix;
    }

    private static void writeMatrixToFile(int[][] matrix, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int[] row : matrix) {
                writer.write(Arrays.toString(row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}