package com.uit.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Report extends GeoPoint {

	private long accountId;
	private String username;
	private long geoPointId;
	private long reportTypeId;
	private String reportTypeName;
	//private Timestamp time;
	private String timeKeyword;
	private String region;
	private String description;
	private long warningId;
	private int warningLevel;
	private String warningName;
	private int thanksCounter;
	private String imageUrl;
	private String videoUrl;

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the geoPointId
	 */
	public long getGeoPointId() {
		return geoPointId;
	}

	/**
	 * @param geoPointId the geoPointId to set
	 */
	public void setGeoPointId(long geoPointId) {
		this.geoPointId = geoPointId;
	}

	/**
	 * @return the reportTypeId
	 */
	public long getReportTypeId() {
		return reportTypeId;
	}

	/**
	 * @param reportTypeId the reportTypeId to set
	 */
	public void setReportTypeId(long reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	/**
	 * @return the reportTypeName
	 */
	public String getReportTypeName() {
		return reportTypeName;
	}

	/**
	 * @param reportTypeName the reportTypeName to set
	 */
	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

	/**
	 * @return the time
	 */
	/*public Timestamp getTime() {
		return time;
	}

	*//**
	 * @param time the time to set
	 *//*
	public void setTime(Timestamp time) {
		this.time = time;
	}*/

	/**
	 * @return the timeKey
	 */
	public String getTimeKeyword() {
		return timeKeyword;
	}

	/**
	 * @param timeKey the timeKey to set
	 */
	public void setTimeKeyword(String timeKeyword) {
		this.timeKeyword = timeKeyword;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
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

	/**
	 * @return the warningId
	 */
	public long getWarningId() {
		return warningId;
	}

	/**
	 * @param warningId the warningId to set
	 */
	public void setWarningId(long warningId) {
		this.warningId = warningId;
	}

	/**
	 * @return the warningLevel
	 */
	public int getWarningLevel() {
		return warningLevel;
	}

	/**
	 * @param warningLevel the warningLevel to set
	 */
	public void setWarningLevel(int warningLevel) {
		this.warningLevel = warningLevel;
	}

	/**
	 * @return the warningName
	 */
	public String getWarningName() {
		return warningName;
	}

	/**
	 * @param warningName the warningName to set
	 */
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}

	/**
	 * @return the thanksCounter
	 */
	public int getThanksCounter() {
		return thanksCounter;
	}

	/**
	 * @param thanksCounter the thanksCounter to set
	 */
	public void setThanksCounter(int thanksCounter) {
		this.thanksCounter = thanksCounter;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the videoUrl
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * @param videoUrl the videoUrl to set
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	public Report() {
		super(-1, 0, 0);
		this.reportTypeId = 0;
		this.reportTypeName = "";
		this.username = "";
		this.region = "";
		this.description = "";
		this.timeKeyword = "";
		this.warningId = 0;
		this.warningLevel = 0;
		this.warningName = "";
		this.thanksCounter = 0;
		this.imageUrl = "";
		this.videoUrl = "";
	}
	
	/*public Report(long id, String username, double latitude, double longitude, 
			String reportTypeName, Timestamp time, String region, String description, 
			int warningLevel, String warningName, int thanksCounter, String imageUrl) {
		super(id, latitude, longitude);
		this.reportTypeName = reportTypeName;
		this.username = username;
		this.time = time;
		this.region = region;
		this.description = description;
		this.warningLevel = warningLevel;
		this.warningName = warningName;
		this.thanksCounter = thanksCounter;
		this.imageUrl = imageUrl;
	}*/
	
	public Report(long id, double latitude, double longitude, long reportTypeID) {
		super(id, latitude, longitude);
		this.reportTypeId = reportTypeID;
	}
	
	public Report(long id, String username, double latitude, double longitude, String reportTypeName,
			long reportTypeID, String timeKeyword, String region, String description, 
			int warningLevel, String warningName, int thanksCounter, String imageUrl, String videoUrl) {
		super(id, latitude, longitude);
		this.reportTypeId = reportTypeID;
		this.reportTypeName = reportTypeName;
		this.username = username;
		this.timeKeyword = timeKeyword;
		this.region = region;
		this.description = description;
		this.warningLevel = warningLevel;
		this.warningName = warningName;
		this.thanksCounter = thanksCounter;
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
	}
}
