package com.vip.karl.tcc.test;

import com.vip.karl.tcc.test.service.ManagerService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TccTest {
    private static ApplicationContext context;
    private static ManagerService coordinator;

    @BeforeClass
    public static void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        coordinator = context.getBean(ManagerService.class);
    }

    @Test
    public void testSuccessTcc() {
        System.out.println("##########################");
        coordinator.demo("0k",1);
        System.out.println("##########################");
    }
    @Test
    public void testLocalError() {
        System.out.println("##########################");
        coordinator.demo("error",1);
        System.out.println("##########################");

    }
    @Test
    public void testRemoteError() {
        System.out.println("##########################");
        coordinator.demo("success",-1);
        System.out.println("##########################");
    }
    @Test
    public void testBothError() {
        System.out.println("##########################");
        coordinator.demo("error",-1);
        System.out.println("##########################");
    }
}
