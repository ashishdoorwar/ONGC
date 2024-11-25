package com.ongc.liferay.vigilance.exception;

import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import javax.portlet.PortletException;

public class UserChangePasswordException extends PortletException {

	
	public UserChangePasswordException() {
	}

	public UserChangePasswordException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UserChangePasswordException(Throwable cause) {
		super(cause);
	}
	
	public UserChangePasswordException(String...errors) {
		this(ListUtil.toList(errors));
	}
	
	/**
	 * Custom constructor for validation
	 * @param errors
	 */
	public UserChangePasswordException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	private List<String> _errors;
}
