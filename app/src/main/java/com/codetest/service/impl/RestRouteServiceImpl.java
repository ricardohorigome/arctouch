package com.codetest.service.impl;

import com.codetest.entity.Departure;
import com.codetest.entity.Route;
import com.codetest.entity.Stop;
import com.codetest.net.appglu.AppGluHttpRequest;
import com.codetest.net.appglu.Operation;
import com.codetest.net.appglu.ResponseHolder;
import com.codetest.net.appglu.parser.BasicParseParamter;
import com.codetest.net.appglu.parser.DepartureParseResponse;
import com.codetest.net.appglu.parser.LikeParseParameter;
import com.codetest.net.appglu.parser.ParseParameter;
import com.codetest.net.appglu.parser.ParseResponse;
import com.codetest.net.appglu.parser.RoutesParseResponse;
import com.codetest.net.appglu.parser.StopsParseResponse;
import com.codetest.service.RouteService;

import java.net.ConnectException;
import java.util.List;

/**
 * Created by Ricardo on 08/12/2015.
 */
public class RestRouteServiceImpl implements RouteService {

    private static final String PARAMETER_NAME_STOP_NAME = "stopName";
    private static final String PARAMETER_NAME_ROUT_ID = "routeId";

    /**
     * @see RouteService#findRoutesByStopName(String)
     * @param stopName name or part of
     * @return
     * @throws ConnectException
     */
    public List<Route> findRoutesByStopName(String stopName) throws ConnectException{

        if (stopName == null || stopName.equals("")){
            throw new IllegalArgumentException();
        }
        ParseParameter<String> parseParameter = new LikeParseParameter(PARAMETER_NAME_STOP_NAME);
        ParseResponse<ResponseHolder<Route>> parseResponse = new RoutesParseResponse();

        AppGluHttpRequest<String, ResponseHolder<Route>> appGluHttpRequest = new AppGluHttpRequest<String, ResponseHolder<Route>>(
                Operation.ROUTES_BY_STOP_NAME, parseParameter, parseResponse);
        ResponseHolder<Route> responseholder = appGluHttpRequest.send(stopName);
        return responseholder.getRows();

    }

    /**
     * @see RouteService#findStopsByRouteId(Long)
     * @param routeId id of the {@Link Route}
     * @return
     * @throws ConnectException
     */
    public List<Stop> findStopsByRouteId(Long routeId) throws ConnectException{


        ParseParameter<Long> parseParameter = new BasicParseParamter<Long>(PARAMETER_NAME_ROUT_ID);
        ParseResponse<ResponseHolder<Stop>> parseResponse = new StopsParseResponse();

        AppGluHttpRequest<Long, ResponseHolder<Stop>> appGluHttpRequest = new AppGluHttpRequest<Long,ResponseHolder<Stop>>(
                Operation.STOPS_BY_ROUTE_ID, parseParameter,parseResponse);
        ResponseHolder<Stop> responseholder = appGluHttpRequest.send(routeId);
        return responseholder.getRows();

    }

    /**
     * @see RouteService#findDeparturesByRouteId(Long)
     * @param routeId id of the {@Link Route}
     * @return
     * @throws ConnectException
     */
    public List<Departure> findDeparturesByRouteId(Long routeId)throws ConnectException{

        ParseParameter<Long> parseParameter = new BasicParseParamter<Long>(PARAMETER_NAME_ROUT_ID);
        ParseResponse<ResponseHolder<Departure>> parseResponse = new DepartureParseResponse();

        AppGluHttpRequest<Long, ResponseHolder<Departure>> appGluHttpRequest = new AppGluHttpRequest<Long,ResponseHolder<Departure>>(
                Operation.DEPARTURES_BY_ROUTE_ID, parseParameter,parseResponse);
        ResponseHolder<Departure> responseholder = appGluHttpRequest.send(routeId);
        return responseholder.getRows();

    }
}
