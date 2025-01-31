package com.roger.OwnSpringFramework.app.step2Priv.framework;

import com.roger.OwnSpringFramework.app.step2Priv.app.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private final Object objectToHandle;
    private static final Logger logger = LoggerFactory.getLogger(ProxyHandler.class);

    public ProxyHandler(Object objectToHandle) {
        this.objectToHandle = objectToHandle;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            beginTransaction();

            final Object result = method.invoke(objectToHandle, args);

            commitTransaction();

            return result;
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }


    private void beginTransaction() {
        logger.debug("BEGIN TRANSACTION");
    }

    private void commitTransaction() {
        logger.debug("COMMIT TRANSACTION");
    }

    private void rollbackTransaction() {
        logger.error("ROLLBACK TRANSACTION");
    }
}
