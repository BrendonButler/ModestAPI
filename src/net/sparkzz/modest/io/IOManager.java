package net.sparkzz.modest.io;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.utils.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.*;
import java.util.*;

import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;

/**
 * Created by Brendon Butler on 5/2/2016.
 */
public class IOManager {

	private static BufferedReader reader;
	private static File file;
	private static FileWriter writer;
	private static JSONParser parser = new JSONParser();
	private static List<String> data;
	private static Logger log = Modest.getLogger();
	private static Map<String, Object> tempMap, result;
	private static String tempString;
	private static StringBuilder builder;

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
}