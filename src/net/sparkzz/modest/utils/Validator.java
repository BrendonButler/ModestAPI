package net.sparkzz.modest.utils;

/**
 * @author Brendon Butler
 * @since  0.1
 */
public class Validator {

	public static boolean isDecimalNumber(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		if (length == 1 ) {
			return false;
		}

		int i = 0;

		if (str.charAt(0) == '-') {
			if (length < 3) {
				return false;
			}
			i = 1;
		}

		int numOfDot = 0;

		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c == '.')
				numOfDot++;
			else if (c == '/')
				return false;
			else if (c < '.' || c > '9') {
				return false;
			}
		}

		if (numOfDot != 1 )
			return false;
		return true;
	}

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
}