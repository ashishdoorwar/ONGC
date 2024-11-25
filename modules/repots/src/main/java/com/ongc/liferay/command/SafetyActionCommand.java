package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.dao.PledgeDao;
import com.ongc.liferay.dao.UserDao;
import com.ongc.liferay.model.User;
import com.ongc.liferay.util.CommonUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.SafetyPlegde,
			"mvc.command.name=safetyPledge"
		},
		service = MVCActionCommand.class
	)
public class SafetyActionCommand extends BaseMVCActionCommand{
	private PledgeDao dao = new PledgeDao();
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String cpfNo=ParamUtil.getString(actionRequest,"cpfNo");
		String type=ParamUtil.getString(actionRequest,"type");
		String userName=ParamUtil.getString(actionRequest,"userName");
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		boolean flag = false;
		flag = dao.checkCpf(cpfNo,type);

		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.sendRedirect("mvcPath", "/safetyPledge/view.jsp");
		} else {		
		
		String subject = "Certificate of online Safety Pledge on Ongc Reports";
		//String toEmailid = "s.singh@velocis.co.in";
		String toEmailid=cpfNo+"@ongc.co.in";
		String filename = "D:/ONGC/pdfs/" + cpfNo+ ".pdf";
		String htmlInpt = "D:/ONGC/template/cert.html";
		String htmlOtpt = "D:/ONGC/html/" + cpfNo+ ".html";
		
		String msg = "Dear "
						+ userName
						+ ",<br/><br/>Congratulations for taking the online safety pledge on reports.ongc.co.in.<br/><br/>The certificate is attached<br/><br/>Regards,<br/>Chief HSE";

		StringBuffer xmlBuffer = new StringBuffer();
		flag = false;
		flag = dao.submitPledge(cpfNo, type);
		if (flag) {

			flag = dao.createPDF(userName, filename, htmlInpt, htmlOtpt);
			if (flag) {
				boolean flag1 = dao.sendEmail(subject,msg, userName, toEmailid,filename);
				if (flag1) {
					UserDao userDao = new UserDao();
					User us = userDao.getUserByCPFNumber(cpfNo);
					String mobNum = us.getMobileNo();
					if (mobNum.length() >= 10) {
						mobNum = "91"+ mobNum.substring(mobNum.length() - 10,mobNum.length());
					}
					String sUserMsg = "Chief HSE congratulates and appreciates your taking the Safety Pledge.";
					dao.sendPassOnSMS(mobNum,sUserMsg);
				}
			}
		}

		xmlBuffer.append("<status>").append(flag).append("</status>");
		System.out.print(xmlBuffer.toString());
	}
	}

}
