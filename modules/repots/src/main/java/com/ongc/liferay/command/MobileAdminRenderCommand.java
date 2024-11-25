package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.connection.DatasourceConnection;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PassionDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.MobileUserBean;
import com.ongc.liferay.model.Passion;
import com.ongc.liferay.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.Admin,
		 "mvc.command.name=getmobileAdmin"
		 }, service = MVCRenderCommand.class
		 )

public class MobileAdminRenderCommand implements MVCRenderCommand{
	public final static String renderingMobilAdminPagePath = "/admin/mobileAdmin.jsp";
	
	private UserDao userDao=new UserDao();
	private Connection conn;
	private ResultSet set;
	private PreparedStatement pstmt;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		List<MobileUserBean> userDtl = new ArrayList<MobileUserBean>();
	    MobileUserBean mub = null;
	    String query = "SELECT cpf_no,(select EMP_NAME from ONGC_USER_MASTER where CPF_NUMBER=cpf_no) userName,imei_no, recent_login as loginDate from RPT_IMEI order by recent_login DESC";
	    try
	    {
	      this.conn = DatasourceConnection.getConnection();
	      this.pstmt = this.conn.prepareStatement(query);
	      this.set = this.pstmt.executeQuery();
	      while (this.set.next())
	      {
	        mub = new MobileUserBean();
	        mub.setCpfNo(this.set.getString("cpf_no"));
	        mub.setUserName(this.set.getString("userName"));
	        mub.setImeiNo(this.set.getString("imei_no"));
	        mub.setLoginDate(this.set.getString("loginDate"));
	        userDtl.add(mub);
	      }
	      renderRequest.setAttribute("userLog", userDtl);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	    	DatasourceConnection.closeConnection(this.conn, this.pstmt, this.set);
	    }
		return renderingMobilAdminPagePath;
	}
}
