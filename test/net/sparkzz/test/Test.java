package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends Modest {

	private static Logger logger;

	public static void main(String[] args) {
		logger = new Logger("Test");

		Config config = new YAMLConfig();

		config.set("Horse", "DEAD HORSE D:");
		config.set("Health", 20);

		System.out.println(config.getBoolean("Health"));

		config.save();

		System.exit(0);
	}
}