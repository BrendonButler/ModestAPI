package net.sparkzz.modest.io;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.utils.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

/**
 * @author Brendon Butler
 * @since  0.1
 */
public class FileManager {

	private static BufferedReader reader;
	private static File file;
	private static FileWriter writer;
	private static JSONParser parser = new JSONParser();
	private static List<String> data,
					logCache = Collections.synchronizedList(new ArrayList<String>()),
					tempList;
	private static Logger log = ModestGame.getDefaultLogger();
	private static Scanner scanner;

	public static JSONObject readJSON(File file) {
		try {
			if (!file.exists()) {
				log.warn("JSON configuration file doesn't exist!");
				return new JSONObject();
			}

			return (JSONObject) parser.parse(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getLogCache() {
		return logCache;
	}

	public static List<String> readTXT(File file) {
		tempList = Collections.synchronizedList(new ArrayList<String>());
		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				tempList.add(scanner.nextLine());
			}

			return tempList;
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> readYAML(File file, Yaml yaml) {
		try {
			if (!file.exists()) {
				log.warn("YAML configuration file doesn't exist");
				return null;
			}

			return Collections.synchronizedMap((Map < String, Object >) yaml.load(new FileReader(file)));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static void addToLogCache(String message) {
		logCache.add(message);
	}

	public static void saveLog() {
		data = log.getData();

		if (data != null) {
			try {
				file = new File(System.getProperty("user.dir") + "/data/log.txt");

				if (!file.exists())
					file.createNewFile();

				writer = new FileWriter(file);

				for (String line : data)
					writer.write(line);

				writer.flush();
				writer.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	public static void write(File folder, String fileName, JSONObject object) {
		try {
			if (!folder.exists())
				folder.mkdir();

			file = new File(folder, fileName + ".json");

			if (!file.exists())
				file.createNewFile();

			writer = new FileWriter(file);

			writer.write(object.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void write(File folder, String fileName, String string) {
		try {
			if (!folder.exists())
				folder.mkdir();

			file = new File(folder, fileName + ".yaml");

			if (!file.exists())
				file.createNewFile();

			writer = new FileWriter(file);

			writer.write(string);
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}