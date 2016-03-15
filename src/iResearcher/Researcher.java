package iResearcher;

import java.util.ArrayList;
import java.util.Scanner;

public class Researcher {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Message> msgBank = new ArrayList<Message>();
	User currentUser = null;
	Scanner s = new Scanner(System.in);
	
	public Researcher(){}
	
	public int searchUser(String name){
		for(int i=0; i < users.size(); i++){
			if(this.users.get(i).getName().toLowerCase().equals(name.toLowerCase())){
				return i;
			}
		}
		
		return -1;
	}
	
	public int exceptionCatcher(int minOpt, int maxOpt){
        if(minOpt >= maxOpt){
            return -1;
        }
        int option = -1;
        while(option < minOpt || option > maxOpt){
            try{
                option = Integer.parseInt(this.s.next());
                this.s.nextLine();
            } catch(Exception e){
                option = -1;
                System.out.println("Please, select an option between "+minOpt+" & "+maxOpt+"\n");
                continue;
            } if(option < minOpt || option > maxOpt){
                option = -1;
                System.out.println("Please, select an option between "+minOpt+" & "+maxOpt+"\n");
            }
        }
        return option;
    }
	
	public void createUser(){
		System.out.print("Name: ");
		String name = this.s.nextLine();
		System.out.print("Login: ");
		String login = this.s.nextLine();
		System.out.print("Password: ");
		String pass = this.s.nextLine();
		
		User u = new User(name, login, pass);
		this.users.add(u);
		System.out.println("Your account was created!");
	}
	
	public void removeUser(){
		String name = currentUser.getName();
		
		//Cleaning Msgs
		for(int i=0; i < this.msgBank.size(); i++){
			if(this.msgBank.get(i).getSender().equals(name)){
				this.msgBank.remove(i);
			}
		}
		
		User u;
		//Cleaning Relationships
		for(int i=0; i < this.users.size(); i++){
			u = this.users.get(i);
			for(int j=0; j < u.getFriends().size(); j++){
				if(u.getFriends().get(j).equals(name)){
					u.getFriends().remove(j);
					break;
				}
			}
		}
	}
	
	public void sendMessage(){
		String sender = currentUser.getName();
		
		System.out.print("Receiver Name: ");
		String receiver = s.nextLine().toLowerCase();
		int index = -1;
		
		while(index == -1){
			
			index = searchUser(receiver);
			if(index == -1){
				System.out.print("Please type a valid name: ");
				receiver = s.nextLine();
			}
		}
		
		System.out.print("Type Message: ");
		String text = s.nextLine();
		
		Message m = new Message(text, sender, receiver);
		this.msgBank.add(m);
		System.out.println("Your Message was sent!");
	}
	
	public void seeMessages(){
		Message m = null;
		for(int i = 0; i < this.msgBank.size(); i++){
			if(this.msgBank.get(i).getReceiver().equals(this.currentUser.getName().toLowerCase())){
				m = this.msgBank.get(i);
				System.out.println("Sender: " + m.getSender());
				System.out.println("Text: " + m.getText());
				System.out.print("-------------------------------------------------------------------");
			}
		}
		if(m == null){
			System.out.println("No Messages.");
		}
		
	}
	
	public void editPerfil(){
		User u = this.currentUser;
		int opt = -1;
		while(opt != 7){
			System.out.println("What do you want to Edit?\n"
					 + "1) Name\n"
					 + "2) Login\n"
					 + "3) Password\n"
					 + "4) Age\n"
					 + "5) Address\n"
					 + "6) Field\n"
					 + "7) Exit");
			opt = exceptionCatcher(1,7);
			System.out.print("Type the new information wanted:");
			switch(opt){
				case 1:
					u.setName(this.s.nextLine());
					break;
				case 2:
					u.setLogin(this.s.nextLine());
					break;
				case 3:
					u.setPassword(this.s.nextLine());
					break;
				case 4:
					u.setAge(exceptionCatcher(5,100));
					break;
				case 5:
					u.setAddress(this.s.nextLine());
					break;
				case 6:
					u.setField(this.s.nextLine());
					break;
			}
		}
	}
	
	public void login(){
		System.out.print("Login: ");
		String login = this.s.nextLine();
		System.out.print("Password: ");
		String pass = this.s.nextLine();
		
		
		while(this.currentUser == null){
			for(int i=0; i < this.users.size(); i++){
				if(this.users.get(i).getLogin().equals(login)){
					if(this.users.get(i).getPassword().equals(pass)){
						this.currentUser = this.users.get(i);
						return;
					}
				}
			}
			System.out.println("Login or Password invalid.");
			
			System.out.print("Login: ");
			login = this.s.next();
			System.out.print("Password: ");
			pass = this.s.next();
		}
	}
	
	public void logout(){
		this.currentUser = null;
	}
	
	public void onlineMenu(){
		int opt = -1;
		while(opt != 7){
			System.out.println("\nWelcome " + this.currentUser.getName()
							 + "\n1) Add Friend\n"
							 + "2) Check your Perfil\n"
							 + "3) Send Message\n"
							 + "4) See Messages\n"
							 + "5) Edit Perfil\n"
							 + "6) Delete Account\n"
							 + "7) Logout\n");
			opt = exceptionCatcher(1,7);
			
			switch(opt){
			case 1:
				System.out.print("Friend Name: ");
				String name = s.nextLine();
				while(searchUser(name) == -1){
					System.out.println("Type a valid name.");
					name = s.nextLine();
				}
				this.currentUser.addFriend(name);
				break;
			case 2:
				this.currentUser.checkPerfil();
				break;
			case 3:
				sendMessage();
				break;
			case 4:
				seeMessages();
				break;
			case 5:
				editPerfil();
				break;
			case 6:
				System.out.println("Are you sure you want to delete your account?\n"
								 + "1) Yes\n"
								 + "2) No");
				int choose = exceptionCatcher(1,2);
				if(choose == 1){
					removeUser();
					opt = 7;
				}
				break;
			case 7:
				logout();
				break;
			}
		}
	}
	
	public void offlineMenu(){
		int opt = -1;
		while(opt != 3){
			System.out.println("\nWelcome to iResearch!\n"
			         + "1) Create Account\n"
			         + "2) Sign in\n"
			         + "3) Exit\n");
			opt = exceptionCatcher(1,3);
			switch(opt){
				case 1:
					createUser();
					break;
				case 2:
					login();
					if(this.currentUser != null){
						onlineMenu();
					}
					break;
			}
		}
	}
	
	public static void main(String[] args){
		Researcher r = new Researcher();
		r.offlineMenu();
	}
}
