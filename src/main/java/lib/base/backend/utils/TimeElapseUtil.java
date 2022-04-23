package lib.base.backend.utils;

public class TimeElapseUtil {

	long start;
	long end;
	
	String moduleTitle;
	
	public TimeElapseUtil(String moduleTitle) {
		
		this.moduleTitle = moduleTitle;
	}
	
	public void printStart() {
		
		start = System.nanoTime();
		System.out.println("------ START\t " + moduleTitle + " --------");
	}
	
	public void printEnd() {
		
		end = System.nanoTime();
		
		double elapsedTimeInSecond = getElapseSeconds(start, end);
		System.out.println("------ END\t " + moduleTitle + " --------" + elapsedTimeInSecond + " seconds");
	}
	
	public void printEnd(String moduleTitle) {
		
		end = System.nanoTime();
		
		double elapsedTimeInSecond = getElapseSeconds(start, end);
		System.out.println("------ END\t " + moduleTitle + " --------" + elapsedTimeInSecond + " seconds");
	}
	
	public double getElapseSeconds(long start, long end) {
		
		long elapsedTime = end - start;
		double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
		
		return elapsedTimeInSecond;
	}
}
