package com.ongc.liferay.reports.dao.Impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ongc.liferay.reports.connection.DatasourceConnection;
import com.ongc.liferay.reports.dao.PageLikeDao;
import com.ongc.liferay.reports.model.PageLike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageLikeDaoImpl implements PageLikeDao{
	private static final Log LOGGER = LogFactoryUtil.getLog(PageLikeDaoImpl.class);

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet set = null;

	public boolean savePageLike(PageLike dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "INSERT INTO RPTTX_PAGELIKE(CPF_NO, PAGE_ID, CONTENT_ID,SECTION_NAME) VALUES(?,?,?,?)";
		boolean flage = true;
		try {
			if (!getPageLikeByCpfNo(dto.getCpfNo(), dto.getPageId())) {
				conn = DatasourceConnection.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getCpfNo());
				pstmt.setString(2, dto.getPageId());
				pstmt.setString(3, dto.getContentId());
				pstmt.setString(4, dto.getSectionName());
				flage = this.pstmt.executeUpdate() > 0;
			}
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flage;
	}

	public boolean getPageLikeByCpfNo(String cpf, String pageid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT post_date FROM RPTTX_PAGELIKE WHERE CPF_NO =? AND PAGE_ID = ? ";
		boolean flag = true;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			pstmt.setString(2, pageid);
			set = this.pstmt.executeQuery();
			flag = set.next();
		} catch (SQLException ex) {
			LOGGER.error("erro"+ex);
		} finally {
				DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flag;
	}

	public String getPageLikeforSparrow(String cpf, String pageid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT post_date FROM RPTTX_PAGELIKE WHERE CPF_NO =? AND PAGE_ID = ? ";
		String stat = "NOT";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			pstmt.setString(2, pageid);
			set = this.pstmt.executeQuery();
			while (set.next()) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf1.parse(set.getDate("post_date").toString());
				Date date2 = sdf1.parse("2016-01-01");
				if (date2.after(date1)) {
					stat = "PREVIOUS";
				} else if (date1.after(date2)) {
					stat = "CURRENT";
				}
			}

		} catch (SQLException | ParseException ex) {
			LOGGER.error("erro"+ex);
		} finally {
				DatasourceConnection.closeConnection(conn,pstmt);
		}
		return stat;
	}

	public int getPageLikeCount(String pageid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;		
		String query = "SELECT count(ID_KEY) as TOTAL FROM RPTTX_PAGELIKE WHERE PAGE_ID = ? ";
		int i = 0;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, pageid);
			set = this.pstmt.executeQuery();
			while (set.next())
				i = set.getInt("TOTAL");
		} catch (SQLException ex) {
			LOGGER.error("erro"+ex);
		} finally {
				DatasourceConnection.closeConnection(conn,pstmt);
		}
		return i;
	}

	public String getPageLikeEmpName(String pageid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT u.CPF_NUMBER,u.EMP_NAME FROM RPTTX_PAGELIKE p,ONGC_USER_MASTER u WHERE u.CPF_NUMBER=p.cpf_no and p.PAGE_ID = ? ";
		int i = 0;
		String details = "";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, pageid);
			set = this.pstmt.executeQuery();
			while (set.next())
				details = details + " <empcpfno>"
						+ set.getString("CPF_NUMBER")
						+ "</empcpfno><empname>"
						+ set.getString("EMP_NAME") + "</empname>";

		} catch (SQLException ex) {
			LOGGER.error("erro"+ex);
		} finally {
				DatasourceConnection.closeConnection(conn,pstmt);
		}
		return details;
	}

	public boolean check_cpf(String cpf) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select cpfnum from leadership_board where cpfnum='"
				+ cpf + "'";
		boolean flage = false;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			if (!res.next()) {
				flage = true;
			}
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
				DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flage;

	}

	public boolean saveUser(String cpf, String chk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "insert into leadership_board(lid,cpfnum,likes) VALUES(RPTT_LBOARD.nextval,?,?)";
		boolean flage = false;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			pstmt.setInt(2, 1);
			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flage;
	}

	public boolean updateUser(String cpf, String chk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "update leadership_board set likes=? where cpfnum = ? ";
		boolean flage = false;
		try {
			int like = getLikesCount(cpf);
			like = like + 1;
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, like);
			pstmt.setString(2, cpf);
			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flage;
	}

	public int getLikesCount(String cpf) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "Select likes from leadership_board where cpfnum= ? ";
		int count = 0;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			set = pstmt.executeQuery();
			while (set.next()) {
				count = set.getInt("likes");
			}
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return count;
	}

	public boolean saveDislikeUser(String cpf, String chk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "insert into leadership_board(lid,cpfnum,dislikes) VALUES(RPTT_LBOARD.nextval,?,?)";
		boolean flage = false;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			pstmt.setInt(2, 1);

			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flage;
	}

	public boolean updateDislike(String cpf, String chk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "update leadership_board set dislikes=? where cpfnum = ? ";
		boolean flage = false;
		try {
			int dislike = getDisLikesCount(cpf);
			dislike = dislike + 1;
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dislike);
			pstmt.setString(2, cpf);

			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn,pstmt);
		}
		return flage;
	}

	public int getDisLikesCount(String cpf) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "Select dislikes from leadership_board where cpfnum= ? ";
		int count = 0;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			set = pstmt.executeQuery();
			while (set.next()) {
				count = set.getInt("dislikes");
			}
		} catch (Exception ex) {
			LOGGER.error("erro"+ex);
		} finally {
			DatasourceConnection.closeConnection(conn, pstmt, set);
		}
		return count;
	}

	public String getPostDate(String pageid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = " select created_on from RPTT_STORY_TITLE where page_link = ? order by created_on desc limit 1 ";
		String stat = "";
		String postdate = "";
		try {
			conn = DatasourceConnection.getConnection();
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, pageid);
			set = this.pstmt.executeQuery();
			while (set.next()) {
				String dd = set.getString("created_on");
				String x1=dd.substring(0,10);
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			    SimpleDateFormat format2 = new SimpleDateFormat("dd/MMM/yyyy");
			    Date date = format1.parse(x1);
			   String x=format2.format(date);
				String xx[]=x.split("/");
				postdate=xx[0]+" "+xx[1];
			}
		} catch (SQLException | ParseException ex) {
			LOGGER.error("erro"+ex);
		} finally {
				DatasourceConnection.closeConnection(conn,pstmt);
		}
		return postdate;
	}
}
