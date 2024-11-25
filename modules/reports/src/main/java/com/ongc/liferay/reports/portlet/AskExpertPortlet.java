package com.ongc.liferay.reports.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.reports.constants.ReportsPortletKeys;
import com.ongc.liferay.reports.dao.Impl.AskExpertDaoImpl;
import com.ongc.liferay.reports.model.AskExpert;
import com.ongc.liferay.reports.model.AskExpertSearch;
import com.ongc.liferay.reports.model.Domain;
import com.ongc.liferay.reports.model.ExpertReply;
import com.ongc.liferay.reports.model.User;
import com.ongc.liferay.reports.service.AskExpertService;
import com.ongc.liferay.reports.service.UserService;
import com.ongc.liferay.reports.service.Impl.AskExpertServiceImpl;
import com.ongc.liferay.reports.service.Impl.UserServiceImpl;
import com.ongc.liferay.reports.util.CommonUtil;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ONGC REPORT",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Ask Expert",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/askExpert/view.jsp",
		"javax.portlet.name=" + ReportsPortletKeys.AskExpert,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AskExpertPortlet extends MVCPortlet{

	private AskExpertService askExpertService = new AskExpertServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		super.doView(renderRequest, renderResponse);
	}
	
	public void feedbackPostCommentForm(ActionRequest actionRequest,ActionResponse actionResponse) {
		
		int domainId = ParamUtil.getInteger(actionRequest, "domain");
		String message = ParamUtil.getString(actionRequest, "message");
		String priority = ParamUtil.getString(actionRequest, "priority");
		//boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkString(message) || checkPara(String.valueOf(domainId)) || checkPara(priority)) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/askExpert/askExpertForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			}else {
		AskExpert askExpert = new AskExpert();
		User user= userService.getUser();
		askExpert.setDomainId(domainId);
		askExpert.setMessage(message);
		askExpert.setPriority(priority);
		askExpert.setCpfno(user.getCpfNo());
		askExpertService.saveAskExpert(askExpert);
		SessionMessages.add(actionRequest, "Success");
		}
	}
	
	public void saveAskExpertComment(ActionRequest actionRequest,ActionResponse actionResponse) {
		int queryId = ParamUtil.getInteger(actionRequest, "queryId");
		String message = ParamUtil.getString(actionRequest, "comment");
		//boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		
		if(checkString(message) || checkPara(String.valueOf(queryId))) {
			SessionErrors.add(actionRequest,"error");
			try {
				actionResponse.sendRedirect("mvcPath", "/askExpert/askExpertReplyForm.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			}else {
		AskExpert askExpert = new AskExpert();
		ExpertReply expertReply = new ExpertReply();
		User user = userService.getUser();
		boolean flag=false;
		String cpfNo = user.getCpfNo().toString();
		askExpert.setLastcommented_by(cpfNo);
		askExpert.setQueryid(queryId);
		expertReply.setCpfno(user.getCpfNo());
		expertReply.setQueryId(queryId);
		expertReply.setReplyMessage(message);
		if("Y".equalsIgnoreCase(expertReply.getIsExpert())){
			askExpert.setExpertResponse("Y");
		} else {
			askExpert.setExpertResponse("");
		}
		
		expertReply.setCpfno(cpfNo);
		expertReply.setQueryId(askExpert.getQueryid());
		flag=askExpertService.saveAskExpertComment(expertReply);
		if(flag)
		{
			askExpertService.updateAskExpert(askExpert);
			setQueryId(askExpert.getQueryid()+"");
		}
		else
		{
			askExpert=askExpertService.viewAskExpert(askExpert.getQueryid());
			setRoleCheck(askExpertService.checkExpertRoleForDomain(cpfNo,askExpert.getDomainId()));
		}
		SessionMessages.add(actionRequest, "Success");
			}
	}
	private String queryId;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	private boolean roleCheck;
	public boolean isRoleCheck() {
		return roleCheck;
	}

	public void setRoleCheck(boolean roleCheck) {
		this.roleCheck = roleCheck;
	}
	
	private boolean checkString(String message) {
		boolean parameterCheck=false;
		if(message.contains("<script>") || message.contains("</script>") ||message.contains("<SCRIPT>") || message.contains("</SCRIPT>") || message.contains("&lt;script&gt;")|| message.contains("alert")) {
			parameterCheck = true;
	}
			return parameterCheck;
	}
	
	private boolean checkPara(String message) {
		boolean parameterCheck=false;
		if (!Pattern.matches("[^<>]*", message)) {
//			System.out.println(parameterCheck+"========================>"+Pattern.matches("[^<>]*", (message)));
			parameterCheck = true;
		}
		return parameterCheck;
	}	
}
