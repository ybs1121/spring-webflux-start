package com.study.springwebfluxstart;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class MyFilter implements Filter {

    private EventNotify eventNotify;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        System.out.println("필터 실행");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/event-stream;charset=utf-8");
        PrintWriter writer = response.getWriter();
        for (int i = 0; i < 5; i++) {
            writer.println("응답 " + i );
            writer.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        while (true) {
            try {

                if (eventNotify.getChange()) {
                    writer.println("응답 " + eventNotify.getEvents()
                            .get(eventNotify.getEvents().size() - 1));
                    writer.flush();
                    eventNotify.setChange(false);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
