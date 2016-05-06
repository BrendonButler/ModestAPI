package net.sparkzz.modest.io.config;

import net.sparkzz.modest.io.IOManager;
import net.sparkzz.modest.utils.Validator;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Brendon Butler on 5/5/2016.
 */
public class YAMLConfig extends Validator implements Config {

	private DumperOptions dumperOptions;
	private File configLocation;
	private Map<String, Object> data, tempMap;
	private Object tempObject;
	private Representer representer;
	private String fileName;
	private Yaml yaml;

	public YAMLConfig() {
		configLocation = new File(System.getProperty("user.dir") + "/data");
		fileName = "config";
		load();

		setupDumper();

		yaml = new Yaml(representer, dumperOptions);
	}

	public YAMLConfig(File folder, String fileName) {
		configLocation = folder;
		this.fileName = fileName;
		load();

		setupDumper();

		yaml = new Yaml();
	}

	private void setupDumper() {
		dumperOptions = new DumperOptions();
		representer = new Representer();

		dumperOptions.setIndent(2);
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
		data = Collections.synchronizedMap(new HashMap<String, Object>());
	}

	public void reload() {
		save();
		load();
	}

	public void save() {
		IOManager.write(configLocation, fileName, yaml.dump(data));
	}
}