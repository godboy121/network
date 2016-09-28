package com.bit2016.network.thread.test;

public class DigitThread extends Thread {



	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<=9;i++)
		{
			System.out.print(i);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		super.run();
	}

}
