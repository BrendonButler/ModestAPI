package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.modest.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends ModestGame {

	private static Logger logger;

	private Test() {
		run();
	}

	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void init() {
		setTicks(1);
	}

	@Override
	public void postInit() {
		Config config = new YAMLConfig();

		config.load();

		Console.out(config.getString("testing.whereAreYouFrom"));

		config.set("testing.times", 99);
		config.set("this.is.a.test", "Hello World! This is ballin'");

		Languages languages = new Languages();

		Map testMap = new HashMap<>();

		testMap.put("Hello World! This is ballin'", ".. or is it?");
		testMap.put("A little land far, far away!", "AHH");

		languages.addLanguage("test", testMap);
		languages.setLanguage("test");

		Console.out("A little land far, far away!");
		Console.out(config.getString("this.is.a.test-a-rooni"));

		config.save();

		interrupt();
	}
}
