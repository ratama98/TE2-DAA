/* Java program for solution of Hamiltonian Path problem
   using backtracking */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HamiltonianBacktracking
   {
       final int V = 20;
       int path[];

       // driver program to test above function
       public static void main(String args[])
       {
            int graph[][] = readFile("graph_20.txt");

            long startTime = System.nanoTime();
            double startMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory());
            
            HamiltonianBacktracking hamiltonian =
                                   new HamiltonianBacktracking();

            hamiltonian.hamPath(graph);

            double endMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory());
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("Total Running Time: " + totalTime);
            System.out.println("Total memory : " + (endMemory - startMemory));
            Runtime.getRuntime().gc();
       }
   
       /* A utility function to check if the vertex v can be
          added at index 'pos'in the Hamiltonian Path
          constructed so far (stored in 'path[]') */
       boolean isSafe(int v, int graph[][], int path[], int pos)
       {
           /* Check if this vertex is an adjacent vertex of
              the previously added vertex. */
           if (graph[path[pos - 1]][v] == 0)
               return false;
   
           /* Check if the vertex has already been included.
              This step can be optimized by creating an array
              of size V */
           for (int i = 0; i < pos; i++)
               if (path[i] == v)
                   return false;
   
           return true;
       }
   
       /* A recursive utility function to solve hamiltonian
          path problem */
       boolean hamPathUtil(int graph[][], int path[], int pos)
       {
           /* base case: If all vertices are included in
              Hamiltonian Path */
           if (pos == V)
           {
               return true;
           }
   
           // Try different vertices as a next candidate in
           // Hamiltonian Path. We don't try for 0 as we
           // included 0 as starting point in hamPath()
           for (int v = 1; v < V; v++)
           {
               /* Check if this vertex can be added to Hamiltonian
                  Path */
               if (isSafe(v, graph, path, pos))
               {
                   path[pos] = v;
   
                   /* recur to construct rest of the path */
                   if (hamPathUtil(graph, path, pos + 1) == true)
                       return true;
   
                   /* If adding vertex v doesn't lead to a solution,
                      then remove it */
                   path[pos] = -1;
               }
           }
   
           /* If no vertex can be added to Hamiltonian Path
              constructed so far, then return false */
           return false;
       }
   
       /* This function solves the Hamiltonian Path problem using
          Backtracking. It mainly uses hamPathUtil() to solve the
          problem. It returns false if there is no Hamiltonian Path
          possible, otherwise return true and prints the path.
          Please note that there may be more than one solutions,
          this function prints one of the feasible solutions. */
       int hamPath(int graph[][])
       {
           path = new int[V];
           for (int i = 0; i < V; i++)
               path[i] = -1;
   
           /* Let us put vertex 0 as the first vertex in the path.
              If there is a Hamiltonian Path, then the path can be
              started from any point of the Path as the graph is
              undirected */
           path[0] = 0;
           if (hamPathUtil(graph, path, 1) == false)
           {
               System.out.println("\nSolution does not exist");
               return 0;
           }
   
           printSolution(path);
           return 1;
       }
   
       /* A utility function to print solution */
       void printSolution(int path[])
       {
           System.out.println("Solution Exists: Following" +
                              " is one Hamiltonian Path");
           for (int i = 0; i < V; i++)
               System.out.print(" " + path[i] + " ");
            System.out.println();
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
   // This code is contributed by Abhishek Shankhadhar