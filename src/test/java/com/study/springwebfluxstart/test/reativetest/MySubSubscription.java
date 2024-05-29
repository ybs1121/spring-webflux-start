package com.study.springwebfluxstart.test.reativetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

//구독 정보(구독자, 어떤 데이터를 구독할지)

public class MySubSubscription implements Subscription {

    private Iterator<Integer> its;
    private Subscriber subscriber;

    public MySubSubscription(Iterator<Integer> its, Subscriber subscriber) {
        System.out.println("구독정보 완료~");
        this.its = its;
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {
        while (l > 0 ){
            if (its.hasNext()) {
                subscriber.onNext(its.next());
            }else {
                subscriber.onComplete();
            }
            l--;
        }
    }

    @Override
    public void cancel() {

    }
}
