package com.vip.karl.tcc.test.service.local;


import com.vip.karl.tcc.annotation.Tcc;
import org.springframework.stereotype.Service;

/**
 * @author karl.zhong
 */
@Service
public class LocalService {

    @Tcc
    public void tryService(String para) {
        System.out.println("local try, para:"+para);
        if("error".equalsIgnoreCase(para)) {
            throw new RuntimeException("");
        }
    }


    public void confirm(String para) {
        System.out.println("local confirm, para:"+para);
    }


    public void cancel(String para) {
        System.out.println("local cancel, para:"+para);
    }
}
