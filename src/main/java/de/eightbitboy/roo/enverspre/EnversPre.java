package de.eightbitboy.roo.enverspre;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.eightbitboy.roo.enverspre.Database;
import de.eightbitboy.roo.enverspre.data.Animal;

public class EnversPre{
	public static Logger log = LoggerFactory.getLogger(EnversPre.class);
	
	public static void main(String[] args){
		
		addAnimal("Sam");
		addAnimal("Max");
		addAnimal("Jimmy");
		addAnimal("Snowball");
		/*
		Session session = Database.openSession();		
		transaction = session.beginTransaction();
		Query query = session.createQuery("from Animal");
		List list = query.list();
		transaction.commit();
		
		log.info(list.toString());
		
		session.disconnect();
		session.close();
		*/
	}
	
	public static long addAnimal(String name){
		Animal animal = new Animal();
		animal.setName(name);
		
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		long animalId = (Long)session.save(animal);
		log.info("Added Animal with ID: " + String.valueOf(animalId));
		transaction.commit();
		session.close();
		
		return animalId;
	}
}