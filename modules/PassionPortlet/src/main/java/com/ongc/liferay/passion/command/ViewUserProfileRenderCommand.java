package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.PassionAudioDao;
import com.ongc.liferay.passion.dao.PassionDao;
import com.ongc.liferay.passion.dao.PassionDocDao;
import com.ongc.liferay.passion.dao.PassionPhotoDao;
import com.ongc.liferay.passion.dao.PassionVideoDao;
import com.ongc.liferay.passion.dao.UserDao;
import com.ongc.liferay.passion.dao.Impl.PassionAudioDaoImpl;
import com.ongc.liferay.passion.dao.Impl.PassionDaoImpl;
import com.ongc.liferay.passion.dao.Impl.PassionDocDaoImpl;
import com.ongc.liferay.passion.dao.Impl.PassionPhotoDaoImpl;
import com.ongc.liferay.passion.dao.Impl.PassionVideoDaoImpl;
import com.ongc.liferay.passion.dao.Impl.UserDaoImpl;
import com.ongc.liferay.passion.model.Passion;
import com.ongc.liferay.passion.model.PassionAudio;
import com.ongc.liferay.passion.model.PassionDoc;
import com.ongc.liferay.passion.model.PassionPhoto;
import com.ongc.liferay.passion.model.PassionVideo;
import com.ongc.liferay.passion.model.SubPassion;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;
import com.ongc.liferay.passion.service.Impl.UserServiceImpl;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		property = {
				"javax.portlet.name="+PassionPortletKeys.PASSION,
				"mvc.command.name=view_profile"
		}, service = MVCRenderCommand.class
		)
public class ViewUserProfileRenderCommand implements MVCRenderCommand{

