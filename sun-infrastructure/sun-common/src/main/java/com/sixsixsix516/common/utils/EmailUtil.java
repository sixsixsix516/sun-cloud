package com.sixsixsix516.common.utils;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EmailUtil {

	private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(3,3,1, TimeUnit.MINUTES,
			new ArrayBlockingQueue<>(5), (runnable) -> {
		Thread thread = new Thread(runnable);
		thread.setName("exception-email-thread");
		return thread;
	});

    public static void sendQQEmail(List<String> receives,String subject,String content,boolean isHtml){
		THREAD_POOL_EXECUTOR.execute(() -> {


		});
    }


}
