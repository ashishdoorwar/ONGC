package com.ongc.liferay.vigilance.exception;

import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import javax.portlet.PortletException;

public class UserLoginException extends PortletException{

	public UserLoginException() {
	}

	public UserLoginException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UserLoginException(Throwable cause) {
		super(cause);
	}
	
	public UserLoginException(String...errors) {
		this(ListUtil.toList(errors));
	}
	
	/**
	 * Custom constructor for validation
	 * @param errors
	 */
	public UserLoginException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	private List<String> _errors;
}
