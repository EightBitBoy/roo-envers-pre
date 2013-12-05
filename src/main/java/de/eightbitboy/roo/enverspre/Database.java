package de.eightbitboy.roo.enverspre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import de.eightbitboy.roo.enverspre.data.Animal;

public class Database {
	private static final Database INSTANCE = new Database();
	private SessionFactory sessionFactory;
	
	private Database(){
		Configuration configuration = new Configuration();
		configuration.configure();
		this.addAnnotatedClasses(configuration);
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).buildServiceRegistry();
		
		this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	private void addAnnotatedClasses(Configuration configuration){
		configuration.addAnnotatedClass(Animal.class);
	}
	
	public static Session openSession(){
		return INSTANCE.sessionFactory.openSession();
	}
}