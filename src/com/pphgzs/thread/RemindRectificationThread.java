package com.pphgzs.thread;

public class RemindRectificationThread {

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
					// ApplicationContext ctx = new
					// ClassPathXmlApplicationContext(new String[] {
					// "applicationContext.xml",
					// "spring/ServiceSpring.xml", "spring/UnitSpring.xml",
					// "spring/UserSpring.xml" });
					// ServiceService serviceService = (ServiceService)
					// ctx.getBean("serviceService");
					try {
						// serviceService.grab_serviceInstance_auto();
						// serviceService.distribution_serviceInstance_auto();
						// 60分钟——3600秒——3600000毫秒
						// 4个小时——3600秒——14400000毫秒
						Thread.sleep(14400000);
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
		RemindRectificationThread.threadState = threadState;
	}

	public static String getStop() {
		return STOP;
	}

}
