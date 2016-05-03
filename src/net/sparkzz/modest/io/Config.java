package net.sparkzz.modest.io;

import net.sparkzz.modest.utils.Validator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Brendon Butler on 5/2/2016.
 */
public class Config extends Validator {

	private File configLocation;
	private JSONObject object;
	private Object tempObject;
	private String fileName;

	public Config() {
		configLocation = new File(System.getProperty("user.dir") + "/data");
		fileName = "config";
		object = new JSONObject();
	}

	public Config(File folder, String fileName) {
		configLocation = folder;
		this.fileName = fileName;
		object = new JSONObject();
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

	public byte getByte(String key) {
		return (byte) getInteger(key);
	}

	public char getChar(String key) {
		tempObject = object.get(key);

		if (tempObject instanceof Character)
			return (Character) tempObject;

		if (tempObject instanceof String)
			return tempObject.toString().charAt(0);
		return Character.MIN_VALUE;
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

	public Collection getList(String key) {
		if (object.get(key) instanceof Collection<?>)
			return (Collection<?>) object.get(key);
		return null;
	}

	public long getLong(String key) {
		return (long) getInteger(key);
	}

	public Object get(String key) {
		return object.get(key);
	}

	public short getShort(String key) {
		return (short) getInteger(key);
	}

	public String getString(String key) {
		tempObject = object.get(key);

		if (tempObject instanceof String)
			return (String) tempObject;

		return "";
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