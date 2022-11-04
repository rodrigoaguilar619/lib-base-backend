package lib.base.backend.utils;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeElapseUtil {
	
	private static final Logger log = LoggerFactory.getLogger(TimeElapseUtil.class);

	long start;
	
	long end;
	
	String moduleTitle;
	
	private String getDecimal(Double numberDecimal) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(numberDecimal);
	}
	
	public TimeElapseUtil(String moduleTitle) {
		
		this.moduleTitle = moduleTitle;
	}
	
	public void printStart() {
		
		start = System.nanoTime();
		log.info("------ START  {} --------", moduleTitle);
	}
	
	public void printEnd() {
		
		end = System.nanoTime();
		
		double elapsedTimeInSecond = getElapseSeconds(start, end);
		log.info("------ FINISH {} --------  exucute time: {} seconds", moduleTitle, getDecimal(elapsedTimeInSecond));
	}
	
	public double getElapseSeconds(long start, long end) {
		
		long elapsedTime = end - start;
		return (double) elapsedTime / 1_000_000_000;
	}
}
