package net.sparkzz.modest.utils;

import com.sun.deploy.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brendon Butler
 * @since 1.0.2
 */
public class Languages extends Validate {

	private static Map<String, Map<String, String>> locales = Collections.synchronizedMap(new HashMap<>());
	private static String activeLocale;

	public static String localize(String input) {
		return localize(activeLocale, input);
	}

	public static String localize(String locale, String input) {
		if (locales.isEmpty() || locale.equals("") || !locales.containsKey(locale))
			return input;

		Map<String, String> regexMap = locales.get(activeLocale);

		Pattern pattern = Pattern.compile("(" + StringUtils.join(regexMap.keySet(), "|") + ")");
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