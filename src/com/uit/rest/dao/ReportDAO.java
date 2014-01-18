package com.uit.rest.dao;

import java.util.ArrayList;
import java.util.Map;

import com.uit.rest.model.Report;

public interface ReportDAO {

	boolean add(Report report);
	boolean update(Report report);
	ArrayList<Report> getList(String region, boolean isValid);
	ArrayList<Report> getListByReportType(String region, long reportTypeId);
	Report get(long id);
	ArrayList<Report> getListForCalculatingAmount(String region);
	Map<Long, Integer> getAmountOfReport(String region);
	boolean isThanked(long accountId, long reportId);
	boolean increaseThanksCounter(long accountId, long reportId);
	boolean deactivate(long id);
	boolean validate(long id);
	boolean delete(long id);
}
