package net.sparkzz.test;

import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Languages;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Brendon Butler
 */
public class LanguageTests {

	@Before
	public void setUp() throws Exception {
		Languages.addLanguage("censor", Arrays.asList("poop", "butt"), Arrays.asList("bad word", "#replaced"));
		Languages.setLanguage("censor");
	}

	@Test
	public void addingNewLanguageViaArrays() {
		Languages.addLanguage("french", Arrays.asList("Hello"), Arrays.asList("Bonjour"));
		Languages.setLanguage("french");

		assertEquals("Bonjour", Languages.localize("Hello"));
	}

	@Test
	public void addingNewLanguageViaMap() {
		Map french = new HashMap();

		french.put("Hi", "Salut");

		Languages.addLanguage("french", french);

		assertEquals("Salut!", Languages.localize("french", "Hi!"));
	}
}