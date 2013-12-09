package de.eightbitboy.roo.enverspre;


import java.util.Iterator;
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
		
		Animal animal1 = addAnimal("Sam");
		Animal animal2 = addAnimal("Max");
		addAnimal("Jimmy");
		addAnimal("Snowball");
		listAnimals();
		
		log.info(getAnimal(1).toString());
		log.info(getAnimal(2).toString());
		
		animal1.setName("Felix");
		animal2.setName("Dolly");
		updateAnimal(animal1);
		updateAnimal(animal2);
		listAnimals();
	}
	
	public static Animal addAnimal(String name){
		Animal animal = new Animal();
		animal.setName(name);
		
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		
		long animalId = (Long)session.save(animal);
		log.info("Added Animal with ID: " + String.valueOf(animalId));
		
		transaction.commit();
		session.close();
		
		return animal;
	}
	
	public static Animal getAnimal(long id){
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		
		Animal animal = (Animal)session.get(Animal.class, id);
		
		transaction.commit();
		session.close();
		
		return animal;
	}
	
	public static void updateAnimal(Animal animal){
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(animal);
		
		transaction.commit();
		session.close();
	}
	
	public static void listAnimals(){
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		
		List animals = session.createQuery("from Animal").list();
		for(Iterator iterator = animals.iterator(); iterator.hasNext();){
			Animal animal = (Animal)iterator.next();
			log.info("Animal, " + animal.getId() + ", " + animal.getName());
		}
		
		transaction.commit();
		session.close();
	}
	
	public static void getAnimalAudits(long id){
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		
		
		
		transaction.commit();
		session.close();
	}
}