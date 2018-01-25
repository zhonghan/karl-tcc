package com.vip.karl.tcc.test.service.remote;


import com.vip.karl.tcc.annotation.Tcc;
import org.springframework.stereotype.Service;

/**
 * @author karl.zhong
 */
@Service
public class RemoteService {

    @Tcc(confirmMethod = "pay", cancelMethod = "rollBackPay")
    public void tryRemoteService(int para) {
        System.out.println("remote try, para:"+para);
        if(-1==para) {
            throw new NullPointerException("");
        }
    }


    public void pay(int para) {
        System.out.println("remote pay, para:"+para);
    }


    public void rollBackPay(int para) {
        System.out.println("remote rollBackPay, para:"+para);
    }
}
