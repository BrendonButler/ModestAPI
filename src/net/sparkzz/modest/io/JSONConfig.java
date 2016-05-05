package net.sparkzz.modest.io;

import net.sparkzz.modest.utils.Validator;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;

/**
 * Created by Brendon Butler on 5/2/2016.
 */
public class JSONConfig extends Validator implements Config {

	private File configLocation;
	private JSONObject object;
	Map<String, Object> tempMap;
	private Object tempObject;
	private String fileName;

	public JSONConfig() {
		configLocation = new File(System.getProperty("user.dir") + "/data");
		fileName = "config";
		object = new JSONObject();
	}

	public JSONConfig(File folder, String fileName) {
		configLocation = folder;
		this.fileName = fileName;
		object = new JSONObject();
	}

	public boolean contains(String key) {
		return object.containsKey(key);
	}

	public boolean containsKey(String key) {
		return object.containsKey(key);
	}

	public boolean getBoolean(String key) {
		tempObject = object.get(key);

		if (tempObject instanceof Boolean)
			return (Boolean) tempObject;
		if (tempObject instanceof String){
			if (tempObject.toString().equalsIgnoreCase("true"))
				return true;
			if (tempObject.toString().equalsIgnoreCase("false"))
				return false;
		}
		return false;
	}

	public boolean hasValue(String key) {
		return object.get(key) != null;
	}

	public boolean isEmpty() {
		return object.isEmpty();
	}

	public byte getByte(String key) {
		return (byte) getInteger(key);
	}

	public Collection<?> getCollection(String key) {
		if (object.get(key) instanceof Collection<?>)
			return (Collection<?>) object.get(key);
		return null;
	}

	public char getChar(String key) {
		tempObject = object.get(key);

		if (tempObject instanceof Character)
			return (Character) tempObject;

		if (tempObject instanceof String)
			return tempObject.toString().charAt(0);
		return Character.MIN_VALUE;
	}

	public List<?> getList(String key) {
		if (object.get(key) instanceof List<?>)
			return (List<?>) object.get(key);
		return null;
	}

	public double getDouble(String key) {
		tempObject = object.get(key);

		if (tempObject instanceof Double || isDouble(tempObject.toString()))
			return Double.parseDouble(tempObject.toString());
		return -1.0;
	}

	public int getInteger(String key) {
		tempObject = object.get(key);

		if (tempObject instanceof Double || isDouble(tempObject.toString()))
			return (int) Double.parseDouble(tempObject.toString());

		if (tempObject instanceof Integer || isInteger(tempObject.toString()))
			return Integer.parseInt(tempObject.toString());
		return -1;
	}

	public long getLong(String key) {
		return (long) getInteger(key);
	}

	public Map<String, Object> getValues() {
		tempMap = new HashMap<String, Object>();

		for (String curr : getKeys()) {
			tempMap.put(curr, object.get(curr));
		}

		return tempMap;
	}

	public Object get(String key) {
		return object.get(key);
	}

	public Set<String> getKeys() {
		if (!isEmpty())
			return (Set<String>) object.keySet();
		return null;
	}

	public short getShort(String key) {
		return (short) getInteger(key);
	}

	public String getString(String key) {if (tempObject instanceof String)
			return (String) tempObject;

		return "";
	}

	public Vector<?> getVector(String key) {
		if (object.get(key) instanceof Vector<?>)
			return (Vector<?>) object.get(key);
		return null;
	}

	public void set(String key, Object object) {
		this.object.put(key, object);
	}

	public void load() {
		object = IOManager.read(new File(configLocation + "/" + fileName + ".json"));
	}

	public void reload() {
		save();
		load();
	}

	public void save() {
		IOManager.write(configLocation, fileName, object);
	}
}