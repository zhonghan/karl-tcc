package com.vip.karl.tcc.proxy;

import com.vip.karl.tcc.annotation.Tcc;
import com.vip.karl.tcc.annotation.TccTransaction;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;


/**
 * @author karl.zhong
 */
public class TccProxyCreationProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(anyMethodWithTccAnnotation(bean)){
            return getProxy(bean);
        }
        return bean;
    }

    private Object getProxy(Object bean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        CglibInterceptor cglibInterceptor = new CglibInterceptor(bean);
        enhancer.setCallback(cglibInterceptor);
        return enhancer.create();
    }

    private boolean anyMethodWithTccAnnotation(Object bean) {
        for(Method method : bean.getClass().getMethods()) {
            if(method.isAnnotationPresent(Tcc.class) || method.isAnnotationPresent(TccTransaction.class)){
                return true;
            }
        }
        return false;
    }
}
