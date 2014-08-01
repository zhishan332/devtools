package com.yermoon.utils;

/**
 * 从Spring中抽取的一个对参数或表达式合法性进行判断的工具类
 * 该工具类的所有方法在判定为参数不合法时均会抛出IllegalArgumentException
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
public abstract class MyAssert {

    /**
     * MyAssert a boolean expression, throwing <code>IllegalArgumentException</code>
     * if the test result is <code>false</code>.
     * <pre class="code">MyAssert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if expression is <code>false</code>
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * MyAssert a boolean expression, throwing <code>IllegalArgumentException</code>
     * if the test result is <code>false</code>.
     * <pre class="code">MyAssert.isTrue(i &gt; 0);</pre>
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if expression is <code>false</code>
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * MyAssert that an object is <code>null</code> .
     * <pre class="code">MyAssert.isNull(value, "The value must be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is not <code>null</code>
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * MyAssert that an object is <code>null</code> .
     * <pre class="code">MyAssert.isNull(value);</pre>
     *
     * @param object the object to check
     * @throws IllegalArgumentException if the object is not <code>null</code>
     */
    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    /**
     * MyAssert that an object is not <code>null</code> .
     * <pre class="code">MyAssert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is <code>null</code>
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * MyAssert that an object is not <code>null</code> .
     * <pre class="code">MyAssert.notNull(clazz);</pre>
     *
     * @param object the object to check
     * @throws IllegalArgumentException if the object is <code>null</code>
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * MyAssert that an array has no null elements.
     * Note: Does not complain if the array is empty!
     * <pre class="code">MyAssert.noNullElements(array, "The array must have non-null elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a <code>null</code> element
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * MyAssert that an array has no null elements.
     * Note: Does not complain if the array is empty!
     * <pre class="code">MyAssert.noNullElements(array);</pre>
     *
     * @param array the array to check
     * @throws IllegalArgumentException if the object array contains a <code>null</code> element
     */
    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    /**
     * MyAssert that the provided object is an instance of the provided class.
     * <pre class="code">MyAssert.instanceOf(Foo.class, foo);</pre>
     *
     * @param clazz the required class
     * @param obj   the object to check
     * @throws IllegalArgumentException if the object is not an instance of clazz
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    /**
     * MyAssert that the provided object is an instance of the provided class.
     * <pre class="code">MyAssert.instanceOf(Foo.class, foo);</pre>
     *
     * @param type    the type to check against
     * @param obj     the object to check
     * @param message a message which will be prepended to the message produced by
     *                the function itself, and which may be used to provide context. It should
     *                normally end in a ": " or ". " so that the function generate message looks
     *                ok when prepended to it.
     * @throws IllegalArgumentException if the object is not an instance of clazz
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(message +
                    "Object of class [" + (obj != null ? obj.getClass().getName() : "null") +
                    "] must be an instance of " + type);
        }
    }

    /**
     * MyAssert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
     * <pre class="code">MyAssert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the super type to check
     * @param subType   the sub type to check
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void isAssignable(Class superType, Class subType) {
        isAssignable(superType, subType, "");
    }

    /**
     * MyAssert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
     * <pre class="code">MyAssert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the super type to check against
     * @param subType   the sub type to check
     * @param message   a message which will be prepended to the message produced by
     *                  the function itself, and which may be used to provide context. It should
     *                  normally end in a ": " or ". " so that the function generate message looks
     *                  ok when prepended to it.
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void isAssignable(Class superType, Class subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }

    /**
     * MyAssert a boolean expression, throwing <code>IllegalStateException</code>
     * if the test result is <code>false</code>. Call isTrue if you wish to
     * throw IllegalArgumentException on an assertion failure.
     * <pre class="code">MyAssert.state(id == null, "The id property must not already be initialized");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalStateException if expression is <code>false</code>
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * MyAssert a boolean expression, throwing {@link IllegalStateException}
     * if the test result is <code>false</code>.
     * <p>Call {@link #isTrue(boolean)} if you wish to
     * throw {@link IllegalArgumentException} on an assertion failure.
     * <pre class="code">MyAssert.state(id == null);</pre>
     *
     * @param expression a boolean expression
     * @throws IllegalStateException if the supplied expression is <code>false</code>
     */
    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }

    /**
     * 判断String类型的参数是否为空值
     *
     * @param text 参数
     */
    public static void notBlank(String text) {
        if (!hasLength(text)) {
            throw new IllegalArgumentException("[Assertion failed] - this String argument must have length; it must not be null or empty");
        }
    }

    /**
     * 判断String类型的参数是否为空值
     *
     * @param texts 参数
     */
    public static void notBlank(String... texts) {
        if (texts != null && texts.length > 0) {
            for (String text : texts) {
                if (!hasLength(text)) {
                    throw new IllegalArgumentException("[Assertion failed] - this String argument must have length; it must not be null or empty");
                }
            }
        }
    }

    /**
     * 判断String类型的参数是否为空值
     *
     * @param text    参数
     * @param message 自定义异常消息
     */
    public static void notBlank(String text, String message) {
        if (!hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    private static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }
}
