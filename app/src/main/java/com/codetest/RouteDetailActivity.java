package com.codetest;

import com.codetest.entity.Route;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Represents the Activity with the Route Details
 */
public class RouteDetailActivity extends Activity {

	public static final String ROUTE_PARAM = "ROUTE_PARAM";

	private TextView routeNameTextView;
	private ListView streetsListView;
	private ExpandableListView departuresListView;
	private Route route;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_route_detail);
		receiveParameters();
		bindComponents();
		loadData();
	}

	/**
	 * Loads the remote data from AppGlu in parallel
	 */
	private void loadData(){
		routeNameTextView.setText(route.toString());
		SearchStreetsTask searchStreetsTask = new SearchStreetsTask(this);
		SearchDeparturesTask searchDeparturesTask = new SearchDeparturesTask(this);
		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
			searchStreetsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, route.getId());
			searchDeparturesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, route.getId());
		} else {
			searchStreetsTask.execute(route.getId());
			searchDeparturesTask.execute( route.getId());
		}
	}

	/**
	 * receives the Route selectes in the caller Activity
	 */
	private void receiveParameters() {
		this.route = (Route) getIntent().getSerializableExtra(ROUTE_PARAM);
	}

	private void bindComponents() {
		this.routeNameTextView = (TextView) findViewById(R.id.routeNameTextView);
		this.departuresListView = (ExpandableListView) findViewById(R.id.departuresListView);
		this.streetsListView = (ListView)findViewById(R.id.streetsListView);
	}

	/**
	 * handle the onclick event on Back button
	 * @param view
	 */
	public void back(View view){
		finish();
	}

	public ListView getStreetsListView() {
		return streetsListView;
	}

	public ExpandableListView getDeparturesListView() {
		return departuresListView;
	}


}
