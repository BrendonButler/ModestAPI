package net.sparkzz.test;

import com.google.gson.Gson;
import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.JSONConfig;
import net.sparkzz.modest.utils.Languages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void loadingJsonFromFile() {
		try {
			File tempFile = File.createTempFile("test-language", ".json");
			Gson gson = new Gson();
			Map<String, String> languageTest = new HashMap<>();

			tempFile.deleteOnExit();

			languageTest.put("Test", "Nest");
			languageTest.put("Cat", "Bat");

			FileManager.write(tempFile.getParentFile(), tempFile.getName(), gson.toJson(languageTest));

			Map<String, String> loadedResource = Languages.loadFromFile(tempFile.getAbsolutePath());

			assertEquals(languageTest, loadedResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}