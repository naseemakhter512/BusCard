package in.co.buscard.wsx.model;

import in.co.buscard.common.model.BaseModel;
import in.co.buscard.util.JDBCDataSource;
import in.co.buscard.wsx.exception.ApplicationException;
import in.co.buscard.wsx.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ErrorMessageModel extends BaseModel {

	private static Logger log = Logger.getLogger(ErrorMessageModel.class);
	private String schedularName;
	private String errorMessage;
	private String errorTrace;

	public String getSchedularName() {
		return schedularName;
	}

	public void setSchedularName(String schedularName) {
		this.schedularName = schedularName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}
	
	
	/**
	 * created next PK of record
	 * 
	 * @throws DatabaseException
	 */

	public long nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM " + getTableName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	/**
	 * Add ErrorMessage
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_ERRORMESSAGE (ID,SCHEDULAR_NAME,ERROR_MESSAGE,ERROR_TRACE,LOGGER_TIME)VALUES(?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, schedularName);
			pstmt.setString(3, errorMessage);
			pstmt.setString(4, errorTrace);
			pstmt.setTimestamp(5, createdDatetime);

			
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
	
	/**
	 * Searches records on the basis of model NOT NULL attributes with
	 * pagination.
	 * 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ApplicationException
	 */
	public List search(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model search Started");

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_ERRORMESSAGE  WHERE 1=1");

		if (id > 0) {
			sql.append(" AND ID = " + id);
		}

		if (schedularName != null && schedularName.length() > 0) {
			sql.append(" AND SCHEDULAR_NAME LIKE '" + schedularName + "%'");
		}

		if (errorMessage != null && errorMessage.length() > 0) {
			sql.append(" AND ERROR_MESSAGE LIKE '" + errorMessage + "%'");
		}

		
		

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		log.info("SQL : " + sql);

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ErrorMessageModel model = new ErrorMessageModel();
				model.setId(rs.getLong(1));
				model.setSchedularName(rs.getString(2));
				model.setErrorMessage(rs.getString(3));
				model.setErrorTrace(rs.getString(4));
				model.setCreatedDatetime(rs.getTimestamp(5));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Attendence");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Searches records on the basis of model NOT NULL attributes
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public List search() throws ApplicationException {
		return search(0, 0);
	}
	@Override
	public String getKey() {

		return id + "";
	}

	@Override
	public String getValue() {

		return schedularName;
	}

	@Override
	public String getTableName() {
	
		return "ST_ERRORMESSAGE";
	}


}
