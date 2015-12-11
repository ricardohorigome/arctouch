package com.codetest.net.appglu.parser;

import org.json.JSONException;

/**
 * @see com.codetest.net.appglu.parser.BasicParseParamter
 * Converts a single string parameter to a like format (ex: "mauro" to "%mauro%")
 */
public class LikeParseParameter extends BasicParseParamter<String> {

	public LikeParseParameter(String paramName) {
		super(paramName);
	}

	@Override
	public String parse(String objParam) throws JSONException {
		String paramAsLike = convertToLikeParam(objParam);
		return super.parse(paramAsLike);
	}

	private String convertToLikeParam(String objParam) {
		String paramAsLike = "%" + objParam + "%";
		return paramAsLike;
	}
}
