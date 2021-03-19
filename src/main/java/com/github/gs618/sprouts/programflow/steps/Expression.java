package com.github.gs618.sprouts.programflow.steps;

/**
 * @author sgao
 */
@FunctionalInterface
public interface Expression<T> {
	/**
	 * Invoke comparison expression
	 * @param t source
	 *
	 * @return if matches
	 */
	Boolean compare(T t);
}
