package net.sparkzz.modest.io;

import net.sparkzz.modest.Modest;
import net.sparkzz.modest.utils.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Brendon Butler on 5/2/2016.
 */
public class IOManager {

	private static File file;
	private static FileWriter writer;
	private static JSONParser parser = new JSONParser();
	private static Logger log = Modest.log;

	public static void write(File folder, String fileName, JSONObject object) {
		try {
			if (!folder.exists())
				folder.mkdir();

			file = new File(folder, fileName + ".json");

			if (!file.exists()) {
				file.createNewFile();
			}

			writer = new FileWriter(file);

			writer.write(object.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static JSONObject read(File file) {
		try {
			if (!file.exists()) {
				log.print("File doesn't exist!");
				return new JSONObject();
			}

			log.print(file.toString());
			return (JSONObject) parser.parse(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}