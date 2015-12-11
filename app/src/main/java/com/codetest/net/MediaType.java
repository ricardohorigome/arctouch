package com.codetest.net;

/**
 * Media Types on HTTP connections
 */
public enum MediaType {
	APPLICATION_JSON("application/json"),
	TEXT_PLAIN("text/plain"),
	APPLICATION_XML("application/xml");

	private String mediaType;

	private MediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return mediaType;
	}
}
