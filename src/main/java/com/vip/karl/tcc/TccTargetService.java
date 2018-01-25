package com.vip.karl.tcc;

import java.lang.reflect.Method;

public class TccTargetService {
    private Object service;
    private Method confirmMethod;
    private Method cancelMethod;
    private Object[] parameters;

    public TccTargetService() {
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }

    public Method getConfirmMethod() {
        return confirmMethod;
    }

    public void setConfirmMethod(Method confirmMethod) {
        this.confirmMethod = confirmMethod;
    }

    public Method getCancelMethod() {
        return cancelMethod;
    }

    public void setCancelMethod(Method cancelMethod) {
        this.cancelMethod = cancelMethod;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
