package de.eightbitboy.roo.enverspre.data;

public class Animal{
	private long id;
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