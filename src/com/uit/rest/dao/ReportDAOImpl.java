package com.uit.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uit.rest.model.Report;

public class ReportDAOImpl implements ReportDAO {

	@Override
	public boolean add(Report report) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "insert into report values(null,?,?,?,?,?,null,?,?,?,1,0)";
		RegionDAO regionDAO = new RegionDAOImpl();
		long regionId = regionDAO.add(report.getRegion());
		
		if (regionId == -1) {
			return false;
		}
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, report.getReportTypeId());
			statement.setLong(2, regionId);
			statement.setLong(3, report.getWarningId());
			statement.setLong(4, report.getAccountId());
			statement.setLong(5, report.getGeoPointId());
			//statement.setTimestamp(6, report.getTime());
			statement.setString(6, report.getDescription());
			statement.setString(7, report.getImageUrl());
			statement.setString(8, report.getVideoUrl());
			
			// Insert into location table and return result
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

	@Override
	public boolean update(Report report) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "update report " +
				"set account_id = ?, warning_id = ?, time = null, " +
				"description = ?, imageurl = ?, videourl = ? " +
				"where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, report.getAccountId());
			statement.setLong(2, report.getWarningId());
			statement.setString(3, report.getDescription());
			statement.setString(4, report.getImageUrl());
			statement.setString(5, report.getVideoUrl());
			statement.setLong(6, report.getId());
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

	@Override
	public ArrayList<Report> getList(String region, boolean isValid) {
		ArrayList<Report> result = new ArrayList<Report>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		/*String queryString = 
				"select report.id, username, latitude, longitude, reporttype.name, reporttype.id, time, " +
				"description, warning.level, warning.name, imageurl, videourl, " +
				"count(thanksmanager.id) as counter, warning.id " +
				"from region, account, geopoint, reporttype, warning, " +
				"thanksmanager right join report on report.id = thanksmanager.report_id " +
				"where region.id = report.region_id and account.id = report.account_id " +
				"and geopoint.id = report.geopoint_id and reporttype.id = report.reporttype_id " +
				"and warning.id = report.warning_id and region.name = ? and report.isactive = 1 " +
				"and report.isvalid = ? " +
				"group by report.id " +
				"order by time ";*/
		String queryString = 
				"select report.id, username, latitude, longitude, reporttype.name, reporttype.id, time, " +
				"description, warning.level, warning.name, imageurl, videourl, " +
				"count(thanksmanager.id) as counter, warning.id " +
				"from region, account, geopoint, reporttype, warning, " +
				"thanksmanager right join report on report.id = thanksmanager.report_id " +
				"where region.id = report.region_id and account.id = report.account_id " +
				"and geopoint.id = report.geopoint_id and reporttype.id = report.reporttype_id " +
				"and warning.id = report.warning_id and region.name = ? and report.isactive = 1 " +
				"and report.isvalid = ? " +
				"and ((curdate() = date(time) " +
				"and (((hour(curtime()) - hour(time) = 1) and (minute(curtime()) <= minute(time))) " +
				"or (hour(curtime()) = hour(time)))) " +
				"or (hour(curtime()) = 0 and hour(time) = 23 " +
				"and (minute(curtime()) <= minute(time)) and (day(curdate()) - day(date(time)) = 1))) " +
				"group by report.id " +
				"order by time ";
		
		try {
			// Connect to database
			baseDAO.connectDB();
			
			queryString += (isValid) ? "desc" : "asc";

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, region);
			statement.setBoolean(2, isValid);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				Report report = new Report(
								rs.getLong(1), 
								rs.getString(2),
								rs.getDouble(3), 
								rs.getDouble(4), 
								rs.getString(5),
								rs.getLong(6),
								rs.getTimestamp(7).toString(), 
								region, 
								rs.getString(8), 
								rs.getInt(9),
								rs.getString(10),
								rs.getInt(13), 
								rs.getString(11),
								rs.getString(12));
				
				report.setWarningId(rs.getLong(14));
				
				result.add(report);
			}
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return result;
	}

	@Override
	public ArrayList<Report> getListByReportType(String region,
			long reportTypeId) {
		ArrayList<Report> result = new ArrayList<Report>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		/*String queryString = 
				"select report.id, username, latitude, longitude, reporttype.name, reporttype.id, time, " +
				"description, warning.level, warning.name, imageurl, videourl, " +
				"count(thanksmanager.id) as counter " +
				"from account, warning, reporttype, region, geopoint, " +
				"thanksmanager right join report on report.id = thanksmanager.report_id " +
				"where account.id = report.account_id and geopoint.id = report.geopoint_id " +
				"and reporttype.id = report.reporttype_id and region.id = report.region_id " +
				"and warning.id = report.warning_id and report.isactive = 1 " +
				"and report.isvalid = 1 " +
				"and region.name = ? " +
				"and reporttype.id = ? " +
				"group by report.id " +
				"order by time desc";*/
		String queryString = 
				"select report.id, username, latitude, longitude, reporttype.name, reporttype.id, time, " +
				"description, warning.level, warning.name, imageurl, videourl, " +
				"count(thanksmanager.id) as counter " +
				"from account, warning, reporttype, region, geopoint, " +
				"thanksmanager right join report on report.id = thanksmanager.report_id " +
				"where account.id = report.account_id and geopoint.id = report.geopoint_id " +
				"and reporttype.id = report.reporttype_id and region.id = report.region_id " +
				"and warning.id = report.warning_id and report.isactive = 1 " +
				"and report.isvalid = 1 " +
				"and ((curdate() = date(time) " +
				"and (((hour(curtime()) - hour(time) = 1) and (minute(curtime()) <= minute(time))) " +
				"or (hour(curtime()) = hour(time)))) " +
				"or (hour(curtime()) = 0 and hour(time) = 23 " +
				"and (minute(curtime()) <= minute(time)) and (day(curdate()) - day(date(time)) = 1))) " +
				"and region.name = ? " +
				"and reporttype.id = ? " +
				"group by report.id " +
				"order by time desc";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, region);
			statement.setLong(2, reportTypeId);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				Report report = new Report(
						rs.getLong(1), 
						rs.getString(2),
						rs.getDouble(3), 
						rs.getDouble(4), 
						rs.getString(5),
						rs.getLong(6),
						rs.getTimestamp(7).toString(), 
						region, 
						rs.getString(8), 
						rs.getInt(9),
						rs.getString(10),
						rs.getInt(13), 
						rs.getString(11),
						rs.getString(12));
		
				result.add(report);
			}
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return result;
	}

	@Override
	public Report get(long id) {
		Report result = new Report();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = 
				"select report.id, username, latitude, longitude, reporttype.name, reporttype.id, time, " +
				"description, warning.level, warning.name, imageurl, videourl, " +
				"count(thanksmanager.id) as counter, warning.id " +
				"from account, warning, reporttype, region, geopoint, " +
				"thanksmanager right join report on report.id = thanksmanager.report_id " +
				"where account.id = report.account_id and geopoint.id = report.geopoint_id " +
				"and reporttype.id = report.reporttype_id and region.id = report.region_id " +
				"and warning.id = report.warning_id and report.isactive = 1 " +
				"and report.id = ? " +
				"group by report.id";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, id);
			
			rs = statement.executeQuery();
			
			if (rs.next()) {
				result = new Report(
						rs.getLong(1), 
						rs.getString(2),
						rs.getDouble(3), 
						rs.getDouble(4), 
						rs.getString(5),
						rs.getLong(6),
						rs.getTimestamp(7).toString(), 
						"", 
						rs.getString(8), 
						rs.getInt(9),
						rs.getString(10),
						rs.getInt(13), 
						rs.getString(11),
						rs.getString(12));
				result.setWarningId(rs.getLong(14));
			}
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return result;
	}

	@Override
	public ArrayList<Report> getListForCalculatingAmount(String region) {
		ArrayList<Report> result = new ArrayList<Report>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		/*String queryString = 
				"select report.id, latitude, longitude, reporttype.id " +
				"from region, geopoint, reporttype, report " +
				"where region.id = report.region_id " +
				"and geopoint.id = report.geopoint_id and reporttype.id = report.reporttype_id " +
				"and region.name = ? and report.isactive = 1 and report.isvalid = 1";*/
		String queryString = 
				"select report.id, latitude, longitude, reporttype.id " +
				"from region, geopoint, reporttype, report " +
				"where region.id = report.region_id " +
				"and geopoint.id = report.geopoint_id and reporttype.id = report.reporttype_id " +
				"and region.name = ? and report.isactive = 1 and report.isvalid = 1 " + 
				"and ((curdate() = date(time) " +
				"and (((hour(curtime()) - hour(time) = 1) and (minute(curtime()) <= minute(time))) " +
				"or (hour(curtime()) = hour(time)))) " +
				"or (hour(curtime()) = 0 and hour(time) = 23 " +
				"and (minute(curtime()) <= minute(time)) and (day(curdate()) - day(date(time)) = 1))) " +
				"group by report.id";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, region);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				Report report = new Report(
						rs.getLong(1),
						rs.getDouble(2),
						rs.getDouble(3),
						rs.getLong(4));
				
				result.add(report);
			}
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return result;
	}

	@Override
	public Map<Long, Integer> getAmountOfReport(String region) {
		Map<Long, Integer> result = new HashMap<Long, Integer>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		/*String queryString = 
				"select reporttype.id, count(report.id) as counter " +
				"from region, report right join reporttype " +
				"on report.reporttype_id = reporttype.id and report.isactive = 1 " +
				"and report.isvalid = 1 " +
				"where region.name = ?  " +
				"group by reporttype.id";*/
		String queryString = 
				"select reporttype.id, count(report.id) as counter " +
				"from region, report right join reporttype " +
				"on report.reporttype_id = reporttype.id and report.isactive = 1 " +
				"and report.isvalid = 1 " +
				"and ((curdate() = date(report.time) " +
				"and (((hour(curtime()) - hour(report.time) = 1) and (minute(curtime()) <= minute(report.time))) " +
				"or (hour(curtime()) = hour(report.time)))) " +
				"or (hour(curtime()) = 0 and hour(report.time) = 23 " +
				"and (minute(curtime()) <= minute(report.time)) and (day(curdate()) - day(date(report.time)) = 1))) " +
				"where region.name = ?  " +
				"group by reporttype.id";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, region);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				result.put(rs.getLong(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return result;
	}

	@Override
	public boolean isThanked(long accountId, long reportId) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = "select id from thanksmanager where account_id = ? and report_id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);

			statement.setLong(1, accountId);
			statement.setLong(2, reportId);

			rs = statement.executeQuery();

			return (rs.next()) ? true : false;
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

	@Override
	public boolean increaseThanksCounter(long accountId, long reportId) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "insert into thanksmanager values(null,?,?)";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, accountId);
			statement.setLong(2, reportId);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

	@Override
	public boolean deactivate(long id) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "update report set isactive = 0 where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, id);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

	@Override
	public boolean validate(long id) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "update report set isvalid = 1 where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, id);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

	@Override
	public boolean delete(long id) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "delete from report where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, id);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

}
