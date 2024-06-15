package Phone;

import java.util.ArrayList;

public class SMS extends App{
	ArrayList<chat> chat_list = new ArrayList<chat>();
	public SMS() {
		super.appId = "3";
		super.appName = "SMS";
		addMenuOptions();
	}
		
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
		//initialize the menu for the calendar app
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
		if(contactList==null || contactList.name == null) {
			System.out.println("No contacts in the phone");
			return 0;
		}
		//provide menu functionality
		switch(decision) {
		
		//add event
		case 0:
			chooseContact(decision);
            break;
        
        //remove event by name
		case 1:
			chooseContact(decision);
			break;
		
		//display events for certain date
		case 2:
			chooseContact(decision);
			break;
		
		//display events for a certain contact
		case 3:
			searchMessage();
			break;
			
		//Remove overlapping events
		case 4:
			printAllChat();
			break;
			
		//Print all Events
		case 5:
			break;
		}
		
		return 0;
	}
	public void chooseContact(int decision){
		//get event name, time, length and type
		System.out.println("Please enter contact name");
		String contact = s.nextLine();
		if(!(contactList.searchEntryByName(contact, 1))) {
			System.out.println("No contact go by that name");
		}
		else {
			switch(decision) {
			
			//add event
			case 0:
				sendMessage(contact);
	            break;
	        
	        //remove event by name
			case 1:
				removeChat(contact);
				break;
			
			//display events for certain date
			case 2:
				printChat(contact);
				break;
			
			}
		}
	}
	public void sendMessage(String contact) {
		System.out.println("Please enter message");
		String message = s.nextLine();

		for(int i = 0; i < chat_list.size(); i++) {
			if(chat_list.get(i).contactName.equals(contact)) {
				chat_list.get(i).message_list.add(message);
				return;
			}
		}
		chat_list.add(new chat(contact, message));
	} 
	//remove event
	public void removeChat(String contact){
		//find and remove
		for(int i = 0; i < chat_list.size(); i++) {
			if(chat_list.get(i).contactName.equals(contact)) {
				chat_list.remove(i);
				System.out.println("Removed chat");
				return;
			}

			System.out.println("That chat dosent exist");
		}

		System.out.println("Chat doesnt exist");
	}
	
	public void searchMessage() {
		//empty warning***
		System.out.println("Please enter the message you want to search");
		String message = s.nextLine();
		for(int i = 0; i < chat_list.size(); i++) {
			chat_list.get(i).findSentence(message);
			}

	} 
	public void printChat(String contact) {
		//empty warning***
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
		for(int i = 0; i < chat_list.size(); i++) {
			System.out.println("chat with: " + chat_list.get(i).contactName);
			for(int j = 0; j < chat_list.get(i).message_list.size(); j++) {
				System.out.println(chat_list.get(i).message_list.get(j));
			}
		}
	} 
	
}



