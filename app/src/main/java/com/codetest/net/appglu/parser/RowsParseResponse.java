package com.codetest.net.appglu.parser;

import com.codetest.net.appglu.ResponseHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a {@link ResponseHolder}
 * @param <T> type of Object in the rows list
 */
public abstract class RowsParseResponse<T> implements ParseResponse<ResponseHolder<T>> {

    /**
     * Parse a Row object to a {@link T}
     * @param jsonObject JSONObject that represents a Row
     * @return {@link T} Object
     * @throws JSONException if the parse fails
     */
    protected abstract T parseRow(JSONObject jsonObject) throws JSONException;

    public ResponseHolder<T> parse(String responseMessage) throws JSONException {

        ResponseHolder<T> responseHolder = new ResponseHolder<T>();

        JSONObject jsonBaseObject = new JSONObject(responseMessage);

        parseBaseObject(responseHolder, jsonBaseObject);
        parseRows(responseHolder, jsonBaseObject);

        return responseHolder;
    }

    private void parseBaseObject(ResponseHolder<T> responseHolder, JSONObject jsonBaseObject) throws JSONException {
        responseHolder.setRowsAffected(jsonBaseObject.getInt("rowsAffected"));
    }

    /**
     * iterates over the rows list to parse the the objects
     * @param responseHolder to add the rows
     * @param jsonBaseObject representing the JSON base object with the rows
     * @throws JSONException if the parse fails
     */
    private void parseRows(ResponseHolder<T> responseHolder, JSONObject jsonBaseObject) throws JSONException {
        JSONArray jsonRows = jsonBaseObject.getJSONArray("rows");

        List<T> rows = new ArrayList<T>();
        for (int i = 0; i < jsonRows.length(); i++) {
            JSONObject jsonObject = jsonRows.getJSONObject(i);
            T row = parseRow(jsonObject);
            rows.add(row);
        }
        responseHolder.setRows(rows);
    }

}

