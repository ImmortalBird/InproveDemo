package com.xiaobing.improvedemo.network.retrofit;

/**
 * 网络请求基类
 */
public class Api {
    private static CommonService service;

    public static synchronized CommonService getComApi(){
        if (service == null){
            synchronized (Api.class){
                if (service == null){
                    service = HttpService.create(CommonService.class);
                }
            }
        }
            return service;
    }
}
