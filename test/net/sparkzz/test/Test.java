package net.sparkzz.test;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 */
public class Test extends Modest {

	public static void main(String[] args) {
		log = new Logger("Test"); // creates a new logger with a title

		log.print("test");
	}
}