package com.bit2016.network.thread.test;

public class multiThreadex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1=new DigitThread();
		Thread thread2=new AlphaThread();
		Thread thread3=new Thread(new UppercaseAlphabetRunnableImpl());
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
