package com.codetest.net.appglu.parser;

import org.json.JSONException;

/**
 * Interface to parse an JSON string to an Object
 * @param <R> Type of Object represented by JSON string
 */
public interface ParseResponse<R> {

	R parse(String responseMessage) throws JSONException;

}
