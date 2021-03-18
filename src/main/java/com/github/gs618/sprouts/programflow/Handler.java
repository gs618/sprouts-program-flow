package com.github.gs618.sprouts.programflow;

/**
 * @author sgao
 */
public interface Handler<I, O> {

	/**
	 * Handle whatever it should be
	 *
	 * @param input
	 * @param output
	 */
	void start(I input, O output);
}
