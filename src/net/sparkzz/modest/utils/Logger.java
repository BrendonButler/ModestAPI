package net.sparkzz.modest.utils;

import net.sparkzz.modest.io.FileManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * <p>This class is used to log data to a file or to the console if debugging is enabled.</p>
 *
 * @author Brendon Butler
 * @since  0.1
 */
public class Logger {

	/** <p>If {@code true}, output log to console.</p> */
	private boolean debug = false;
	/** <p>Current date used for logging purposes.</p> */
	private Date date;
	/** <p>Date format to be outputted.</p>*/
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	/** <p>Logger title, will be displayed before each log message to distinguish what is logging.</p>*/
	private String title = "ModestAPI";

	/**
	 * <p>Logger constructor.</p>
	 *
	 * @param title Used as the title prefix for logs.
	 */
	public Logger(String title) {
		this.title = title;
	}

	/**
	 * <p>Gets the current log cache.</p>
	 *
	 * @return {@link List} of this runtime's logs.
	 */
	public List<String> getData() {
		return FileManager.getLogCache();
	}

	/**
	 * <p>Sets the logger's debug mode.</p>
	 *
	 * @param value Used to set the debug mode.
	 */
	public void debug(boolean value) {
		debug = value;
	}

	/**
	 * <p>Logs an info message.</p>
	 *
	 * @param message Messages that are displayed.
	 */
	public void info(String message) {
		log(Level.INFO, message);
	}

	/**
	 * <p>Logs multiple info messages.</p>
	 *
	 * @param messages Message that is displayed.
	 */
	public void info(String... messages) {
		log(Level.INFO, messages);
	}

	/**
	 * <p>Logs a formatted info message.</p>
	 *
	 * @param message Message that is displayed.
	 * @param objects Regex objects.
	 */
	public void infof(String message, Object... objects) {
		log(Level.INFO, String.format(message, objects));
	}

	/**
	 * <p>Logs a message using a supplied logging level.</p>
	 *
	 * @param level   Level of severity of the message.
	 * @param message Message that is displayed.
	 */
	public void log(Level level, String message) {
		if (debug)
			System.out.printf("[%s][%s] %s%n", title, level.getLocalizedName(), message);

		date = new Date();
		FileManager.addToLogCache(String.format("[%s][%s][%s] %s%n", sdf.format(date), title, level.getLocalizedName(), message));
	}


	/**
	 * <p>Logs multiple messages using a supplied logging level.</p>
	 *
	 * @param level    Level of severity of the message.
	 * @param messages Messages that are displayed.
	 */
	public void log(Level level, String... messages) {
		for (String message : messages) {
			log(level, message);
		}
	}

	/**
	 * <p>Logs a formatted message using a supplied logging level.</p>
	 *
	 * @param level   Level of severity of the message.
	 * @param message Message that is displayed.
	 * @param objects Regex objects.
	 */
	public void logf(Level level, String message, Object... objects) {
		log(level, String.format(message, objects));
	}

	/**
	 * <p>Logs a severe message.</p>
	 *
	 * @param message Message that is displayed.
	 */
	public void severe(String message) {
		log(Level.SEVERE, message);
	}

	/**
	 * <p>Logs multiple severe messages.</p>
	 *
	 * @param messages Messages that are displayed.
	 */
	public void severe(String... messages) {
		log(Level.SEVERE, messages);
	}

	/**
	 * <p>Logs a formatted severe message.</p>
	 *
	 * @param message Message that is displayed.
	 * @param objects Regex objects.
	 */
	public void severef(String message, Object... objects) {
		log(Level.SEVERE, String.format(message, objects));
	}

	/**
	 * <p>Logs a warning message.</p>
	 *
	 * @param message Message that is displayed.
	 */
	public void warn(String message) {
		log(Level.WARNING, message);
	}

	/**
	 * <p>Logs multiple warning messages.</p>
	 *
	 * @param messages Messages that are displayed.
	 */
	public void warn(String... messages) {
		log(Level.WARNING, messages);
	}

	/**
	 * <p>Logs a formatted warning message.</p>
	 *
	 * @param message Message that is displayed.
	 * @param objects Regex objects.
	 */
	public void warnf(String message, Object... objects) {
		log(Level.WARNING, String.format(message, objects));
	}
}