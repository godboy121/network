package com.bit2016.network.thread.test;

public class multiThreadEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1=new DigitThread();
		Thread thread2=new AlphaThread();
		
		thread1.start();
		thread2.start();
		//System.out.println("안녕하세요");

	}

}
