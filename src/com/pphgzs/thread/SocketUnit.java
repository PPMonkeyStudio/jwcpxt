package com.pphgzs.thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.pphgzs.util.TimeUtil;

/**
 * 
 * @author JXX
 *
 */
public class SocketUnit {

	/**
	 * @throws IOException
	 * @throws UnknownHostException
	 * 
	 */
	public void testSend(String ip, String phone) throws UnknownHostException, IOException {
		Socket socket = new Socket(ip, 4530);
		String msg = "";
		msg = "<CRESM>MAPJJ03@360300" + TimeUtil.getDateBy() + TimeUtil.sixRandom() + "CP";
		msg = msg + "@" + phone + "</CRESM>";
		System.out.println("fasong");
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
		dos.close();
		/*
		 * DataInputStream dis = new DataInputStream(socket.getInputStream()); String
		 * echo = dis.readUTF(); System.out.println(echo);
		 */
	}

}
