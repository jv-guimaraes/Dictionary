package Util;

public class Timer {
	private long start;
	
	public Timer() {
		this.start = System.nanoTime();
	}
	
	public void start() {
		start = System.nanoTime();
	}
	
	public void end() {
		System.out.printf("%,d\n", System.nanoTime() - start);
	}
}
