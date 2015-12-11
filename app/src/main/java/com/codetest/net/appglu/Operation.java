package com.codetest.net.appglu;

/**
 * represents the operations suported by AppGlu with their path
 */
public enum Operation {
	ROUTES_BY_STOP_NAME("findRoutesByStopName"), STOPS_BY_ROUTE_ID("findStopsByRouteId"), DEPARTURES_BY_ROUTE_ID("findDeparturesByRouteId");

	private final String path;

	private Operation(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}



}
