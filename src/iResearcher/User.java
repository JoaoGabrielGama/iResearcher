package iResearcher;

import java.util.ArrayList;

public class User {
	private String name;
	private String login;
	private String password;
	private int age = 0;
	private String address = "-x-";
	private String field = "-x-";
	private ArrayList<String> friends = new ArrayList<String>();
	
	public User(String name, String login, String password){
		this.name = name;
		this.login = login;
		this.password = password;
	}
	
	//Sets & Gets
	public String getName(){ return this.name; }
	public String getLogin(){ return this.login; }
	public String getPassword(){ return this.password; }
	public int getAge(){ return this.age; }
	public String getAddress(){ return this.address; }
	public String getField(){ return this.field; }
	public ArrayList getFriends(){ return this.friends; }
	
	public void setName(String name){ this.name = name; }
	public void setLogin(String login){ this.login = login; }
	public void setPassword(String pass){ this.password = pass; }
	public void setAge(int age){ this.age = age; }
	public void setAddress(String address){ this.address = address; }
	public void setField(String field){ this.field = field; }
	
	
	//Other Methods
	public void addFriend(String name){
		this.friends.add(name);
	}
	
	public boolean removeFriend(String name){
		for(int i = 0; i < this.friends.size(); i++){
			if(this.friends.get(i).equals(name)){
				this.friends.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public void checkPerfil(){
		System.out.println("Name: " + this.name
						 + "\nLogin: " + this.login
						 + "\nAge: " + this.age
						 + "\nAddress: " + this.address
						 + "\nField: " + this.field
						 + "\nFriends: ");
		for(int i = 0; i < this.friends.size(); i++){
			System.out.println(this.friends.get(i));
		}
	}
}
