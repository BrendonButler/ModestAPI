package net.sparkzz.modest;

import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.io.console.Console;
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
public class ModestGame {

	/**
	 * <p>Current ticks per second</p>
	 */
	private int TPS;

	/**
	 * <p>Next update time</p>
	 */
	private long nextTime;

	/**
	 * <p>Default logger, should only be accessed by API classes</p>
	 *
	 * @return {@link Logger}
	 */
	public static Logger getDefaultLogger() {
		return modestLogger;
	}

	/**
	 * <p>Default logger at the API level</p>
	 * <p>New logger should be created</p>
	 */
	private static Logger modestLogger = new Logger("ModestAPI");

	/**
	 * <p>Logger cache</p>
	 * <p>every time something is logged, it should be put into this cache</p
	 */
	private List<String> logCache;

	/**
	 * <p>Variable that indicates whether or not the program is running</p>
	 */
	public boolean running = true;

	/**
	 * <p>Variable that indicates whether or not to save logs</p>
	 */
	public boolean saveLogs = true;

	/**
	 * <p>Maximum ticks per second (TPS) for the run loop</p>
	 */
	private int maxTicks = 10;

	/**
	 * <p>Can be overwritten to return sub-class logger</p>
	 *
	 * @return {@link Logger}
	 */
	public Logger getLogger() {
		return null;
	}

	/**
	 * <p>To be run after initial setup is complete</p>
	 * <p>Will automatically be called unless the {@code run()} method is overridden</p>
	 */
	public void postInit() {
		Console.out("Running");
	}

	/**
	 * <p>The initial setup of the program</p>
	 * <p>Will be automatically called unless the {@code run()} method is overridden</p>
	 */
	public void init() {

	}

	/**
	 * <p>This will continuously loop the {@code postInit()} method</p>
	 * <p>This method doesn't need to be accessed/overrode for programs that don't need a run loop</p>
	 */
	public void run() {
		init();

		while (running) {
			TPS = 0;
			nextTime = System.currentTimeMillis() + 1000;

			while (System.currentTimeMillis() < nextTime) {
				if (TPS < maxTicks) {
					postInit();
					TPS++;
				}
			}

			Console.outf("TPS: %s", TPS);
		}

		if (saveLogs)
			saveLogs();
	}

	/**
	 * <p>This will save all the logs to a file, overwrites at close</p>
	 */
	public void saveLogs() {
		FileManager.saveLog();
	}
}