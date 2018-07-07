package com.pphgzs.thread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AllThread implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServiceDistributionThread.startServiceDistributionThread();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
