package com.codetest.net.appglu.parser;

import com.codetest.entity.Departure;
import com.codetest.net.appglu.parser.RowsParseResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @see RowsParseResponse
 */
public class DepartureParseResponse extends RowsParseResponse<Departure> {

    /**
     * @see RowsParseResponse#parseRow(JSONObject)
     * @param jsonObject JSONObject that represents a Row
     * @return
     * @throws JSONException
     */
    protected Departure parseRow(JSONObject jsonObject) throws JSONException {
        Departure departure = new Departure();
        departure.setId(jsonObject.getLong("id"));
        departure.setCalendar(jsonObject.getString("calendar"));
        departure.setTime(jsonObject.getString("time"));
        return departure;
    }

}