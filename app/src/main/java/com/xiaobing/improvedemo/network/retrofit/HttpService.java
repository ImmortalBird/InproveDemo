package com.xiaobing.improvedemo.network.retrofit;

import com.xiaobing.improvedemo.BuildConfig;
import com.xiaobing.improvedemo.network.Tls12SocketFactory;
import com.xiaobing.improvedemo.util.GsonUtil;
import com.xiaobing.improvedemo.util.HttpsUtil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class HttpService {
    // 请求地址
    private static final String BASE_URL = BuildConfig.HOST;
    // 请求超时时间 单位s
    private static final int TIME_OUT = 10;

    private static Retrofit retrofit;

    private static void getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(chain -> new HttpHandler().onRequest(chain))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    static <T> T create(Class<T> service) {
        if (retrofit == null) {
            getRetrofit();
        }
        return retrofit.create(service);
    }

//    private void initHttpsClient() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
//                .readTimeout(30000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("OkHttpClient")).hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                });
//        if (AppParams.isBypassAuthen) {
//            HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(null, null, null);
//            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//        } else {
//            SSLContext sslContext = null;
//            try {
//                sslContext = SSLContext.getInstance("TLS");
//                try {
//                    sslContext.init(null, null, null);
//                } catch (KeyManagementException e) {
//                    e.printStackTrace();
//                }
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//            SSLSocketFactory socketFactory = new Tls12SocketFactory(sslContext.getSocketFactory());
//            builder.sslSocketFactory(socketFactory, new HttpsUtil.UnSafeTrustManager());
//        }
//        OkHttpClient okHttpClient = builder.build();
//        OkHttpUtils.initClient(okHttpClient);
//    }

}
