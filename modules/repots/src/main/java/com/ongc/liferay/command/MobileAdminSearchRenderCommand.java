package com.ongc.liferay.command;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(
		 property = {
		 "javax.portlet.name="+ RepotsPortletKeys.Admin,
		 "mvc.command.name=searchMobileAdmin"
		 }, service = MVCRenderCommand.class
		 )

public class MobileAdminSearchRenderCommand implements MVCRenderCommand{
	public final static String renderingMobilAdminPagePath = "/admin/mobileAdmin.jsp";
	
	private UserDao userDao=new UserDao();
	private Connection conn;
	private ResultSet set;
	private PreparedStatement pstmt;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		//ThemeDisplay themeDisplay =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(38964,41957);
//		Set<Long> numbers = new TreeSet<Long>();
//		
//		for (JournalArticle articl:articles) {
//			numbers.add(Long.parseLong(articl.getArticleId()));
//			}
//		
//		 long getString = 43501;
//	     List<Long> arr = new ArrayList<>(numbers);
//	     System.out.println(arr.indexOf(getString));
//	     if(!(arr.indexOf(getString) < 0)) {
//	    	 System.out.println(arr.get(arr.indexOf(getString)-1));}
//	     if(arr.indexOf(getString) <= arr.size()) {
//	    	 System.out.println(arr.get(arr.indexOf(getString)+1));}
	     
//		for (long num:numbers) {
//			 try {
//				JournalArticle latestArticle = JournalArticleLocalServiceUtil.getLatestArticle(38964, num, 0);
//				System.out.println(latestArticle.getTitle());
//			} catch (PortalException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
	
		String searchFrom= ParamUtil.getString(renderRequest, "searchFrom");
		String searchTo= ParamUtil.getString(renderRequest, "searchTo");
		String cpfNo= ParamUtil.getString(renderRequest, "cpfNo");
		String name= ParamUtil.getString(renderRequest, "name");
		List<MobileUserBean> userDtl = new ArrayList<MobileUserBean>();
	    MobileUserBean mub = null;
	    Date d1=null,d2=null;
		SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stm1 = new SimpleDateFormat("dd-MM-yyyy");
		if(searchFrom != null && !searchFrom.isEmpty() && searchTo != null && !searchTo.isEmpty())
	    {
		try{
			d1 = stm.parse(searchFrom);
			d2 = stm.parse(searchTo);
			}
			catch(Exception e){
			e.printStackTrace();
			}

		searchFrom=stm1.format(d1);
		searchTo=stm1.format(d2);
	    }
	    String query = "SELECT cpf_no,(select EMP_NAME from ONGC_USER_MASTER where CPF_NUMBER=cpf_no) userName,imei_no, recent_login as loginDate from RPT_IMEI WHERE  UPPER(cpf_no) like '%" + cpfNo + "%' ";
	    try
	    {
	      if ((searchFrom != null) && (searchFrom.trim().length() > 0) && (searchTo != null) && (searchTo.trim().length() > 0)) {
	        query = query + " and DATE(recent_login ) >= DATE('"+searchFrom+"') and DATE(recent_login) <= DATE('"+searchTo+"')";
	      }
	      if ((name != null) && (name.trim().length() > 0)) {
	        query = query + " and cpf_no in (select CPF_NUMBER from ONGC_USER_MASTER where UPPER(EMP_NAME) like UPPER('%" + name + "%'))";
	      }
	      query = query + "  order by recent_login DESC";
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
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	      DatasourceConnection.closeConnection(this.conn, this.pstmt, this.set);
	    }
	    renderRequest.setAttribute("userLog", userDtl);
		return renderingMobilAdminPagePath;
	}
}
