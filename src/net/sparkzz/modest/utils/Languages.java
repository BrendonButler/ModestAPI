package net.sparkzz.modest.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.io.console.Console;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brendon Butler
 * @since 1.0.2
 */
public class Languages extends Validate {

	private static Map<String, Map<String, String>> locales = Collections.synchronizedMap(new HashMap<>());
	private static String activeLocale;

	public static List<String> getAvailable() {
		return new LinkedList<>(locales.keySet());
	}

	public static Map<String, String> loadFromFile(String path) {
		tryLoad: try {
			File saveFile = new File(path);

			if (path.toLowerCase().endsWith(".json")) {
				Gson gson = new Gson();

				if (!saveFile.exists()) break tryLoad;

				JsonReader reader = new JsonReader(new FileReader(saveFile));
				return new LinkedHashMap(gson.fromJson(reader, new TypeToken<Map<String, String>>() {}.getType()));
			} else if (path.toLowerCase().endsWith(".yaml") || path.toLowerCase().endsWith(".yml")) {
				FileReader reader = FileManager.read(saveFile);
				Yaml yaml = new Yaml();

				if (!saveFile.exists() || reader == null) break tryLoad;

				return new LinkedHashMap((Map) yaml.load(reader));
			} else if (path.toLowerCase().endsWith(".txt")) {
				List<String> lines = FileManager.readByLine(saveFile);
				Map<String, String> data = new HashMap<>();
				String[] keyValuePair;

				for (String line : lines) {
					keyValuePair = line.split("=", 2);
					data.put(keyValuePair[0], keyValuePair[1].substring(1, keyValuePair[1].length() - 1));
				}

				return data;
			}
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static String localize(String input) {
		return localize(activeLocale, input);
	}

	public static String localize(String locale, String input) {
		if (locales.isEmpty() || locale.equals("") || !locales.containsKey(locale))
			return input;

		Map<String, String> regexMap = locales.get(locale);

		Pattern pattern = Pattern.compile("(" + String.join("|", regexMap.keySet()) + ")");
		Matcher matcher = pattern.matcher(input);

		StringBuffer buffer = new StringBuffer();

		while (matcher.find())
			matcher.appendReplacement(buffer, regexMap.get(matcher.group(1)));

		matcher.appendTail(buffer);

		return buffer.toString();
	}

	public static void addLanguage(String locale, List<String> regex, List<String> translations) {
		Map<String, String> output = new HashMap<>();

		int i = 0;

		for (String reg : regex) {
			output.put(reg, translations.get(i));
			i++;
		}

		locales.put(locale, output);
	}

	public static void addLanguage(String locale, Map<String, String> regexMap) {
		locales.put(locale, regexMap);
	}

	public static void loadAllFromFile() {
		loadAllFromFile(System.getProperty("user.dir") + "/data/locales");
	}

	public static void loadAllFromFile(String path) {
		File[] files = new File(path).listFiles();
		int index = 0;
		String fileName;

		for (File file : files) {
			fileName = file.getName();

			addLanguage(fileName.substring(0, fileName.lastIndexOf('.')), loadFromFile(file.getAbsolutePath()));
		}
	}

	public static void setLanguage(String locale) {
		activeLocale = locale;
	}
}