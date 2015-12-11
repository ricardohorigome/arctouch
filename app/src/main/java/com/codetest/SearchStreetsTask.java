package com.codetest;


import android.content.Context;
import android.widget.ArrayAdapter;

import com.codetest.entity.Stop;
import com.codetest.service.ServiceFactory;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * consults the streets in a async thread holding a progress dialog
 */
public class SearchStreetsTask extends ProgressAsyncTask<Long, Void, List<Stop>> {



    public SearchStreetsTask(Context context){
        super(context);
    }

    /**
     * realize the search in background
     * @param params
     * @return
     */
    @Override
    protected List<Stop> doInBackground(Long... params) {
        Long routeId = Long.valueOf(params[0]);
        List<Stop> stops;
        try{
            stops = ServiceFactory.getRouteService().findStopsByRouteId(routeId);
            if (stops.size() == 0){
                setMessage(getContext().getString(R.string.msg_warning_no_result));
            }
        }catch (ConnectException e){
            setMessage(getContext().getString(R.string.msg_error_connection));
            stops = new ArrayList<>();
        }
        return stops;
    }

    /**
     * Sets the result on the Activity
     * @param stops
     */
    @Override
    protected void onPostExecute(List<Stop> stops) {
        super.onPostExecute(stops);
        ArrayAdapter<Stop> adapter = new ArrayAdapter<Stop>(getContext(), R.layout.item_list_view,stops);
        RouteDetailActivity routeDetailActivity = (RouteDetailActivity)getContext();
        routeDetailActivity.getStreetsListView().setAdapter(adapter);
    }
}
