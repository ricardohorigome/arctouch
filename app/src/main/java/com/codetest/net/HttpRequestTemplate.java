package com.codetest.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.util.Base64;

/**
 * Represents a HTTP Request template
 */
public class HttpRequestTemplate {
	private static final String HEADER_PROPERTY_ACCEPT = "Accept";
	private static final String HEADER_PROPERTY_CONTENT_TYPE = "Content-type";
	private static final String HEADER_PROPERTY_AUTHORIZATION = "Authorization";

	private String url;
	private String username;
	private String password;
	private Map<String, String> requestProperties;
	
	protected HttpRequestTemplate(String url) {
		this.url = url;
		this.requestProperties = new HashMap<String, String>();
	}
	protected void preConfigureHeaders(){
	}

	protected void preConfigureAuthentication() {
	};

	public void addHeaderProperty(String property, String value){
		requestProperties.put(property, value);
	}
	
	public void setAccept(MediaType mediatype) {
		addHeaderProperty(HEADER_PROPERTY_ACCEPT, mediatype.toString());
	}

	public void setContentType(MediaType mediatype) {
		addHeaderProperty(HEADER_PROPERTY_CONTENT_TYPE, mediatype.toString());
	}

	protected String sendPost(String param) throws ConnectException {
		try {
			preConfigureAuthentication();
			preConfigureHeaders();
			HttpURLConnection connection = openConnection();
			configureConnection(connection);
			writeParameter(param, connection);
			String respondeMessage = readAndCloseConnection(connection);
			return respondeMessage;
		} catch (MalformedURLException e) {
			throw new ConnectException();
		} catch (IOException e) {
			throw new ConnectException();
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String readAndCloseConnection(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder messageReturn = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			messageReturn.append(line);
		}
		in.close();
		return messageReturn.toString();
	}

	private HttpURLConnection openConnection() throws MalformedURLException, IOException {
		URL url = new URL(this.url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		return connection;
	}

	private void writeParameter(String param, HttpURLConnection connection) throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(param);
		out.flush();
		out.close();
	}

	private void configureConnection(HttpURLConnection connection) throws ProtocolException {
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		for (String property : requestProperties.keySet()) {
			connection.addRequestProperty(property, requestProperties.get(property));
		}
		configureBasicAuthentication(connection);
	}

	private void configureBasicAuthentication(HttpURLConnection connection) {
		if (username == null || username.equals("")) {
			return;
		}
		String userpass = username + ":" + password;
		String basicAuth = "Basic " + new String(Base64.encode(userpass.getBytes(), Base64.NO_WRAP));
		connection.setRequestProperty(HEADER_PROPERTY_AUTHORIZATION, basicAuth);
	}
}
