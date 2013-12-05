package de.eightbitboy.roo.enverspre;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.eightbitboy.roo.enverspre.Database;
import de.eightbitboy.roo.enverspre.data.Animal;

public class EnversPre{
	public static void main(String[] args){
		Logger log = LoggerFactory.getLogger(EnversPre.class);
		
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		Animal animal = new Animal();
		animal.setName("Frankenstein");
		long animalId = (Long)session.save(animal);
		log.info(String.valueOf(animalId));
		transaction.commit();
		session.disconnect();
		session.close();
	}
}