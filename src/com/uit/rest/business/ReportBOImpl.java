package com.uit.rest.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uit.rest.dao.GeoPointDAO;
import com.uit.rest.dao.GeoPointDAOImpl;
import com.uit.rest.dao.ReportDAO;
import com.uit.rest.dao.ReportDAOImpl;
import com.uit.rest.model.Report;

public class ReportBOImpl implements ReportBO {

	@Override
	public boolean add(Report report) {
		GeoPointDAO geoPointDAO = new GeoPointDAOImpl();
		ReportDAO reportDAO = new ReportDAOImpl();
		
		report.setGeoPointId(geoPointDAO.add(report));
			
		return (report.getGeoPointId() != -1) ? reportDAO.add(report) : false;
	}

	@Override
	public boolean update(Report report) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.update(report);
	}

	@Override
	public ArrayList<Report> getList(
			double lat, double lng, String region, double limitDistance, boolean isValid) {
		ReportDAO dao = new ReportDAOImpl();
		ArrayList<Report> resultList = dao.getList(region, isValid);
		double distance;
		
		if (limitDistance != 0) {
			for (int i = 0; i < resultList.size(); i++) {
				distance = getDistanceFromPosition(
						lat, lng, 
						resultList.get(i).getLatitude(), resultList.get(i).getLongitude());
				if (distance > limitDistance) {
					resultList.remove(i--);
				}
			}
		}
		
		return resultList;
	}

	@Override
	public ArrayList<Report> getListByReportType(
			double lat, double lng, String region, long reportTypeId, double limitDistance) {
		ReportDAO dao = new ReportDAOImpl();
		ArrayList<Report> resultList = dao.getListByReportType(region, reportTypeId);
		double distance;
		
		if (limitDistance != 0) {
			for (int i = 0; i < resultList.size(); i++) {
				distance = getDistanceFromPosition(
						lat, lng, 
						resultList.get(i).getLatitude(), resultList.get(i).getLongitude());
				if (distance > limitDistance) {
					resultList.remove(i--);
				}
			}
		}
		
		return resultList;
	}

	@Override
	public Report get(long id) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.get(id);
	}

	@Override
	public Map<Long, Integer> getAmountOfReport(
			double lat, double lng, String region, double limitDistance) {
		ReportDAO dao = new ReportDAOImpl();
		
		if (limitDistance == 0) {
			return dao.getAmountOfReport(region);
		} else {
			Map<Long, Integer> result = new HashMap<Long, Integer>();
			ArrayList<Report> resultList = dao.getListForCalculatingAmount(region);
			double distance;
			
			for (long i = 1; i <= 8; i++) {
				result.put(i, 0);
			}
			
			for (int i = 0; i < resultList.size(); i++) {
				distance = getDistanceFromPosition(
						lat, lng, 
						resultList.get(i).getLatitude(), resultList.get(i).getLongitude());
				if (distance <= limitDistance) {
					int count = result.get(resultList.get(i).getReportTypeId());
						
					result.put(resultList.get(i).getReportTypeId(), count + 1);
				}
			}
			
			return result;
		}
	}

	@Override
	public boolean isThanked(long accountId, long reportId) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.isThanked(accountId, reportId);
	}

	@Override
	public boolean increaseThanksCounter(long accountId, long reportId) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.increaseThanksCounter(accountId, reportId);
	}

	@Override
	public boolean deactivate(long id) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.deactivate(id);
	}

	@Override
	public boolean validate(long id) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.validate(id);
	}

	@Override
	public boolean delete(long id) {
		ReportDAO dao = new ReportDAOImpl();
		
		return dao.delete(id);
	}

	private double getDistanceFromPosition(double lat1, double lng1, double lat2, double lng2) {
		int r = 6371; // radius of the earth in km
		double dLat = convertDegToRad(lat2 - lat1);
		double dLng = convertDegToRad(lng2 - lng1);
		double a = 
				Math.sin(dLat / 2) * Math.sin(dLat / 2) +
				Math.cos(convertDegToRad(lat1)) * Math.cos(convertDegToRad(lat2)) *
				Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = r * c;
		
		return d;
	}
	
	private double convertDegToRad(double deg) {
		return deg * (Math.PI / 180);
	}
}
