package com.ongc.liferay.vigilance.exception;

import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import javax.portlet.PortletException;

public class AdminLoginException extends PortletException{

	public AdminLoginException() {
	}

	public AdminLoginException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AdminLoginException(Throwable cause) {
		super(cause);
	}
	
	public AdminLoginException(String...errors) {
		this(ListUtil.toList(errors));
	}
	
	/**
	 * Custom constructor for validation
	 * @param errors
	 */
	public AdminLoginException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	private List<String> _errors;
}
