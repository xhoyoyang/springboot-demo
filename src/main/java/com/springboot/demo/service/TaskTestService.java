package com.springboot.demo.service;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xuhuiyang
 * @Date 2022-03-30
 **/
@Service
public class TaskTestService {

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10));


    public String task1() throws InterruptedException {

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int index = i;
            tasks.add(() -> {
                Thread.sleep(1000);
                return index;
            });
        }
        List<Future<Integer>> futures = pool.invokeAll(tasks);
        StringBuilder sb = new StringBuilder();
        futures.forEach(item -> {
            try {
                sb.append(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return sb.toString();
    }

    public String task2() throws InterruptedException {

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            int index = i;
            tasks.add(() -> {
                Thread.sleep(1000);
                return index;
            });
        }
        List<Future<Integer>> futures = pool.invokeAll(tasks);
        StringBuilder sb = new StringBuilder();
        futures.forEach(item -> {
            try {
                sb.append(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return sb.toString();
    }


}
