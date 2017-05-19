package hackerrank.weekofcode32;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution3CircularWalkJenAndHerFriend {

	static List<Integer> R = null;
	static int whereJenIs, timeElapsed;

	static void initR(int n, int r_0, int g, int seed, int p) {
		R = new ArrayList<>(n);
		R.add(r_0);

		for (int i = 1; i < n; i++) {
			R.add((R.get(i - 1) * g + seed) % p);
		}
	}

	static int naiveWalk(int n, int s, int t, int r_0, int g, int seed, int p) {

		whereJenIs = s;

		OUTER: while (true) {
			// Is the final square in her view? Also find largest R[i] to move
			// to if not.
			int largestRi = R.get(whereJenIs);
			int largestRiIndex = whereJenIs;
			for (int i = -R.get(whereJenIs); i <= R.get(whereJenIs); i++) {
				int possibleSpot = whereJenIs + i;

				// Stay in the circle
				if (possibleSpot < 0) {
					possibleSpot = n + possibleSpot;
				}
				possibleSpot %= n;

				// Can we reach our friend?
				if (possibleSpot == t) {
					timeElapsed++;
					break OUTER;
				}

				// Find Max
				if (largestRi < R.get(possibleSpot)) {
					largestRiIndex = possibleSpot;
					largestRi = R.get(possibleSpot);
				}
			}

			// If this fails we need to choose the largest amount we can move
			// From within the choices we have. We will take it.
			// Choose largest R[i] from available ones to move to.
			whereJenIs = largestRiIndex;
			timeElapsed++;
			// Stop if we cannot move anymore.
			if (largestRi == 0)
				return -1;
		}

		return timeElapsed;
	}
	
	static int optimalBFSWalk(int n, int s, int t, int r_0, int g, int seed, int p) {
		Queue<Integer> Q = new ArrayDeque<>(n);
		Set<Integer> S = new HashSet<>(n);
		int[] distances = new int[n];
		
		S.add(s); // Visited starting point
		Q.add(s); // Enqueue for processing
		distances[s] = 0; // Set initial position distance to 0
		
		while(!Q.isEmpty()) {
			int current = Q.poll();
			if (current == t) { // If we reached
				return distances[current];
			}
			for (int i = -R.get(current); i <= R.get(current); i++) {
				int adjacentSpot = current + i;

				// Stay in the circle
				if (adjacentSpot < 0) {
					adjacentSpot = n + adjacentSpot;
				}
				adjacentSpot %= n; // This is an "adjacent node"
				
				if (!S.contains(adjacentSpot)) { // If it was not visited
					S.add(adjacentSpot);
					Q.offer(adjacentSpot);
					distances[adjacentSpot] = distances[current] + 1;
				}
			}
		}
		
		return -1;
	}

	static int circularWalk(int n, int s, int t, int r_0, int g, int seed, int p) {
		initR(n, r_0, g, seed, p);
		return optimalBFSWalk(n, s, t, r_0, g, seed, p);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int s = in.nextInt();
		int t = in.nextInt();
		int r_0 = in.nextInt();
		int g = in.nextInt();
		int seed = in.nextInt();
		int p = in.nextInt();
		int result = circularWalk(n, s, t, r_0, g, seed, p);
		System.out.println(result);
	}
}
