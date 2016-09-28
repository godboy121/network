package com.bit2016.network.thread.test;

public class AlphaThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(char c='a';c<='z';c++)
		{
			System.out.print(c);
		}
		super.run();
	}

}
