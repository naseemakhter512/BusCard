package in.co.buscard.common.model;



import in.co.buscard.util.JDBCDataSource;
import in.co.buscard.wsx.exception.ApplicationException;
import in.co.buscard.wsx.exception.DatabaseException;
import in.co.buscard.wsx.exception.DuplicateRecordException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Route Model
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */
public class RouteModel extends BaseModel {

	private static Logger log = Logger.getLogger(RouteModel.class);

	private String routeId;
	private String km;
	private String routeName;
	

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");
		RouteModel model = new RouteModel();
		Connection conn = null;
		long pk = 0;

		RouteModel existModel = model.findByPK(pk);
		if (existModel != null) {
			System.out.println("in exist");
			throw new DuplicateRecordException("ID already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ROUTE(ID,ROUTE_ID,ROUTE_NAME,KM) VALUES(?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, routeId);
			pstmt.setString(3, routeName);
			pstmt.setString(4, km);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);

			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	public void updateRoute() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ROUTE SET ROUTE_ID=?,ROUTE_NAME=?,KM=? WHERE ID=?");

			pstmt.setString(1, routeId);
			pstmt.setString(2, routeName);
			pstmt.setString(3, km);
			pstmt.setLong(4, id);
			pstmt.executeUpdate();

			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search() {
		return search(0, 0);
	}
	
	public List search(int pageNo, int pageSize) {

		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ROUTE WHERE 1=1");

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (routeName != null && routeName.length() > 0) {
			sql.append(" AND ROUTE_NAME like '" + routeName + "%'");
		}
		if (routeId != null && routeId.length() > 0) {
			sql.append(" AND ROUTE_ID like '" + routeId + "%'");
		}
		
		log.info("SQL : " + sql);
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RouteModel model = populateModel(new RouteModel(), rs);
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "ROUTE";
	}

	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM ROUTE WHERE ID=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	public RouteModel findByPK(long pk) throws ApplicationException {
		
		StringBuffer sql = new StringBuffer("SELECT * FROM ROUTE  WHERE ID=?");

		RouteModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new RouteModel(), rs);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Route by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	public RouteModel findByRoute(String routeId) throws ApplicationException {
		log.debug("Model findByPK Started");

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ROUTE WHERE ROUTE_ID=?");

		RouteModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, routeId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new RouteModel(), rs);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.printStackTrace();
			throw new ApplicationException(
					"Exception : Exception in getting Route by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	

	@Override
	protected <T extends BaseModel> T populateModel(T m, ResultSet rs)
			throws SQLException {
		super.populateModel(m, rs);

		RouteModel model = (RouteModel) m;
		model.setId(rs.getLong("ID"));
		model.setRouteId(rs.getString("ROUTE_ID"));
		model.setRouteName(rs.getString("ROUTE_NAME"));
		model.setKm(rs.getString("KM"));
        return m;
	}



	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return routeName;
	}

}