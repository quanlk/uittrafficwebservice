package com.uit.rest.business;

import java.util.ArrayList;
import java.util.Map;

import com.uit.rest.model.Report;

public interface ReportBO {

	boolean add(Report report);
	boolean update(Report report);
	ArrayList<Report> getList(double lat, double lng, String region, double limitDistance, boolean isValid);
	ArrayList<Report> getListByReportType(
			double lat, double lng, String region, long reportTypeId, double limitDistance);
	Report get(long id);
	Map<Long, Integer> getAmountOfReport(
			double lat, double lng, String region, double limitDistance);
	boolean isThanked(long accountId, long reportId);
	boolean increaseThanksCounter(long accountId, long reportId);
	boolean deactivate(long id);
	boolean validate(long id);
	boolean delete(long id);
}
