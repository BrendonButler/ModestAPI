package net.sparkzz.modest;

import net.sparkzz.modest.io.IOManager;
import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 *
 * v1 Command Based Game API
 */
public class Modest {

	private static boolean running = false;

	public static Logger log = new Logger("ModestAPI");

	public static void init() {

	}

	public static void save() {
		IOManager.saveLog();
	}
}