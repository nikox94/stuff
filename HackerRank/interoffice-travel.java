import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // Each index 'i' contains the energy necessary to travel 'i' units.
        int[] w = new int[n];
        for(int w_i=0; w_i < n; w_i++){
            w[w_i] = in.nextInt();
        }
        
        // Upper bound on all entries in dim 2 array is n-1
        // Which means 1..10^5 which
        // fits in an int = 2^31 -1 = 2*10^9 -1 ok.
        int[][] cons = new int[n][n];
        // Initialisation
        for (int i = 0; i< n ; i++)
            for (int j = 0; j < n; j++) {
            if (i == j)
                cons[i][j] = 0;
            else
                cons[i][j] = 1_000_000; // No path
        }
        
        printArr(cons, n);
        
        for(int a0 = 0; a0 < n-1; a0++){
            // A hallway connects offices 'u' and 'v'
            int u = in.nextInt();
            int v = in.nextInt();
            // Write Your Code Here
            
            // Update distances
            int jp = (u < v ? u : v) - 1;
            int ip = (u < v ? v : u) - 1;
            cons[ip][jp] = 1;
            
            // Find indices bigger than 0 in u
            // Let's say we find e1 = 2
            // Then that means that we look if [3,2]
            // is set and bigger than the sum of the previous two
            // if so we set it. If unset we set it.
            // Then for each found, look at []
           
            // jp = 0
            for (int i = 1; i < n; i++) {
                if (i == ip)
                    continue;
                // i = 1, jp = 0, ip = 2
                if (cons[i][jp] < 100_001) { // if elem is set
                    int newDist = cons[ip][jp] + cons[i][jp];
                    if (i < ip) {
                        if (newDist < cons[ip][i])
                            cons[ip][i] = newDist;
                    } else {
                        if (newDist < cons[i][ip])
                            cons[i][ip] = newDist;
                    }
                }
            }
            
            //symmetrise
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    cons[j][i] = cons[i][j];
                }
            }
            
            
            // ip = 2
            /*for (int j = 0; j < n-1; j++) {
                if (j == jp)
                    continue;
                // j = 1, jp=0, ip=2
                if (cons[ip][j] < 100_001) { // if set
                    int newDist = cons[ip][jp] + cons[ip][j];
                    if (j < jp) {
                        if (newDist < cons[jp][j])
                            cons[jp][j] = newDist;
                    } else {
                        if (newDist < cons[j][jp])
                            cons[j][jp] = newDist;
                    }
                }
            }*/
            
            printArr(cons, n);
        }
    }
    
    public static void printArr(int[][] arr, int n) {
        for (int i = 0; i< n ; i++) {
            for (int j = 0; j < n; j++) {
              System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
