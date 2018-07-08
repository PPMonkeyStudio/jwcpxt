package com.pphgzs.thread;

import com.pphgzs.service.impl.ServiceServiceImpl;
import com.pphgzs.util.TimeUtil;

public class ServiceGrabThread {

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

						ServiceServiceImpl serviceServiceImpl = new ServiceServiceImpl();

						if (serviceServiceImpl.if_grabServiceInstance_byDate(TimeUtil.getStringDay())) {

						} else {
							serviceServiceImpl.grab_serviceInstance_auto();
						}

						// 30分钟——1800000毫秒
						Thread.sleep(1800000);
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
		ServiceGrabThread.threadState = threadState;
	}

	public static String getStop() {
		return STOP;
	}

}
