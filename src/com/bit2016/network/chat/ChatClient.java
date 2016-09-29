package com.bit2016.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP="192.168.1.16";
	private static final int SERVER_PORT=9090;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket=null;
		try{
			
			socket=new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			System.out.println("Client connected");
			
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			
			Scanner a=new Scanner(System.in);
			System.out.print("닉네임>>");
			String nickname=a.nextLine();
			pw.print("JOIN:"+nickname+"\r\n");
			pw.flush();
			
			Thread aa=new ChatClientReceiveThred(br);
			aa.start();
			
			while(true)
			{
				System.out.print(">>");
				String input =a.nextLine();
				
				if("QUIT".equals(input)==true)
				{
					//pw.println("QUIT:\r\n");
					
				}
				else if(input.equals(""))
				{
					continue;
				}
				else
				{
					pw.print("MESSAGE:"+input+"\r\n");
					pw.flush();
				}
				
				
				
			}
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
