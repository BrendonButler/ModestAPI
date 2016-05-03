package net.sparkzz.modest.utils;

import java.util.logging.Level;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Logger {

	private String title = "ModestAPI";

	public Logger(String title) {
		this.title = title;
	}

	public void info(String message) {
		log(Level.INFO, message);
	}

	public void info(String... messages) {
		log(Level.INFO, messages);
	}

	public void infof(String message, Object... objects) {
		log(Level.INFO, String.format(message, objects));
	}

	public void log(Level level, String message) {
		System.out.printf("[%s][%s] %s%n", title, level.getLocalizedName(), message);
	}

	public void log(Level level, String... messages) {
		for (String message : messages) {
			log(level, message);
		}
	}

	public void logf(Level level, String message, Object... objects) {
		log(level, String.format(message, objects));
	}

	public void severe(String message) {
		log(Level.SEVERE, message);
	}

	public void severe(String... messages) {
		log(Level.SEVERE, messages);
	}

	public void severef(String message, Object... objects) {
		log(Level.SEVERE, String.format(message, objects));
	}

	public void warn(String message) {
		log(Level.WARNING, message);
	}

	public void warn(String... messages) {
		log(Level.WARNING, messages);
	}

	public void warnf(String message, Object... objects) {
		log(Level.WARNING, String.format(message, objects));
	}
}