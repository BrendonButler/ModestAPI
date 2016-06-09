package net.sparkzz.modest.utils;

/**
 * Validator class
 *
 * @author Brendon Butler
 * @since  0.1
 */
public class Validate {

	/**
	 * <p>Validates whether or not a string is a decimal number.</p>
	 *
	 * <p>See: {@link Double}, {@link Float}.</p>
	 *
	 * @param string Input {@link String}.
	 * @return {@code true} if input is a decimal number.
	 */
	public static boolean isDecimalNumber(String string) {
		if (string == null) {
			return false;
		}

		int length = string.length();

		if (length == 1 ) {
			return false;
		}

		int i = 0;

		if (string.charAt(0) == '-') {
			if (length < 3) {
				return false;
			}
			i = 1;
		}

		int numOfDot = 0;

		for (; i < length; i++) {
			char c = string.charAt(i);
			if (c == '.')
				numOfDot++;
			else if (c == '/')
				return false;
			else if (c < '.' || c > '9') {
				return false;
			}
		}

		return (numOfDot == 1);
	}

	/**
	 * <p>Validates whether or not a string is a number.</p>
	 * 
	 * @param string Input {@link String}.
	 * @return {@code true} if input is a number.
	 */
	public static boolean isNumber(String string) {
		if (string == null) return false;

		int length = string.length();

		if (length == 0) return false;

		int i = 0;

		if (string.charAt(0) == '-') {
			if (length == 1) return false;

			i = 1;
		}

		for (; i < length; i++) {
			char c = string.charAt(i);

			if (c <= '/' || c >= ':') return false;
		}
		return true;
	}

	/**
	 * <p>Validates that an {@link Object} is not null.</p>
	 *
	 * @param input Input {@link Object}.
	 * @return {@code true} if input is not null.
	 */
	public static boolean notNull(Object input) {
		return (input != null);
	}
}