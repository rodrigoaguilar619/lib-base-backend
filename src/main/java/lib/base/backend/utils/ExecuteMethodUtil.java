package lib.base.backend.utils;

import lib.base.backend.utils.interfaces.executable.ExecutableCustom;

public class ExecuteMethodUtil {
	
	private ExecuteMethodUtil() {}
	
	public static void execute(String title, ExecutableCustom executableCustom) throws Throwable {

		TimeElapseUtil timeElapseUtil = new TimeElapseUtil(title);
		timeElapseUtil.printStart();
		
		executableCustom.execute();
		
		timeElapseUtil.printEnd();
	}
}
