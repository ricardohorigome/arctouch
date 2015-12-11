package com.codetest;

import java.io.Serializable;
import java.text.MessageFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Main Activity that starts the application. Represents th initial search for routes
 */
public class MainActivity extends Activity {

	private EditText searchEditText;
	private ListView routesListView;
	private Button googleMapsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureThreadPolicy();
		bindComponents();
		configureRoutesListView();
		configureMapButton();
	}

	private void bindComponents() {
		this.searchEditText = (EditText) findViewById(R.id.searchEditText);
		this.routesListView = (ListView) findViewById(R.id.routesListView);
		this.googleMapsButton = (Button) findViewById(R.id.map);
	}

	/**
	 * binds and configure the onclick events on the routes ListView
	 */
	private void configureRoutesListView() {
		this.routesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent detail = new Intent(getApplicationContext(), RouteDetailActivity.class);
				detail.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				Bundle bundle = new Bundle();
				bundle.putSerializable(RouteDetailActivity.ROUTE_PARAM, (Serializable) parent.getAdapter().getItem(position));
				detail.putExtras(bundle);
				startActivity(detail);
			}
		});
	}

	/**
	 * configure the policy for network usage
	 */
	private void configureThreadPolicy() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * handle the Search button onclick event
	 * @param view
	 */
	public void searchRoute(View view) {
		String searchInput = this.searchEditText.getText().toString();
		searchRoute_(searchInput);
	}

	/**
	 * search for routes by stop name in a new thread
	 */
	private void searchRoute_(String stopName) {
		if ("".equals(stopName)) {
			String msg = MessageFormat.format(getString(R.string.msg_field_mandatory), getString(R.string.label_field_street));
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
			return;
		}
		new SearchRouteTask(this, routesListView).execute(stopName);
	}


	private static final int GOOGLE_MAPS_REQUEST_CODE = 1;

	/**
	 * configures the Maps button to open the google maps activity
	 */
	private void configureMapButton() {
		googleMapsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
				mapIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivityForResult(mapIntent, GOOGLE_MAPS_REQUEST_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode != GOOGLE_MAPS_REQUEST_CODE) {
			super.onActivityResult(requestCode, resultCode, data);
			return;
		}

		if (resultCode != RESULT_OK) {
			return;
		}

		String streetName = data.getStringExtra(MapsActivity.STREET_NAME_PARAMETER);
		streetName = formatStreetNameWithoutPrefix(streetName);
		this.searchEditText.setText(streetName);
		searchRoute_(this.searchEditText.getText().toString());
	}

	/**
	 * removes the street name prefix(R., Av., Servidão)
	 * ex: R. Mauro Ramos -> Mauro Ramos
	 * 	   Av.
	 * @param street name
	 * @return street name without prefix R. , Av. ow Servidão
	 */
	private String formatStreetNameWithoutPrefix(String street) {
		street  = street.replaceFirst("\\A(Av. )|(R. )|(Servidão )", "");
		return street;
	}


}
