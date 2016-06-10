package net.sparkzz.modest.utils;

/**
 * Math Helper Class
 *
 * @author Brendon Butler
 * @since  0.1.3
 */
public class Math {

	/**
	 * <p>Calculate the greatest common multiple of two numbers.</p>
	 *
	 * @param first  First {@link Integer}.
	 * @param second Second {@link Integer}.
	 * @return Greatest {@link Integer} that can be divided by both inputs and be a whole number.
	 */
	public static Integer gcm(int first, int second) {
		int i = first >= second ? first : second;

		while (i != 0) {
			if (first % i == 0 && second % i == 0)
				return i;
			i--;
		}
		return 1;
	}

	/**
	 * <p>Calculate the least common multiple of two numbers.</p>
	 *
	 * @param first  First {@link Integer}.
	 * @param second Second {@link Integer}.
	 * @return Least {@link Integer} that can be divided by both inputs and be a whole number.
	 */
	public static Integer lcm(int first, int second) {
		if (first == 0 || first == 1 || second == 0 || second == 1)
			return 1;

		for (int i = 2; i <= first && i <= second; i++) {
			if (first % i == 0 && second % i == 0)
				return i;
		}
		return -1;
	}
}