package net.sparkzz.test;

import org.junit.Test;

import static net.sparkzz.modest.utils.Math.*;
import static org.junit.Assert.*;

/**
 * @author Brendon Butler
 */
public class MathTests {

	@Test
	public void greatestCommonDenominator() throws Exception {
		assertEquals(9, (int) gcd(18, 27));
	}

	@Test
	public void leastCommonDenominator() throws Exception {
		assertEquals(2, (int) lcd(2, 8));
	}

	@Test
	public void leastCommonMultiple() throws Exception {
		assertEquals(30, (int) lcm(6, 10));
	}
}