package com.codetest;

import android.content.Context;

import com.codetest.entity.Departure;
import com.codetest.service.ServiceFactory;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * consults the departures in a async thread holding a progress dialog
 */
public class SearchDeparturesTask  extends ProgressAsyncTask<Long, Void, List<Departure>> {


    public SearchDeparturesTask(Context context) {
        super(context);
    }

    /**
     * realize the search in background
     * @param params
     * @return
     */
    @Override
    protected List<Departure> doInBackground(Long... params) {
        Long routeId = Long.valueOf(params[0]);
        List<Departure> departures;
        try{
            departures = ServiceFactory.getRouteService().findDeparturesByRouteId(routeId);
            if (departures.size() == 0){
                setMessage(getContext().getString(R.string.msg_warning_no_result));
            }
        }catch (ConnectException e){
            setMessage(getContext().getString(R.string.msg_error_connection));
            departures = new ArrayList<>();
        }
        return departures;
    }

    /**
     * sets the result on the caller Acticity
     * @param departures
     */
    @Override
    protected void onPostExecute(List<Departure> departures) {
        super.onPostExecute(departures);
        DeparturesByDayTypeExpandableAdapter departuresByDayTypeExpandableAdapter = new DeparturesByDayTypeExpandableAdapter(getContext(), departures);
        RouteDetailActivity routeDetailActivity = (RouteDetailActivity) getContext();
        routeDetailActivity.getDeparturesListView().setAdapter(departuresByDayTypeExpandableAdapter);
    }
}