package de.eightbitboy.roo.enverspre;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class EnversPre{
	public static void main(String[] args){
		Configuration configuration = new Configuration();
		configuration.configure();
		/*
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(settings);
		*/
	}
}