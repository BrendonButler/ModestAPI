package net.sparkzz.modest.utils;

/**
 * Math Helper Class
 *
 * @author Brendon Butler
 * @since  0.1.3
 */
public class Math {

	// TODO:butler make gcd, lcd, lcm take multiple integers

	/**
	 * <p>Calculate the greatest common denominator of two numbers.</p>
	 *
	 * @param first  First {@link Integer}.
	 * @param second Second {@link Integer}.
	 * @return Greatest {@link Integer} that can be divided by both inputs and be a whole number.
	 */
	public static Integer gcd(int first, int second) {
		int i = first >= second ? first : second;

		while (i != 0) {
			if (first % i == 0 && second % i == 0)
				return i;
			i--;
		}
		return 1;
	}

	/**
	 * <p>Calculate the least common denominator of two numbers, excluding 1.</p>
	 *
	 * @param first  First {@link Integer}.
	 * @param second Second {@link Integer}.
	 * @return Least {@link Integer} that can be divided by both inputs and be a whole number.
	 */
	public static Integer lcd(int first, int second) {
		if (first == 0 || first == 1 || second == 0 || second == 1)
			return 1;

		for (int i = 2; i <= first && i <= second; i++) {
			if (first % i == 0 && second % i == 0)
				return i;
		}
		return -1;
	}

	/**
	 * <p>Calculate the least common denominator of two numbers, excluding 1.</p>
	 *
	 * @param first  First {@link Integer}.
	 * @param second Second {@link Integer}.
	 * @return Least {@link Integer} found by multiplying the two numbers together, then dividing by the greatest common denominator.
	 */
	public static Integer lcm(int first, int second) {
		return first * second / gcd(first, second);
	}
}