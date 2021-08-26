package com.scaudachuang.catlife.utils;

import java.util.concurrent.*;

/**
 * @author hiluyx
 * @since 2021/8/26 23:08
 **/
public class IdentifyTaskPool {
    private static final Object lock = new Object();
    private static volatile ThreadPoolExecutor executor;
    private static final int coreTs = 5;
    private static final int maxTs = 10;
    private static final long keepAliveTime = 5;

    public void submit(Runnable runnable) {
        ThreadPoolExecutor executor = getExecutor();
        executor.submit(runnable);
    }

    private ThreadPoolExecutor getExecutor() {
        if (executor == null) {
            synchronized (lock) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(coreTs, maxTs, keepAliveTime, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(10), new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                        }
                    });
                }
            }
        }
        return executor;
    }
}
