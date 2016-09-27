package com.bit2016.network.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class echo {
	private static final String SERVER_IP="192.168.1.16";
	private static final int SERVER_PORT=5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket=null;
		
		try{
			serverSocket=new ServerSocket();
			serverSocket.bind(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			
			System.out.println("[서버] 연결기다림");
			Socket socket=serverSocket.accept();
			//InetSocketAddress inetSocketAddress=(InetSocketAddress)socket.getRemoteSocketAddress();
			System.out.println("[서버] 연결됨 " );
			
			//InputStream is=socket.getInputStream();
			//OutputStream os=socket.getOutputStream();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true );
			
			while(true){
				String a=br.readLine(); //"hello"
				//System.out.println( a );
				System.out.println("[서버]데이터수신 :"+a);
				pw.println(a);
				
				//pw.flush();
				
//				byte[] buffer=new byte[256];
//				int readByteCount=is.read(buffer);
//				if(readByteCount<0)
//				{
//					System.out.println("[서버] 클라이언트로부터 연결끊김");
//					is.close();
//					socket.close();
//					break;
//				}
//				String data=new String(buffer,0,readByteCount,"UTF-8");
//				System.out.print(data);
				//pw.println(data.getBytes());
				//os.write(data.getBytes());
				
			}
			//String data="hello world";
			//os.write(data.getBytes("UTF-8"));
			//os.flush();
			//os.close();
			
			
		}catch(IOException ee)
		{
			ee.printStackTrace();
		}finally
		{
			if(serverSocket!=null&&serverSocket.isClosed()==false)
			{
				try{
					serverSocket.close();
				}catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		

	}

}
