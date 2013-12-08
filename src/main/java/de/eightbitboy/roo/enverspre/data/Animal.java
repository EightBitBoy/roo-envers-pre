package de.eightbitboy.roo.enverspre.data;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="ANIMAL")
public class Animal implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	public Animal(){};
	
	public Animal(String name){
		this.name = name;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}