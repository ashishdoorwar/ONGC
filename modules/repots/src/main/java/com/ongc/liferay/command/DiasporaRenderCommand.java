package com.ongc.liferay.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PassionDao;
import com.ongc.liferay.dao.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		property = {
				"javax.portlet.name="+ RepotsPortletKeys.Diaspora,
				"mvc.command.name=diasporaSummeryURL"
		}, service = MVCRenderCommand.class
		)

public class DiasporaRenderCommand implements MVCRenderCommand{
	

	private static final Log LOGGER = LogFactoryUtil.getLog(DiasporaRenderCommand.class);
	public final static String renderingEmployeePagePath = "/diaspora/diasporaSummery.jsp";

	private UserDao userDao=new UserDao();
	private PassionDao passionDao= new PassionDao();

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String country = ParamUtil.getString(renderRequest, "country");
		String academics = ParamUtil.getString(renderRequest,"academics");
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		Connection conn=null;
		Statement stmt=null;
		ResultSet res=null;
		String queryPart = "";
		try {
			if(country == null ){
				country="";
			}
			if ( academics ==null)
			{
				academics="";
			}
			if(Validator.isNotNull(country) || Validator.isNotNull(academics)){
				if(country.length()>0 && academics.length()>0 )
				{
					queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on  from ongc_diaspora a where a.status='A' and a.sub_category='POST' and upper(a.country)='"
							+ country.toUpperCase()
							+ "' and a.academics ='"
							+ academics
							+ "'  order by a.created_on desc";
				}	

				else if(country.length()>0 )
				{
					queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on from ongc_diaspora a where a.status='A' and a.sub_category='POST' and upper(a.country)='"
							+ country.toUpperCase()
							+ "' order by a.created_on desc";
				}
				else if(academics.length()>0)
				{
					queryPart = "select id,cpf_no,name,ward_name,email,mobile_number,country,state,academics,university,description,photo,created_on as created_on from ongc_diaspora a where a.status='A' and a.sub_category='POST' and a.academics ='"
							+ academics
							+ "' order by a.created_on desc";
				}
				
				conn = DatasourceConnection.getConnection();
				stmt = conn.createStatement();
				res = stmt.executeQuery(queryPart);
				boolean empty = true;
				while (res.next()) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
					empty = false;
					//jsonObject.put("photo", res.getBlob("photo"));
					jsonObject.put("id", res.getString("id"));
					jsonObject.put("ward_name", res.getString("ward_name"));
					jsonObject.put("created_on", res.getString("created_on"));
					jsonObject.put("cpf_no", res.getString("cpf_no"));
					jsonObject.put("name", res.getString("name"));
					jsonObject.put("country", res.getString("country"));
					jsonObject.put("state", res.getString("state"));
					jsonObject.put("academics", res.getString("academics"));
					jsonObject.put("university", res.getString("university"));
					jsonObject.put("mobile_number", res.getString("mobile_number"));
					jsonObject.put("email", res.getString("email"));
					jsonObject.put("description", res.getString("description"));
					jsonArray.put(jsonObject);
				}
				renderRequest.setAttribute("jsonArrayDiaspora", jsonArray);
//				renderRequest.setAttribute("queryPart", queryPart);
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}
		return renderingEmployeePagePath;
	}
}
