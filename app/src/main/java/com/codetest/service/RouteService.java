package com.codetest.service;

import java.net.ConnectException;
import java.util.List;

import com.codetest.entity.Departure;
import com.codetest.entity.Route;
import com.codetest.entity.Stop;

public interface RouteService {

	/**
	 * Lists the routes by the stop name
	 * @param stopName name or part of
	 * @return list of {@Link Route} that passes by the stop
	 * @throws ConnectException if there is any problem with the server or connection
	 */
	List<Route> findRoutesByStopName(String stopName)throws ConnectException;

	/**
	 * Lists the stops of the route
	 * @param routeId id of the {@Link Route}
	 * @return list of {@Link Stop}
	 * @throws ConnectException if there is any problem with the server or connection
	 */
	List<Stop> findStopsByRouteId(Long routeId)throws ConnectException;

	/**
	 * Lists the departures of the route
	 * @param routeId id of the {@Link Route}
	 * @return list of {@Link Departure}
	 * @throws ConnectException if there is any problem with the server or connection
	 */
	List<Departure> findDeparturesByRouteId(Long routeId)throws ConnectException;


}
