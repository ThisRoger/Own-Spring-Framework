package com.roger.OwnSpringFramework.app.step4.framework;

import com.roger.OwnSpringFramework.app.step4.framework.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private final Object objectToHandle;
    private static final Logger logger = LoggerFactory.getLogger(ProxyHandler.class);

    public ProxyHandler(Object objectToHandle) {
        this.objectToHandle = objectToHandle;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isTransactional(method)) {
            return hanldeInTransaction(method, args);
        }
        return calculateResult(method, args);
    }

    private boolean isTransactional(Method method) {
        try {
            return objectToHandle.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Transactional.class);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private Object hanldeInTransaction(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        try {
            beginTransaction();

            final Object result = calculateResult(method, args);

            commitTransaction();

            return result;
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    private Object calculateResult(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        return method.invoke(objectToHandle, args);
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