	private UserService userService=new UserServiceImpl();
	private static final Log LOGGER = LogFactoryUtil.getLog(ViewUserProfileRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		int k = 0;
		int i = 0;
		UserDao userDao = new UserDaoImpl();
		PassionDao passionDao = new PassionDaoImpl();
		PassionPhotoDao passionPhotoDao = new PassionPhotoDaoImpl();
		PassionAudioDao passionAudioDao = new PassionAudioDaoImpl();
		PassionVideoDao passionVideoDao = new PassionVideoDaoImpl();
		PassionDocDao passionDocDao = new PassionDocDaoImpl();
		List<Passion> passionName = null;
		List<Passion> allPassionName = null;
		List<SubPassion> subpassionName = null;
		String strPassionId=null;
		String strSubPassionId=null;
		List<PassionPhoto> psnPhoto1 = null;
		List<PassionPhoto> psnPhoto2 = null;
		List<PassionPhoto> psnPhoto3 = null;
		List<PassionAudio> psnAudio1 = null;
		List<PassionAudio> psnAudio2 = null;
		List<PassionAudio> psnAudio3 = null;
		List<PassionVideo> psnVideo1 = null;
		List<PassionVideo> psnVideo2 = null;
		List<PassionVideo> psnVideo3 = null;
		List<PassionDoc> psnDoc1 = null;
		List<PassionDoc> psnDoc2 = null;
		List<PassionDoc> psnDoc3 = null;
		String category1 = "";
		String category2 = "";
		String category3 = "";
		String strPassionId1="";
		String strPassionId2="";
		String strPassionId3="";
		String strSubPassionId1 = "";
		String strSubPassionId2 = "";
		String strSubPassionId3 = "";
		String strPassionname1 = "";
		String strPassionname2 = "";
		String strPassionname3 = "";
		String strSubPassionname1 = "";
		String strSubPassionname2 = "";
		String strSubPassionname3 = "";
		String strPassionNote1 = "";
		String strPassionNote2 = "";
		String strPassionNote3 = "";
		
		String cpfNo=ParamUtil.getString(renderRequest, "cpfno");
		
		if(!validateCpf(cpfNo)){
			return "failure";
		}
//		removeSessionAttribute("loggedInUserCpfNo");
//		setSessionAttribute("loggedInUserCpfNo", cpfNo);
		
		
		User userData=userService.getUser();
		String empCpf=userData.getCpfNo();
		
		String empCpfReport= ParamUtil.getString(renderRequest, "empCpfReport");
		

		if(empCpf!=null && !"".equalsIgnoreCase(empCpf)){
			cpfNo=empCpf;
		} else if(empCpfReport!=null && !"".equalsIgnoreCase(empCpfReport)){
			cpfNo=empCpfReport;
		}
		User user= (User)userDao.getUserByCPFNumber(cpfNo);
		passionName = passionDao.getUserPassion(cpfNo);
		allPassionName = passionDao.getPassionName();
		subpassionName = passionDao.getSubPassionName();
		//system.out.println(passionName.size()+".................passion size");
		for (i = 0; i < passionName.size(); i++) {
			strPassionId = passionName.get(i).getPassionId();
			String strPassionname= passionName.get(i).getPassionName();
			strSubPassionId = passionName.get(i).getSubPassionId();
			String strSubPassionname= passionName.get(i).getSubPassion();
			String strPassionNote= passionName.get(i).getComments();
			//system.out.println(i+"...test");
			if (i == 0) {
				category1 = "category1";
				strPassionId1=strPassionId;
				strSubPassionId1=strSubPassionId;
				strPassionname1=strPassionname;
				strSubPassionname1=strSubPassionname;
				strPassionNote1=strPassionNote;
				psnPhoto1 = passionPhotoDao.getPassionPhoto(cpfNo, strPassionId, strSubPassionId);
				psnAudio1 = passionAudioDao.getPassionAudio(cpfNo, strPassionId, strSubPassionId);
				psnVideo1 = passionVideoDao.getPassionVideo(cpfNo, strPassionId, strSubPassionId);
				psnDoc1=passionDocDao.getPassionDocumentFile(cpfNo, strPassionId, strSubPassionId);
			}
			else if (i == 1) {
				category2 = "category2";
				strPassionId2=strPassionId;
				strSubPassionId2=strSubPassionId;
				strPassionname2=strPassionname;
				strSubPassionname2=strSubPassionname;
				strPassionNote2=strPassionNote;
				psnPhoto2 = passionPhotoDao.getPassionPhoto(cpfNo, strPassionId, strSubPassionId);
				psnAudio2 = passionAudioDao.getPassionAudio(cpfNo, strPassionId, strSubPassionId);
				psnVideo2 = passionVideoDao.getPassionVideo(cpfNo, strPassionId, strSubPassionId);
				psnDoc2=passionDocDao.getPassionDocumentFile(cpfNo, strPassionId, strSubPassionId);
			}
			else if (i == 2) {
				category3 = "category3";
				strPassionId3=strPassionId;
				strSubPassionId3=strSubPassionId;
				strPassionname3=strPassionname;
				strSubPassionname3=strSubPassionname;
				strPassionNote3=strPassionNote;
				psnPhoto3 = passionPhotoDao.getPassionPhoto(cpfNo, strPassionId, strSubPassionId);
				psnAudio3 = passionAudioDao.getPassionAudio(cpfNo, strPassionId, strSubPassionId);
				psnVideo3 = passionVideoDao.getPassionVideo(cpfNo, strPassionId, strSubPassionId);
				psnDoc3=passionDocDao.getPassionDocumentFile(cpfNo, strPassionId, strSubPassionId);
			}
			i = i++;
			k = i;
		}
		renderRequest.setAttribute("userData",user);
		renderRequest.setAttribute("allPassionName", allPassionName);
		renderRequest.setAttribute("passionName", passionName);
		renderRequest.setAttribute("subpassionName", subpassionName);
		renderRequest.setAttribute("psnPhoto1", psnPhoto1);
		renderRequest.setAttribute("psnPhoto2", psnPhoto2);
		renderRequest.setAttribute("psnPhoto3", psnPhoto3);
		renderRequest.setAttribute("psnAudio1", psnAudio1);
		renderRequest.setAttribute("psnAudio2", psnAudio2);
		renderRequest.setAttribute("psnAudio3", psnAudio3);
		renderRequest.setAttribute("psnVideo1", psnVideo1);
		renderRequest.setAttribute("psnVideo2", psnVideo2);
		renderRequest.setAttribute("psnVideo3", psnVideo3);
		renderRequest.setAttribute("psnDoc1", psnDoc1);
		renderRequest.setAttribute("psnDoc2", psnDoc2);
		renderRequest.setAttribute("psnDoc3", psnDoc3);
		renderRequest.setAttribute("category1", category1);
		renderRequest.setAttribute("category2", category2);
		renderRequest.setAttribute("category3", category3);
		renderRequest.setAttribute("strPassionId1", strPassionId1);
		renderRequest.setAttribute("strPassionId2", strPassionId2);
		renderRequest.setAttribute("strPassionId3", strPassionId3);
		renderRequest.setAttribute("strSubPassionId1", strSubPassionId1);
		renderRequest.setAttribute("strSubPassionId2", strSubPassionId2);
		renderRequest.setAttribute("strSubPassionId3", strSubPassionId3);
		renderRequest.setAttribute("strPassionname1", strPassionname1);
		renderRequest.setAttribute("strPassionname2", strPassionname2);
		renderRequest.setAttribute("strPassionname3", strPassionname3);
		renderRequest.setAttribute("strSubPassionname1", strSubPassionname1);
		renderRequest.setAttribute("strSubPassionname2", strSubPassionname2);
		renderRequest.setAttribute("strSubPassionname3", strSubPassionname3);
		renderRequest.setAttribute("strPassionNote1", strPassionNote1);
		renderRequest.setAttribute("strPassionNote2", strPassionNote2);
		renderRequest.setAttribute("strPassionNote3", strPassionNote3);
		renderRequest.setAttribute("cpfnumber", cpfNo);
		renderRequest.setAttribute("i", i);
		renderRequest.setAttribute("k", k); 
		//request.getSession().removeAttribute("ongcUserCpf");
		
		
		return "/jsp/viewProfile.jsp";
	}

	private void setUser(Object userByCPFNumber) {
		// TODO Auto-generated method stub
		
	}

	public boolean validateCpf(String cpfNumber){
		boolean flag=false;
		
		UserDao userDao = new UserDaoImpl();
		
		if(cpfNumber==null || cpfNumber=="" || !userDao.validateUser(cpfNumber)){
			flag=false;
		}
		else{
			flag=true;
		}
		return flag;
	}
	
	
}
