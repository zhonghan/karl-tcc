package com.vip.karl.tcc;


/**
 * @author karl.zhong
 */
public class TccTransactionThreadLocal {
    private static ThreadLocal<TccTransactionHolder> tccTransactionHolderTL = new ThreadLocal<>();

    public static TccTransactionHolder get(){
        return tccTransactionHolderTL.get();
    }

    public static void init() {
        TccTransactionHolder holder = new TccTransactionHolder();
        tccTransactionHolderTL.set(holder);
    }

    public static void destroy() {
        tccTransactionHolderTL.remove();
    }
}
