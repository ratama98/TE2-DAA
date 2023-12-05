import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class HamiltonianDP{
    // Driver Code
    public static void main(String[] args)
    {
        int adj[][] = readFile("adjacency_matrix_20.txt");
        int N = adj.length;
    
        long startTime = System.nanoTime();
        double startMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory());

        if (Hamiltonian_path(adj, N))
            System.out.println("YES");
        else
            System.out.println("NO");
        
        double endMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory());
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("Total Running Time: " + totalTime);
        System.out.println("Total memory : " + (endMemory - startMemory));
        Runtime.getRuntime().gc();
    }

 
    // Function to check whether there
    // exists a Hamiltonian Path or not
    static boolean Hamiltonian_path(int adj[][], int N)
    {
        boolean dp[][] = new boolean[N][(1 << N)];
    
        // Set all dp[i][(1 << i)] to
        // true
        for(int i = 0; i < N; i++)
            dp[i][(1 << i)] = true;
    
        // Iterate over each subset
        // of nodes
        for(int i = 0; i < (1 << N); i++) 
        {
            for(int j = 0; j < N; j++) 
            {
                
                // If the jth nodes is included
                // in the current subset
                if ((i & (1 << j)) != 0)
                {
    
                    // Find K, neighbour of j
                    // also present in the
                    // current subset
                    for(int k = 0; k < N; k++) 
                    {
                        
                        if ((i & (1 << k)) != 0 && 
                            adj[k][j] == 1 && j != k && 
                            dp[k][i ^ (1 << j)])
                        {
                            
                            // Update dp[j][i]
                            // to true
                            dp[j][i] = true;
                            break;
                        }
                    }
                }
            }
        }
    
        // Traverse the vertices
        for(int i = 0; i < N; i++) 
        {
            
            // Hamiltonian Path exists
            if (dp[i][(1 << N) - 1])
                return true;
        }
    
        // Otherwise, return false
        return false;
    }
    
    private static int[][] readFile(String filePath) {
        List<String> lines;
        int[][] adjacencyMatrix = null;

        try {
            lines = Files.readAllLines(Paths.get(filePath));

            int numRows = lines.size();
            int numCols = lines.get(0).split("\\s*,\\s*").length;
            adjacencyMatrix = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                String[] values = lines.get(i).split("\\s*,\\s*");
                for (int j = 0; j < numCols; j++) {
                    adjacencyMatrix[i][j] = Integer.parseInt(values[j].replaceAll("[^0-9]", ""));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return adjacencyMatrix;
    }
}
 
// This code is contributed by Kingash