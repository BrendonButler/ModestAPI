package net.sparkzz.modest.io.config;

import java.util.*;

/**
 * Created by Brendon Butler on 5/2/2016.
 */
public interface Config {

	boolean contains(String key);

	boolean containsKey(String key);

	boolean getBoolean(String key);

	boolean hasValue(String key);

	boolean isEmpty();

	byte getByte(String key);

	Collection<?> getCollection(String key);

	char getChar(String key);

	double getDouble(String key);

	int getInteger(String key);

	List<?> getList(String key);

	long getLong(String key);

	Map<String, Object> getValues();

	Object get(String key);

	Set<String> getKeys();

	short getShort(String key);

	String getString(String key);

	Vector<?> getVector(String key);

	void set(String key, Object object);

	void load();

	void reload();

	void save();
}