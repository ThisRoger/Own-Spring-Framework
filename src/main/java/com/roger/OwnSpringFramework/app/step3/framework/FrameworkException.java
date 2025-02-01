package com.roger.OwnSpringFramework.app.step3.framework;

public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(Throwable throwable) {
        super(throwable);
    }

}
