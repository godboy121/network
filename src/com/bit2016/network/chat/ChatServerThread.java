package com.bit2016.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ChatServerThread extends Thread {
	private Socket socket;
	private String name;
	private List<PrintWriter> listPrintWriter;
	
	public ChatServerThread( Socket socket, List<PrintWriter> listPrintWriter ) {
		this.socket = socket;
		this.listPrintWriter = listPrintWriter;
	}
	
	@Override
	public void run() {
		try {
			//1. print remote socket address
			InetSocketAddress remoteSocketAddress = 
					(InetSocketAddress)socket.getRemoteSocketAddress();
			ChatServer.consolelog(
				"connected by client[" +
				remoteSocketAddress.getAddress().getHostAddress() + ":" + 
				remoteSocketAddress.getPort() +
				"]" );
			
			//2. create stream( from Basic Stream )
			BufferedReader br =
				new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF-8") );
			PrintWriter pw = 
				new PrintWriter( new OutputStreamWriter( socket.getOutputStream(), "UTF-8"), true );
			
			//3. processing...
			while( true ) {
				String line = br.readLine();
				if( line == null ) {
					//doQuit();
					break;
				}
				
				String[] tokens = line.split( ":" );
				if( "JOIN".equals( tokens[0] )|| "join".equals(tokens[0]) ) {
					doJoin(tokens[1], pw );
				} else if( "MESSAGE".equals( tokens[0] ) ) {
					doMessage(tokens[1]);
				} else if( "QUIT".equals( tokens[0] ) ) {
					doQuit(pw);
				}
				
			}
			
		} catch (UnsupportedEncodingException e) {
			ChatServer.consolelog( "error:" + e );
		} catch (IOException e) {
			ChatServer.consolelog( "error:" + e );
		} finally {
			try {
				if( socket != null &&
					socket.isClosed() == false ) {
					socket.close();
				}
			}catch( IOException e ) {
				ChatServer.consolelog( "error:" + e );
			}
		}
	}
	
	private void doJoin(String name, PrintWriter pw ){
		// 1. save nickname
		this.name = name;
		
	
	
		// 2. broadcasting..
		String message = name + "님이 입장했습니다";
		broadcastMessage( message );
		
		//3. add PrintWriter
		addPrintWriter( pw );
		
		
		
		//4. ack
		
		
		//pw.println( "JOIN:OK" );
	}
	
	private void doMessage(String message){
		
		String data=this.name+":"+message;
		broadcastMessage(data);
		
	}
	
	private void doQuit(PrintWriter printWriter){
		removeWriter(printWriter);
		
		String data=this.name+"님이 퇴장하셨습니다";
		broadcastMessage(data);
	}
	
	private void removeWriter(PrintWriter printWriter){
		synchronized( listPrintWriter ) {
			listPrintWriter.remove(printWriter);
		}
	}
	
	private void addPrintWriter( PrintWriter printWriter ) {
		synchronized( listPrintWriter ) {
			listPrintWriter.add(printWriter);
		}
	}
	
	private void deletePrintWriter( PrintWriter printWriter ) {
		synchronized( listPrintWriter ) {
			listPrintWriter.remove( printWriter );
		}
	}
	
	private void broadcastMessage( String message ) {
		synchronized( listPrintWriter ) {
			for( PrintWriter printWriter : listPrintWriter ) {
				//PrintWriter pw =(PrintWriter)printWriter;
				printWriter.println( message );
				printWriter.flush();
			}
		}
	}
	
}