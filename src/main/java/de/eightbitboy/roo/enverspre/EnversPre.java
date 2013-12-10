package de.eightbitboy.roo.enverspre;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

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
		
		animal1.setName("Alfred");
		updateAnimal(animal1);
		animal1.setName("Jimmy");
		updateAnimal(animal1);
		animal1.setName("Suzy");
		updateAnimal(animal1);
		
		listAnimalAudits(animal1.getId());
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
	
	public static void listAnimalAudits(long id){
		Session session = Database.openSession();
		Transaction transaction = session.beginTransaction();
		
		
		AuditReader auditReader = AuditReaderFactory.get(session);
		List<Number> revisionNumbers = auditReader.getRevisions(Animal.class, id);
		log.info(revisionNumbers.toString());
		List revisions = auditReader.createQuery().forRevisionsOfEntity(Animal.class, true, false).add(AuditEntity.id().eq(id)).getResultList();
		
		log.info("Animal, ID: " + id);
		/*
		for(Iterator iterator = revisions.iterator(); iterator.hasNext();){
			Animal revision = (Animal)iterator.next();
			log.info("^^^ Name: " + revision.getName());
		}
		*/
		
		revisions = auditReader.createQuery().forRevisionsOfEntity(Animal.class, false, false).add(AuditEntity.id().eq(id)).getResultList();
		for(Iterator iterator = revisions.iterator(); iterator.hasNext();){
			Object[] revision = (Object[])iterator.next();
			log.info("^^^ " + ((Animal)revision[0]).getName() + "; " +
					((RevisionType)revision[2]).toString() + "; " +
					((DefaultRevisionEntity)revision[1]).getRevisionDate().toString());
		}
		
		
		/*
		Map revisions = auditReader.findRevisions(Animal.class, revisionNumbers);
		for(Iterator iterator = revisions.  .iterator(); iterator.hasNext();){
			Animal animalRevision = (Animal)iterator.next();
			log.info("Animal, " + animalRevision.getId() + ", " + animalRevision.getName());
		}
		*/
		transaction.commit();
		session.close();
	}
}