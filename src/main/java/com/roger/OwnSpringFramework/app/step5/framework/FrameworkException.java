package com.roger.OwnSpringFramework.app.step5.framework;

public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(Throwable throwable) {
        super(throwable);
    }

}
