package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
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
		System.out.println("Working!");
	}
}