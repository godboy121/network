package com.bit2016.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ChatClientReceiveThred extends Thread {
	private BufferedReader br;
	public ChatClientReceiveThred(BufferedReader br) {
		this.br=br;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message;
		try{
			while((message=br.readLine())!=null)
			{
				System.out.println(message);
			}
				
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		super.run();
	}

}
		
		
	

	



