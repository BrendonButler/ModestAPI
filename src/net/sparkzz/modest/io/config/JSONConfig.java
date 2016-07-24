package net.sparkzz.modest.io.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.utils.Validate;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * <p>JSon Configuration</p>
 *
 * @author Brendon Butler
 * @since  0.1
 */
public class JSONConfig extends Validate implements Config {

	private File configLocation;
	private JsonReader reader;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private Map<String, Object> data;
	private Object tempObject;
	private String fileName;

	public JSONConfig() {
		configLocation = new File(System.getProperty("user.dir") + "/data");
		fileName = "config.json";
		load();
	}

	public JSONConfig(File folder, String fileName) {
		configLocation = folder;
		this.fileName = fileName + ".json";
		load();
	}

	public boolean contains(String key) {
		return data.containsKey(key);
	}

	public boolean exists() {
		return data != null;
	}

	public boolean getBoolean(String key) {
		tempObject = get(key);

		if (tempObject instanceof Boolean)
			return (Boolean) tempObject;
		if (tempObject instanceof String)
			if (tempObject.toString().equalsIgnoreCase("true") || tempObject.toString().equalsIgnoreCase("1"))
				return true;
			else if (tempObject.toString().equalsIgnoreCase("false") || tempObject.toString().equalsIgnoreCase("0"))
				return false;
		if (tempObject instanceof Number)
			if (((Number) tempObject).intValue() == 1)
				return true;
		return false;
	}

	public boolean hasValue(String key) {
		tempObject = data.get(key);

		return tempObject != null;
	}

	public boolean isEmpty() {
		return data.isEmpty() || data == null;
	}

	public byte getByte(String key) {
		tempObject = get(key);

		if (tempObject instanceof Byte)
			return (Byte) tempObject;
		if (tempObject instanceof String)
			if (isNumber(tempObject.toString()))
				return Byte.parseByte(tempObject.toString());
		if (tempObject instanceof Number)
			if (tempObject instanceof Double || tempObject instanceof Float)
				return (byte) Double.parseDouble(tempObject.toString());
			else return Byte.parseByte(tempObject.toString());
		return -1;
	}

	public Collection<?> getCollection(String key) {
		tempObject = get(key);

		if (tempObject instanceof Collection<?>)
			return (Collection) tempObject;
		return null;
	}

	public char getChar(String key) {
		tempObject = get(key);

		if (tempObject instanceof Character)
			return (Character) tempObject;
		if (tempObject instanceof String)
			return tempObject.toString().charAt(0);
		if (tempObject instanceof Number)
			return tempObject.toString().charAt(0);
		return '\u0000';
	}

	public double getDouble(String key) {
		tempObject = get(key);

		if (tempObject instanceof Double)
			return (Double) tempObject;
		if (tempObject instanceof String)
			if (isDecimalNumber(tempObject.toString()))
				return Double.parseDouble(tempObject.toString());
		if (tempObject instanceof Number)
			return Double.parseDouble(tempObject.toString());
		return -1;
	}

	public int getInteger(String key) {
		tempObject = get(key);

		if (tempObject instanceof Integer)
			return (Integer) tempObject;
		if (tempObject instanceof String)
			if (isNumber(tempObject.toString()))
				return Integer.parseInt(tempObject.toString());
		if (tempObject instanceof Number)
			if (tempObject instanceof Double || tempObject instanceof Float)
				return (int) Double.parseDouble(tempObject.toString());
			else return Integer.parseInt(tempObject.toString());
		return -1;
	}

	public List<?> getList(String key) {
		tempObject = get(key);

		if (tempObject instanceof List<?>)
			return (List) tempObject;
		return null;
	}

	public long getLong(String key) {
		tempObject = get(key);

		if (tempObject instanceof Long)
			return (Long) tempObject;
		if (tempObject instanceof String)
			if (isNumber(tempObject.toString()))
				return Long.parseLong(tempObject.toString());
		if (tempObject instanceof Number)
			if (tempObject instanceof Double || tempObject instanceof Float)
				return (long) Double.parseDouble(tempObject.toString());
			else return Long.parseLong(tempObject.toString());
		return -1;
	}

	public Map<?, ?> getMap(String key) {
		tempObject = get(key);

		if (tempObject instanceof Map<?, ?>)
			return (Map) tempObject;
		return null;
	}

	public Map<String, Object> getValues() {
		if (!isEmpty())
			return data;
		return null;
	}

	public Object get(String key) {
		if (isEmpty() || !contains(key) || (contains(key) && !hasValue(key)))
			return null;
		return data.get(key);
	}

	public Set<?> getSet(String key) {
		tempObject = get(key);

		if (tempObject instanceof Set<?>)
			return (Set) tempObject;
		return null;
	}

	public Set<String> getKeys() {
		if (!isEmpty())
			return data.keySet();
		return new HashSet<String>();
	}

	public short getShort(String key) {
		tempObject = get(key);

		if (tempObject instanceof Short)
			return (Short) tempObject;
		if (tempObject instanceof String)
			if (isNumber(tempObject.toString()))
				return Short.parseShort(tempObject.toString());
		if (tempObject instanceof Number)
			return Short.parseShort(tempObject.toString());
		return -1;
	}

	public String getString(String key) {
		tempObject = get(key);

		if (tempObject instanceof String)
			return (String) tempObject;
		return null;
	}

	public Vector<?> getVector(String key) {
		tempObject = get(key);

		if (tempObject instanceof Vector<?>)
			return (Vector) tempObject;
		return null;
	}

	public void set(String key, Object object) {
		if (!exists())
			return;

		data.put(key, object);
	}

	public void load() {
		try {
			reader = new JsonReader(new FileReader(new File(configLocation + "/" + fileName)));

			if (!Validate.notNull(reader)) {
				ModestGame.getDefaultLogger().warnf("%s file could not be found", fileName);
				data = new HashMap<>();
				return;
			}

			data = gson.fromJson(reader, new TypeToken<Map<String, Object>>(){}.getType());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void reload() {
		save();
		load();
	}

	public void save() {
		FileManager.write(configLocation, fileName, gson.toJson(data));
	}
}