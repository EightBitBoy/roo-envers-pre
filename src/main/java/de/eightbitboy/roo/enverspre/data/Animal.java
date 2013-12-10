package de.eightbitboy.roo.enverspre.data;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Table(name="ANIMAL")
public class Animal implements Serializable{
	private long id;	
	private String name;
	
	public Animal(){};
	
	public Animal(String name){
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	@Audited
	@Column(name="NAME")
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Animal: " + this.name + "; ID: " + this.id;
	}
}