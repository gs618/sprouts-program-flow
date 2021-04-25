package com.github.gs618.sprouts.programflow;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有的输入参数，中间结果都会保存在Input中，所以输入总是全量的
 * Input总是代表对下个步骤的输入
 *
 * @author sgao
 */
public class Input {

	Map<String, Object> data = new HashMap<>(20);

	/**
	 * 获取某个数据
	 *
	 * @param key data-key
	 * @param <T> data type of value
	 * @return
	 */
	public <T> T getData(String key) {
		return (T) data.get(key);
	}

	/**
	 * 加入某个数据
	 *
	 * @param key data-key
	 * @param object data-value
	 * @param <T> data type of value
	 */
	public <T> void putData(String key, T object) {
		data.put(key, object);
	}
}
