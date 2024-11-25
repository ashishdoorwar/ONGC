package com.ongc.liferay.passion.dao.Impl;

import com.ongc.liferay.passion.util.NameFormatter;
import com.ongc.liferay.passion.model.HomeNews;
import com.ongc.liferay.passion.model.HomeTrending;
import com.ongc.liferay.passion.model.HomeUpdates;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.model.UserAdmin;
import com.ongc.liferay.passion.connection.DatasourceConnection;
import com.ongc.liferay.passion.dao.HomeDao;
import com.ongc.liferay.passion.model.HomeData;
import com.ongc.liferay.passion.model.HomeElite;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeDaoImpl implements HomeDao {

	
	private Connection connection;
	private Statement stmt=null;
	private ResultSet rs=null ;
	NameFormatter nf=new NameFormatter();
	
	
	//This Method is for fetching List of Passion Categories, which are enrolled by most number of people.
	
	public List<HomeData> fetchMostEnrolled() {
		
		List al=new ArrayList<HomeData>();
	
		//String query="select * from (select passion_category, (select passion_name from psn_ms_passion where passion_id=passion_category) as psn_name, count(passion_category) as enrollments from psn_mypassion group by passion_category  order by count(passion_category) desc) where rownum<=3";
		
		String query="SELECT E.ROWNUM, E.PASSION_SUB_CATEGORY, E.PASSION_NAME, E.SUBPSN_NAME, E.ENROLLMENTS FROM ( SELECT ROW_NUMBER() OVER(ORDER BY COUNT (A.PASSION_SUB_CATEGORY) DESC) AS ROWNUM, A.PASSION_SUB_CATEGORY,X.PASSION_NAME, (SELECT SUB_PASSION FROM PSN_MS_SUBPASSION WHERE SUBPASSION_ID = A.PASSION_SUB_CATEGORY) AS SUBPSN_NAME, COUNT (A.PASSION_CATEGORY) AS ENROLLMENTS  FROM PSN_MYPASSION A,PSN_MS_PASSION X WHERE X.PASSION_ID=A.PASSION_CATEGORY GROUP BY A.PASSION_SUB_CATEGORY,X.PASSION_NAME ORDER BY COUNT (A.PASSION_SUB_CATEGORY) DESC) E WHERE E.ROWNUM <= 3";
		try {
			connection = DatasourceConnection.getConnection();
			stmt = connection.createStatement();
			
			//system.out.println(query);
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				HomeData homeData=new HomeData();
				
				homeData.setPassionSubCatId(rs.getString(2));
				homeData.setPassionName(rs.getString(3));
				homeData.setSubpassionName(rs.getString(4));
				homeData.setNoOfEnrollMents(rs.getInt(5));
				
				al.add(homeData);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return al;
		}
		
		
	
	//This method is for getting subPassion of a user in single String separated by /.
	
	private String subPassion(String cpfNo,Connection conn){
		
		String sp="";
		String query1="select pmsp.sub_passion from psn_mypassion pmp,psn_ms_subpassion pmsp where PMP.PASSION_SUB_CATEGORY=PMSP.SUBPASSION_ID and PMP.CPF_NO='"+cpfNo+"'";
		Statement stmt2=null;ResultSet rs2=null;
		try {
			stmt2 = conn.createStatement();

			//system.out.println(query1);
			rs2 = stmt2.executeQuery(query1);

			while(rs2.next()){
				sp=sp+"/"+rs2.getString("sub_passion");
			}
			
			sp=sp.replaceFirst("/", "");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			stmt2=null;rs2=null;
			//DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return sp;
		
	}
	
	//This method is for fetching list of News from database.
	
	public List news(){
		List nl=new ArrayList();
		
		String query="select NEW_ID, TITLE, NEWS_DESC, TO_CHAR(created_on, 'DD Mon YYYY') from PSN_MS_NEWS where PUBLISHED='Y' order by CREATED_ON desc";
		try {
			connection=DatasourceConnection.getConnection();
			stmt=connection.createStatement();
			////system.out.println(query);
			rs = stmt.executeQuery(query);

			while(rs.next()){
				HomeNews hnews=new HomeNews();
				hnews.setNewsId(rs.getString(1));
				hnews.setNewsTitle(rs.getString(2));
				hnews.setNewsDesc(rs.getString(3));
				hnews.setNewsCreatedOn(rs.getString(4));
				nl.add(hnews);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}		
		return nl;
		
	}
	
	//This method is for List of trending employees in LoggedIn user's passion.
	
	public List myTrending(String cpfNo){
		List<HomeTrending> tl=new ArrayList<HomeTrending>();
		String query="select passion_category, (select passion_name from psn_ms_passion where passion_id=passion_category) as passion_name, passion_sub_category, (select sub_passion from psn_ms_subpassion where subpassion_id=passion_sub_category) as sub_passion from psn_mypassion where cpf_no='"+cpfNo+"'";
		try {
			connection=DatasourceConnection.getConnection();
			stmt=connection.createStatement();
			////system.out.println(query);
			System.out.print("-----------------------"+query);
			rs = stmt.executeQuery(query);

			while(rs.next()){
				HomeTrending htrending=new HomeTrending();
				htrending.setPassionId(rs.getString(1));
				htrending.setPassion(rs.getString(2));
				htrending.setSubPassionId(rs.getString(3));
				htrending.setSubPassion(rs.getString(4));
				//htrending.setData(hd.getData(rs.getString(3), connection));
				tl.add(htrending);
			}
			
			for(HomeTrending htrending:tl){
				htrending.setData(getData(htrending.getSubPassionId(), connection));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		return tl;
	}
	
	
	//This method is for List of trending employees in all passion.
	
	public List<HomeTrending> trending(){
		List<HomeTrending> tl=new ArrayList<HomeTrending>();
		String query="select pmsp.PASSION_ID, (Select pmp.PASSION_NAME from PSN_MS_PASSION pmp where PMP.PASSION_ID=PMSP.PASSION_ID ) as PASSION_NAME, pmsp.SUBPASSION_ID, pmsp.SUB_PASSION,(select count(passion_sub_category) from psn_mypassion where passion_sub_category=pmsp.subpassion_id) as enrollments  from PSN_MS_SUBPASSION pmsp ORDER BY enrollments desc";
		try {
			connection=DatasourceConnection.getConnection();
			stmt=connection.createStatement();
			////system.out.println(query);
			rs = stmt.executeQuery(query);

			while(rs.next()){
				HomeTrending htrending=new HomeTrending();
				htrending.setPassionId(rs.getString(1));
				htrending.setPassion(rs.getString(2));
				htrending.setSubPassionId(rs.getString(3));
				htrending.setSubPassion(rs.getString(4));
				tl.add(htrending);
			}
			stmt.close();
			for(HomeTrending htrending:tl){
				htrending.setData(getData(htrending.getSubPassionId(), connection));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return tl;
	}
	
	
	//This method is for fetching detail of trending persons of Passions.
	
	public List getData(String subPassionId, Connection conn){
		List data=new ArrayList();
		String query="select * from((select endorse_count, photo_id as file_id, file_name, photo_name as caption, description, psn_mp_id,(select pmp.passion_sub_category from psn_mypassion pmp where pmp.psn_mp_id=psn_photo.psn_mp_id) as sub_passion, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_photo.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_photo.psn_mp_id ) ) as Emp_Name, 'photo' as type from psn_photo) "+
						" union (select endorse_count, audio_id as file_id, file_name, audio_name as caption, description, psn_mp_id,(select pmp.passion_sub_category from psn_mypassion pmp where pmp.psn_mp_id=psn_audio.psn_mp_id) as sub_passion, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_audio.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_audio.psn_mp_id ) ) as Emp_Name, 'audio' as type from psn_audio) "+
						" union (select endorse_count, video_id as file_id, file_name, video_name as caption, description, psn_mp_id,(select pmp.passion_sub_category from psn_mypassion pmp where pmp.psn_mp_id=psn_video.psn_mp_id) as sub_passion, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_video.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_video.psn_mp_id ) ) as Emp_Name, 'video' as type from psn_video) "+
                        " union (select endorse_count, docs_id as file_id, file_name, docs_name as caption, description, psn_mp_id,(select pmp.passion_sub_category from psn_mypassion pmp where pmp.psn_mp_id=psn_docs.psn_mp_id) as sub_passion, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_docs.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=psn_docs.psn_mp_id ) ) as Emp_Name, 'docs' as type from psn_docs) "+
						" order by endorse_count desc) as rs where sub_passion='"+subPassionId+"' and endorse_count !=0 FETCH FIRST 3 ROWS ONLY ";
		Statement stmt=null;	
		ResultSet rs=null;
		try {
			
			stmt=conn.createStatement();
			////system.out.println(query);
			rs = stmt.executeQuery(query);

			while(rs.next()){
				HomeTrending htrending=new HomeTrending();
				htrending.setEndorseCount(rs.getInt(1));
				htrending.setFileId(rs.getString(2));
				htrending.setFileName(rs.getString(3));
				htrending.setCaption(rs.getString(4));
				htrending.setDescription(rs.getString(5));
				htrending.setMyPassionId(rs.getInt(6));
				htrending.setCpfNo(rs.getString(8));
				//htrending.setEmpName(nf.InitialCaps(rs.getString(6)));
				htrending.setEmpName(rs.getString(9));
				htrending.setFileType(rs.getString(10));
				data.add(htrending);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(stmt,rs);
		}
		return data;
	}

	//This method is for fetching List of members of Elite Group.
	
	public List fetchElite(){
		List<HomeElite> elite=new ArrayList<HomeElite>();
		
		String query="select * from(select x.*, rank() over (partition by cpf order by endorse_count desc, file_name ) r from ((select photo_id as file_id, photo_name as caption, description, file_name, 'photo' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pp.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pp.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PP.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PP.PSN_MP_ID)) as sub_psn from psn_photo pp) union (select audio_id as file_id, audio_name as caption, description, file_name, 'audio' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pa.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pa.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PA.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PA.PSN_MP_ID)) as sub_psn from psn_audio pa) union (select video_id as file_id, video_name as caption, description, file_name, 'video' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pv.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pv.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PV.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PV.PSN_MP_ID)) as sub_psn from psn_video pv) union (select docs_id as file_id, docs_name as caption, description, file_name, 'docs' as type, endorse_count, (select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pd.psn_mp_id ) as cpf, (select OUM.EMP_NAME from ONGC_USER_MASTER oum where OUM.CPF_NUMBER=(select pmp.cpf_no from psn_mypassion pmp where pmp.psn_mp_id=pd.psn_mp_id ) ) as Emp_Name, (select passion_name from psn_ms_passion where passion_id=(select passion_category from psn_mypassion where psn_mp_id=PD.PSN_MP_ID)) as psn, (select sub_passion from psn_ms_subpassion where subpassion_id=(select passion_sub_category from psn_mypassion where psn_mp_id=PD.PSN_MP_ID)) as sub_psn from psn_docs pd) ) x order by endorse_count desc) s where r=1"; 

		try {
			connection=DatasourceConnection.getConnection();
			stmt=connection.createStatement();
			rs=		stmt.executeQuery(query);
			while(rs.next()){
				HomeElite helite=new HomeElite();
				helite.setFileId(rs.getString(1));
				helite.setFileType(rs.getString(5));
				elite.add(helite);
			}
			stmt.close();
			for(HomeElite helite:elite){
				if(helite!=null){
					this.eliteFileDetail(connection, helite);
					this.eliteEmpDetail(connection, helite, helite.getPassionId() );
				}
				
			}
			
			//system.out.println(elite.size());
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return elite;
		
	}
	
	//This method is for fetching detail of file of Elite Group's member.
	
	private void eliteFileDetail(Connection connection2, HomeElite helite) {
		
		String fileType=helite.getFileType();
		String fileId=helite.getFileId();
		String query="";
		
		if(fileType.equalsIgnoreCase("photo")){
			query="select photo_name, psn_mp_id, file_name, endorse_count, description  from psn_photo where photo_id='"+fileId+"'";
		}
		if(fileType.equalsIgnoreCase("audio")){
			query="select audio_name, psn_mp_id, file_name, endorse_count, description  from psn_audio where audio_id='"+fileId+"'";
		}
		if(fileType.equalsIgnoreCase("video")){
			query="select video_name, psn_mp_id, file_name, endorse_count, description  from psn_video where video_id='"+fileId+"'";
		}
		if(fileType.equalsIgnoreCase("docs")){
			query="select docs_name, psn_mp_id, file_name, endorse_count, description  from psn_docs where docs_id='"+fileId+"'";
		}
		Statement stmt3=null;ResultSet rs3=null;
		try {
			stmt3=connection2.createStatement();
			rs3=stmt3.executeQuery(query);
			//system.out.println(query);
			while(rs3.next()){
				
				helite.setFileCaption(rs3.getString(1));
				helite.setPassionId(rs3.getString(2));
				helite.setFileName(rs3.getString(3));
				helite.setEndorse(rs3.getInt(4));
				helite.setFileDesc(rs3.getString(5));
				//this.eliteEmpDetail(connection2, helite, rs3.getString(2) );
			}
			
		}
	 catch (SQLException e) {
		 e.printStackTrace();
	} finally{
		stmt3=null;rs3=null;
	}		
		
	}
	
	//This method is for detail of member of Elite Group.
	
	private void eliteEmpDetail(Connection connection2, HomeElite helite, String psnId) {
		
		//String query="select cpf_no, (select oum.emp_name from ongc_user_master oum where oum.cpf_number=cpf_no) emp_name from psn_mypassion where psn_mp_id='"+psnId+"'";
		String query="SELECT a.cpf_no, (SELECT oum.emp_name FROM ongc_user_master oum WHERE oum.cpf_number = cpf_no) emp_name, b.passion_name,c.sub_passion,(SELECT OUM.PLACE_POSTING FROM ongc_user_master oum WHERE oum.cpf_number = cpf_no) emp_loc FROM psn_mypassion a, psn_ms_passion b, psn_ms_subpassion c WHERE psn_mp_id = '"+psnId+"' AND a.passion_sub_category = c.subpassion_id   AND a.passion_category = b.passion_id";
		Statement stmt1=null;ResultSet rs1=null;
		try {
			stmt1=connection2.createStatement();
			rs1=stmt1.executeQuery(query);
			//system.out.println(query);
			while(rs1.next()){
				//system.out.println(3);
				helite.setEmpCpf(rs1.getString(1));
				//helite.setEmpName(nf.InitialCaps(rs1.getString(2)));
				helite.setEmpName(rs1.getString(2));
				//helite.setEpmPassion(this.subPassion(rs1.getString(1), connection2));
				helite.setEpmPassion(rs1.getString(3));
				helite.setEpmSubPassion(rs1.getString(4));
				helite.setEmpLoc(rs1.getString(5));
			}
			
		}
	 catch (SQLException e) {
		 e.printStackTrace();
	} finally{
		stmt1=null;rs1=null;
	}		
	}
	//This method is for recent activities updates.

	public List fetchUpdates(String cpf){
		List<HomeUpdates> updates=new ArrayList<HomeUpdates>();
		
		/*String query="select a.* , ROW_NUMBER() over(ORDER BY action_on DESC) ROWNUM from((select 'topicid' as topic_id, 'topic_name' as topic_name, 1 as file_id, 'caption' as caption, Z.PASSION_NAME as Passion_Name, (select sub_passion from psn_ms_subpassion where subpassion_id=passion_sub_category) sub_passion, a.cpf_no as action_by, (select emp_name from ongc_user_master where cpf_number=cpf_no) as emp_name, "+
							 "a.created_on as action_on, 'enrolled' as action "+
							"from psn_mypassion a, psn_ms_passion z where cpf_no!='"+cpf+"' and a.passion_sub_category in (select passion_sub_category from psn_mypassion where cpf_no='"+cpf+"')and passion_sub_category=Z.PASSION_ID) "+
							"union "+
							"(select 'topicid' as topic_id, 'topic_name' as topic_name, 2 as file_id, 'caption' as caption, Z.PASSION_NAME as Passion_Name, a.sub_passion, 'admin' as action_by, 'admin' as emp_name, a.created_on as action_on, 'newpassion' as action from psn_ms_subpassion a,PSN_MS_PASSION z where A.PASSION_ID=Z.PASSION_ID) "+
							"union "+
							"(select topic_id, topic_name, 3 as file_id, 'caption' as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, created_by as action_by, (select emp_name from ongc_user_master where cpf_number=pdb.created_by) as emp_name, created_on as action_on, 'newtopic' as action from psn_discussion_board pdb where (group_id in (select group_id from psn_user_group where created_by='"+cpf+"') "+
							" or group_id in (select group_id from psn_user_grp_invitee where invitee='"+cpf+"' and joining_status='Y')) and created_by!='"+cpf+"') "+
							"union "+
							"((select '1' as topic_id, '2' as topic_name, photo_id as file_id, (select photo_name from psn_photo where photo_id=ppce.photo_id) as caption,'Passion' as Passion_Name,'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=ppce.action_by) as emp_name, comments_on as action_on, 'photoendorsed' as action from PSN_PHOTO_CMNTS_ENDRSMNT ppce where comments is null and photo_id in "+
							"(select photo_id from psn_photo where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"' union "+
							"select '1' as topic_id, '2' as topic_name, audio_id as file_id, (select audio_name from psn_audio where audio_id=pace.audio_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=pace.action_by) as emp_name, endorsed_on as action_on, 'audioendorsed' as action from PSN_AUDIO_CMNTS_ENDRSMNT pace where comments is null and audio_id in "+
							"(select audio_id from psn_audio where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"' union "+
							"select '1' as topic_id, '2' as topic_name, video_id as file_id, (select video_name from psn_video where video_id=pvce.video_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=pvce.action_by) as emp_name, endorsed_on as action_on, 'videoendorsed' as action from PSN_VIDEO_CMNTS_ENDRSMNT pvce where comments is null and video_id in "+
							"(select video_id from psn_video where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"' union "+
							"select '1' as topic_id, '2' as topic_name, docs_id as file_id, (select docs_name from psn_docs where docs_id=pdce.docs_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=pdce.action_by) as emp_name, endorsed_on as action_on, 'docsendorsed' as action from PSN_DOCS_CMNTS_ENDRSMNT pdce where comments is null and docs_id in "+
                            "(select docs_id from psn_docs where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"') "+
							"union "+
							"(select '1' as topic_id, '2' as topic_name, photo_id as file_id, (select photo_name from psn_photo where photo_id=ppce.photo_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=ppce.action_by) as emp_name, comments_on as action_on, 'photocommented' as action from PSN_PHOTO_CMNTS_ENDRSMNT ppce where comments is not null and photo_id in "+
							"(select photo_id from psn_photo where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"' union "+
							"select '1' as topic_id, '2' as topic_name, audio_id as file_id, (select audio_name from psn_audio where audio_id=pace.audio_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=pace.action_by) as emp_name, comments_on as action_on, 'audiocommented' as action from PSN_AUDIO_CMNTS_ENDRSMNT pace where comments is not null and audio_id in "+
							"(select audio_id from psn_audio where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"' union "+
							"select '1' as topic_id, '2' as topic_name, video_id as file_id, (select video_name from psn_video where video_id=pvce.video_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=pvce.action_by) as emp_name, comments_on as action_on, 'videocommented' as action from PSN_VIDEO_CMNTS_ENDRSMNT pvce where comments is not null and video_id in "+
							"(select video_id from psn_video where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"' union "+
							"select '1' as topic_id, '2' as topic_name, docs_id as file_id, (select docs_name from psn_docs where docs_id=pdce.docs_id) as caption,'Passion' as Passion_Name, 'subpassion' as sub_passion, action_by, (select emp_name from ongc_user_master where cpf_number=pdce.action_by) as emp_name, comments_on as action_on, 'docscommented' as action from PSN_DOCS_CMNTS_ENDRSMNT pdce where comments is not null and docs_id in "+
        					"(select docs_id from psn_docs where psn_mp_id in (select psn_mp_id from psn_mypassion where cpf_no='"+cpf+"')) and action_by!='"+cpf+"')) " +
        					" UNION "+
        					" SELECT '1' as topic_id, '2' as topic_name, z.photo_id AS file_id, Z.PHOTO_NAME AS caption, Y.PASSION_NAME as Passion_Name, " +
        					"(SELECT sub_passion  FROM psn_ms_subpassion  WHERE subpassion_id = passion_sub_category) sub_passion," +
        					" cpf_no AS action_by, (SELECT emp_name FROM ongc_user_master WHERE cpf_number =cpf_no)  AS emp_name, Z.UPLOADED_ON AS action_on," +
        					" 'photoadded' AS action FROM psn_mypassion b, psn_photo z, PSN_MS_PASSION y   WHERE b.passion_sub_category in (SELECT a.passion_sub_category   FROM psn_mypassion a  WHERE a.cpf_no = '"+cpf+"') " +
        					" AND b.cpf_no != '"+cpf+"' and B.PASSION_CATEGORY=Y.PASSION_ID AND  b.psn_mp_id = z.psn_mp_id UNION  SELECT '1' AS topic_id, " +
        					" '2' as topic_name, z.audio_id AS file_id,  z.audio_name AS caption, Y.PASSION_NAME as Passion_Name, (SELECT sub_passion FROM psn_ms_subpassion " +
        					" WHERE subpassion_id = passion_sub_category) sub_passion, cpf_no AS action_by, (SELECT emp_name  FROM ongc_user_master   WHERE cpf_number = cpf_no) AS emp_name, z.uploaded_on AS action_on, " +
        					" 'audioadded' AS action   FROM psn_mypassion b, psn_audio z, PSN_MS_PASSION y  WHERE b.passion_sub_category in (SELECT a.passion_sub_category" +
        					" FROM psn_mypassion a   WHERE a.cpf_no = '"+cpf+"')    AND b.cpf_no != '"+cpf+"'   and B.PASSION_CATEGORY=Y.PASSION_ID AND b.psn_mp_id = z.psn_mp_id    union    SELECT '1' as topic_id, '2' as topic_name, z.docs_id AS file_id, z.docs_name AS caption, Y.PASSION_NAME as Passion_Name, (SELECT sub_passion FROM psn_ms_subpassion" +
        					" WHERE subpassion_id = passion_sub_category) sub_passion, cpf_no AS action_by, (SELECT emp_name " +
        					" FROM ongc_user_master WHERE cpf_number = cpf_no) AS emp_name, z.uploaded_on AS action_on, 'docsadded' AS action FROM psn_mypassion b, PSN_DOCS z, PSN_MS_PASSION y  WHERE b.passion_sub_category in (SELECT a.passion_sub_category FROM psn_mypassion a WHERE a.cpf_no = '"+cpf+"') AND b.cpf_no != '"+cpf+"' and B.PASSION_CATEGORY=Y.PASSION_ID AND b.psn_mp_id = z.psn_mp_id " +
        					" union    SELECT '1' as topic_id, '2' as topic_name, z.video_id AS file_id,  z.video_name AS caption,   Y.PASSION_NAME as Passion_Name,     (SELECT sub_passion FROM psn_ms_subpassion WHERE subpassion_id = passion_sub_category) sub_passion, cpf_no AS action_by, (SELECT emp_name FROM ongc_user_master WHERE cpf_number = cpf_no) AS emp_name, z.uploaded_on AS action_on, 'videoadded' AS action FROM psn_mypassion b, PSN_VIDEO z , PSN_MS_PASSION y WHERE b.passion_sub_category in (SELECT a.passion_sub_category  FROM psn_mypassion a WHERE a.cpf_no = '"+cpf+"')    AND b.cpf_no != '"+cpf+"' and B.PASSION_CATEGORY=Y.PASSION_ID AND b.psn_mp_id = z.psn_mp_id ORDER BY action_on DESC) a WHERE  a.action_on >sysdate-15";
		 		*/
		
		String query="SELECT a.*, ROW_NUMBER () OVER (ORDER BY action_on DESC) ROWNUM FROM (" +
				"(SELECT 'topicid' AS topic_id, 'topic_name' AS topic_name, 1 AS file_id, 'caption' AS caption, p.passion_name AS passion_name, sp.sub_passion, mp.cpf_no AS action_by, o.emp_name, mp.created_on AS action_on, 'enrolled' AS action FROM psn_mypassion mp, psn_ms_passion p, psn_ms_subpassion sp, ongc_user_master o where o.cpf_number = mp.cpf_no and p.passion_id=mp.PASSION_CATEGORY and sp.subpassion_id=mp.passion_sub_category) UNION " +
				"(SELECT 'topicid' AS topic_id, 'topic_name' AS topic_name, 2 AS file_id, 'caption' AS caption, z.passion_name AS passion_name, a.sub_passion, 'admin' AS action_by, 'admin' AS emp_name, now() AS action_on, 'newpassion' AS action FROM psn_ms_subpassion a, psn_ms_passion z WHERE a.passion_id = z.passion_id order by a.created_on desc) UNION " +
				"(SELECT topic_id, topic_name, 3 AS file_id, 'caption' AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pdb.created_by AS action_by, o.emp_name, pdb.created_on AS action_on, 'newtopic' AS action FROM psn_discussion_board pdb, ongc_user_master o WHERE o.cpf_number = pdb.created_by and (   pdb.GROUP_ID IN (SELECT GROUP_ID FROM psn_user_group WHERE created_by = '"+cpf+"') OR pdb.GROUP_ID IN ( SELECT GROUP_ID FROM psn_user_grp_invitee WHERE invitee = '"+cpf+"' AND joining_status = 'Y') or pdb.VISIBLE_TO='P' ) AND pdb.created_by != '"+cpf+"') UNION " +
				"(SELECT '1' AS topic_id, '2' AS topic_name, pp.photo_id AS file_id, pp.photo_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, action_by, o.emp_name, comments_on AS action_on, 'photoendorsed' AS action FROM psn_photo_cmnts_endrsmnt ppce, psn_photo pp, ongc_user_master o,psn_mypassion myp WHERE ppce.comments IS NULL and o.cpf_number = ppce.action_by and pp.photo_id = ppce.photo_id AND ppce.action_by != '"+cpf+"' and myp.psn_mp_id=pp.psn_mp_id and myp.cpf_no='"+cpf+"' UNION " +
				"SELECT '1' AS topic_id, '2' AS topic_name, pa.audio_id AS file_id, pa.audio_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pace.action_by, o.emp_name, endorsed_on AS action_on, 'audioendorsed' AS action FROM psn_audio_cmnts_endrsmnt pace, psn_audio pa, ongc_user_master o,psn_mypassion myp WHERE pace.comments IS NULL and o.cpf_number = pace.action_by and pa.audio_id = pace.audio_id AND pace.action_by != '"+cpf+"' and myp.psn_mp_id=pa.psn_mp_id and myp.cpf_no='"+cpf+"' UNION " +
				"SELECT '1' AS topic_id, '2' AS topic_name, pv.video_id AS file_id, pv.video_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pvce.action_by, o.emp_name, pvce.endorsed_on AS action_on, 'videoendorsed' AS action FROM psn_video_cmnts_endrsmnt pvce, psn_video pv, ongc_user_master o,psn_mypassion myp WHERE pvce.comments IS NULL and o.cpf_number = pvce.action_by and pv.video_id = pvce.video_id AND pvce.action_by != '"+cpf+"' and myp.psn_mp_id=pv.psn_mp_id and myp.cpf_no='"+cpf+"'  UNION " +
				"SELECT '1' AS topic_id, '2' AS topic_name, pd.docs_id AS file_id, pd.docs_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pdce.action_by, o.emp_name, pdce.endorsed_on AS action_on, 'docsendorsed' AS action FROM psn_docs_cmnts_endrsmnt pdce, psn_docs pd, ongc_user_master o,psn_mypassion myp WHERE pdce.comments IS NULL and o.cpf_number = pdce.action_by and pd.docs_id = pdce.docs_id AND pdce.action_by != '"+cpf+"' and myp.psn_mp_id=pd.psn_mp_id and myp.cpf_no='"+cpf+"') UNION " +
				"(SELECT '1' AS topic_id, '2' AS topic_name, pp.photo_id AS file_id, pp.photo_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, action_by, o.emp_name, comments_on AS action_on, 'photocommented' AS action FROM psn_photo_cmnts_endrsmnt ppce, psn_photo pp, ongc_user_master o,psn_mypassion myp  WHERE ppce.comments IS NOT NULL and o.cpf_number = ppce.action_by and pp.photo_id = ppce.photo_id AND ppce.action_by != '"+cpf+"' and myp.psn_mp_id=pp.psn_mp_id and myp.cpf_no='"+cpf+"'  UNION " +
				"SELECT '1' AS topic_id, '2' AS topic_name, pa.audio_id AS file_id, pa.audio_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pace.action_by, o.emp_name, pace.comments_on AS action_on, 'audiocommented' AS action FROM psn_audio_cmnts_endrsmnt pace, psn_audio pa, ongc_user_master o, psn_mypassion myp WHERE pace.comments IS NOT NULL and o.cpf_number = pace.action_by and pa.audio_id = pace.audio_id AND pace.action_by != '"+cpf+"' and myp.psn_mp_id=pa.psn_mp_id and myp.cpf_no='"+cpf+"' UNION "  +
				"SELECT '1' AS topic_id, '2' AS topic_name, pv.video_id AS file_id, pv.video_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pvce.action_by, o.emp_name, pvce.comments_on AS action_on, 'videocommented' AS action FROM psn_video_cmnts_endrsmnt pvce, psn_video pv, ongc_user_master o, psn_mypassion myp WHERE pvce.comments IS NOT NULL and o.cpf_number = pvce.action_by and pv.video_id = pvce.video_id AND pvce.action_by != '"+cpf+"' and myp.psn_mp_id=pv.psn_mp_id and myp.cpf_no='"+cpf+"' UNION " +
				"SELECT '1' AS topic_id, '2' AS topic_name, pd.docs_id AS file_id, pd.docs_name AS caption, 'Passion' AS passion_name, 'subpassion' AS sub_passion, pdce.action_by, o.emp_name, pdce.comments_on AS action_on, 'docscommented' AS action FROM psn_docs_cmnts_endrsmnt pdce, psn_docs pd, ongc_user_master o, psn_mypassion myp WHERE pdce.comments IS NOT NULL and o.cpf_number = pdce.action_by and pd.docs_id = pdce.docs_id AND pdce.action_by != '"+cpf+"' and myp.psn_mp_id=pd.psn_mp_id and myp.cpf_no='"+cpf+"' ) UNION " +
				"SELECT   '1' AS topic_id, '2' AS topic_name, z.photo_id AS file_id, z.photo_name AS caption, y.passion_name AS passion_name, x.sub_passion, cpf_no AS action_by, o.emp_name, z.uploaded_on AS action_on, 'photoadded' AS action FROM psn_mypassion b, psn_photo z, psn_ms_passion y, psn_ms_subpassion x, ongc_user_master o WHERE x.subpassion_id = b.passion_sub_category AND o.cpf_number = b.cpf_no AND b.cpf_no != '"+cpf+"' AND b.passion_category = y.passion_id AND b.psn_mp_id = z.psn_mp_id UNION " +
				"SELECT   '1' AS topic_id, '2' AS topic_name, z.audio_id AS file_id, z.audio_name AS caption, y.passion_name AS passion_name, x.sub_passion, cpf_no AS action_by, o.emp_name, z.uploaded_on AS action_on, 'audioadded' AS action FROM psn_mypassion b, psn_audio z, psn_ms_passion y, psn_ms_subpassion x, ongc_user_master o WHERE x.subpassion_id = b.passion_sub_category AND o.cpf_number = b.cpf_no AND b.cpf_no != '"+cpf+"' AND b.passion_category = y.passion_id AND b.psn_mp_id = z.psn_mp_id UNION " +
				"SELECT   '1' AS topic_id, '2' AS topic_name, z.docs_id AS file_id, z.docs_name AS caption, y.passion_name AS passion_name, x.sub_passion, cpf_no AS action_by, o.emp_name, z.uploaded_on AS action_on, 'docsadded' AS action FROM psn_mypassion b, psn_docs z, psn_ms_passion y, psn_ms_subpassion x, ongc_user_master o WHERE x.subpassion_id = b.passion_sub_category AND o.cpf_number = b.cpf_no AND b.cpf_no != '"+cpf+"' AND b.passion_category = y.passion_id AND b.psn_mp_id = z.psn_mp_id UNION " +
				"SELECT   '1' AS topic_id, '2' AS topic_name, z.video_id AS file_id, z.video_name AS caption, y.passion_name AS passion_name, x.sub_passion, cpf_no AS action_by, o.emp_name, z.uploaded_on AS action_on, 'videoadded' AS action FROM psn_mypassion b, psn_video z, psn_ms_passion y, psn_ms_subpassion x, ongc_user_master o WHERE x.subpassion_id = b.passion_sub_category AND o.cpf_number = b.cpf_no AND b.cpf_no != '"+cpf+"' AND b.passion_category = y.passion_id AND b.psn_mp_id = z.psn_mp_id ORDER BY action_on DESC) a WHERE a.action_on > current_date - 15";
		
		try {
			connection=DatasourceConnection.getConnection();
			stmt=connection.createStatement();
			//system.out.println(query);
			rs = stmt.executeQuery(query);

			while(rs.next()){
				HomeUpdates hUpdate=new HomeUpdates();
				hUpdate.setTopicId(rs.getString(1));
				hUpdate.setTopicName(rs.getString(2));
				hUpdate.setFileId(rs.getInt(3));
				hUpdate.setCaption(rs.getString(4));
				hUpdate.setPassionName(rs.getString(5));
				hUpdate.setSubPassion(rs.getString(6));
				hUpdate.setEmpCpf(rs.getString(7));
				hUpdate.setEmpName(rs.getString(8));
				hUpdate.setActionOn(rs.getString(9));
				hUpdate.setAction(rs.getString(10));
				hUpdate.setRownum(rs.getString(11));
				
				updates.add(hUpdate);
			}
			
			stmt.close();
			
			for(HomeUpdates hUpdate:updates){
				if("1".equalsIgnoreCase(hUpdate.getTopicId())){
					this.activityFileDetail(hUpdate, hUpdate.getFileId(), hUpdate.getAction());
				}				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatasourceConnection.closeConnection(connection,stmt, rs);
			DatasourceConnection.closeConnection(connection,stmt, rs);
		}
		
		return updates;
	}
	
	private void activityFileDetail(HomeUpdates hUpdate, int fileId, String fileType) {
		
		
		String query="";
		if(fileType.equalsIgnoreCase("photoendorsed") || fileType.equalsIgnoreCase("photocommented") || fileType.equalsIgnoreCase("photoadded")){
			query="SELECT pp.file_name, pp.endorse_count, pp.description, pms.passion_name, spms.sub_passion "+
						" FROM psn_photo pp, psn_mypassion pmp, psn_ms_passion pms, psn_ms_subpassion spms "+
						" WHERE photo_id = "+fileId+" AND pp.psn_mp_id = pmp.psn_mp_id AND pms.passion_id = pmp.passion_category AND spms.subpassion_id = pmp.passion_sub_category";
		}
		if(fileType.equalsIgnoreCase("audioendorsed") || fileType.equalsIgnoreCase("audiocommented") || fileType.equalsIgnoreCase("audioadded")){
			query="SELECT pp.file_name, pp.endorse_count, pp.description, pms.passion_name, spms.sub_passion "+
						" FROM psn_audio pp, psn_mypassion pmp, psn_ms_passion pms, psn_ms_subpassion spms "+
						" WHERE audio_id = "+fileId+" AND pp.psn_mp_id = pmp.psn_mp_id AND pms.passion_id = pmp.passion_category AND spms.subpassion_id = pmp.passion_sub_category";
		}
		if(fileType.equalsIgnoreCase("videoendorsed") || fileType.equalsIgnoreCase("videocommented") || fileType.equalsIgnoreCase("videoadded")){
			query="SELECT pp.file_name, pp.endorse_count, pp.description, pms.passion_name, spms.sub_passion "+
					" FROM psn_video pp, psn_mypassion pmp, psn_ms_passion pms, psn_ms_subpassion spms "+
					 " WHERE docs_id = "+fileId+" AND pp.psn_mp_id = pmp.psn_mp_id AND pms.passion_id = pmp.passion_category AND spms.subpassion_id = pmp.passion_sub_category";
		}
		if(fileType.equalsIgnoreCase("docsendorsed") || fileType.equalsIgnoreCase("docscommented") || fileType.equalsIgnoreCase("docsadded")){
			query="SELECT pp.file_name, pp.endorse_count, pp.description, pms.passion_name, spms.sub_passion "+
					" FROM psn_docs pp, psn_mypassion pmp, psn_ms_passion pms, psn_ms_subpassion spms "+
					" WHERE docs_id = "+fileId+" AND pp.psn_mp_id = pmp.psn_mp_id AND pms.passion_id = pmp.passion_category AND spms.subpassion_id = pmp.passion_sub_category";
		}
		Connection conn3=null;Statement stmt3=null;ResultSet rs3=null;
		try {
			conn3=DatasourceConnection.getConnection();
			stmt3=conn3.createStatement();
			rs3=stmt3.executeQuery(query);
			while(rs3.next()){
				
				hUpdate.setFileName(rs3.getString(1));
				hUpdate.setEndorseCount(rs3.getInt(2));
				hUpdate.setFileDesc(rs3.getString(3));
				hUpdate.setPassionName(rs3.getString(4));
				hUpdate.setSubPassion(rs3.getString(5));
			}
			
		}
	 catch (SQLException e) {
		 e.printStackTrace();
	} finally{
		DatasourceConnection.closeConnection(conn3, stmt3, rs3);
	}		
		
	}
	
	public static void main(String[] args) {
		//system.out.println(hdao.fetchElite());
		/*List al=hdao.fetchUpdates("128012");
		//system.out.println(al);
		//system.out.println(al.size());
		Iterator itr=al.iterator();
		while(itr.hasNext()){
			HomeUpdates helite=(HomeUpdates)itr.next();
			//system.out.println(helite.getAction());
			//system.out.println(helite.getCaption());
			//system.out.println(helite.getEmpName());
			//system.out.println(helite.getSubPassion());
			//system.out.println("--------------------");
		}*/
	}



	public HomeNews newsDetail(String newsId) {
		String query="select title, news_desc, file_caption, new_id,TO_CHAR(created_on, 'DD Mon YYYY') from psn_ms_news where sno=?";
		
		HomeNews homeNews=new HomeNews();
		Connection conn=DatasourceConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(newsId));
			rs=pstmt.executeQuery();
			while(rs.next()){
				homeNews.setNewsTitle(rs.getString(1));
				homeNews.setNewsDesc(rs.getString(2));
				homeNews.setFileCaption(rs.getString(3));
				homeNews.setNewsId(rs.getString(4));
				homeNews.setNewsCreatedOn(rs.getString(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn, pstmt, rs);
		}
		
		return homeNews;
	}



	public void submitFeedback(String cpfNo, String message) {
		
		String query="insert into psn_feedback (id, posted_by, message, posted_on) values (?, ?, ?, current_timestamp)";
		
		PreparedStatement pstmt=null;
		
		try{

			connection=DatasourceConnection.getConnection();
			
			pstmt=connection.prepareStatement(query);
			pstmt.setInt(1, getMaxIdFromTable("psn_feedback", "id"));
			pstmt.setString(2, cpfNo);
			pstmt.setString(3, message);
			pstmt.executeUpdate();

		}
		catch(Exception ex){
			
			ex.printStackTrace();
			

		}
		finally{
			DatasourceConnection.closeConnection(pstmt, connection);
			
		}
	}
	
	public List<UserAdmin> getAdminData() {
		Connection conn = DatasourceConnection.getConnection();
		List<UserAdmin>  adminList = new ArrayList<UserAdmin> ();
		String query = "select * from PSN_USER_ADMIN";
		PreparedStatement pstmt=null;
		ResultSet set=null;
		try {
			pstmt = conn.prepareStatement(query);
			set = pstmt.executeQuery();
			UserAdmin passion = null;
			while (set.next()) {
				passion = new UserAdmin();
				passion.setUserId(set.getString("USERID"));
				passion.setUserName(set.getString("USERNAME"));
				passion.setEmailId(set.getString("EMAILID"));
				passion.setMobileNumber(set.getString("MOBILE_NO"));					
				adminList.add(passion);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DatasourceConnection.closeConnection(conn,pstmt,set);
		}
		return adminList;
	}
	
	public byte[] getNewsPic(String newsId){
		//system.out.println(":::::::::photoId");
		//system.out.println(newsId+":::::::::CPFEMP");
		byte fileData[] = null;
		String query="select FILE_DATA from PSN_MS_NEWS where NEW_ID=?";
		Connection conn=DatasourceConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet set=null;
		try{
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, newsId);
			set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("FILE_DATA");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}
	
	public byte[] getPassionPic(String subpassionId){
		byte fileData[] = null;
		String query="select SUB_PASSION_IMG from PSN_MS_SUBPASSION where SUBPASSION_ID=?";
		Connection conn=DatasourceConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet set=null;
		try{
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, subpassionId);
			set=pstmt.executeQuery();
			while(set.next()){
				fileData = set.getBytes(1);
				Blob blob = set.getBlob("SUB_PASSION_IMG");
				if (blob == null) {
				    return null;
				  }
				fileData = blob.getBytes(1, (int) blob.length());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DatasourceConnection.closeConnection(conn,pstmt,set);	
		} 
		return fileData;	
	}


	public List getPassion(String subPsn) {
 		List list=new ArrayList();
 		String query="select Distinct PMSP.PASSION_NAME,PMSSP.SUB_PASSION,PMSP.PASSION_ID,PMSSP.SUBPASSION_ID from psn_mypassion pmyp,psn_ms_passion pmsp ,psn_ms_subpassion pmssp,ongc_user_master oum where pmyp.passion_category=PMSP.PASSION_ID and PMYP.PASSION_SUB_CATEGORY=PMSSP.SUBPASSION_ID and PMYP.CPF_NO=OUM.CPF_NUMBER and UPPER(PMSSP.SUB_PASSION) like upper('%"+subPsn+"%')";
 		try {
 			connection = DatasourceConnection.getConnection();
 			stmt = connection.createStatement();
 			rs = stmt.executeQuery(query);
 			while(rs.next())
 			{
 				User user=new User();
 				user.setPassionName(rs.getString(1));
 				user.setSubPassion(rs.getString(2));
 				user.setPassionId(rs.getString(3));
 				user.setSubPassionId(rs.getString(4));
 				list.add(user);
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally{
 			DatasourceConnection.closeConnection(connection,stmt, rs);
 		}
 		return list;
 		}
		
     
     public List getPassionEnrlUser(String subPsn) {
  		List userList=new ArrayList();
  		String query="select Distinct PMSP.PASSION_NAME,PMSSP.SUB_PASSION,PMSP.PASSION_ID,PMSSP.SUBPASSION_ID,OUM.CPF_NUMBER,OUM.EMP_NAME from psn_mypassion pmyp,psn_ms_passion pmsp ,psn_ms_subpassion pmssp,ongc_user_master oum where pmyp.passion_category=PMSP.PASSION_ID and PMYP.PASSION_SUB_CATEGORY=PMSSP.SUBPASSION_ID and PMYP.CPF_NO=OUM.CPF_NUMBER and UPPER(PMSSP.SUB_PASSION) like upper('%"+subPsn+"%')";
  		try {
  			connection = DatasourceConnection.getConnection();
  			stmt = connection.createStatement();
  			rs = stmt.executeQuery(query);
  			while(rs.next())
  			{
  				User user=new User();
  				user.setPassionName(rs.getString(1));
  				user.setSubPassion(rs.getString(2));
  				user.setPassionId(rs.getString(3));
  				user.setSubPassionId(rs.getString(4));
  				user.setCpfNo(rs.getString(5));
  				user.setEmpName(rs.getString(6));
  				userList.add(user);
  			}
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally{
  			DatasourceConnection.closeConnection(connection,stmt, rs);
  		}
  		return userList;
  		}

 	private int getMaxIdFromTable(String tableName,String colName){
 		int id=0;
 		
 		String query="select max(int("+colName+")) from "+tableName;
 		Statement stmt=null;
 		ResultSet rs=null;
 		
 		try {
 			connection = DatasourceConnection.getConnection();
 			stmt = connection.createStatement();
 			rs = stmt.executeQuery(query);			
 			while(rs.next())
 			{  
 				id=rs.getInt(1);
 			}
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally{
 			DatasourceConnection.closeConnection(connection,stmt, rs);
 		}
 		
 		return ++id;
 	}
     
     
	
}
