package net.sparkzz.test;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.io.config.Config;
import net.sparkzz.modest.io.config.YAMLConfig;
import net.sparkzz.modest.io.console.Console;
import net.sparkzz.modest.utils.Languages;
import net.sparkzz.modest.utils.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		File tempFile = new File(System.getProperty("user.dir") + "/data/test-language.txt");
		List<String> output = new LinkedList<>();

		Map<String, String> languageTest = new HashMap<>();

		languageTest.put("Test", "Nest");
		languageTest.put("Cat", "Bat");

		for (Map.Entry<String, String> entry : languageTest.entrySet())
			output.add(String.format("%s=\"%s\"", entry.getKey(), entry.getValue()));

		tempFile.deleteOnExit();

		FileManager.write(tempFile.getParentFile(), tempFile.getName(), output);

		Map<String, String> loadedResource = Languages.loadFromFile(tempFile.getAbsolutePath());

		Console.outln(loadedResource.toString());

		interrupt();
	}
}
