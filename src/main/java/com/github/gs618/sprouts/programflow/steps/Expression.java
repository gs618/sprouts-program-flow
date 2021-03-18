package com.github.gs618.sprouts.programflow.steps;

/**
 * @author sgao
 */
@FunctionalInterface
public interface Expression<T> {
	/**
	 * Invoke comparison expression
	 *
	 * @return
	 */
	Boolean compare(T t);
}
