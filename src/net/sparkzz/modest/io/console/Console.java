package net.sparkzz.modest.io.console;

import net.sparkzz.modest.utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Brendon Butler
 * @since  0.1.1
 */
public class Console extends Validator {

	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static String prompt() {
		return prompt("> ");
	}

	public static String prompt(String prompt) {

		String tempString;

		if (!notNull(prompt))
			System.out.println();
		else System.out.printf(prompt);

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
		return prompt(String.format(prompt, regex));
	}

	// TODO: need to find a better way of handling this
	public static void clear() {
			for (int tempInt = 1; tempInt < 50; tempInt++) {
				outln();
			}
	}

	public static void out(String output) {
		outf(output);
	}

	public static void outf(String output, Object... regex) {
		System.out.println((String.format(output, regex)));
	}

	public static void outln() {
		System.out.println();
	}

	public static void outln(String output) {
		System.out.println(output);
	}

	public static void quit() {
		System.exit(0);
	}

	public static void quit(int status) {
		System.exit(status);
	}
}