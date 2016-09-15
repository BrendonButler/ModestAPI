package net.sparkzz.modest.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	public static Map<String, String> loadFromFile(String path) {
		tryLoad: try {
			if (path.toLowerCase().endsWith(".json")) {
				Gson gson = new Gson();

				File saveFile = new File(path);

				if (!saveFile.exists()) break tryLoad;

				JsonReader reader = new JsonReader(new FileReader(saveFile));
				return new LinkedHashMap(gson.fromJson(reader, new TypeToken<Map<String, String>>() {}.getType()));
			} else if (path.toLowerCase().endsWith(".yaml") || path.toLowerCase().endsWith(".yml")) {
				// Yaml Parser
			} else if (path.toLowerCase().endsWith(".txt")) {
				// read text file
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

	public static void setLanguage(String locale) {
		activeLocale = locale;
	}
}