package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.io.Config;
import net.sparkzz.modest.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends Modest {

	public static void main(String[] args) {
		log = new Logger("Test"); // creates a new logger with a title

		log.print("test");

		Config config = new Config(new File(System.getProperty("user.dir") + "/test"), "file-name"); // look, no extension necessary! :)

		config.load();
		log.print("" + config.getString("Horse"));

		List<String> stringList = new ArrayList(config.getList("list"));

		log.print(stringList.get(1));

		stringList.add("Hello");
		stringList.add("World");

		config.set("list", stringList);

		config.set("Horse", 10000.00);
		config.save();

		System.exit(0);
	}
}