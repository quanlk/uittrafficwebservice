package com.uit.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "favorite")
public class Favorite extends GeoPoint {

	private long accountID;
	private long geoPointID;
	private String address;
	private String description;
	
	/**
	 * @return the accountID
	 */
	public long getAccountID() {
		return accountID;
	}
	
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	
	/**
	 * @return the geoPointID
	 */
	public long getGeoPointID() {
		return geoPointID;
	}

	/**
	 * @param geoPointID the geoPointID to set
	 */
	public void setGeoPointID(long geoPointID) {
		this.geoPointID = geoPointID;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Favorite() { 
		super();
	}

	public Favorite(long id, long accountID, double latitude, double longitude,
			String address, String description) {
		super(id, latitude, longitude);
		this.accountID = accountID;
		this.address = address;
		this.description = description;
	}
}
