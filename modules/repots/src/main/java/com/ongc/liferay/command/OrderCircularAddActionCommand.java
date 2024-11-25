package com.ongc.liferay.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ongc.liferay.constants.RepotsPortletKeys;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

import java.io.File;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.io.FileUtils;
import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" +RepotsPortletKeys.Admin,
			"mvc.command.name=add_order_circular"
		},
		service = MVCActionCommand.class
	)
public class OrderCircularAddActionCommand extends BaseMVCActionCommand{

	private OrderAndCircularService orderAndCircularService =new OrderAndCircularServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		OrderCircular orderCircular =new OrderCircular();
		
		int id=ParamUtil.getInteger(actionRequest, "id");
		String subject=ParamUtil.getString(actionRequest, "subject");
		String issuedBy=ParamUtil.getString(actionRequest, "issuedBy");
		String category=ParamUtil.getString(actionRequest, "category");
		String orderNumber=ParamUtil.getString(actionRequest, "orderNumber");
		String orderDate=ParamUtil.getString(actionRequest, "orderDate");
		
		ResourceBundle szResBundl = ResourceBundle.getBundle("/content/Repots");				
		String filePath = szResBundl.getString("ordercircularPdfFilePath").toString().trim();

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName=uploadPortletRequest.getFileName("fileUpload");
		File file = uploadPortletRequest.getFile("fileUpload");

String ext =fileName.substring(fileName.lastIndexOf("."));
//		dbFileName=String.valueOf(getMaxIdFromTable("ongc_facilitation", "id")+file.getName().substring(file.getName().lastIndexOf(".")));
//		File to = new File(filePath+dbFileName);
		
		orderCircular.setId(id);
		orderCircular.setSubject(subject);
		orderCircular.setIssuedBy(issuedBy);
		orderCircular.setCategory(category);
		orderCircular.setOrderNumber(orderNumber);
		orderCircular.setOrderDate(orderDate);
		orderCircular.setFileUploadFileName(ext);
		if(id==0) {
			int insertOrderAndCircular = orderAndCircularService.insertOrderAndCircular(orderCircular, null, null);
			String dbFileName = String.valueOf(insertOrderAndCircular+file.getName().substring(file.getName().lastIndexOf(".")));
			FileUtils.copyFile(uploadPortletRequest.getFile("fileUpload"), new File(filePath,dbFileName));
		}else {
//			orderAndCircularService.updateOrderAndCircular(orderCircular, null, null);
		}
		
	}

}
