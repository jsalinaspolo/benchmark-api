package com.jspcore;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppShould {

    @Test
    public void be_async() throws InterruptedException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        final long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);


        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Runnable call = new Runnable() {
            @Override public void run() {
                HttpGet httpget = new HttpGet("http://localhost:5050/slow");
                try {
                    final CloseableHttpResponse response = httpclient.execute(httpget);
                    countDownLatch.countDown();
                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            }
        };
        executorService.execute(call);
        executorService.execute(call);
        countDownLatch.await();

        System.out.println("time elapsed: " + (System.currentTimeMillis() - start));
        executorService.shutdown();

    }
}
