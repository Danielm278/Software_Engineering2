package Phone;

import java.util.ArrayList;
//connect to App
public class SMS extends App{
	ArrayList<chat> chat_list = new ArrayList<chat>();
	public SMS() {
		super.appId = "3";
		super.appName = "SMS";
		addMenuOptions();
	}
//check for if a contact got deleted		
	EntryNode contactList = new EntryNode();
	public void startApp(EntryNode contactList) {
		this.contactList = contactList;
		ArrayList<chat> temp = new ArrayList<chat>();
		try {
		for(int i = 0; i < chat_list.size(); i++) {

				chat currChat = (chat)chat_list.get(i);
				
				if(!(contactList.searchEntryByName(currChat.contactName, 1))) {
					continue;
				}
			
			temp.add(chat_list.get(i));
		}
		}
		catch(Exception e){
			
		}
		chat_list = temp;
		
		super.startApp();
	}
	public void addMenuOptions() {
		//initialize the menu for the SMS app
		super.addMenuOption("send message");
		super.addMenuOption("delete chat");
		super.addMenuOption("print chat");
		super.addMenuOption("Search in all chats");
		super.addMenuOption("Print all chats");
		super.addMenuOption("Exit");
	}
	@Override
	public int waitForInputAndRun() {

		//get the user decision in super
		int decision = super.waitForInputAndRun();
		
		if(endFlag) {
			return 0;
		}		
		//check if contact list is empty
		if(contactList==null || contactList.name == null) {
			System.out.println("No contacts in the phone");
			return 0;
		}
		//provide menu functionality
		switch(decision) {
		
		//sent message
		case 0:
			chooseContact(decision);
            break;
        
        //remove chat by name
		case 1:
			chooseContact(decision);
			break;
		
		//display chat for certain contact
		case 2:
			chooseContact(decision);
			break;
		
		//display which contacts has a specific message in chat
		case 3:
			searchMessage();
			break;
			
		//print all chats
		case 4:
			printAllChat();
			break;

		}
		
		return 0;
	}
	//all 3 functions need to work with specific contact
	public void chooseContact(int decision){
		//get contact name
		System.out.println("Please enter contact name");
		String contact = s.nextLine();
		if(!(contactList.searchEntryByName(contact, 1))) {
			System.out.println("No contact go by that name");
		}
		else {
			switch(decision) {
			
			//sent message
			case 0:
				sendMessage(contact);
	            break;

	    	//display chat for certain contact
			case 1:
				removeChat(contact);
				break;
			
			//display which contacts has a specific message in chat
			case 2:
				printChat(contact);
				break;
			
			}
		}
	}
	//send message to a specific contact and if contact is empty create new chat
	public void sendMessage(String contact) {
		System.out.println("Please enter message");
		String message = s.nextLine();
		//search contact
		for(int i = 0; i < chat_list.size(); i++) {
			if(chat_list.get(i).contactName.equals(contact)) {
				//if chat exist add message
				chat_list.get(i).message_list.add(message);
				return;
			}
		}
		//if not create new
		chat_list.add(new chat(contact, message));
	} 
	//remove chat
	public void removeChat(String contact){
		//find contact
		for(int i = 0; i < chat_list.size(); i++) {
			if(chat_list.get(i).contactName.equals(contact)) {
				//if found remove
				chat_list.remove(i);
				System.out.println("Removed chat");
				return;
			}
			//else
		}

	}
	
	public void searchMessage() {
		//enter a message and search all chats for it
		System.out.println("Please enter the message you want to search");
		String message = s.nextLine();
		for(int i = 0; i < chat_list.size(); i++) {
			//use function is chat
			chat_list.get(i).findSentence(message);
			}

	} 
	public void printChat(String contact) {
		//print a specific chat
		for(int i = 0; i < chat_list.size(); i++) {
			if(chat_list.get(i).contactName.equals(contact)) {
				System.out.println("chat with: " + chat_list.get(i).contactName);
				for(int j = 0; j < chat_list.get(i).message_list.size(); j++) {
					System.out.println(chat_list.get(i).message_list.get(j));
					
				}
			}	
		}
	} 
	
	public void printAllChat() {
		//for all chats print all chat
		for(int i = 0; i < chat_list.size(); i++) {
			System.out.println("chat with: " + chat_list.get(i).contactName);
			for(int j = 0; j < chat_list.get(i).message_list.size(); j++) {
				System.out.println(chat_list.get(i).message_list.get(j));
			}
		}
	} 
	
}



