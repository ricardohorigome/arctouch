package com.codetest;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import com.codetest.entity.Route;
import com.codetest.service.ServiceFactory;


import android.content.Context;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * consults the routes in a async thread holding a progress dialog
 */
public class SearchRouteTask extends ProgressAsyncTask<String, Void, List<Route>> {

	private ListView routesListView;

	public SearchRouteTask(Context context, ListView routesListView) {
		super(context);
		this.routesListView = routesListView;
	}

	/**
	 * realize the search in background
	 * @param params
	 * @return
	 */
	@Override
	protected List<Route> doInBackground(String... params) {
		List<Route> routes;
		try {
			routes = ServiceFactory.getRouteService().findRoutesByStopName(params[0]);
			if (routes.size() == 0){
				setMessage(getContext().getString(R.string.msg_warning_no_result));
			}
		}catch (ConnectException e){
			setMessage(getContext().getString(R.string.msg_error_connection));
			routes = new ArrayList<>();
		}
		return routes;
	}

	/**
	 * sets the result on the caller Acticity
	 * @param routes
	 */
	@Override
	protected void onPostExecute(List<Route> routes) {
		super.onPostExecute(routes);
		ArrayAdapter<Route> adapter = new ArrayAdapter<Route>(getContext(), android.R.layout.simple_list_item_1,
				routes);
		this.routesListView.setAdapter(adapter);
	}


}
