package net.bynaryscode.util;

public class Iterator {
	
	private int start;
	private int iteration;
	private int end;
	
	private boolean hasEnd;
	
	private int pos;
	
	public Iterator() {
		this.start = 0;
		this.end = Integer.MAX_VALUE;
		this.iteration = 1;
		this.pos = this.start;
		this.hasEnd = true;
	}
	
	public Iterator(boolean haveEnd) {
		this();
		this.hasEnd = haveEnd;
	}
	
	public Iterator(int start, int iteration, int end, boolean haveEnd) {
		if (iteration == 0) iteration = 1;
		
		if (iteration > 0 && start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		
		if (iteration < 0 && start < end) {
			int temp = start;
			start = end;
			end = temp;
		}
		
		this.start = start;
		this.end = end;
		this.iteration = iteration;
		this.pos = this.start;
		this.hasEnd = haveEnd;
	}
	
	public int next() {
		this.pos += iteration;
		
		if (this.hasEnd) {
			if (this.iteration < 0 && this.pos < this.end) {
				pos = start;
			}
			if (iteration > 0 && pos > end) {
				pos = start;
			}
		}
		
		return pos;
	}
	
	public void reinit() {
		this.pos = start;
	}
}
