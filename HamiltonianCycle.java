/* Java program for solution of Hamiltonian Cycle problem
   using backtracking */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class HamiltonianBacktracking
   {
       final int V = 20;
       int path[];

       // driver program to test above function
       public static void main(String args[])
       {
            int graph[][] = readFile("graph_20.txt");

            
            HamiltonianBacktracking hamiltonian =
                                   new HamiltonianBacktracking();
   
            long startTime = System.nanoTime();
            double startMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory());

            hamiltonian.hamCycle(graph);

            double endMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory());
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("Total Running Time: " + totalTime);
            System.out.println("Total memory : " + (endMemory - startMemory));
            Runtime.getRuntime().gc();
       }
   
       /* A utility function to check if the vertex v can be
          added at index 'pos'in the Hamiltonian Cycle
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
          cycle problem */
       boolean hamCycleUtil(int graph[][], int path[], int pos)
       {
           /* base case: If all vertices are included in
              Hamiltonian Cycle */
           if (pos == V)
           {
               // And if there is an edge from the last included
               // vertex to the first vertex
               if (graph[path[pos - 1]][path[0]] == 1)
                   return true;
               else
                   return false;
           }
   
           // Try different vertices as a next candidate in
           // Hamiltonian Cycle. We don't try for 0 as we
           // included 0 as starting point in hamCycle()
           for (int v = 1; v < V; v++)
           {
               /* Check if this vertex can be added to Hamiltonian
                  Cycle */
               if (isSafe(v, graph, path, pos))
               {
                   path[pos] = v;
   
                   /* recur to construct rest of the path */
                   if (hamCycleUtil(graph, path, pos + 1) == true)
                       return true;
   
                   /* If adding vertex v doesn't lead to a solution,
                      then remove it */
                   path[pos] = -1;
               }
           }
   
           /* If no vertex can be added to Hamiltonian Cycle
              constructed so far, then return false */
           return false;
       }
   
       /* This function solves the Hamiltonian Cycle problem using
          Backtracking. It mainly uses hamCycleUtil() to solve the
          problem. It returns false if there is no Hamiltonian Cycle
          possible, otherwise return true and prints the path.
          Please note that there may be more than one solutions,
          this function prints one of the feasible solutions. */
       int hamCycle(int graph[][])
       {
           path = new int[V];
           for (int i = 0; i < V; i++)
               path[i] = -1;
   
           /* Let us put vertex 0 as the first vertex in the path.
              If there is a Hamiltonian Cycle, then the path can be
              started from any point of the cycle as the graph is
              undirected */
           path[0] = 0;
           if (hamCycleUtil(graph, path, 1) == false)
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
                              " is one Hamiltonian Cycle");
           for (int i = 0; i < V; i++)
               System.out.print(" " + path[i] + " ");
   
           // Let us print the first vertex again to show the
           // complete cycle
           System.out.println(" " + path[0] + " ");
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