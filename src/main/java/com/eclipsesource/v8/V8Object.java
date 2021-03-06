/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.v8;

import java.lang.reflect.Method;

public class V8Object extends V8Value {

    protected V8Object() {
        v8 = (V8) this;
        objectHandle = 0;
        released = false;
    }

    public V8Object(final V8 v8) {
        this(v8, true);
    }

    protected V8Object(final V8 v8, final boolean initialize) {
        this.v8 = v8;
        V8.checkThread();
        objectHandle = v8ObjectInstanceCounter++;
        if (initialize) {
            initialize(v8.getV8RuntimeHandle(), objectHandle);
        }
    }

    public boolean contains(final String key) {
        V8.checkThread();
        checkReleaesd();
        return v8._contains(v8.getV8RuntimeHandle(), objectHandle, key);
    }

    public String[] getKeys() {
        V8.checkThread();
        checkReleaesd();
        return v8._getKeys(v8.getV8RuntimeHandle(), objectHandle);
    }

    public int getType(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        return v8._getType(v8.getV8RuntimeHandle(), objectHandle, key);
    }

    public int getInteger(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        return v8._getInteger(v8.getV8RuntimeHandle(), objectHandle, key);
    }

    public boolean getBoolean(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        return v8._getBoolean(v8.getV8RuntimeHandle(), objectHandle, key);
    }

    public double getDouble(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        return v8._getDouble(v8.getV8RuntimeHandle(), objectHandle, key);
    }

    public String getString(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        return v8._getString(v8.getV8RuntimeHandle(), objectHandle, key);
    }

    public V8Array getArray(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        V8Array result = new V8Array(v8, false);
        try {
            result.released = false;
            v8.addObjRef();
            v8._getArray(v8.getV8RuntimeHandle(), getHandle(), key, result.getHandle());
        } catch (Exception e) {
            result.release();
            throw e;
        }
        return result;
    }

    public V8Object getObject(final String key) throws V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        V8Object result = new V8Object(v8, false);
        try {
            result.released = false;
            v8.addObjRef();
            v8._getObject(v8.getV8RuntimeHandle(), objectHandle, key, result.getHandle());
        } catch (Exception e) {
            result.release();
            throw e;
        }
        return result;
    }

    public V8Array createParameterList(final int size) {
        V8.checkThread();
        checkReleaesd();
        return null;
    }

    public int executeIntFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException,
    V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        int parametersHandle = parameters == null ? -1 : parameters.getHandle();
        return v8._executeIntFunction(v8.getV8RuntimeHandle(), getHandle(), name, parametersHandle);
    }

    public double executeDoubleFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException,
    V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        int parametersHandle = parameters == null ? -1 : parameters.getHandle();
        return v8._executeDoubleFunction(v8.getV8RuntimeHandle(), getHandle(), name, parametersHandle);
    }

    public String executeStringFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException,
    V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        int parametersHandle = parameters == null ? -1 : parameters.getHandle();
        return v8._executeStringFunction(v8.getV8RuntimeHandle(), getHandle(), name, parametersHandle);
    }

    public boolean executeBooleanFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException,
    V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        int parametersHandle = parameters == null ? -1 : parameters.getHandle();
        return v8._executeBooleanFunction(v8.getV8RuntimeHandle(), getHandle(), name, parametersHandle);
    }

    public V8Array executeArrayFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException,
    V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        V8Array result = new V8Array(v8);
        try {
            int parametersHandle = parameters == null ? -1 : parameters.getHandle();
            v8._executeArrayFunction(v8.getV8RuntimeHandle(), objectHandle, name, parametersHandle, result.getHandle());
        } catch (Exception e) {
            result.release();
            throw e;
        }
        return result;
    }

    public V8Object executeObjectFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException,
    V8ResultUndefined {
        V8.checkThread();
        checkReleaesd();
        V8Object result = new V8Object(v8);
        try {
            int parametersHandle = parameters == null ? -1 : parameters.getHandle();
            v8._executeObjectFunction(v8.getV8RuntimeHandle(), objectHandle, name, parametersHandle, result.getHandle());
        } catch (Exception e) {
            result.release();
            throw e;
        }
        return result;
    }

    public void executeVoidFunction(final String name, final V8Array parameters) throws V8ScriptExecutionException {
        V8.checkThread();
        checkReleaesd();
        int parametersHandle = parameters == null ? -1 : parameters.getHandle();
        v8._executeVoidFunction(v8.getV8RuntimeHandle(), objectHandle, name, parametersHandle);
    }

    public V8Object add(final String key, final int value) {
        V8.checkThread();
        checkReleaesd();
        v8._add(v8.getV8RuntimeHandle(), objectHandle, key, value);
        return this;
    }

    public V8Object add(final String key, final boolean value) {
        V8.checkThread();
        checkReleaesd();
        v8._add(v8.getV8RuntimeHandle(), objectHandle, key, value);
        return this;
    }

    public V8Object add(final String key, final double value) {
        V8.checkThread();
        checkReleaesd();
        v8._add(v8.getV8RuntimeHandle(), objectHandle, key, value);
        return this;
    }

    public V8Object add(final String key, final String value) {
        V8.checkThread();
        checkReleaesd();
        v8._add(v8.getV8RuntimeHandle(), objectHandle, key, value);
        return this;
    }

    public V8Object add(final String key, final V8Value value) {
        V8.checkThread();
        checkReleaesd();
        v8._addObject(v8.getV8RuntimeHandle(), objectHandle, key, value.getHandle());
        return this;
    }

    public V8Object addUndefined(final String key) {
        V8.checkThread();
        checkReleaesd();
        v8._addUndefined(v8.getV8RuntimeHandle(), objectHandle, key);
        return this;
    }

    public V8Object setPrototype(final V8Object value) {
        V8.checkThread();
        checkReleaesd();
        v8._setPrototype(v8.getV8RuntimeHandle(), objectHandle, value.getHandle());
        return this;
    }

    public V8Object registerJavaMethod(final JavaCallback callback, final String jsFunctionName) {
        V8.checkThread();
        checkReleaesd();
        v8.registerCallback(callback, getHandle(), jsFunctionName);
        return this;
    }

    public V8Object registerJavaMethod(final JavaVoidCallback callback, final String jsFunctionName) {
        V8.checkThread();
        checkReleaesd();
        v8.registerVoidCallback(callback, getHandle(), jsFunctionName);
        return this;
    }

    public V8Object registerJavaMethod(final Object object, final String methodName, final String jsFunctionName,
            final Class<?>[] parameterTypes) {
        V8.checkThread();
        checkReleaesd();
        try {
            Method method = object.getClass().getMethod(methodName, parameterTypes);
            method.setAccessible(true);
            v8.registerCallback(object, method, getHandle(), jsFunctionName);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        } catch (SecurityException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public String toString() {
        V8.checkThread();
        checkReleaesd();
        return executeStringFunction("toString", null);
    }

}
