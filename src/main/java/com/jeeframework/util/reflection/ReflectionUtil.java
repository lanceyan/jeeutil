package com.jeeframework.util.reflection;

import com.jeeframework.util.classes.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author lanceyan
 */
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * Retrieves the value of the specified boolean-field of the given object.
     *
     * @param object    the object that holds the field
     * @param fieldName the name of the field
     * @return the field value
     * @throws NoSuchFieldException when the field does not exist
     */
    public static boolean getBooleanField(Object object, String fieldName) throws NoSuchFieldException {

        Field field = getField(object, fieldName);
        try {
            return field.getBoolean(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoSuchFieldException("unable to access field [" + fieldName + "]: " + e.toString());
        }
    }

    /**
     * Retrieves the value of the specified String-field of the given object.
     *
     * @param object    the object that holds the field
     * @param fieldName the name of the field
     * @return the field value
     * @throws NoSuchFieldException when the field does not exist
     */
    public static String getStringField(Object object, String fieldName) throws NoSuchFieldException {
        Field field = getField(object, fieldName);
        try {
            return (String) field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoSuchFieldException("unable to access field [" + fieldName + "]: " + e.toString());
        }
    }

    /**
     * Retrieves the specified field of the given object.
     *
     * @param object    the object that holds the field
     * @param fieldName the name of the field
     * @return the field
     * @throws NoSuchFieldException when the field does not exist
     */
    public static Field getField(Object object, String fieldName) throws NoSuchFieldException {
        return getField(object.getClass(), fieldName);
    }

    /**
     * Retrieves the specified field of the given object.
     *
     * @param instanceClass the class that contains the field
     * @param fieldName     the name of the field
     * @return the field
     * @throws NoSuchFieldException when the field does not exist
     */
    public static Field getField(Class instanceClass, String fieldName) throws NoSuchFieldException {
        try {
            Field field = null;
            while (field == null) {
                try {
                    field = instanceClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    instanceClass = instanceClass.getSuperclass();
                    if (instanceClass == null) {
                        throw e;
                    }
                }
            }
            field.setAccessible(true);
            return field;
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to access field [" + fieldName + "]: " + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to access field [" + fieldName + "]: " + e.toString());
        }
    }

    /**
     * Sets a field value for the given object.
     *
     * @param object    the object that should be changed
     * @param fieldName the name of the field
     * @param value     the value
     * @throws NoSuchFieldException when the field does not exist or could not be written
     */
    public static void setField(Object object, String fieldName, int value) throws NoSuchFieldException {
        setField(object, fieldName, Integer.valueOf(value));
    }

    /**
     * Sets a field value for the given object.
     *
     * @param object    the object that should be changed
     * @param fieldName the name of the field
     * @param value     the value
     * @throws NoSuchFieldException when the field does not exist or could not be written
     */
    public static void setField(Object object, String fieldName, Object value) throws NoSuchFieldException {
        try {
            Field field = null;
            Class instanceClass = object.getClass();
            while (field == null) {
                try {
                    field = instanceClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    instanceClass = instanceClass.getSuperclass();
                    if (instanceClass == null) {
                        throw e;
                    }
                }
            }
            field.setAccessible(true);
            field.set(object, value);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to set field [" + fieldName + "]: " + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to set field [" + fieldName + "]: " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to set field [" + fieldName + "]: " + e.toString());
        }
    }

    /**
     * Sets a field value for the given object.
     *
     * @param fieldClass the class that should be changed
     * @param fieldName  the name of the field
     * @param value      the value
     * @throws NoSuchFieldException when the field does not exist or could not be written
     */
    public static void setStaticField(Class fieldClass, String fieldName, Object value) throws NoSuchFieldException {
        try {
            Field field = null;
            while (field == null) {
                try {
                    field = fieldClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    fieldClass = fieldClass.getSuperclass();
                    if (fieldClass == null) {
                        throw e;
                    }
                }
            }
            field.setAccessible(true);
            field.set(null, value);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to set field [" + fieldName + "]: " + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to set field [" + fieldName + "]: " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchFieldException("Unable to set field [" + fieldName + "]: " + e.toString());
        }
    }

    @SuppressWarnings("unchecked")
    public static Object callMethod(Object object, String methodName, int value) throws NoSuchMethodException {
        Class instanceClass = object.getClass();
        Method method = null;
        while (method == null) {
            try {
                method = instanceClass.getDeclaredMethod(methodName, new Class[]{Integer.TYPE});
            } catch (NoSuchMethodException e) {
                instanceClass = instanceClass.getSuperclass();
                if (instanceClass == null) {
                    throw e;
                }
            }
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, new Object[]{Integer.valueOf(value)});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchMethodException(e.toString());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    @SuppressWarnings("unchecked")
    public static Object callMethod(String methodName, Object object, Class[] signature, Object[] values)
            throws NoSuchMethodException {
        Class instanceClass = object.getClass();
        Method method = null;
        while (method == null) {
            try {
                method = instanceClass.getDeclaredMethod(methodName, signature);
            } catch (NoSuchMethodException e) {
                instanceClass = instanceClass.getSuperclass();
                if (instanceClass == null) {
                    throw e;
                }
            }
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchMethodException(e.toString());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    @SuppressWarnings("unchecked")
    public static Object callMethod(String methodName, Object object) throws NoSuchMethodException {
        Class instanceClass = object.getClass();
        Method method = null;
        while (method == null) {
            try {
                method = instanceClass.getDeclaredMethod(methodName, new Class[0]);
            } catch (NoSuchMethodException e) {
                instanceClass = instanceClass.getSuperclass();
                if (instanceClass == null) {
                    throw e;
                }
            }
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchMethodException(e.toString());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * @param object
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field field = getField(object, fieldName);
        return field.get(object);
    }

    /**
     * @param parameterClass
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     */
    public static Object getStaticFieldValue(Class parameterClass, String fieldName) throws IllegalArgumentException,
            IllegalAccessException, NoSuchFieldException {
        Field field = getField(parameterClass, fieldName);
        return field.get(null);
    }

    private static boolean isValid(final StackTraceElement element) {
        // ignore native methods (oftentimes are repeated frames)
        if (element.isNativeMethod()) {
            return false;
        }
        final String cn = element.getClassName();
        // ignore OpenJDK internal classes involved with reflective invocation
        if (cn.startsWith("sun.reflect.")) {
            return false;
        }
        final String mn = element.getMethodName();
        // ignore use of reflection including:
        // Method.invoke
        // InvocationHandler.invoke
        // Constructor.newInstance
        if (cn.startsWith("java.lang.reflect.") && (mn.equals("invoke") || mn.equals("newInstance"))) {
            return false;
        }
        // ignore use of Java 1.9+ reflection classes
        if (cn.startsWith("jdk.internal.reflect.")) {
            return false;
        }
        // ignore Class.newInstance
        if (cn.equals("java.lang.Class") && mn.equals("newInstance")) {
            return false;
        }
        // ignore use of Java 1.7+ MethodHandle.invokeFoo() methods
        if (cn.equals("java.lang.invoke.MethodHandle") && mn.startsWith("invoke")) {
            return false;
        }
        // any others?
        return true;
    }

    public static StackTraceElement getEquivalentStackTraceElement(final int depth) {
        // (MS) I tested the difference between using Throwable.getStackTrace() and Thread.getStackTrace(), and
        // the version using Throwable was surprisingly faster! at least on Java 1.8. See ReflectionBenchmark.
        final StackTraceElement[] elements = new Throwable().getStackTrace();
        int i = 0;
        for (final StackTraceElement element : elements) {
            if (isValid(element)) {
                if (i == depth) {
                    return element;
                }
                ++i;
            }
        }
//        LOGGER.error("Could not find an appropriate StackTraceElement at index {}", depth);
        throw new IndexOutOfBoundsException(Integer.toString(depth));
    }

    public static Class getCallerClass(int depth) {
        final StackTraceElement element = getEquivalentStackTraceElement(depth + 1);
        try {
            return ClassUtils.forName(element.getClassName());
        } catch (final ClassNotFoundException e) {
//    LOGGER.error("Could not find class in ReflectionUtil.getCallerClass({}).", depth, e);
        }
//    StackWalker instance = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
//    Class<?> callerClass = instance.getCallerClass();
        return null;
    }

}
