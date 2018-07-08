package com.pphgzs.thread;

import com.pphgzs.service.impl.ServiceServiceImpl;

public class ServiceDistributionThread {

	private final static String RUN = "run";

	private final static String STOP = "stop";

	private static String threadState = STOP;

	/*
	 * 
	 */
	public static void startThread() {
		if (threadState.equals(RUN)) {
			return;
		}
		threadState = RUN;
		new Thread() {
			@Override
			public void run() {
				while (threadState.equals(RUN)) {
					try {
						System.out.println("正在执行分配线程");
						ServiceServiceImpl serviceServiceImpl = new ServiceServiceImpl();

						// 10分钟——600秒——600000毫秒
						// 60分钟——3600秒——3600000毫秒
						Thread.sleep(3600000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public static void stopThread() {
		threadState = STOP;
	}

	/*
	 * 
	 */

	public static String getRun() {
		return RUN;
	}

	public static String getThreadState() {
		return threadState;
	}

	public static void setThreadState(String threadState) {
		ServiceDistributionThread.threadState = threadState;
	}

	public static String getStop() {
		return STOP;
	}

}
