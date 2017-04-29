package example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
	public static void main (String... args) {
		Random rand = new Random();
		
		int n = 100_00;
		
		StringBuilder sb = new StringBuilder();
		sb.append(n + "\n");
		List<Integer> w = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			w.add(rand.nextInt(10_000));
			sb.append(w.get(i) + " ");
		}
		System.out.println(sb.toString());
		
		
		Solution.Graph g = new Solution.Graph(n);
		
		List<Integer> unconnected = new ArrayList<>(n);
		List<Integer> connected = new ArrayList<>(n);
		
		connected.add(0);
		for (int i = 1; i < n; i++)
			unconnected.add(i);
		
		while (!unconnected.isEmpty()) {
			Integer i = unconnected.get(0);
			Collections.shuffle(connected, rand);
			g.addEdge(connected.get(0), i);
			System.out.println(connected.get(0) + " " + i);
			unconnected.remove((Integer) i);
			connected.add(i);
		}

		// Calculate final answer
		long times = System.currentTimeMillis();
		g.findAllShortestPaths();
		long timee = System.currentTimeMillis();
		System.out.println(timee - times);
	}
}
