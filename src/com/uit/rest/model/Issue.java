package com.uit.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Issue {

	private long id;
	private long reportId;
	private long accountId;
	private String usernameIssue;
	private String usernameReport;
	private String time;
	private String message;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the reportId
	 */
	public long getReportId() {
		return reportId;
	}
	
	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(long reportId) {
		this.reportId = reportId;
	}
	
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
	 * @return the usernameIssue
	 */
	public String getUsernameIssue() {
		return usernameIssue;
	}
	
	/**
	 * @param usernameIssue the usernameIssue to set
	 */
	public void setUsernameIssue(String usernameIssue) {
		this.usernameIssue = usernameIssue;
	}
	
	/**
	 * @return the usernameReport
	 */
	public String getUsernameReport() {
		return usernameReport;
	}
	
	/**
	 * @param usernameReport the usernameReport to set
	 */
	public void setUsernameReport(String usernameReport) {
		this.usernameReport = usernameReport;
	}
	
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Issue() {
		this.id = -1;
		this.reportId = -1;
		this.accountId = -1;
		this.usernameIssue = "";
		this.usernameReport = "";
		this.time = null;
		this.message = "";
	}

	public Issue(long id, long reportId, String usernameIssue,
			String time, String message) {
		super();
		this.id = id;
		this.reportId = reportId;
		this.usernameIssue = usernameIssue;
		this.time = time;
		this.message = message;
	}
}
