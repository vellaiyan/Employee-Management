/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.exception;

import java.lang.Exception;

/**
 * The {@code CustomException} class implemented to throw custom exception.
 * trainees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */

public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String exception) {
        super(exception);
    }

    public CustomException(Throwable throwable) {
        super(throwable);

    }
    
    public CustomException(String exception, Throwable throwable) {
        super(exception, throwable);

    }
}