package lib.base.backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lib.base.backend.utils.interfaces.executable.ExecutableCustom;

public class ExecuteMethodUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ExecuteMethodUtil.class);
	
	private ExecuteMethodUtil() {}
	
	public static void execute(String title, ExecutableCustom executableCustom) throws Throwable {

		TimeElapseUtil timeElapseUtil = new TimeElapseUtil(title);
		timeElapseUtil.printStart();
		
		executableCustom.execute();
		
		timeElapseUtil.printEnd();
	}
	
	public static void executeAndCatch(String title, ExecutableCustom executableCustom) throws Throwable {

		TimeElapseUtil timeElapseUtil = new TimeElapseUtil(title);
		timeElapseUtil.printStart();
		
		try {
			executableCustom.execute();
		} catch (Throwable e) {
			
			if (log.isDebugEnabled())
				log.error("Error on execute", e);
			
			throw e;
		}
		
		timeElapseUtil.printEnd();
	}
}
