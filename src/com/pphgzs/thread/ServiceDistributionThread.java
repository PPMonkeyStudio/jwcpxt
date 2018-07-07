package com.pphgzs.thread;

import com.pphgzs.service.impl.ServiceServiceImpl;

public class ServiceDistributionThread {

	private final static String RUN = "run";

	private final static String STOP = "stop";

	private static String serviceDistributionThreadState = STOP;

	/*
	 * 
	 */
	public static void startServiceDistributionThread() {
		if (serviceDistributionThreadState.equals(RUN)) {
			return;
		}
		serviceDistributionThreadState = RUN;
		new Thread() {
			@Override
			public void run() {
				while (serviceDistributionThreadState.equals(RUN)) {
					try {
						System.out.println("正在执行分配线程");
						ServiceServiceImpl serviceServiceImpl = new ServiceServiceImpl();
						serviceServiceImpl.distribution_serviceInstance_auto();
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

	public static void stopServiceDistributionThread() {
		serviceDistributionThreadState = STOP;
	}

	/*
	 * 
	 */
	public static String getServiceDistributionThreadState() {
		return serviceDistributionThreadState;
	}

	public static void setServiceDistributionThreadState(String serviceDistributionThreadState) {
		ServiceDistributionThread.serviceDistributionThreadState = serviceDistributionThreadState;
	}

	public static String getRun() {
		return RUN;
	}

	public static String getStop() {
		return STOP;
	}

}
