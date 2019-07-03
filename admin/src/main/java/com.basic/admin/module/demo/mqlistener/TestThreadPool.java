package com.basic.admin.module.demo.mqlistener;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2019/6/5
 */
public class TestThreadPool {

    public static void main(String[] args) {
        /**
         * Java通过Executors提供四种线程池，分别为：
         *         newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
         *         newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
         *         newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
         *         newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
         */

        /*通过chcaedThreadPool创建线程池*/
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cachedThreadPool.execute(new Runnable() {
//                public void run() {
//                    System.out.println(index);
//                }
//            });
//        }





        /*fixedThreadPool创建线程池*/
        /**
         * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.execute(()-> System.out.println(1));
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            fixedThreadPool.execute(new Runnable() {
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }


        /*scheduledThreadPool创建线程池*/
        ScheduledExecutorService scheduledThreadPool1 = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(5,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        //System.out.println("-----");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("delay 3 seconds---->" + index);
                }
            }, 1, TimeUnit.SECONDS);
        }

        /*singleThreadExecutor创建线程池*/
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
    }
}
