package com.xiaobing.improvedemo.network.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHandler {
    Response onResponse(String httpResult, Interceptor.Chain chain,Response response){
        return  response;
    }

    Response onRequest(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .header("lang","1")
                .header("country", "1876")
                .header("currencycode", "SAR")
//                .header("Authorization", "Bearer " + UserManager.getInstance().getToken())
                .header("User-Agent", "Android")
                .header("channel-id", "4")
                .header("device-code","RWQRRWQEREWQRQWRQR");

        return chain.proceed(builder.build());

    }
}
