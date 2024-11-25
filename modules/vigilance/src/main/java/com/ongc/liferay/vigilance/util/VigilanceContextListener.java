package com.ongc.liferay.vigilance.util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class VigilanceContextListener implements ServletContextListener{
 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		VigilanceHitCountUtils.saveCount(arg0.getServletContext().getRealPath("staticContents/hitcount.properties"));
	//system.out.println("contextDestroyed ");	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		VigilanceHitCountUtils.initializeCount(arg0.getServletContext().getRealPath("staticContents/hitcount.properties"));
	//system.out.println("contextInitialized method::::");	
	}

}
