package com.pphgzs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author JXX
 * 
 */
public class SendMessageUtil {
	private static final String USER = "pxhzc"; // 系统账号
	private static final String PASSWD = MD5Util.GetMD5Code("pxhzc!@#$admin"); // 系统密码
	private static final String URL = "http://10.139.0.167/D001";
	private String SJH;// 手机号码，多个手机号用,隔开
	private String DXNR;

	public SendMessageUtil(String sJH, String dXNR) {
		super();
		SJH = sJH;
		DXNR = dXNR;
	}

	public boolean send() throws IOException {
		String requestTime = TimeUtil.getStringSecond();
		String sendData = "{'user':'" + USER + "','passwd':'" + PASSWD + "','requestTime':'" + requestTime + "','data':"
				+ "{'SJH':'" + SJH + "','DXNR':'" + DXNR + "'}}";
		URL url = new URL(URL);// 创建连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestMethod("POST"); // 设置请求方式
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
		connection.setRequestProperty("Content-Lenth", String.valueOf(sendData.length()));
		connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(sendData);
		bw.flush();
		bw.close();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String returnData = br.readLine();
		System.out.println("结果:" + returnData);
		if (returnData.indexOf("0000") != -1) {
			return true;
		} else {
			return false;
		}
	}
}
