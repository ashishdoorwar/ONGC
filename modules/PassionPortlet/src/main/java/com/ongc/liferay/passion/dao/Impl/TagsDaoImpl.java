package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.TagsDao;
import com.ongc.liferay.passion.model.DiscussionTags;
import com.ongc.liferay.passion.model.PassionTags;
import com.ongc.liferay.passion.model.WorkItemTags;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TagsDaoImpl implements TagsDao{

	private Connection connection;
	
	public List fetchDiscussionTags(String cpfNo){
		List tagList=new ArrayList();
		
		String query="select a.* from (select pdb.tags, (select count(PARENT_REPLY_ID) from PSN_DISCUSSION_BOARD_REPLIES where Topic_id=pdb.topic_id) as No_of_replies, PDB.GROUP_ID from PSN_DISCUSSION_BOARD pdb) a where a.group_id is null or a.group_id='-1' or a.group_id in (select GROUP_ID from PSN_USER_GROUP where created_by='"+cpfNo+"' or group_id in (select group_id from psn_user_grp_invitee where invitee='"+cpfNo+"'))";
		connection=DatasourceConnection.getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				DiscussionTags dTags=new DiscussionTags();
				dTags.setTags(rs.getString(1));
				dTags.setTagsNo(rs.getInt(2));
				tagList.add(dTags);
			}
			
		} catch (Exception e) {
			//system.out.println(e);
		}
		finally{
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}
		
		
		
		return tagList;
	}
	
	public List fetchWorkItemTags(){
		List tagList=new ArrayList();
		
		String query="select * from(select x.*, rank() over (partition by cpf order by endorse_count desc, file_name ) r from ((select photo_id as file_id, photo_name as caption, description, file_name, 'photo' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pp.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pp.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PP.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PP.PSN_MP_ID)) as sub_psn from psn_photo pp) union (select audio_id as file_id, audio_name as caption, description, file_name, 'audio' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pa.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pa.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PA.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PA.PSN_MP_ID)) as sub_psn from psn_audio pa) union (select video_id as file_id, video_name as caption, description, file_name, 'video' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pv.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pv.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PV.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PV.PSN_MP_ID)) as sub_psn from psn_video pv) union (select docs_id as file_id, docs_name as caption, description, file_name, 'docs' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pd.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pd.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PD.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PD.PSN_MP_ID)) as sub_psn from psn_docs pd) ) x order by endorse_count desc) s where r=1";
		connection=DatasourceConnection.getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		System.out.println(query);
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				WorkItemTags wiTags=new WorkItemTags();
				wiTags.setTagsNo(rs.getInt(6));
				wiTags.setEmpCpf(rs.getString(7));
				wiTags.setTags(rs.getString(8));
				String url=rs.getString(4)+"//"+rs.getString(1)+"//"+rs.getString(2)+"//"+rs.getString(3)+"//"+rs.getString(6)+"//"+rs.getString(7)+"//"+rs.getString(9)+"//"+rs.getString(10)+"//"+rs.getString(5);
				wiTags.setUrl(url);
				tagList.add(wiTags);
			}
			
		} catch (Exception e) {
			//system.out.println(e);
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}
		
		
		
		return tagList;
	}
	
	public List fetchPassionTags(){
		List tagList=new ArrayList();
		
		String query="select a.* from (select row_number() over(order by(count(passion_sub_category))) rownum, passion_sub_category, (select sub_passion from psn_ms_subpassion where subpassion_id=passion_sub_category) as subpsn_name, count(passion_sub_category) as enrollments from psn_mypassion group by passion_sub_category  order by count(passion_sub_category) desc)a where rownum<=20";
		connection=DatasourceConnection.getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				PassionTags pTags=new PassionTags();
				pTags.setSubPassionId(rs.getString(2));
				pTags.setTags(rs.getString(3));
				pTags.setTagsNo(rs.getInt(4));
				tagList.add(pTags);
			}
			
		} catch (Exception e) {
			//system.out.println(e);
		}
		finally{
			DatasourceConnection.closeConnection(connection, stmt, rs);
		}
		
		
		
		return tagList;
	}
	
}
