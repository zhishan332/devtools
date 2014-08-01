package com.yermoon.dao;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-22 下午3:52
 */
public class TestTimer {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private Timer tt;

    public static void main(String[] args) {
        TestTimer testTimer = new TestTimer();
//        testTimer.test();
        testTimer.beepForAnHour();
    }

    public void beepForAnHour() {
//        try {
//            int a=1/0;
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        final Runnable beeper = new Runnable() {
            public void run() {
//                try {
//                    Thread.sleep(2 * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(new Date());
            }
        };
        scheduler.scheduleWithFixedDelay(beeper, 0, 300, TimeUnit.MILLISECONDS);
    }

    public void test() {
        tt = new Timer();
        final int[] i = {0};
        tt.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i[0] < 10) {
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(new Date());
                i[0]++;
            }
        }, 10, 2 * 1000);
    }

    public void test2() {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date());
                }
            }
        });
        t2.start();
    }
}
