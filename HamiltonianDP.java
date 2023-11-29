// Java program for the above approach
import java.io.*;
import java.lang.*;
import java.util.*;
 
class HamiltonianDP{
 
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
    
    // Driver Code
    public static void main(String[] args)
    {
        int adj[][] = { { 0, 1, 1, 1, 0 },
                        { 1, 0, 1, 0, 1 },
                        { 1, 1, 0, 1, 1 },
                        { 1, 0, 1, 0, 0 } };
        int N = adj.length;
    
        // Function Call
        if (Hamiltonian_path(adj, N))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
 
// This code is contributed by Kingash