package net.bynaryscode.util;

import java.util.Random;

public class RandomPool {
	private static RandomPool instance = new RandomPool(8);
	
	public static RandomPool getDefault() {
		return instance;
	}
	
	private Random[] pool;
	private int index = 0;
	
	private UpdateThread updateThread = new UpdateThread();
	
	public RandomPool(int slot) {
		if (slot <= 0) throw new IllegalArgumentException("slot <= 0 !");
		this.pool = new Random[slot];
		
		Random rand = new Random();
		for (int i = 0 ; i < slot ; i++) {
			this.pool[i] = new Random(rand.nextLong());
		}
		
		//Thread
		this.updateThread.setDaemon(true);
		this.updateThread.setName("RandomPool updater");
		this.updateThread.setPriority(Thread.MIN_PRIORITY);
		this.updateThread.start();
	}
	
	public Random getRandom() {
		Random rand = this.pool[index];
		incrementIndex();
		return rand;
	}
	
	private void incrementIndex() {
		this.index++;
		if (index >= pool.length) {
			index = 0;
		}
	}
	
	@Override
	public void finalize() {
		this.updateThread.running = false;
	}
	
	private class UpdateThread extends Thread {
		
		private boolean running = false;
		
		@Override
		public void run() {
			this.running = true;
			
			while (this.running) {
				
				pool[index] = new Random(System.currentTimeMillis());
				incrementIndex();
				
				try {
					Thread.sleep(10000 / pool.length);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
 }
