package com.ongc.liferay.portlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=SMALL Report",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Diaspora",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/diaspora/view.jsp",
			"javax.portlet.name=" + RepotsPortletKeys.Diaspora,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class DiasporaPortlet extends MVCPortlet {

	private UserDao userDao = new UserDao();
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		//ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//User userOngc =userDao.getUserByEmailId(themeDisplay.getUser().getEmailAddress());
		super.doView(renderRequest, renderResponse);
	}
	
//	private void saveDiaspora(ActionRequest actionRequest, ActionResponse actionResponse) {
//		
//		
//		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
//		String fileName=uploadPortletRequest.getFileName("imgfile");
//		File file = uploadPortletRequest.getFile("imgfile");
//		String title = fileName;
//		System.out.println("Title=>"+title);
//		//File to = new File("G:/ONGC/Image/"+file.getName());
//		//copy(file, to);
//		String cpfNo=ParamUtil.getString(actionRequest, "cpfNo");
//		String name=ParamUtil.getString(actionRequest, "name");
//		String wname=ParamUtil.getString(actionRequest, "wname");
//		String email=ParamUtil.getString(actionRequest, "email");
//		int mobile=ParamUtil.getInteger(actionRequest, "mobile");
//		String country=ParamUtil.getString(actionRequest, "country");
//		String state=ParamUtil.getString(actionRequest, "state");
//		String academics=ParamUtil.getString(actionRequest, "academics");
//		String universty=ParamUtil.getString(actionRequest, "universty");
//		String description=ParamUtil.getString(actionRequest, "description");
//		byte[] b=null;
//		int id=0;
//				String query="select max(id) from ongc_diaspora";
//				Statement stmt=null;
//				PreparedStatement ps= null;
//				ResultSet rs=null;
//
//					Connection connection = DatasourceConnection.getConnection();
//					try {
//						stmt = connection.createStatement();
//						rs = stmt.executeQuery(query);			
//						while(rs.next())
//						{  
//							id=rs.getInt(1);
//						}
//						id++;
//
//						User userOngc =userDao.getUserByCPFNumber(cpfNo);
//						 ps = connection.prepareStatement("insert into ongc_diaspora(cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,status,sub_category,created_on,id) values(?,?,?,?,?,?,?,?,?,?,?,'A','POST',NOW(),?)");
//							ps.setString(1, userOngc.getCpfNo());
//							ps.setString(2, userOngc.getEmpName());
//							ps.setString(3, wname);
//							ps.setString(4, email);
//							ps.setInt(5, mobile);
//							ps.setString(6, country);
//							ps.setString(7, state);
//							ps.setString(8, academics);
//							ps.setString(9, universty);
//							ps.setString(10, description);
//							ps.setBytes(11,b);
//							ps.setInt(12, id);
//							int a=ps.executeUpdate();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
////					actionResponse.setRenderParameter("mvcPath","/diaspora/diasporaSummery.jsp");	
//	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		JSONObject json = null;
		String query=ParamUtil.getString(resourceRequest, "q");
		try{     
			 String s[]=null;
	         Connection conn=null;
			 conn = DatasourceConnection.getConnection();
			 Statement st=conn.createStatement(); 
			 ResultSet rs = st.executeQuery("select countries from countries");
		    List li = new ArrayList();
				while(rs.next()) 
	 			{ 	
	 			    li.add(rs.getString(1));
	 			}  
				String[] str = new String[li.size()];			
				Iterator it = li.iterator();
				int i = 0;
				while(it.hasNext())
				{
					String p = (String)it.next();	
					str[i] = p;
					i++;
				}
					int cnt=1;
					StringBuffer xmlBuffer = new StringBuffer();
					json = JSONFactoryUtil.createJSONObject();
					//xmlBuffer.append("<ul class='autocomplete'>");
					for(int j=0;j<str.length;j++)
					{
						if(str[j].toUpperCase().startsWith(query.toUpperCase()))
						{
							json.put(str[j],str[j]);
							if(cnt>=5)
								break;
							cnt++;
						}
					}
					//xmlBuffer.append("</ul>");
	 		rs.close(); 
	 		st.close(); 
			conn.close();
			    } 
			catch(Exception e){ 
	 			e.printStackTrace(); 
	 		}
		resourceRequest.setAttribute("json", json);
		super.writeJSON(resourceRequest, resourceResponse, json);//(resourceRequest, resourceResponse);
	}
}
