package com.roger.OwnSpringFramework.app.step4.framework;

import com.roger.OwnSpringFramework.app.step4.app.Step4;
import com.roger.OwnSpringFramework.app.step4.framework.annotation.Autowired;
import com.roger.OwnSpringFramework.app.step4.framework.annotation.Component;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ApplicationContext {

    private final Set<Class<?>> componentBeans;

    public ApplicationContext(Package rootPackage) {
        final Reflections reflections = new Reflections(rootPackage.getName());
        this.componentBeans = reflections.getTypesAnnotatedWith(Component.class).stream()
                .filter(component -> !component.isInterface()).collect(Collectors.toSet());
    }

    public <T> T getBean(Class<T> clazz) {
        if (!clazz.isInterface()) {
            throw new FrameworkException(". . .");
        }
        Class<T> implementation = findImplementation(clazz);
        return createBean(implementation, clazz);
    }

    private <T> T createBean(Class<T> implementation, Class<T> interfaceItem) {
        try {
            Constructor<T> constructor = findConstructor(implementation);
            Object[] params = findParams(constructor);
            final T bean = constructor.newInstance(params);

            return (T) Proxy.newProxyInstance(
                    Step4.class.getClassLoader(),
                    new Class[]{interfaceItem},
                    new ProxyHandler(bean));
        } catch (FrameworkException e) {
            throw e;
        } catch (Exception e) {
            throw new FrameworkException(e);
        }

    }

    private <T> Object[] findParams(Constructor<T> constructor) {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        return Arrays.stream(parameterTypes)
                .map(this::getBean)
                .toArray();
    }

    private <T> Constructor<T> findConstructor(Class<T> implementation) {
        final Constructor<T>[] constructors = (Constructor<T>[]) implementation.getConstructors();
        if (constructors.length == 1) {
            return constructors[0];
        }
        final Set<Constructor<T>> consWithAutowired = Arrays.stream(constructors)
                .filter(constructor -> constructor.isAnnotationPresent(Autowired.class))
                .collect(Collectors.toSet());

        if (consWithAutowired.size() > 1) {
            throw new FrameworkException(". . .");
        }
        return consWithAutowired.stream()
                .findFirst()
                .orElseThrow(() -> new FrameworkException(". . ."));
    }

    private <T> Class<T> findImplementation(Class<T> clazz) {
        final Set<Class<?>> implementations = componentBeans.stream()
                .filter(component -> List.of(component.getInterfaces()).contains(clazz))
                .collect(Collectors.toSet());

        if (implementations.size() > 1) {
            throw new FrameworkException(". . .");
        }

        return (Class<T>) implementations.stream()
                .findFirst()
                .orElseThrow(() -> new FrameworkException("There is no bean for class " + clazz.getName()));
    }
}
