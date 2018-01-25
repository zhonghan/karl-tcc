package com.vip.karl.tcc.proxy;


import com.vip.karl.tcc.TccTargetService;
import com.vip.karl.tcc.TccTransactionThreadLocal;
import com.vip.karl.tcc.annotation.Tcc;
import com.vip.karl.tcc.annotation.TccTransaction;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author karl.zhong
 */
public class CglibInterceptor implements MethodInterceptor {
    private Object target;

    public  CglibInterceptor(Object target) {
        this.target = target;
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(method.isAnnotationPresent(TccTransaction.class)){
            tccTransactionInterceptor(method, args);
        }else if(method.isAnnotationPresent(Tcc.class)){
            if(TccTransactionThreadLocal.get().isNeedCancel()) {
                return null;
            }
            tccTryInterceptor(method, args);
        }
        return null;
    }

    private void tccTryInterceptor(Method method, Object[] args) {
        Tcc tcc = method.getAnnotation(Tcc.class);
        TccTargetService targetService = new TccTargetService();
        targetService.setService(target);
        for(Method m : target.getClass().getMethods()) {
            if(m.getName().equals(tcc.confirmMethod())) {
                targetService.setConfirmMethod(m);
            }
            if(m.getName().equals(tcc.cancelMethod())){
                targetService.setCancelMethod(m);
            }
        }
        targetService.setParameters(args);
        targetService.getCancelMethod();
        TccTransactionThreadLocal.get().addTargetService(targetService);
        try {
            method.invoke(target, args);
        }catch (Exception e) {
            TccTransactionThreadLocal.get().needCancel();
        }
    }

    private void tccTransactionInterceptor(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        TccTransactionThreadLocal.init();
        method.invoke(target, args);
        if(TccTransactionThreadLocal.get().isNeedCancel()){
            for (TccTargetService targetService: TccTransactionThreadLocal.get().getTargetServicesList()){
                targetService.getCancelMethod().invoke(targetService.getService(),targetService.getParameters());
            }
        } else {
            for (TccTargetService targetService: TccTransactionThreadLocal.get().getTargetServicesList()){
                targetService.getConfirmMethod().invoke(targetService.getService(),targetService.getParameters());
            }
        }
        TccTransactionThreadLocal.destroy();
    }
}
