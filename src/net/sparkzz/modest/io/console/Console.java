package net.sparkzz.modest.io.console;

import net.sparkzz.modest.utils.Languages;
import net.sparkzz.modest.utils.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Console Controller</p>
 *
 * @author Brendon Butler
 * @since  0.1.1
 */
public class Console extends Languages {

	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static int maximumChars = 80;

	public static String prompt() {
		return prompt("> ");
	}

	public static String prompt(String prompt) {
		String tempString;
		prompt = localize(prompt);

		if (!notNull(prompt))
			System.out.println();
		else Console.out(prompt);

		tempString = null;

		while (!notNull(tempString)) {
			try {
				tempString = reader.readLine();

				if (!notNull(tempString) || tempString.equals(""))
					tempString = null;
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		return tempString;
	}

	public static String promptBlank() {
		return prompt("");
	}

	public static String promptf(String prompt, Object regex) {
		return prompt(localize(String.format(prompt, regex)));
	}

	public static void align(Alignment alignment, String string) {
		align(alignment, localize(string), ' ');
	}

	public static void align(Alignment alignment, String string, char divider) {
		int remaining = maximumChars;
		
		if (!(string.length() >= maximumChars)) {
			switch(alignment) {
				case CENTER:
					boolean extra = false;
					remaining -= string.length();

					if ((remaining % 2) != 0) {
						remaining -= 1;
						extra = true;
					}

					remaining /= 2;

					while (remaining != 0) {
						string = String.format("%s%s%s", divider, localize(string), divider);
						remaining -= 1;
					}

					if (extra) string += divider;

					out(string);
					break;
				case LEFT:
					remaining -= string.length();

					if (divider != ' ') {
						while (remaining != 0) {
							string = String.format("%s%s", localize(string), divider);
							remaining -= 1;
						}
					}

					out(string);
					break;
				case RIGHT:
					remaining -= string.length();
					
					while (remaining != 0) {
						string = String.format("%s%s", divider, localize(string));
						remaining -= 1;
					}
					
					out(string);
					break;
				default:
					break;
			}
		}
	}

	// TODO: need to find a better way of handling this
	public static void clear() {
		for (int tempInt = 1; tempInt < 50; tempInt++)
			outln();
	}

	public static void fillLine(char character) {
		int remaining = maximumChars;
		String result = "";

		while (remaining != 0) {
			result = String.format("%s%s", result, character);
			remaining -= 1;
		}

		out(result);
	}


	public static void out(String output) {
		outf(output);
	}

	public static void outf(String output, Object... regex) {
		if (output != null)
			System.out.println(localize(String.format(output, regex)));
	}

	public static void outln() {
		outln("");
	}

	public static void outln(String output) {
		System.out.println(localize(output));
	}

	public static void quit() {
		System.exit(0);
	}

	public static void quit(int status) {
		System.exit(status);
	}

	public static void setMaxChars(int maxChars) {
		maximumChars = maxChars;
	}
}