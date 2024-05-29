package com.study.springwebfluxstart.test.reativetest;

// WebFlux = 단일 스레드, 비동기 + Stream을 통해 백프로셔가 적용된 데이터 만큼 간헐적 응답가능
// 데이터 소비가 끝나면 응답이 종료된다.
// SSE (Servlet,WebFlux) = 데이터 소비가 끝나도 Stream을 유지한다.
public class App {
    public static void main(String[] args) {
        MyPub myPub = new MyPub();
        MySub mySub = new MySub();

        myPub.subscribe(mySub);
    }
}
