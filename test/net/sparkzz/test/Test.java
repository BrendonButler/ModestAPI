package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Logger;

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

		config.setProtection(false);

		config.set("First.Second.Third", 3);
		config.set("Player.Health", 20);
		config.set("Player.Max_Health", 20);
		config.set("Player.Is_Alive", true);
		config.set("Player.Weapons.Axe", true);
		config.set("Player.Weapons.Gun", false);

		Console.outln("" + config.getInteger("Player.Max_Health"));

		Console.outln(config.getKeys().toString());

		config.save();

		interrupt();
	}
}
