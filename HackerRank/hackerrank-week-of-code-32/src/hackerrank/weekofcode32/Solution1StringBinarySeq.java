package hackerrank.weekofcode32;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution1StringBinarySeq {

	private static class StringSeq {
		BitSet bs = new BitSet(1024);
		boolean initialised = false;
		int maxTouchedBit = 1;

		public StringSeq() {
			while (maxTouchedBit < 1000) {
				for (int i = 0; i < maxTouchedBit; i++) {
					bs.set(maxTouchedBit + i, !bs.get(i));
				}
				maxTouchedBit *= 2;
			}
			initialised = true;
		}

		public String get(int x) {
			return bs.get(x) ? "1" : "0";
		}
	}

	static StringSeq member = new StringSeq();

	/**
	 * Dynamically grow the array so that we get only as much of it as we need.
	 * Even though it is quite small so we could do it "online" as well. Cache
	 * the result.
	 */
	static String duplication(int x) {
		return member.get(x);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int x = in.nextInt();
			String result = duplication(x);
			System.out.println(result);
		}
	}
}
