package hackerrank.weekofcode32;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2MonstersAndJason {

	static int getMaxMonstersGreedy(int n, int hit, int t, int[] h) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(n);
		int numKilled = 0;

		for (int i = 0; i < n; i++) {
			queue.add(h[i]);
		}

		while (t >= 0 && !queue.isEmpty()) {
			int mMin = queue.peek();
			// How many shots will I need to kill it?
			int shotsToKill = mMin % hit == 0 ? mMin / hit : mMin / hit + 1;

			if (shotsToKill > t)
				return numKilled;

			// Kill monster
			t -= shotsToKill;
			queue.poll();
			numKilled++;
		}

		return numKilled;
	}

	static int getMaxMonsters(int n, int hit, int t, int[] h) {
		return getMaxMonstersGreedy(n, hit, t, h);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int hit = in.nextInt();
		int t = in.nextInt();
		int[] h = new int[n];
		for (int h_i = 0; h_i < n; h_i++) {
			h[h_i] = in.nextInt();
		}
		int result = getMaxMonsters(n, hit, t, h);
		System.out.println(result);
	}
}
