package com.codetest.net.appglu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Basic parse with a single parameter
 * @param <P>
 */
public class BasicParseParamter<P> implements ParseParameter<P> {

	private String paramName;

	private static String PARAMETER_NAME_PARAMS = "params";

	/**
	 *
	 * @param paramName the name of parameter in JSON
	 */
	public BasicParseParamter(String paramName) {
		super();
		this.paramName = paramName;
	}

	/**
	 * @see ParseParameter#parse(Object)
	 * @param objParam
	 * @return
	 * @throws JSONException
	 */
	@Override
	public String parse(P objParam) throws JSONException {
		JSONObject jsonGenericParam = new JSONObject();
		JSONObject jsonParam = new JSONObject();
		jsonParam.put(paramName, objParam);
		jsonGenericParam.put(PARAMETER_NAME_PARAMS, jsonParam);
		return jsonGenericParam.toString();
	}

	
	
	
}
