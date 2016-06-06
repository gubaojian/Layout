package com.efurture.glue.engine;

/**
 * Created by furture on 16/6/1.
 */
public class XmlException extends  RuntimeException {

    public XmlException(Throwable throwable) {
        super(throwable);
    }

    public XmlException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public XmlException(String detailMessage) {
        super(detailMessage);
    }

    public XmlException() {
    }
}
