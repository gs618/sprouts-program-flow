package com.github.gs618.sprouts.programflow;

import java.util.HashMap;
import java.util.Map;

/**
 * Input stands for the input data for every step.
 * Normally, it takes data though the whole process, and it also can take the new data which
 * generated from the middle steps
 *
 * @author sgao
 */
public class Input {

	final Map<String, Object> data = new HashMap<>(20);

	/**
	 * Get data by key
	 * @param key key of data
	 * @param <T>  data
	 * @return  data type of value
	 */
	public <T> T getData(String key) {
		return (T) data.get(key);
	}

	/**
	 * put data with its key
	 *
	 * @param key data-key
	 * @param object data-value
	 * @param <T> data type of value
	 */
	public <T> void putData(String key, T object) {
		data.put(key, object);
	}
}
