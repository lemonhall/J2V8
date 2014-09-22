package com.eclipsesource.v8;

public class V8Array extends V8Object {

    public V8Array(final V8 v8) {
        super(v8);
        V8.checkThread();
    }

    @Override
    protected void initialize(final int runtimeHandle, final int objectHandle) {
        v8._initNewV8Array(runtimeHandle, objectHandle);
    }

    @Override
    public void release() {
        length = -1;
        firstString = null;
        // v8._release(v8.getV8RuntimeHandle(), objectHandle);
        // v8.releaseObjRef();
        if (this instanceof V8Array) {
            addBack(this);
        }
    }

    int length = -1;
    public int length() {
        // V8.checkThread();
        if (length < 0) {
            length = v8._arrayGetSize(v8.v8RuntimeHandle, objectHandle);
        }
        return length;
    }

    public int getType(final int index) {
        // V8.checkThread();
        return v8._getType(v8.v8RuntimeHandle, objectHandle, index);
    }

    public int getInteger(final int index) {
        // V8.checkThread();
        return v8._arrayGetInteger(v8.v8RuntimeHandle, objectHandle, index);
    }

    public boolean getBoolean(final int index) {
        // V8.checkThread();
        return v8._arrayGetBoolean(v8.v8RuntimeHandle, objectHandle, index);
    }

    public double getDouble(final int index) {
        // V8.checkThread();
        return v8._arrayGetDouble(v8.v8RuntimeHandle, objectHandle, index);
    }

    public double[] getDoubles(final int start, final int length) {
        // V8.checkThread();
        return v8._arrayGetDoubles(v8.v8RuntimeHandle, objectHandle, start, length);
    }

    String firstString = null;
    public String getString(final int index) {
        // V8.checkThread();
        // if ((index == 0) && (firstString != null)) {
        // return firstString;
        // }
        // if (index == 0) {
        // firstString = v8._arrayGetString(v8.getV8RuntimeHandle(), getHandle(), index);
        // return firstString;
        // }
        return v8._arrayGetString(v8.v8RuntimeHandle, getHandle(), index);
    }

    public V8Array getArray(final int index) {
        // V8.checkThread();
        V8Array result = createArray(v8);
        try {
            v8._arrayGetArray(v8.getV8RuntimeHandle(), getHandle(), index, result.getHandle(), result);
        } catch (Exception e) {
            result.release();
            throw e;
        }
        return result;
    }

    public V8Object getObject(final int index) {
        // V8.checkThread();
        V8Object result = new V8Object(v8);
        try {
            v8._arrayGetObject(v8.getV8RuntimeHandle(), getHandle(), index, result.getHandle());
        } catch (Exception e) {
            result.release();
            throw e;
        }
        return result;
    }

    public V8Array push(final int value) {
        V8.checkThread();
        v8._addArrayIntItem(v8.getV8RuntimeHandle(), getHandle(), value);
        return this;
    }

    public V8Array push(final boolean value) {
        V8.checkThread();
        v8._addArrayBooleanItem(v8.getV8RuntimeHandle(), getHandle(), value);
        return this;
    }

    public V8Array push(final double value) {
        V8.checkThread();
        v8._addArrayDoubleItem(v8.getV8RuntimeHandle(), getHandle(), value);
        return this;
    }

    public V8Array push(final String value) {
        V8.checkThread();
        v8._addArrayStringItem(v8.getV8RuntimeHandle(), getHandle(), value);
        return this;
    }

    public V8Array push(final V8Object value) {
        V8.checkThread();
        v8._addArrayObjectItem(v8.getV8RuntimeHandle(), getHandle(), value.getHandle());
        return this;
    }

    public V8Array push(final V8Array value) {
        V8.checkThread();
        v8._addArrayArrayItem(v8.getV8RuntimeHandle(), getHandle(), value.getHandle());
        return this;
    }

    public V8Array pushUndefined() {
        V8.checkThread();
        v8._addArrayUndefinedItem(v8.getV8RuntimeHandle(), getHandle());
        return this;
    }

}