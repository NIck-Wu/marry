package com.xy.marry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONObject;
import com.xy.marry.invoke.HttpInvoke;

public class Test {
	
	public static void main(String[] args) {

		// 锁住所有线程，等待并发执行
		final CountDownLatch begin = new CountDownLatch(1);

		final ExecutorService exec = Executors.newFixedThreadPool(500);

		for (int index = 0; index < 500; index++) {
			final int NO = index + 1;

			Runnable run = new Runnable() {
				public void run() {
					try {
						// 等待，所有一起执行
						begin.await();
						HttpInvoke httpInvoke = new HttpInvoke();
						JSONObject json = new JSONObject();
						json.put("userId", NO);
						json.put("number", 1);
						boolean b = httpInvoke.httpPostWithJson(json, "http://localhost:19998/api/shake/shake/shake", "abcd123456");
//						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
					}
				}
			};
			exec.submit(run);
		}
		System.out.println("开始执行");
		// begin减一，开始并发执行
		begin.countDown();

		// 关闭执行
		exec.shutdown();
	}
}