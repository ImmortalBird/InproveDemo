package com.xiaobing.improvedemo.network.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface CommonService {

    @Headers("Api-Version: application/vnd.momshop.v14+json")
    @GET("api")
    Observable<String> getData();
}
