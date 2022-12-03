package Util;

public class Breaker {
	int loops;
	
	public Breaker(int loops) {
		this.loops = loops;
	}
	
	public boolean end() {
		loops--;
		return (loops == 0);
	}
}
