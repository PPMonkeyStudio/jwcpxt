package com.pphgzs.thread;

import java.io.IOException;
import java.net.UnknownHostException;

public class SendPhoneThread {

	public void testSend(String ip, String phone) {
		Thread send = new Thread(new Runnable() {
			@Override
			public void run() {
				SocketUnit socketUtil = new SocketUnit();
				try {
					socketUtil.testSend(ip, phone);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		send.start();
	}

}
