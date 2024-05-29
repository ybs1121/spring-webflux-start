package com.study.springwebfluxstart.test.reativetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {
    private Subscription subscription;
    private int bufferSize = 2;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("구독~");
        this.subscription = subscription;
        System.out.println("신문 매일매일주세요" );
        subscription.request(bufferSize); // 소비자(Sub)가 한번에 처리할 수 있는 데이터를 요청

    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("구독데이터전달 : " + integer);

        bufferSize--;
        if (bufferSize == 0) {
            System.out.println("다음 데이터 넘겨");
            bufferSize = 2;
            subscription.request(bufferSize);

        }

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("구독중 에러");
    }

    @Override
    public void onComplete() {
        System.out.println("구독완료");
    }
}
