package lib.base.backend.utils;

import java.sql.SQLException;
import org.h2.tools.Server;

public class ServerH2Util {

	public void startServerDefault() throws SQLException {
		
		Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "8085").start();
	    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8086").start();
	}
	
}
