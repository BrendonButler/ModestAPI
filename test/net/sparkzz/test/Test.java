package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Logger;
import net.sparkzz.modest.utils.Math;

import java.io.File;
import java.util.Random;

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
		Config jsonConfig = new JSONConfig(new File(System.getProperty("user.dir") + "/data"), "conflig");
		Config yamlConfig = new YAMLConfig();

		Console.outf("JSON Config: %s", jsonConfig.get("test"));
		Console.outf("Yaml Config: %s", yamlConfig.get("test"));

		Random rand = new Random();

		jsonConfig.set("test", rand.nextInt(100));
		yamlConfig.set("test", rand.nextLong());

		jsonConfig.save();
		yamlConfig.save();

		saveLogs();
	}
}