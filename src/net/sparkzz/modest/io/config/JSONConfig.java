package net.sparkzz.modest.io.config;

import net.sparkzz.modest.io.IOManager;
import net.sparkzz.modest.utils.Validator;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;

/**
 * Created by Brendon Butler on 5/2/2016.
 */
public class JSONConfig extends Validator implements Config {

	private File configLocation;
	private JSONObject data;
	private Map<String, Object> tempMap;
	private Object tempObject;
	private String fileName;

	public JSONConfig() {
		configLocation = new File(System.getProperty("user.dir") + "/data");
		fileName = "config";
		data = new JSONObject();
	}

	public JSONConfig(File folder, String fileName) {
		configLocation = folder;
		this.fileName = fileName;
		data = new JSONObject();
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
		return false;
	}

	public boolean hasValue(String key) {
		tempObject = data.get(key);

		if (tempObject != null)
			return true;
		return false;
	}

	public boolean isEmpty() {
		return data.isEmpty() || data == null;
	}

	public byte getByte(String key) {
		tempObject = get(key);

		if (tempObject instanceof Byte)
			return (Byte) tempObject;
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
		return '?';
	}

	public double getDouble(String key) {
		tempObject = get(key);

		if (tempObject instanceof Double)
			return (Double) tempObject;
		return -1;
	}

	public int getInteger(String key) {
		tempObject = get(key);

		if (tempObject instanceof Integer)
			return (Integer) tempObject;
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
		return -1;
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

	public Set<String> getKeys() {
		if (!isEmpty())
			return data.keySet();
		return new HashSet<String>();
	}

	public short getShort(String key) {
		tempObject = get(key);

		if (tempObject instanceof Short)
			return (Short) tempObject;
		return -1;
	}

	public String getString(String key) {
		tempObject = get(key);

		if (tempObject instanceof Short)
			return (String) tempObject;
		return "";
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
		data = IOManager.readJSON(new File(configLocation + "/" + fileName + ".json"));
	}

	public void reload() {
		save();
		load();
	}

	public void save() {
		IOManager.write(configLocation, fileName, data);
	}
}