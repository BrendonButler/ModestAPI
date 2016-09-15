package net.sparkzz.test;

import com.google.gson.Gson;
import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.utils.Languages;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Brendon Butler
 */
public class LanguageTests {

	public Gson gson;
	public Map<String, String> languageTest;
	public Yaml yaml;

	@Before
	public void setUp() throws Exception {
		languageTest = new HashMap<>();

		languageTest.put("Test", "Nest");
		languageTest.put("Cat", "Bat");

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
		Languages.addLanguage("test", languageTest);

		assertEquals("Bat!", Languages.localize("test", "Cat!"));
	}

	@Test
	public void loadingAllFromDirectory() {
		File test1, test2, test3;
		List<String> output = new LinkedList<>();

		for (Map.Entry<String, String> entry : languageTest.entrySet())
			output.add(String.format("%s=\"%s\"", entry.getKey(), entry.getValue()));

		gson = new Gson();
		test1 = new File(System.getProperty("user.dir") + "/data/locales/test1.txt");
		test2 = new File(System.getProperty("user.dir") + "/data/locales/test2.json");
		test3 = new File(System.getProperty("user.dir") + "/data/locales/test3.yml");
		yaml = new Yaml();


		FileManager.write(test1.getParentFile(), test1.getName(), output);
		FileManager.write(test2.getParentFile(), test2.getName(), gson.toJson(languageTest));
		FileManager.write(test3.getParentFile(), test3.getName(), yaml.dump(languageTest));

		Languages.loadAllFromFile(test1.getParent());

		test1.delete();
		test2.delete();
		test3.delete();

		assertEquals("test1-txt", "Nest", Languages.localize("test1", "Test"));
		assertEquals("test2-json", "Nest", Languages.localize("test2", "Test"));
		assertEquals("test3-yml", "Nest", Languages.localize("test3", "Test"));
	}

	@Test
	public void loadingFromTextFile() {
		try {
			File tempFile = File.createTempFile("test-language", ".txt");
			List<String> output = new LinkedList<>();

			for (Map.Entry<String, String> entry : languageTest.entrySet())
				output.add(String.format("%s=\"%s\"", entry.getKey(), entry.getValue()));

			tempFile.deleteOnExit();

			FileManager.write(tempFile.getParentFile(), tempFile.getName(), output);

			Map<String, String> loadedResource = Languages.loadFromFile(tempFile.getAbsolutePath());

			assertEquals(languageTest, loadedResource);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void loadingFromJsonFile() {
		try {
			File tempFile = File.createTempFile("test-language", ".json");
			gson = new Gson();

			tempFile.deleteOnExit();

			FileManager.write(tempFile.getParentFile(), tempFile.getName(), gson.toJson(languageTest));

			Map<String, String> loadedResource = Languages.loadFromFile(tempFile.getAbsolutePath());

			assertEquals(languageTest, loadedResource);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void loadingFromYamlFile() {
		try {
			File tempFile = File.createTempFile("test-language", ".yaml");
			yaml = new Yaml();

			tempFile.deleteOnExit();

			FileManager.write(tempFile.getParentFile(), tempFile.getName(), yaml.dump(languageTest));

			Map<String, String> loadedResource = Languages.loadFromFile(tempFile.getAbsolutePath());

			assertEquals(languageTest, loadedResource);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}