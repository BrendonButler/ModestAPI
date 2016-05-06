package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.io.config.Config;
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

		System.out.println(config.getChar("health"));

		config.set("horse", "DEAD HORSE D:");
		config.set("health", 10);

		System.out.println(config.getInteger("health"));

		config.save();

		System.exit(0);
	}
}