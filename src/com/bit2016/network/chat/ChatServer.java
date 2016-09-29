package com.bit2016.network.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.network.test.LocalHost;

public class ChatServer {
	private static final int PORT=9090;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.create serversocket
		
		List<PrintWriter> listPrintWriter=new ArrayList<PrintWriter>();
		ServerSocket serversocket=null;
		
		try{
			String localHostAddress = InetAddress.getLocalHost().getHostAddress();
			serversocket=new ServerSocket();
			
			//2.bind
			serversocket.bind(new InetSocketAddress(localHostAddress, PORT),10);//숫자 5는 백로그 ->대기열..
			consolelog("binding " +localHostAddress+":"+PORT);
			
			//3. connect 요청
			while(true){
				Socket socket=serversocket.accept();
				
				Thread thread=new ChatServerThread(socket,listPrintWriter);
				thread.start();
			}
			
			
			
		}catch(IOException ex)
		{
			consolelog("error:"+ex);
		}finally{
			try{
				if(serversocket!=null &&serversocket.isClosed()==false)
					serversocket.close();
			}catch(IOException ex){
				consolelog("error:"+ex);
			}
		}
		

	}
	
	public static void consolelog(String message){
		System.out.println("[Chat server] "+message);
	}

}
