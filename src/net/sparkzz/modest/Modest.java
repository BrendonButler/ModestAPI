package net.sparkzz.modest;

import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.utils.Logger;

import java.util.List;

/**
 * Command Based Game API
 *
 * <p>Simple, yet powerful console based game/application API.</p>
 *
 * @author Brendon Butler
 * @since  0.1
 */
public class Modest {

	/**
	 * <p>Logger cache</p>
	 * <p>every time something is logged, it should be put into this cache</p
	 */
	private static List<String> logCache;

	/**
	 * <p>Default logger at the API level</p>
	 * <p>New logger should be created </p>
	 */
	private static Logger modestLogger = new Logger("ModestAPI");

	/**
	 * <p>This will save all the logs to a file, overwrites at close</p>
	 */
	public static void saveLogs() {
		FileManager.saveLog();
	}

	/**
	 *
	 * @return {@code Logger}
	 */
	public static Logger getLogger() {
		return modestLogger;
	}
}