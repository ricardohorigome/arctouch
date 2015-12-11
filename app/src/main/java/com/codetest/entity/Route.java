package com.codetest.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * represents a Route entity
 */
public class Route implements Serializable {

	private Long id;
	private String shortName;
	private String longName;
	private Date lastModifiedDate;
	private Long agencyId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	@Override
	public String toString() {
		return shortName + " - " + longName;
	}

	// @Override
	// public void setValues(JSONObject jsonObject) throws JSONException {
	// this.setId(jsonObject.getLong("id"));
	// this.setAgencyId(jsonObject.getLong("agencyId"));
	// // this.setLastModifiedDate(jsonObject.get("lastModifiedDate);
	// this.setLongName(jsonObject.getString("longName"));
	// this.setShortName(jsonObject.getString("shortName"));
	// }

}
