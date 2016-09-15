package net.sparkzz.modest.io.config;

import net.sparkzz.modest.ModestGame;
import net.sparkzz.modest.io.FileManager;
import net.sparkzz.modest.utils.Validate;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <p>Yaml Configuration</p>
 *
 * @author Brendon Butler
 * @since  0.1
 */
public class YAMLConfig extends Validate implements Config {

	private boolean overwrite = false;
	private DumperOptions dumperOptions;
	private File configLocation;
	private FileReader reader;
	private Map<String, Object> data;
	private Object tempObject;
	private Representer representer;
	private String fileName;
	private Yaml yaml;

	public YAMLConfig() {
		configLocation = new File(System.getProperty("user.dir") + "/data");
		fileName = "config.yaml";

		setupDumper();

		yaml = new Yaml(representer, dumperOptions);
		load();
	}

	public YAMLConfig(File folder, String fileName) {
		configLocation = folder;
		this.fileName = fileName + ".yaml";

		setupDumper();

		yaml = new Yaml(representer, dumperOptions);
		load();
	}

	private void setupDumper() {
		int indent = 2;
		dumperOptions = new DumperOptions();
		representer = new Representer();

		dumperOptions.setIndent(indent);
		dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		dumperOptions.setAllowUnicode(Charset.defaultCharset().name().contains("UTF"));
		representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
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
			else if (((Number) tempObject).intValue() == 0)
				return false;
		return false;
	}

	public boolean hasValue(String key) {
		tempObject = data.get(key);

		return (tempObject != null);
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
			return Byte.parseByte(tempObject.toString());
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

	@Override
	public File getSaveLocation() {
		return configLocation;
	}

	public int getInteger(String key) {
		tempObject = get(key);

		if (tempObject instanceof Integer)
			return (Integer) tempObject;
		if (tempObject instanceof String)
			if (isNumber(tempObject.toString()))
				return Integer.parseInt(tempObject.toString());
		if (tempObject instanceof Number)
			return Integer.parseInt(tempObject.toString());
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
			return Long.parseLong(tempObject.toString());
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
		if (isEmpty())
			return null;

		final String[] nodes = key.split("\\.");
		Map curMap = data;

		for (int i = 0; i <= nodes.length - 1; ++i) {
			Object child = curMap.get(nodes[i]);

			if (child == null) return null;
			else if (!(child instanceof Map)) {
				if (i == nodes.length - 1)
					return child;
				else return null;
			}

			curMap = (Map) child;
		}
		return null;
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
		return new HashSet<>();
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

		final String[] nodes = key.split("\\.");
		Map curMap = data;

		for (int i = 0; i <= nodes.length - 2; ++i) {
			Object child = curMap.get(nodes[i]);

			if (child == null) child = new LinkedHashMap();
			else if (!(child instanceof Map)) {
				if (!overwrite) return;
				child = new LinkedHashMap();
			}

			curMap.put(nodes[i], child);
			curMap = (Map) child;
		}
		curMap.put(nodes[nodes.length - 1], object);
	}

	public void setProtection(boolean value) {
		overwrite = !value;
	}

	public void load() {
		reader = FileManager.read(new File(configLocation + "/" + fileName));

		if (!Validate.notNull(reader)) {
			ModestGame.getDefaultLogger().warnf("%s file could not be found", fileName);
			data = new LinkedHashMap();
			return;
		}

		data = new LinkedHashMap((Map) yaml.load(reader));
	}

	public void reload() {
		save();
		load();
	}

	public void save() {
		FileManager.write(configLocation, fileName, yaml.dump(data));
	}
}