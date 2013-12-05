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

public class EnversPre{
	public static void main(String[] args){
		Logger log = LoggerFactory.getLogger(EnversPre.class);
		
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addAnnotatedClass(Animal.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		Animal animal = new Animal();
		animal.setName("Frankenstein");
		long animalId = (Long)session.save(animal);
		log.info(String.valueOf(animalId));
		session.close();
	}
}