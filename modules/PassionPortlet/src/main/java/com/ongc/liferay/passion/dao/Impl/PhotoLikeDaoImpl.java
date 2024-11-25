package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.model.PhotoLike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoLikeDaoImpl {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet set;

	public boolean savePhotoLike(PhotoLike dto) {
		String query = "INSERT INTO RPTTX_PHOTOLIKE(ID_KEY,CPF_NO, PHOTO_ID)VALUES(RPT_PHOTO_LIKE_SEQ.nextval,?,?)";
		boolean flage=false;
		try {
			conn = DatasourceConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getCpfNo());
			pstmt.setString(2, dto.getPhotoId());
			flage = pstmt.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flage;
	}
	
	public boolean getPhotoLikeByCpfNo(String cpf , String photoid){
		String query = "SELECT ID_KEY FROM RPTTX_PHOTOLIKE WHERE CPF_NO =? AND PHOTO_ID = ? ";
		boolean flag = true;
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cpf);
			pstmt.setString(2, photoid);
			set=pstmt.executeQuery();
			flag  = set.next();
		} catch (SQLException ex) { 
			ex.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return flag;
	}
	public int getTotalPhotoCount(String photoid){
		String query = "SELECT count(ID_KEY) as TOTAL FROM RPTTX_PHOTOLIKE WHERE PHOTO_ID = ? ";
		int i = 0 ;
		try {
			conn=DatasourceConnection.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, photoid);
			set=pstmt.executeQuery();
			while(set.next()){
				i = set.getInt("TOTAL");
			}
		} catch (SQLException ex) { 
			//ex.printStackTrace(//system.out);
			ex.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(pstmt, conn);
		}
		return i;
	}
}
