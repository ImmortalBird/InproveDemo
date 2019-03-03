package com.xiaobing.improvedemo.network.rr2;

import com.xiaobing.improvedemo.BuildConfig;
import com.xiaobing.improvedemo.network.rr2.bean.DoubanFilm;
import com.xiaobing.improvedemo.network.rr2.bean.Response;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Request {
    /**
     * 豆瓣搜索电影接口
     * @param q         电影名称
     * @param start     起始索引
     * @param count     数量
     * @return          数据
     */
    @POST("movie/search")
    @FormUrlEncoded
    Observable<Response<List<DoubanFilm>>> getFilm(@Field("q") String q,
                                                   @Field("start") int start,
                                                   @Field("count") int count);
}
