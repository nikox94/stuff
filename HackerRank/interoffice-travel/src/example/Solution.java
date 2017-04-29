package example;

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
		for (int w_i = 0; w_i < n; w_i++) {
			w[w_i] = in.nextInt();
		}

		Graph g = new Graph(n);
		for (int a0 = 0; a0 < n - 1; a0++) {
			// A hallway connects offices 'u' and 'v'
			int u = in.nextInt();
			int v = in.nextInt();
			// Write Your Code Here
			g.addEdge(u-1, v-1);
		}
		
		List<List<Integer>> shortestPaths = g.findAllShortestPaths();
		
		int[] totEng = new int[n];
		for(int i = 0; i < shortestPaths.size(); i++) {
			List<Integer> choice = shortestPaths.get(i);
			totEng[i] = choice.stream().map(s -> w[s]).reduce((a,b) -> a+b).get();
            System.out.print(totEng[i] + " ");
		}
	}

	public static class Graph {
		public Map<Integer, Set<Integer>> adjTable;
		Integer[] dist;
		int n;

		public Graph(int n) {
			this.n = n;
			dist = new Integer[n];
			adjTable = new HashMap<>(n-1);
		}

		public void addEdge(int i, int j) {
			if (!adjTable.containsKey(i))
				adjTable.put(i, new HashSet<Integer>());
			adjTable.get(i).add(j);
			
			if (!adjTable.containsKey(j))
				adjTable.put(j, new HashSet<Integer>());
			adjTable.get(j).add(i);
		}

		// BFS should work here

		public Integer[] findShortestPaths(int source) {
			Set<Integer> traversed = new HashSet<>(n);
			Queue<Integer> q = new ArrayDeque<>();

			dist[source] = 0;
			q.add(source);
			traversed.add(source);

			while (!q.isEmpty()) {
				int v = q.remove();
				for (int neighbor : adjTable.get(v)) {
					if (!traversed.contains((Integer) neighbor)) {
						dist[neighbor] = dist[v] + 1;
						traversed.add(neighbor);
						q.add(neighbor);
					}
				}
			}

			return Arrays.copyOf(dist, n);
		}

		public List<List<Integer>> findAllShortestPaths() {
			List<List<Integer>> result = new ArrayList<>(n);
			for (int i = 0; i < n; i++) {
				result.add(Arrays.asList(findShortestPaths(i)));
			}
			return result;
		}
	}
}
