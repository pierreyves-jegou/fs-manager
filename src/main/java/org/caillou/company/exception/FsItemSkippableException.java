package org.caillou.company.exception;

public class FsItemSkippableException extends FSException{

    public FsItemSkippableException(String message, Throwable cause) {
        super(message, cause);
    }

    public FsItemSkippableException(String message) {
        super(message);
    }
}
