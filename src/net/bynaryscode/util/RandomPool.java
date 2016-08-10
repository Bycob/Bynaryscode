/* <LICENSE>
Copyright (C) 2015-2016 Louis JEAN

This file is part of BynarysCode.

BynarysCode is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

BynarysCode is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with BynarysCode. If not, see <http://www.gnu.org/licenses/>.
 </LICENSE> */

package net.bynaryscode.util;

import java.util.Random;

public class RandomPool implements Cloneable {
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
	public RandomPool clone() {
		RandomPool clone = new RandomPool(this.pool.length);
		return clone;
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
