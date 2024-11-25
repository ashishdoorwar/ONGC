package com.ongc.liferay.util;

import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.model.OrderCircular;
import com.ongc.liferay.service.OrderAndCircularService;
import com.ongc.liferay.serviceImpl.OrderAndCircularServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class CommonUtil {
	
	private OrderAndCircularService orderAndCircularService = new OrderAndCircularServiceImpl();
	
	private List<OrderCircular> getOrderCircular(RenderRequest renderRequest, RenderResponse renderResponse) {
		String startDate=ParamUtil.getString(renderRequest, "startDate");
		String endDate=ParamUtil.getString(renderRequest, "endDate");
		String subject=ParamUtil.getString(renderRequest, "subject");
		String category=ParamUtil.getString(renderRequest, "category");
		//System.out.println("startDate====>"+startDate+"endDate==========>"+endDate+"subject=====>"+subject+"category====>"+category);
		List<OrderCircular> list = orderAndCircularService.selectOrderAndCircular(startDate, endDate, subject, category);
		return list;
	}
	
	public static void copy(File src, File dest) throws IOException { 
		InputStream is = null; 
		OutputStream os = null; 
		try { 
			is = new FileInputStream(src); 
			os = new FileOutputStream(dest); 
			byte[] buf = new byte[10240];
			int bytesRead; 
			while ((bytesRead = is.read(buf)) > 0){
				os.write(buf, 0, bytesRead); 
			} 
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			is.close();
			os.close();
		} 
	}
	
	public static boolean checkParameter(PortletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		boolean parameterCheck = false;
		 String regex="[^<>]*";
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			if (!Pattern.matches("[^<>]*", (ParamUtil.getString(request, parameterName)))) {
				//System.out.println(ParamUtil.getString(request, parameterName)+"========================>"+Pattern.matches("[^<>]*", (ParamUtil.getString(request, parameterName))));
				parameterCheck = true;
				break;
			}
		}
		return parameterCheck;
	}
	
	
	
}
