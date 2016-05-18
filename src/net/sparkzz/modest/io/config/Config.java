package net.sparkzz.modest.io.config;

import java.util.*;

/**
 * @author Brendon Butler
 * @since  0.1
 */
public interface Config {

	/**
	 * <p>Checks if configuration contains a key</p>
	 *
	 * @param key Input string
	 * @return {@code true} if config contains specified key
	 */
	boolean contains(String key);

	/**
	 * <p>Checks if configuration exists</p>
	 *
	 * @return {@code true} if config exists
	 */
	boolean exists();

	/**
	 * <p>Gets {@code boolean} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@code true} if config contains specified key
	 */
	boolean getBoolean(String key);

	/**
	 * <p>Checks if configuration has value under specified key</p>
	 *
	 * @param key Input string
	 * @return {@code true} if config contains key and has a value
	 */
	boolean hasValue(String key);

	/**
	 * <p>Checks if configuration is empty</p>
	 *
	 * @return {@code true} if config is empty
	 */
	boolean isEmpty();

	/**
	 * <p>Gets {@link Byte} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Byte} from config
	 */
	byte getByte(String key);

	/**
	 * <p>Gets {@link Collection} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Collection} from config using key
	 */
	Collection<?> getCollection(String key);

	/**
	 * <p>Gets {@link Character} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Character} from config using key
	 */
	char getChar(String key);

	/**
	 * <p>Gets {@link Double} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Double} from config using key
	 */
	double getDouble(String key);

	/**
	 * <p>Gets {@link Integer} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Integer} from config using key
	 */
	int getInteger(String key);

	/**
	 * <p>Gets {@link List} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link List} from config using key
	 */
	List<?> getList(String key);

	/**
	 * <p>Gets {@link Long} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Long} from config using key
	 */
	long getLong(String key);

	/**
	 * <p>Gets {@link Map} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Map} from config using key
	 */
	Map<?, ?> getMap(String key);

	/**
	 * <p>Gets configuration {@link Map}</p>
	 *
	 * @return {@link Map} of the config
	 */
	Map<String, Object> getValues();

	/**
	 * <p>Gets {@link Object} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Object} from config using key
	 */
	Object get(String key);

	/**
	 * <p>Gets {@link Double} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Double} from config using key
	 */
	Set<?> getSet(String key);

	/**
	 * <p>Gets {@link Set} of keys from configuration</p>
	 *
	 * @return {@link Set} of keys from config {@link Map}
	 */
	Set<String> getKeys();

	/**
	 * <p>Gets {@link Short} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Short} from config using key
	 */
	short getShort(String key);

	/**
	 * <p>Gets {@link String} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link String} from config using key
	 */
	String getString(String key);

	/**
	 * <p>Gets {@link Vector} from configuration</p>
	 *
	 * @param key Input string
	 * @return {@link Vector} from config using key
	 */
	Vector<?> getVector(String key);

	/**
	 * <p>Sets the {@code object} in the configuration {@link Map},
	 * with the {@code key}</p>
	 * 
	 * @param key    Key for {@link Map}
	 * @param object Value for {@link Map}
	 */
	void set(String key, Object object);

	/**
	 * <p>Loads configuration from file, then stores in variable</p>
	 */
	void load();

	/**
	 * <p>Default: uses the {@code save} then {@code load} methods</p>
	 */
	void reload();

	/**
	 * <p>Saves configuration to file</p>
	 */
	void save();
}