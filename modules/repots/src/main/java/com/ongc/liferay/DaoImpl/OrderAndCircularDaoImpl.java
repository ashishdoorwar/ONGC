package com.ongc.liferay.DaoImpl;

import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.dao.OrderAndCircularDao;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.model.OrderCircularCategory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class OrderAndCircularDaoImpl implements OrderAndCircularDao{

	@Override
	public List<OrderCircularCategory> getOrderAndCircularCategory() {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		List<OrderCircularCategory> list = new ArrayList<OrderCircularCategory>();
		String query = "SELECT CATEGORY_ID , CATEGORY_NAME FROM RPTMS_ORDER_CIRCULAR ORDER BY CATEGORY_NAME ASC";
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			OrderCircularCategory orderCircular;
			while (rs.next()) {
				orderCircular = new OrderCircularCategory();
				orderCircular.setCategoryId(rs.getString("CATEGORY_ID"));
				orderCircular.setCategoryName(rs.getString("CATEGORY_NAME"));
				list.add(orderCircular);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

	@Override
	public int insertOrderAndCircular(OrderCircular orderCircular, String fileUploadFileName, File fileUpload) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		String input = orderCircular.getOrderDate();
//		ATTACHMENT_FILE
		String insertQuery = "INSERT INTO RPTT_ORDER_AND_CIRCULAR ( SUBJECT,ISSUED_BY,CATEGORY,ORDER_NUMBER,ATTACHMENT_NAME,ORDER_DATE,ID,CREATE_DATE)"
				+ " VALUES (?,?,?,?,?,?,?,NOW())";
		int i = 0;
		int maxId=0;
		try {
			maxId=getMaxIdFromTable("RPTT_ORDER_AND_CIRCULAR", "ID");
			String filName = String.valueOf(maxId)+orderCircular.getFileUploadFileName();
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, orderCircular.getSubject());
			pstmt.setString(2, orderCircular.getIssuedBy());
			pstmt.setString(3, orderCircular.getCategory());
			pstmt.setString(4, orderCircular.getOrderNumber());
			pstmt.setString(5, filName);
			try {
				System.out.println(orderCircular.getOrderDate());
				
				SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
				Date d1 = stm.parse(input);
				pstmt.setTimestamp(6, new Timestamp(d1.getTime()));
			} catch (Exception e) {
				e.printStackTrace();
			}
//			pstmt.setBytes(7, FileUtils.readFileToByteArray(fileUpload));
			pstmt.setInt(7,maxId);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return maxId;
	}

	@Override
	public List<OrderCircular> selectTopOrderAndCircular() {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		StringBuffer sql = new StringBuffer(
				"SELECT ID,SUBJECT,ISSUED_BY,CATEGORY,ORDER_NUMBER,ORDER_DATE,ATTACHMENT_NAME,CREATE_DATE FROM RPTT_ORDER_AND_CIRCULAR Where TO_DATE(ORDER_DATE::text, 'yyyy-mm-dd') > date(now()) - INTERVAL '600 DAY' ORDER BY TO_DATE(ORDER_DATE::text, 'yyyy-mm-dd') desc");
		List<OrderCircular> list = new ArrayList<OrderCircular>();
		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());
			OrderCircular orderCircular;
			while (rs.next()) {
				orderCircular = new OrderCircular();
				orderCircular.setId(rs.getInt("ID"));
				orderCircular.setSubject(rs.getString("SUBJECT"));
				orderCircular.setIssuedBy(rs.getString("ISSUED_BY"));
				orderCircular.setCategory(rs.getString("CATEGORY"));
				orderCircular.setOrderNumber(rs.getString("ORDER_NUMBER"));
				orderCircular.setOrderDate(stm.format(rs.getTimestamp("ORDER_DATE")));
				orderCircular.setFileUploadFileName(rs.getString("ATTACHMENT_NAME"));
				orderCircular.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				if (orderCircular.getFileUploadFileName() != null) {
					orderCircular.setFileUploadFileName(orderCircular
							.getFileUploadFileName().replaceAll("&", "_"));
				}
				list.add(orderCircular);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

	@Override
	public List<OrderCircular> selectOrderAndCircular(String startDate, String endDate, String subject,
			String category) {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		Date d1 = null, d2 = null;
		SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stm1 = new SimpleDateFormat("MM-dd-yyyy");
		if (startDate != null && !startDate.isEmpty()) {
			try {
				d1 = stm.parse(startDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			startDate = stm1.format(d1);
		}

		if (endDate != null && !endDate.isEmpty()) {
			try {
				d2 = stm.parse(endDate);
			} catch (Exception e) {

				e.printStackTrace();
			}
			endDate = stm1.format(d2);
		}
		StringBuffer sql = new StringBuffer(
				"SELECT ID,SUBJECT,ISSUED_BY,CATEGORY,ORDER_NUMBER,ORDER_DATE,ATTACHMENT_NAME,CREATE_DATE FROM RPTT_ORDER_AND_CIRCULAR ");
		startDate = startDate != null && startDate.trim().equals("") ? null
				: startDate;
		endDate = endDate != null && endDate.trim().equals("") ? null : endDate;

		boolean flag = true;

		if (subject != null && !subject.trim().equals("")) {
			String[] words = subject.split("[\\s,]");

			int i = 0;
			sql.append("WHERE ");
			for (String w : words) {
				i++;
				sql.append("UPPER(SUBJECT) LIKE UPPER('%").append(w)
				.append("%') ");
				if (i < words.length) {
					sql.append(" OR ");
				} else
					sql.append("  ");

			}

			flag = false;
		}

		if (category != null && !category.trim().equals("")
				&& !category.equalsIgnoreCase("Select")) {
			if (flag) {
				sql.append("WHERE ");
				flag = false;
			} else {
				sql.append("AND ");
			}
			sql.append(" CATEGORY= '").append(category).append("' ");
		}

		if (startDate != null || endDate != null) {
			if (flag) {
				sql.append("WHERE ");
				flag = false;
			} else {
				sql.append("AND ");
			}

			if (startDate != null && endDate == null) {
				sql.append(" DATE(ORDER_DATE) >= DATE('").append(startDate)
				.append("')");
			}
			if (startDate == null && endDate != null) {
				sql.append(" DATE(ORDER_DATE) <= DATE('").append(endDate)
				.append("')");
			}
			if (startDate != null && endDate != null) {
				sql.append(" DATE(ORDER_DATE) >= DATE('").append(startDate)
				.append("') AND DATE(ORDER_DATE) <= ");
				sql.append(" DATE('").append(endDate).append("')");
			}
		}

		sql.append(" ORDER BY ORDER_DATE DESC");
		
		List<OrderCircular> list = new ArrayList<OrderCircular>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(sql);
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());
			OrderCircular orderCircular;
			
			while (rs.next()) {
				orderCircular = new OrderCircular();
				orderCircular.setId(rs.getInt("ID"));
				orderCircular.setSubject(rs.getString("SUBJECT"));
				orderCircular.setIssuedBy(rs.getString("ISSUED_BY"));
				orderCircular.setCategory(rs.getString("CATEGORY"));
				orderCircular.setOrderNumber(rs.getString("ORDER_NUMBER"));
				orderCircular.setOrderDate(rs.getString("ORDER_DATE"));
				//orderCircular.setOrderDate(sdf1.format(sdf.parse(rs.getString("ORDER_DATE"))));
				orderCircular.setFileUploadFileName(rs.getString("ATTACHMENT_NAME"));
				orderCircular.setCretDate(rs.getString("CREATE_DATE"));
				//orderCircular.setCretDate(sdf1.format(sdf.parse(rs.getString("CREATE_DATE"))));
				if (orderCircular.getFileUploadFileName() != null) {
					orderCircular.setFileUploadFileName(orderCircular
							.getFileUploadFileName().replaceAll("&", "_"));
				}

				list.add(orderCircular);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return list;
	}

	@Override
	public byte[] getAttachmentFile(String id) {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		String query = "select ATTACHMENT_FILE from RPTT_ORDER_AND_CIRCULAR where id = "+ Integer.parseInt(id);
		conn = DatasourceConnection.getConnection();
		InputStream fileData = null;
		byte[] byt = new byte[5000];
		List<Byte> ls = new ArrayList<Byte>();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				fileData = rs.getBinaryStream("ATTACHMENT_FILE");

				while (true) {
					byt = new byte[5000];
					int sz = fileData.read(byt);
					for (int x = 0; x < sz; x++) {
						ls.add(byt[x]);
					}

					if (sz < 5000)
						break;

				}
				byt = new byte[ls.size()];
				int x = 0;
				for (Byte bt : ls) {
					byt[x++] = bt;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return byt;
	}

	@Override
	public OrderCircular selectOrderCircularById(int id) {
		Connection conn=null;
		Statement statement = null;
		ResultSet rs=null;
		StringBuffer sql = new StringBuffer(
				"SELECT ID,SUBJECT,ISSUED_BY,CATEGORY,ORDER_NUMBER,ORDER_DATE,ATTACHMENT_NAME,CREATE_DATE FROM RPTT_ORDER_AND_CIRCULAR Where id = ");
		sql.append(id);
		OrderCircular orderCircular = null;
		SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
		try {
			conn = DatasourceConnection.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql.toString());

			while (rs.next()) {
				orderCircular = new OrderCircular();
				orderCircular.setId(rs.getInt("ID"));
				orderCircular.setSubject(rs.getString("SUBJECT"));
				orderCircular.setIssuedBy(rs.getString("ISSUED_BY"));
				orderCircular.setCategory(rs.getString("CATEGORY"));
				orderCircular.setOrderNumber(rs.getString("ORDER_NUMBER"));
				orderCircular
				.setOrderDate(stm.format(rs.getDate("ORDER_DATE")));
				orderCircular.setFileUploadFileName(rs
						.getString("ATTACHMENT_NAME"));
				orderCircular.setCreateDate(rs.getTimestamp("CREATE_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn, statement, rs);
		}
		return orderCircular;
	}

	@Override
	public int updateOrderAndCircular(OrderCircular orderCircular, String fileUploadFileName, File fileUpload) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		String input = orderCircular.getOrderDate();
		String query = "UPDATE RPTT_ORDER_AND_CIRCULAR SET SUBJECT = ?,ISSUED_BY = ?,CATEGORY = ?,ORDER_NUMBER = ?,ATTACHMENT_NAME = ?,"
				+ "ORDER_DATE = ?, ATTACHMENT_FILE = ? WHERE ID = ?";
		int i = 0;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderCircular.getSubject());
			pstmt.setString(2, orderCircular.getIssuedBy());
			pstmt.setString(3, orderCircular.getCategory());
			pstmt.setString(4, orderCircular.getOrderNumber());
			pstmt.setString(5, fileUploadFileName);
			try {

				SimpleDateFormat stm = new SimpleDateFormat("dd-MM-yyyy");
				Date d1 = stm.parse(input);
				pstmt.setTimestamp(6, new Timestamp(d1.getTime()));

			} catch (Exception e) {
				e.printStackTrace();
			}
			// pstmt.setString(6, orderCircular.getOrderDate());
			pstmt.setBytes(7, FileUtils.readFileToByteArray(fileUpload));
			pstmt.setInt(8, orderCircular.getId());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return i;
	}

	String convertDate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM dd, yyyy");
		try {
			return sdf1.format(sdf.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
			return d;
		}
	}

	@Override
	public int deleteOrderCircularById(int id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		String query = "delete from RPTT_ORDER_AND_CIRCULAR WHERE ID = ?";
		int i = 0;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return i;
	}

	private int getMaxIdFromTable(String tableName, String colName) {
		Connection connection=null;
		ResultSet rs=null;
		int id = 0;
		String query = "select max(" + colName + ") from " + tableName;
		Statement stmt = null;
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}

		return ++id;
	}


}
