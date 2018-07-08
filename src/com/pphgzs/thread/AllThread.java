package com.pphgzs.thread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AllThread implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 业务抓取
		ServiceGrabThread.startThread();
		// 业务分配
		ServiceDistributionThread.startThread();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
