package com.hihsoft.baseclass.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hihframework.osplugins.properties.ConfigLoader;

public class PropertiesListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ConfigLoader.listenForChanges();

	}

}
