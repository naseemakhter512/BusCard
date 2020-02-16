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
 * JDBC Implementation of Fare Model
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */
public class FareModel extends BaseModel {

	private static Logger log = Logger.getLogger(FareModel.class);

	private String minKm;
	private String maxKm;
	private int generalFare;
	private int seniorCitizenFare;
	private int femaleFare;
	private int juStudentFare;
	private int srStudentFare;


	

	public String getMinKm() {
		return minKm;
	}

	public void setMinKm(String minKm) {
		this.minKm = minKm;
	}

	public String getMaxKm() {
		return maxKm;
	}

	public void setMaxKm(String maxKm) {
		this.maxKm = maxKm;
	}

	public int getGeneralFare() {
		return generalFare;
	}

	public void setGeneralFare(int generalFare) {
		this.generalFare = generalFare;
	}

	public int getSeniorCitizenFare() {
		return seniorCitizenFare;
	}

	public void setSeniorCitizenFare(int seniorCitizenFare) {
		this.seniorCitizenFare = seniorCitizenFare;
	}

	public int getFemaleFare() {
		return femaleFare;
	}

	public void setFemaleFare(int femaleFare) {
		this.femaleFare = femaleFare;
	}

	public int getJuStudentFare() {
		return juStudentFare;
	}

	public void setJuStudentFare(int juStudentFare) {
		this.juStudentFare = juStudentFare;
	}

	public int getSrStudentFare() {
		return srStudentFare;
	}

	public void setSrStudentFare(int srStudentFare) {
		this.srStudentFare = srStudentFare;
	}

	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		System.out.println("in the systtem");
		log.debug("Model add Started");
		FareModel model = new FareModel();
		Connection conn = null;
		long pk = 0;

		FareModel existModel = model.findByPK(pk);
		if (existModel != null) {
			System.out.println("in exist");
			throw new DuplicateRecordException("ID already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO FARE(ID,MIN_KM,MAX_KM,GENERAL_FARE,SENIOR_CITIZEN_FARE,FEMALE_FARE,JUNIOR_STUDENT,SENIOR_STUDENT) VALUES(?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, minKm);
			pstmt.setString(3, maxKm);
			pstmt.setInt(4, generalFare);
			pstmt.setInt(5, seniorCitizenFare);
			pstmt.setInt(6, femaleFare);
			pstmt.setInt(7, juStudentFare);
			pstmt.setInt(8, srStudentFare);
			
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
	
	public void updateFare() throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE FARE SET MIN_KM=?,MAX_KM=?,GENERAL_FARE=?,SENIOR_CITIZEN_FARE=?,FEMALE_FARE=?,JUNIOR_STUDENT=?,SENIOR_STUDENT=? WHERE ID=?");

			pstmt.setString(1, minKm);
			pstmt.setString(2, maxKm);
			pstmt.setInt(3, generalFare);
			pstmt.setInt(4, seniorCitizenFare);
			pstmt.setInt(5, femaleFare);
			pstmt.setInt(6, juStudentFare);
			pstmt.setInt(7, srStudentFare);
			pstmt.setLong(8, id);
			
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
		StringBuffer sql = new StringBuffer("SELECT * FROM FARE WHERE 1=1");

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (seniorCitizenFare != 0 && seniorCitizenFare > 0) {
			sql.append(" AND SENIOR_CITIZEN_FARE like '" + seniorCitizenFare + "%'");
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
				FareModel model = populateModel(new FareModel(), rs);
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
		return "FARE";
	}

	public void delete() {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM FARE WHERE ID=?");
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

	public FareModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		System.out.println("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM FARE  WHERE ID=?");

		FareModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new FareModel(), rs);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	public FareModel findByFare(int stop) throws ApplicationException {
		log.debug("Model findByPK Started");
		System.out.println("Model User findByFare Started");

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM FARE WHERE ID=?");

		FareModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, stop);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = populateModel(new FareModel(), rs);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.printStackTrace();
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		System.out.println("Model findByEmail End");
		return model;
	}

	

	@Override
	protected <T extends BaseModel> T populateModel(T m, ResultSet rs)
			throws SQLException {
		super.populateModel(m, rs);

		FareModel model = (FareModel) m;
		model.setId(rs.getLong("ID"));
		model.setMinKm(rs.getString("MIN_KM"));
		model.setMaxKm(rs.getString("MAX_KM"));
		model.setGeneralFare(rs.getInt("GENERAL_FARE"));
		model.setSeniorCitizenFare(rs.getInt("SENIOR_CITIZEN_FARE"));
		model.setFemaleFare(rs.getInt("FEMALE_FARE"));
		model.setJuStudentFare(rs.getInt("JUNIOR_STUDENT"));
		model.setSrStudentFare(rs.getInt("SENIOR_STUDENT"));
        return m;
	}



	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return minKm+"";
	}

}