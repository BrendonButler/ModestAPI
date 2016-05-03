package net.sparkzz.modest.utils;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Logger {

	private String title = "ModestAPI";

	private void output(String message) {
		System.out.printf("%s%n", message);
	}

	private void titled(String message) {
		System.out.printf("[%s] %s%n", title, message);
	}

	public Logger() {
		title = null;
	}

	public Logger(String title) {
		this.title = title;
	}

	public void print(String message) {
		if (title == null) output(message);
		else titled(message);
	}

	public void print(String message, Object... objects) {
		print(String.format(message, objects));
	}
}