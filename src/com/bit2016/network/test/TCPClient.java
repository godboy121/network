package com.bit2016.network.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class TCPClient {
	private static final String SERVER_IP="192.168.1.16";
	private static final int SERVER_PORT=5000;
	
	public static void main(String[] args) {
		//1.socket 생성
		Socket socket=new Socket();
		try{
		
			
			//2.서버연결
			
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			System.out.println("Client connected");
			
			//3.IOStream 받아오기
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true );
			Scanner a=new Scanner(System.in);
			String data;
			//4.쓰기
			while(true)
			{
				System.out.print(">>");
				data=a.nextLine();
				if(data.equals("exit"))
				{
					break;
				}
				pw.println(data);
				String aa=br.readLine();
				System.out.println("<<"+aa);
				
				
				
				
				
				
				
			
					
			}
	
			
			//5.읽기
		
			{
				System.out.println("Client closed by server");
				return ;
			}
			//data=new String(buffer,0,readByteCount,"UTF-8");
		
		
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}finally
		{
			try{
				if(socket!=null&socket.isClosed()==false)
				socket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
