package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.console.Alignment;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Logger;
import net.sparkzz.modest.utils.Math;

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
		Console.out("Working!");

		Console.fillLine('=');
		Console.align(Alignment.CENTER, "ModestAPI");
		Console.fillLine('=');

		int i = 0, j = 15;
		Console.outf("GCM (Input %s & %s): %s, %s", i, j, i/Math.gcm(i, j), j/Math.gcm(i, j));

		interrupt();
	}
}