package com.vip.karl.tcc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.zhong
 */
public class TccTransactionHolder {
    private boolean needCancel = false;
    private List<TccTargetService> targetServicesList = new ArrayList<>();

    public List<TccTargetService> getTargetServicesList() {
        return targetServicesList;
    }


    public void needCancel(){
        needCancel = true;
    }

    public boolean isNeedCancel() {
        return needCancel;
    }

    public void addTargetService(TccTargetService targetService) {
        targetServicesList.add(targetService);
    }


}
