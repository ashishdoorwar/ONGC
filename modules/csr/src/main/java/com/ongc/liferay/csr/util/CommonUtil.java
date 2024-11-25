package com.ongc.liferay.csr.util;

import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;

public class CommonUtil {
	
	public static boolean checkParameter(PortletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		boolean parameterCheck = false;
		 String regex="[^<>]*";
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			if (!Pattern.matches("[^<>]*", (ParamUtil.getString(request, parameterName)))) {
				
				System.out.println(ParamUtil.getString(request, parameterName)+"========================>"+Pattern.matches("[^<>]*", (ParamUtil.getString(request, parameterName))));
				parameterCheck = true;
				break;
			}
		}
		return parameterCheck;
	}
	
}
