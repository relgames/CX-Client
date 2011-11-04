package org.relgames.cxclient.service;

/**
 * @author Oleg Poleshuk
 */
public class CxServiceException extends Exception {
    public CxServiceException() {
        super();
    }

    public CxServiceException(String detailMessage) {
        super(detailMessage);
    }

    public CxServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CxServiceException(Throwable throwable) {
        super(throwable);
    }
}

