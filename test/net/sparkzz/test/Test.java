package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.io.Config;
import net.sparkzz.modest.io.JSONConfig;
import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends Modest {

	private static Logger logger;

	public static void main(String[] args) {
		logger = new Logger("Test");

		Config config = new JSONConfig();

		config.set("Horse", "DEAD HORSE D:");
		config.set("Health", 20);

		config.save();

		System.exit(0);
	}
}