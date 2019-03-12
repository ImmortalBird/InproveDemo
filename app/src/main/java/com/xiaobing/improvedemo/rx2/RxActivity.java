package com.xiaobing.improvedemo.rx2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.network.rr2.NetworkManager;
import com.xiaobing.improvedemo.network.rr2.bean.DoubanFilm;
import com.xiaobing.improvedemo.network.rr2.bean.Response;
import com.xiaobing.improvedemo.util.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "XXXX";
    private Disposable subscribe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_rx_main);
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.ID_rx_01_01));
        View tv01 = findViewById(R.id.tv_01);
        findViewById(R.id.tv_02).setOnClickListener(this);
        findViewById(R.id.tv_03).setOnClickListener(this);
        findViewById(R.id.tv_04).setOnClickListener(this);
        findViewById(R.id.tv_05).setOnClickListener(this);
        findViewById(R.id.tv_06).setOnClickListener(this);
        findViewById(R.id.tv_07).setOnClickListener(this);
        findViewById(R.id.tv_08).setOnClickListener(this);
        findViewById(R.id.tv_09).setOnClickListener(this);
        findViewById(R.id.tv_10).setOnClickListener(this);
        findViewById(R.id.tv_11).setOnClickListener(this);
        findViewById(R.id.tv_12).setOnClickListener(this);
        tv01.setOnClickListener(this);
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                i++;
                Log.e(TAG, "onNext :value : " + integer + "\n");
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n");
            }
        });

    }

    private void test2() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            LogUtil.print("Observable emit : 1");
            emitter.onNext(2);
            LogUtil.print("Observable emit : 2");
            emitter.onNext(3);
            LogUtil.print("Observable emit : 3");
            emitter.onNext(4);
            LogUtil.print("Observable emit : 4");
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                LogUtil.print("onSubscribe isDisposed: " + disposable.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                i = integer;
                LogUtil.print("onNext value: " + integer);
                if (i == 2) {
                    disposable.dispose();
                    LogUtil.print("onNext isDisposed: " + disposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.print("onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtil.print("onComplete : ");
            }
        });
    }

    private void map() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            LogUtil.print("Observable emit : 1");
            emitter.onNext(2);
            LogUtil.print("Observable emit : 2");
            emitter.onNext(3);
            LogUtil.print("Observable emit : 3");
            emitter.onNext(4);
            LogUtil.print("Observable emit : 4");
        })
                .map(integer -> integer + "   -  ")
                .subscribe(new Observer<String>() {
                    private String i;
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        LogUtil.print("onSubscribe isDisposed: " + disposable.isDisposed());
                    }

                    @Override
                    public void onNext(String integer) {
                        i = integer;
                        LogUtil.print("onNext value: " + integer);
//                if (i == 2) {
//                    disposable.dispose();
//                    LogUtil.print("onNext isDisposed: " + disposable.isDisposed());
//                }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.print("onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.print("onComplete : ");
                    }
                });
    }

    private void concat() {
        Observable<Integer> io = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            LogUtil.print("Observable emit : 1");
            emitter.onNext(2);
            LogUtil.print("Observable emit : 2");
            emitter.onNext(3);
            LogUtil.print("Observable emit : 3");
            emitter.onNext(4);
            LogUtil.print("Observable emit : 4");
            emitter.onComplete();
        });
        Observable<Integer> ioo = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(100);
            LogUtil.print("Observable emit : 100");
            emitter.onNext(200);
            LogUtil.print("Observable emit : 200");
            emitter.onNext(300);
            LogUtil.print("Observable emit : 300");
            emitter.onNext(400);
            LogUtil.print("Observable emit : 400");
            emitter.onComplete();
        });

        Observable.concat(io, ioo)
                .subscribe(new Observer<Integer>() {
                    private int i;
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        LogUtil.print("onSubscribe isDisposed: " + disposable.isDisposed());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        i = integer;
                        LogUtil.print("onNext value: " + integer);
//                        if (i == 2) {
//                            disposable.dispose();
//                            LogUtil.print("onNext isDisposed: " + disposable.isDisposed());
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.print("onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.print("onComplete : ");
                    }
                });
    }

    @Override
    public void onClick(View view) {
        LogUtil.print("onClick : id:  "+view.getId());
        switch (view.getId()) {
            case R.id.tv_01:
                test1();
                break;
            case R.id.tv_02:
                map();
                break;
            case R.id.tv_03:
                concat();
                break;
            case R.id.tv_04:
                flatMap();
                break;
            case R.id.tv_05:
                zip();
                break;
            case R.id.tv_06:
                interval();
                break;
            case R.id.tv_07:
                mapFlowable();
                break;
            case R.id.tv_08:
                concatFlowable();
                break;
            case R.id.tv_09:
                flatMapFlowable();
                break;
            case R.id.tv_10:
                zipFlowable();
                break;
            case R.id.tv_11:
                intervalFlowable();
                break;
            case R.id.tv_12:

                break;
        }
    }

    private void mapFlowable() {


    }

    private void concatFlowable() {
    }

    private void flatMapFlowable() {

    }

    private void zipFlowable() {
    }

    private void flatMap() {
        Observable<Response<List<DoubanFilm>>> request1 =
                NetworkManager.getRequest().getFilm("战狼", 25, 25)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        Observable<Response<List<DoubanFilm>>> request2 =
                NetworkManager.getRequest().getFilm("肖申克的救赎", 25, 25)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        Disposable subscribe = request1
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Response<List<DoubanFilm>>>() {
                    @Override
                    public void accept(Response<List<DoubanFilm>> listResponse) throws Exception {
                        LogUtil.print(listResponse.getTitle());
                    }
                })
                .flatMap(new Function<Response<List<DoubanFilm>>, ObservableSource<Response<List<DoubanFilm>>>>() {
                    @Override
                    public ObservableSource<Response<List<DoubanFilm>>> apply(Response<List<DoubanFilm>> listResponse) throws Exception {
                        return request2;
                    }
                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<List<DoubanFilm>>>() {
                    @Override
                    public void accept(Response<List<DoubanFilm>> listResponse) throws Exception {
                        LogUtil.print(listResponse.getTitle());
                    }
                });

    }

    private void zip() {
        Observable<Response<List<DoubanFilm>>> request1 =
                NetworkManager.getRequest().getFilm("战狼", 25, 25)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
        Observable<Response<List<DoubanFilm>>> request2 =
                NetworkManager.getRequest().getFilm("肖申克的救赎", 25, 25)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
        Disposable subscribe = Observable.zip(request1, request2, (listResponse, listResponse2) -> {
            LogUtil.print(listResponse.getTitle());
            LogUtil.print(listResponse2.getTitle());
            return listResponse.getTitle() + listResponse2.getTitle();
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LogUtil::print, throwable -> {

        });
    }
    private void interval(){
        subscribe = Observable.interval(1000, 2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtil.print("accept: " + aLong);
                        if (aLong == 5 && subscribe != null && !subscribe.isDisposed())
                            subscribe.dispose();

                    }
                });
    }

    private void intervalFlowable(){
        Subscriber<Long> s = new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                LogUtil.print("onSubscribe ");
            }

            @Override
            public void onNext(Long aLong) {

                LogUtil.print("onNext : value = " + aLong);
            }

            @Override
            public void onError(Throwable t) {
                LogUtil.print("onError : " + t.getMessage());

            }

            @Override
            public void onComplete() {

                LogUtil.print("onComplete");
            }
        };
        Flowable.interval(1000L,1000L,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtil.print("doOnNext onNext : value = " + aLong);
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtil.print("subscribe onNext : value = " + aLong);
                    }
                });
    }
}
