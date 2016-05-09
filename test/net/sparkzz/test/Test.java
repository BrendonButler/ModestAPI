package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends Modest {

	private static Logger logger;

	public static void main(String[] args) {
		Console.clear();
		Console.out("Working");
		Console.outf("Working, %s", "World!");
		Console.outln("Working!");
		Console console = new Console();
		console.out("This also works!");
		console.out(Console.prompt());
	}
}