package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends ModestGame {

	private static Logger logger;

	public Test() {
		run();
	}

	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void postInit() {
		Console.out("Working!");

		Console.fillLine('=');
		Console.align(Alignment.CENTER, "ModestAPI");
		Console.fillLine('=');
	}
}