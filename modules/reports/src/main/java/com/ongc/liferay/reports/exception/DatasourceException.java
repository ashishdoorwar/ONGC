package com.ongc.liferay.reports.exception;

/**
 * @author Ranjeet
 *
 */
public class DatasourceException extends Exception{

	public DatasourceException() {
        super();
}

public DatasourceException(String message) {
    super(message);
}

public DatasourceException(String message, Throwable cause) {
    super(message, cause);
}

public DatasourceException(Throwable cause) {
    super(cause);
}
}
