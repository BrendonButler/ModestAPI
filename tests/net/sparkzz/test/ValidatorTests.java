package net.sparkzz.test;

import net.sparkzz.modest.utils.Validate;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Brendon Butler
 */
public class ValidatorTests {

	@Test
	public void returnsFalseIfNotValidDecimalNumber() {
		assertTrue(!Validate.isNumber("this.that"));
	}

	@Test
	public void returnsTrueIfValidDecimalNumber() throws Exception {
		assertTrue(Validate.isDecimalNumber("2.85"));
	}

	@Test
	public void returnsFalseIfNotValidNumber() throws Exception {
		assertTrue(!Validate.isNumber("2.5"));
	}

	@Test
	public void returnsTrueIfValidNumber() throws Exception {
		assertTrue(Validate.isNumber("200"));
	}
}