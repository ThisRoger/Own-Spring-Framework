package com.roger.OwnSpringFramework.app.step5.framework;

import com.roger.OwnSpringFramework.app.step5.framework.annotation.Cachable;
import com.roger.OwnSpringFramework.app.step5.framework.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProxyHandler implements InvocationHandler {

    private final Object objectToHandle;
    private final Map<List<Object>, Object> cacheMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(ProxyHandler.class);

    public ProxyHandler(Object objectToHandle) {
        this.objectToHandle = objectToHandle;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isCachable(method)) {
            List<Object> key = createCacheKey(method, args);
            if (cacheMap.containsKey(key)) {
                return cacheMap.get(key);
            }
        }
        if (isTransactional(method)) {
            return handleInTransaction(method, args);
        }
        return calculateResult(method, args);
    }

    private List<Object> createCacheKey(Method method, Object[] args) {
        return List.of(method, Arrays.stream(args).toList());
    }

    private boolean isTransactional(Method method) {
        try {
            return objectToHandle.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Transactional.class);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean isCachable(Method method) {
        try {
            return objectToHandle.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Cachable.class);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private Object handleInTransaction(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
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
        final Object result = method.invoke(objectToHandle, args);
        if (isCachable(method)) {
            List<Object> key = createCacheKey(method, args);
            cacheMap.put(key, result);
        }
        return result;
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
