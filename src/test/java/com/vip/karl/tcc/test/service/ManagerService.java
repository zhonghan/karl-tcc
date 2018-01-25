package com.vip.karl.tcc.test.service;


import com.vip.karl.tcc.annotation.TccTransaction;
import com.vip.karl.tcc.test.service.local.LocalService;
import com.vip.karl.tcc.test.service.remote.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author karl.zhong
 */
@Service
public class ManagerService {
    @Autowired
    private LocalService local;
    @Autowired
    private RemoteService remote;


    @TccTransaction()
    public void demo(String param1, int param2) {
        local.tryService(param1);
        remote.tryRemoteService(param2);
    }
}
