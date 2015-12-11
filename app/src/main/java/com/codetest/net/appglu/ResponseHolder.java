package com.codetest.net.appglu;

import java.util.List;

/**
 * Base object that represents a list return
 * @param <T> The element type of row list
 */
public class ResponseHolder<T> {

	private List<T> rows;
	private Integer rowsAffected;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getRowsAffected() {
		return rowsAffected;
	}

	public void setRowsAffected(Integer rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

}
