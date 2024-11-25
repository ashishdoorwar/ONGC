package com.ongc.liferay.vigilance.exception;

import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import javax.portlet.PortletException;

public class AdminChangePasswordException extends PortletException {
	public AdminChangePasswordException() {
	}

	public AdminChangePasswordException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AdminChangePasswordException(Throwable cause) {
		super(cause);
	}
	
	public AdminChangePasswordException(String...errors) {
		this(ListUtil.toList(errors));
	}
	
	/**
	 * Custom constructor for validation
	 * @param errors
	 */
	public AdminChangePasswordException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	private List<String> _errors;
}
