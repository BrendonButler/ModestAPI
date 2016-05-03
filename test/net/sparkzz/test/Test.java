package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.io.Config;
import net.sparkzz.modest.utils.Logger;

import java.util.Map;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends Modest {

	public static void main(String[] args) {
		log = new Logger("Test"); // creates a new logger with a title

		log.print("test");

		Config config = new Config(); // look, no extension necessary! :)

		config.load();
		log.print("" + config.getString("Horse"));


		Map<String, Object> map = config.getValues();

		log.print("" + map.get("list"));

		config.set("Horse", 10000.00);
		config.save();

		System.exit(0);
	}
}