package com.codetest.net.appglu.parser;

import org.json.JSONException;

/**
 * Interface to parse an Object to a JSON string
 * @param <P> Type of parameter to be parsed
 */
public interface ParseParameter<P> {
	String parse(P objParam) throws JSONException;
}
