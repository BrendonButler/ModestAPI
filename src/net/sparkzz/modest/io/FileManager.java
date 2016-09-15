package net.sparkzz.modest.io;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.utils.Logger;
import net.sparkzz.modest.utils.Validate;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * <p>File Manager, deals with IO of files.</p>
 *
 * @author Brendon Butler
 * @since  0.1
 */
public class FileManager {

	private static final Logger log = ModestGame.getDefaultLogger();
	private static File file;
	private static FileWriter writer;
	private static List<String> logCache = Collections.synchronizedList(new ArrayList<String>());

	public static FileReader read(File file) {
		try {
			if (!file.exists())
				return null;

			return new FileReader(file);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static List<String> getLogCache() {
		return logCache;
	}

	public static List<String> readByLine(File file) {
        List<String> tempList = Collections.synchronizedList(new ArrayList<String>());
        Scanner scanner;

		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine())
				tempList.add(scanner.nextLine());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return tempList;
	}

	public static void addToLogCache(String message) {
		logCache.add(message);
	}

	public static void saveLog() {
		List<String> data = log.getData();

		if (Validate.notNull(data))
			write(new File(System.getProperty("user.dir") + "/data"), "log.txt", data);
	}

	public static void write(File folder, String fileName, List<?> output) {
		try {
			file = new File(folder, fileName);

			if (!file.exists())
				file.createNewFile();

			writer = new FileWriter(file);

			for (Object line : output)
				writer.write(line.toString() + System.getProperty("line.separator"));

			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void write(File folder, String fileName, String output) {
		try {
			if (!folder.exists())
				folder.mkdir();

			file = new File(folder, fileName);

			if (!file.exists())
				file.createNewFile();

			writer = new FileWriter(file);

			writer.write(output);
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}