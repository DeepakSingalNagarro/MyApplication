package com.example.deepaksingal.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test();
        mayBeTest();
    }

    public void test(){
        Observable<String> observable = Observable.just("a", "b", "c", "d", "e");
        observable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        }).subscribe(s->System.out.println(s));
        observable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });
    }

    public void test1(){
        /*Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        observable.subscribe(s->System.out.println(s));*/

        Observable<Object> observable1 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("a");
                e.onComplete();
            }
        });
        observable1.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        }, Throwable::printStackTrace);

    }

    public void mayBeTest(){
        Maybe<Integer> maybe1 = Maybe.just(100);

        maybe1.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return true;
            }
        })
        .subscribe(i->System.out.println(i), Throwable::printStackTrace,() -> System.out.println("Process 1 done!"));
    }
}
