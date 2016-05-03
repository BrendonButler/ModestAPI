package net.sparkzz.modest;

import net.sparkzz.modest.utils.Logger;

/**
 * Created by Brendon Butler on 3/28/2016.
 *
 * v1 Command Based Game API
 */
public class ModestGame {

	private static boolean running = false;

	// (UPS) Updates Per Second | (TPS) Ticks Per Second
	private static int targetTicks = 25, ticks;
	private static long currTime, nextTime;

	public static Logger log = new Logger();

	public ModestGame() {

	}

	public ModestGame(int TPS) {
		targetTicks = TPS;
	}

	public static void init() {
		running = true; // start game

		while (running) {
			ticks = 0;
			nextTime = System.currentTimeMillis() + 1000;

			while (System.currentTimeMillis() < nextTime && ticks < targetTicks) {
				update();
				ticks++;
			}

			log.print("[UPS} %s", ticks);
		}
	}

	//
	public static void update() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}