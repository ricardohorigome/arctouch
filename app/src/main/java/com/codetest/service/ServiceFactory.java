package com.codetest.service;

import com.codetest.service.impl.RestRouteServiceImpl;

/**
 * Provides the services
 */
public abstract class ServiceFactory {

    public static RouteService getRouteService(){
        return new RestRouteServiceImpl();
    }
}
