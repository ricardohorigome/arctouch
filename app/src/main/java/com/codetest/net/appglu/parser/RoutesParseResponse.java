package com.codetest.net.appglu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.codetest.entity.Route;
import com.codetest.net.appglu.ResponseHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @see RowsParseResponse
 */
public class RoutesParseResponse  extends RowsParseResponse<Route> {

	/**
	 * @see RowsParseResponse#parseBaseObject(ResponseHolder, JSONObject)
	 * @param jsonObject JSONObject that represents a Row
	 * @return
	 * @throws JSONException
	 */
	@Override
	protected Route parseRow(JSONObject jsonObject) throws JSONException {
		Route route = new Route();
		route.setId(jsonObject.getLong("id"));
		route.setAgencyId(jsonObject.getLong("agencyId"));
		route.setLongName(jsonObject.getString("longName"));
		route.setShortName(jsonObject.getString("shortName"));
		try{
			route.setLastModifiedDate(parseDate(jsonObject.getString("lastModifiedDate")));
		}catch (ParseException e){
			throw new JSONException(e.getMessage());
		}
		return route;
	}

	private Date parseDate(String strDate) throws ParseException {
		if (strDate == null | strDate.equals("")) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(strDate);
	}


}
