package com.codetest.net.appglu.parser;

import com.codetest.entity.Stop;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @see RowsParseResponse
 */
public class StopsParseResponse extends RowsParseResponse<Stop> {
    /**
     * @ see RowsParseResponse#parseRow
     * @param jsonObject JSONObject that represents a Row
     * @return
     * @throws JSONException
     */
    protected Stop parseRow(JSONObject jsonObject) throws JSONException{
        Stop stop = new Stop();
        stop.setId(jsonObject.getLong("id"));
        stop.setName(jsonObject.getString("name"));
        stop.setSequence(jsonObject.getInt("sequence"));
        stop.setRoute_id(jsonObject.getLong("route_id"));
        return stop;
    }

}
