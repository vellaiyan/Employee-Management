package com.ideas2it.exception;

import java.lang.Exception;

public class customException extends Exception{

    public customException() {
        super();
    }

    public customException(String exception) {
        super(exception);
    }

    public customException(Throwable throwable) {
        super(throwable);

    }
    
    public customException(String exception, Throwable throwable) {
        super(exception, throwable);

    }
}