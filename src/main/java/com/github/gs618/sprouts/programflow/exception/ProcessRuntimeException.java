package com.github.gs618.sprouts.programflow.exception;

/**
 * @author sgao
 */
public class ProcessRuntimeException extends RuntimeException {

	public ProcessRuntimeException(){}

	public ProcessRuntimeException(Exception e) {
		super(e);
	}

	public ProcessRuntimeException(String message) {
		super(message);
	}
}

