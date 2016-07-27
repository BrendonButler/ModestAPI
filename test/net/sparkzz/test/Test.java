package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.modest.utils.Logger;
import net.sparkzz.modest.utils.Math;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
		Config config = new YAMLConfig();

		config.load();

		Console.out(config.getString("testing.whereAreYouFrom"));

		config.set("this.is.a.test-a-rooni", "Hello World! This is ballin'");

		Console.out(config.getString("this.is.a.test-a-rooni"));

		config.save();

		interrupt();
	}
}
