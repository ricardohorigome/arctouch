package com.codetest.net.appglu;

import com.codetest.net.HttpRequestTemplate;
import com.codetest.net.MediaType;
import com.codetest.net.appglu.parser.ParseParameter;
import com.codetest.net.appglu.parser.ParseResponse;


import java.net.ConnectException;
import java.text.MessageFormat;

import org.json.JSONException;

/**
 * Represents a HttpRequestTemplate pre configured to AppGlu
 * @param <P> Type of parameter
 * @param <R> Type of return after parsed by ParseResponse
 */
public class AppGluHttpRequest<P, R> extends HttpRequestTemplate {


	private static final String SERVER_PATTERN = "https://api.appglu.com/v1/queries/{0}/run";
	private static final String USERNAME = "WKD4N7YMA1uiM8V";
	private static final String PASSWORD = "DtdTtzMLQlA0hk2C1Yi5pLyVIlAQ68";
	private static final String HEADER_APP_ENVIRONMENT = "X-AppGlu-Environment";
	private static final String HEADER_APP_ENVIRONMENT_VALUE = "staging";

	private ParseParameter<P> parseParameter;
	private ParseResponse<R> parseResponse;

	public AppGluHttpRequest(Operation operation, ParseParameter<P> parseParameter, ParseResponse<R> parseResponse) {
		super(MessageFormat.format(SERVER_PATTERN, operation.getPath()));
		this.parseParameter = parseParameter;
		this.parseResponse = parseResponse;
	}

	public R send(P objectParam) throws IllegalArgumentException, ConnectException{
		String objParam;
		try {
			objParam = parseParameter.parse(objectParam);
			String respondeMessage = sendPost(objParam);
			return parseResponse.parse(respondeMessage);
		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	protected void preConfigureHeaders() {
		setAccept(MediaType.APPLICATION_JSON);
		setContentType(MediaType.APPLICATION_JSON);
		addHeaderProperty(HEADER_APP_ENVIRONMENT, HEADER_APP_ENVIRONMENT_VALUE);
	}

	@Override
	protected void preConfigureAuthentication() {
		setUsername(USERNAME);
		setPassword(PASSWORD);
	}

}
