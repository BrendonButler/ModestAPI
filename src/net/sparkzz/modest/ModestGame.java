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
public abstract class ModestGame {

	/**
	 * <p>Tells the run loop whether to initialize again or not.</p>
	 */
	private static boolean runOnce = false;

	/**
	 * <p>Maximum ticks per second (TPS) for the run loop.</p>
	 */
	private static int maxTicks = 10;

	/**
	 * <p>Default logger at the API level.</p>
	 * <p>New logger should be created.</p>
	 */
	private static Logger modestLogger = new Logger("ModestAPI");

	/**
	 * <p>Has the program initialized?</p>
	 */
	private boolean hasInitialized = false;

	/**
	 * <p>Variable that indicates whether or not the program is running.</p>
	 */
	private boolean running = true;

	/**
	 * <p>Variable that indicates whether or not to save logs.</p>
	 */
	private boolean saveLogs = true;

	/**
	 * <p>Current ticks per second.</p>
	 */
	private int TPS;

	/**
	 * <p>Logger cache</p>
	 * <p>every time something is logged, it should be put into this cache.</p>
	 */
	private List<String> logCache;

	/**
	 * <p>Next update time.</p>
	 */
	private long nextTime;

	/**
	 * <p>Sets the program to run the {@code init()} method only once.</p>
	 *
	 * @param value Input {@link Boolean}.
	 */
	protected static void runOnce(boolean value) {
		runOnce = value;
	}

	/**
	 * <p>This will set the max ticks of the program (default 10).</p>
	 *
	 * @param ticks Input {@link Integer}.
	 */
	protected static void setTicks(int ticks) {
		maxTicks = ticks;
	}

	/**
	 * <p>Can be overwritten to return sub-class logger.</p>
	 *
	 * @return {@link Logger}.
	 */
	protected Logger getLogger() {
		return null;
	}

	/**
	 * <p>To be run after initial setup is complete.</p>
	 * <p>Will automatically be called unless the {@code run()} method is overridden.</p>
	 */
	protected abstract void postInit();

	/**
	 * <p>The initial setup of the program.</p>
	 * <p>Will be automatically called unless the {@code run()} method is overridden.</p>
	 */
	protected abstract void init();

	/**
	 * <p>This will halt the run loop.</p>
	 */
	protected void interrupt() {
		running = false;
	}

	/**
	 * <p>This will continuously loop the {@code postInit()} method.</p>
	 * <p>This method doesn't need to be accessed/overrode for programs that don't need a run loop.</p>
	 */
	protected void run() {
		if (!(runOnce && hasInitialized))
			init();

		hasInitialized = true;

		while (running) {
			TPS = 0;
			nextTime = System.currentTimeMillis() + 1000;

			while (System.currentTimeMillis() < nextTime) {
				if (TPS < maxTicks) {
					postInit();
					TPS++;
				}
			}

			// Console.outf("TPS: %s", TPS);
		}

		if (saveLogs)
			saveLogs();
	}

	/**
	 * <p>This will save all the logs to a file, overwrites at close.</p>
	 */
	protected void saveLogs() {
		FileManager.saveLog();
	}

	/**
	 * <p>Default logger, should only be accessed by API classes.</p>
	 *
	 * @return The default (API Level) {@link Logger}.
	 */
	public static Logger getDefaultLogger() {
		return modestLogger;
	}
}