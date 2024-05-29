package com.study.springwebfluxstart.test.reativetest;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;
import java.util.Iterator;

public class MyPub implements Publisher<Integer> {

    public Iterator<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10).iterator();
    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        System.out.println("1. 신문 구독~");
        System.out.println("2. 구독정보를 만들어서 줄게 기다려~");
        MySubSubscription subSubscription = new MySubSubscription(its,subscriber);
        System.out.println("3.구독 정보 생성 완료!");
        subscriber.onSubscribe(subSubscription);
    }
}
