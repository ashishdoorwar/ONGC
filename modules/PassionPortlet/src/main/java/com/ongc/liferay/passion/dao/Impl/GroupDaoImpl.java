package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.model.Employee;
import com.ongc.liferay.passion.model.Group;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.util.NameFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GroupDaoImpl {


	private Connection connection;
	private Statement stmt = null;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private ResultSet rs2 = null;
	NameFormatter nf = new NameFormatter();

	public List fetchGroups(String cpf) {

		List glist = new ArrayList();

		String query = "select a.* from (select row_number() over(order by created_on desc) rownum, PUG.GROUP_ID , PUG.GROUP_NAME , (select count(INVITEE) from PSN_USER_GRP_INVITEE where GROUP_ID=PUG.GROUP_ID AND JOINING_STATUS='Y') as No_of_MEMBERS, "
				+ "TO_CHAR(created_on, 'DD Mon YYYY') AS CREATED_DATE, (SELECT EMP_NAME FROM ONGC_USER_MASTER WHERE CPF_NUMBER=PUG.CREATED_BY ) AS ADMIN, "
				+ "PUG.CREATED_BY,(select count(group_id) from psn_discussion_board where group_id=pug.group_id) as toipcsCount from PSN_USER_GROUP pug order by created_on desc) a where created_by='"
				+ cpf
				+ "' or group_id in (select group_id from psn_user_grp_invitee where invitee='"
				+ cpf + "')";

		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			System.out.println(query);
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Group group = new Group();

				group.setRowNum(rs.getInt(1));
				group.setGroupId(rs.getString(2));
				group.setGroupName(rs.getString(3));
				group.setNoOfMembers(rs.getInt(4));
				group.setCreatedOn(rs.getString(5));
				group.setCreatedByName(rs.getString(6));
				group.setCreatedByCpf(rs.getString(7));
				group.setTopicCount(rs.getInt(8));
				glist.add(group);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}

		return glist;
	}

	public List fetchInvitingGroups(String cpf) {

		List invgroup = new ArrayList();

		String query = "select a.* from (select row_number() over(order by created_on desc) rownum, PUG.GROUP_ID, PUG.GROUP_NAME, PUG.CREATED_BY, (SELECT EMP_NAME FROM ONGC_USER_MASTER WHERE CPF_NUMBER=PUG.CREATED_BY ) AS ADMIN, TO_CHAR(pug.created_on, 'DD Mon YYYY') AS CREATED_DATE from psn_user_group pug where PUG.GROUP_ID in (select PUGI.GROUP_ID from psn_user_grp_invitee pugi where pugi.invitee='"
				+ cpf
				+ "' and pugi.joining_status='N') order by created_on desc ) a";

		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			//system.out.println(query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Group group = new Group();

				group.setRowNum(rs.getInt(1));
				group.setGroupId(rs.getString(2));
				group.setGroupName(rs.getString(3));
				group.setCreatedByCpf(rs.getString(4));
				group.setCreatedByName(rs.getString(5));
				group.setCreatedOn(rs.getString(6));
				invgroup.add(group);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, stmt,rs );
		}

		return invgroup;
	}

	public List<Employee> getEmployeeName(String cpf, String q) {
		List<Employee> employeeList = new ArrayList<Employee>();
		String query = "select cpf_number, emp_name from ongc_user_master where cpf_number != '"
				+ cpf
				+ "' and UPPER(emp_name) like upper('"
				+ q
				+ "%') order by emp_name";
		try {
			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpCpf(rs.getString(1));
				emp.setEmpName(rs.getString(2) + " - " + rs.getString(1));
				employeeList.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, pstmt,rs );
		}
		return employeeList;
	}

	public boolean insertNewGroup(User user, Group group) {

		boolean flage = false;

		String query1 = "insert into psn_user_group(sno, group_id, group_name, created_by, created_on) values (?,?,?,?,current_timestamp)";
		String query2 = "insert into psn_user_grp_invitee(sno, group_id, invitee, joining_status) values (?,?,?,?)";
		
		
		
		try {

			connection = DatasourceConnection.getConnection();
			connection.setAutoCommit(false);
			int groupId=getMaxIdFromTable("psn_user_group", "group_id", connection);
			pstmt = connection.prepareStatement(query1);
			pstmt.setInt(1, getMaxIdFromTable("psn_user_group", "sno", connection));
			pstmt.setInt(2, groupId);
			pstmt.setString(3, group.getGroupName());
			pstmt.setString(4, user.getCpfNo());

			pstmt.executeUpdate();

			for (int i = 0; i < group.getMembers().length; i++) {
				String noMembers = group.getMembers()[i];
				pstmt2 = connection.prepareStatement(query2);

				pstmt2.setInt(1, getMaxIdFromTable("psn_user_grp_invitee", "sno", connection));
				pstmt2.setInt(2, groupId);
				pstmt2.setString(3, noMembers);
				pstmt2.setString(4, "N");

				pstmt2.executeUpdate();

			}
			connection.commit();
			flage = true;
		} catch (Exception ex) {
			try {
				connection.rollback();

			} catch (SQLException e) {

				e.printStackTrace();
			}
			ex.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(pstmt, connection);
			pstmt2 = null;
		}
		return flage;

	}

	public boolean updateGroup(User user, Group group, List al) {

		boolean flage = false;

		ArrayList<String> al1 = new ArrayList<String>();// List of invitees
														// previously
		Iterator itr = al.iterator();
		while (itr.hasNext()) {
			Employee grp = (Employee) itr.next();
			al1.add(grp.getEmpCpf());
		}

		ArrayList<String> al2 = new ArrayList<String>();// List of invitees now
		for (int i = 0; i < group.getMembers().length; i++) {
			al2.add(group.getMembers()[i]);
		}

		String groupName = group.getGroupName();
		String groupId = group.getGroupId();
		// String
		// query1="update psn_user_group set group_name='"+groupName+"' where group_id='"+groupId+"'";
		String query1 = "update psn_user_group set group_name=? where group_id=?";
		String query2 = "delete from psn_user_grp_invitee where group_id=? and invitee= ? ";
		String query3 = "insert into psn_user_grp_invitee(sno, group_id, invitee, joining_status) values (?,?,?,?)";
		try {

			connection = DatasourceConnection.getConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(query1);
			pstmt.setString(1, groupName);
			pstmt.setString(2, groupId);
			pstmt.executeUpdate();
			Iterator itr1 = al1.iterator();
			while (itr1.hasNext()) {
				String m = (String) itr1.next();
				if (!al2.contains(m)) {
					pstmt2 = connection.prepareStatement(query2);
					pstmt2.setString(1, groupId);
					pstmt2.setString(2, m);
					pstmt2.executeUpdate();
				}
			}
			for (int i = 0; i < group.getMembers().length; i++) {

				String noMembers = group.getMembers()[i];

				if (!al1.contains(noMembers)) {

					pstmt3 = connection.prepareStatement(query3);

					pstmt3.setInt(1,
							getMaxIdFromTable("psn_user_grp_invitee", "sno", connection));
					pstmt3.setString(2, groupId);
					pstmt3.setString(3, noMembers);
					pstmt3.setString(4, "N");

					pstmt3.executeUpdate();
				}

			}
			connection.commit();
			flage = true;
		} catch (Exception ex) {
			try {
				connection.rollback();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(pstmt, connection);
			pstmt2 = null;
			pstmt3 = null;
		}
		return flage;

	}

	public List fetchMembers(String gid) {

		List mList = new ArrayList();
		List memList = new ArrayList();

		String query = "select PUG.CREATED_BY as member, (select emp_name from ongc_user_master where cpf_number=pug.created_by ) as name from psn_user_group pug where pug.group_id='"
				+ gid
				+ "' union "
				+ "select PUGI.INVITEE as member, (select emp_name from ongc_user_master where cpf_number=pugi.invitee ) as name from psn_user_grp_invitee pugi where pugi.group_id='"
				+ gid + "' and pugi.joining_status='Y'";
		Connection con = null;
		PreparedStatement psm = null;
		try {
			con = DatasourceConnection.getConnection();
			psm = con.prepareStatement(query);
			//system.out.println(query);
			ResultSet rs2 = psm.executeQuery();
			while (rs2.next()) {
				Group group = new Group();
				group.setMemberCpf(rs2.getString(1));
				group.setMemberName(rs2.getString(2));
				//group.setSubPassionName(getPassionByCpf(rs2.getString(1), con));
				mList.add(group);
			}
			Iterator itr=mList.iterator();
			while(itr.hasNext()){
				Group group = (Group)itr.next();
				group.setSubPassionName(getPassionByCpf(group.getMemberCpf()));
				memList.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(con, psm, rs2);

		}

		return mList;

	}

	public List fetchOnlyMembers(String gid) {

		List mList = new ArrayList();

		String query = "select PUGI.INVITEE as member, (select emp_name from ongc_user_master where cpf_number=pugi.invitee ) as name from psn_user_grp_invitee pugi where pugi.group_id='"
				+ gid + "'";
		Connection con = null;
		PreparedStatement psm = null;
		try {
			con = DatasourceConnection.getConnection();
			psm = con.prepareStatement(query);
			ResultSet rs2 = psm.executeQuery();
			Employee emp = null;
			while (rs2.next()) {
				emp = new Employee();
				emp.setEmpCpf(rs2.getString(1));
				emp.setEmpName(rs2.getString(2) + " - " + rs2.getString(1));
				// group.setMemberName(nf.InitialCaps(rs2.getString(2)));
				mList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(con, psm, rs2);

		}

		return mList;

	}

	private String getPassionByCpf(String cpfNo) {
		String sp = "";
		String query1 = "select pmsp.sub_passion from psn_mypassion pmp,psn_ms_subpassion pmsp where PMP.PASSION_SUB_CATEGORY=PMSP.SUBPASSION_ID and PMP.CPF_NO='"
				+ cpfNo + "'";

		Connection con = DatasourceConnection.getConnection();
		Statement stmt1 = null;
		try {
			stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			while (rs1.next()) {
				sp = sp + "/" + rs1.getString("sub_passion");
			}

			sp = sp.replaceFirst("/", "");
		} catch (SQLException e) {
			e.printStackTrace();
			//system.out.println("eeeee" + e);
		} finally {
			// DatasourceConnection.closeConnection(rs1,stmt1, conn);
			rs1 = null;
			stmt1 = null;
		}

		return sp;
	}

	public boolean deleteGroup(String gid) {
		boolean flage = false;
		String query1 = "delete from PSN_USER_GRP_INVITEE where group_id='"
				+ gid + "'";
		String query2 = "delete from PSN_USER_GROUP where group_id='" + gid
				+ "'";
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = DatasourceConnection.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(query1);
			pstmt2 = con.prepareStatement(query2);
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
			con.commit();
			flage = true;
		} catch (Exception ex) {
			try {
				con.rollback();

			} catch (SQLException e) {

				e.printStackTrace();
			}
			ex.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(pstmt1, con);
			pstmt2 = null;
		}
		return flage;
	}

	public boolean joinStatus(String member, String gid) {
		boolean flage = false;

		String query = "update psn_user_grp_invitee set joining_status='Y' where group_id='"
				+ gid + "' and invitee='" + member + "'";
		try {

			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			//system.out.println(query);
			pstmt.executeUpdate();
			flage = true;
		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {
			DatasourceConnection.closeConnection(pstmt, connection);
		}

		return flage;
	}

	public boolean declineStatus(String member, String gid) {
		boolean flage = false;
		//system.out.println("decline dao");
		String query = "delete from psn_user_grp_invitee where group_id='"
				+ gid + "' and invitee='" + member + "'";

		try {

			connection = DatasourceConnection.getConnection();
			pstmt = connection.prepareStatement(query);
			//system.out.println(query);
			pstmt.executeUpdate();
			connection.commit();
			flage = true;
		} catch (Exception ex) {
			//system.out.println(ex);

		} finally {
			DatasourceConnection.closeConnection(pstmt, connection);
		}

		return flage;
	}

	public String getGroupNameById(String gid) {

		String gname = "";

		String query = "select GROUP_NAME from PSN_USER_GROUP where GROUP_ID='"
				+ gid + "'";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				gname = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}

		return gname;

	}

	private int getMaxIdFromTable(String tableName, String colName, Connection conn) {
		int id = 0;

		String query = "select max((" + colName + ")) from " + tableName;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//DatasourceConnection.closeConnection(rs, stmt, connection);
		}

		return ++id;
	}


}
