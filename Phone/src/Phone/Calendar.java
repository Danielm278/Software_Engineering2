package Phone;

import java.util.ArrayList;

public class Calendar extends App {
	
	//set up the event calendar
	ArrayList<Event> event_calendar = new ArrayList<Event>();
	
	public void addMenuOptions() {
		//initialize the menu for the calendar app
		super.addMenuOption("Create Event");
		super.addMenuOption("Remove Event");
		super.addMenuOption("Display all events on a certain date (in order)");
		super.addMenuOption("Display all meetings with a contact");
		super.addMenuOption("Remove overlapping events");
		super.addMenuOption("Print all events");
		super.addMenuOption("Exit");
	}
	
	@Override
	public void startApp() { //provide the calendar class access to rootnode of the contact class
		//setup the menu
		addMenuOptions();
		
		//start the app
		super.startApp();
	}
	
	@Override
	public int waitForInputAndRun() {
		//get the user decision in super
		int decision = super.waitForInputAndRun();
		ArrayList<Event> temp = new ArrayList<Event>();
		for(int i = 0; i < event_calendar.size(); i++) {
			if(!event_calendar.get(i).removeDate()) {
				temp.add(event_calendar.get(i));
			}
		}
		
		event_calendar = temp;
		
		//provide menu functionality
		switch(decision) {
		
		//add event
		case 0:
			addEvent();
            break;
        
        //remove event by name
		case 1:
			rm_byName();
			break;
		
		//display events for certain date
		case 2:
			break;
		
		//display events for a certain contact
		case 3:
			break;
			
		//Remove overlapping events
		case 4:
			break;
			
		//Print all Events
		case 5:
			break;
		}
		
		return 0;
	}

	public void addEvent(){
		//get event name, time, length and type
		System.out.println("Please enter event Name");
		String name = s.nextLine();
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int eventLength = 0;
		
		while(true) {
			System.out.println("When will this event occur? (Format MM/DD/YYYY");
			String date = s.nextLine();
			String[] dissembledDate;
			try {
				dissembledDate = date.split("/");
				
				if (dissembledDate.length != 3) {
					throw new Exception("Exception message");
				}
				
				year = Integer.parseInt(dissembledDate[2]);
				month = Integer.parseInt(dissembledDate[0]);;
				day = Integer.parseInt(dissembledDate[1]);
				
				if(month > 12 || month < 1) {
					throw new Exception("Exception message");
				}
				
				if(day > 1) {
					switch(month) {
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
						if (day > 31) {
							throw new Exception("Exception message");
						}
					break;
					
					case 4:
					case 6:
					case 9:
					case 11:
						if (day > 30) {
							throw new Exception("Exception message");
						}
					break;
					
					case 2:
						if(year%4 == 0) {
							if (day > 29) {
								
							}
						}
						else {
							if(day > 28) {
								
							}
						}
					}
				}
				else {
					throw new Exception("Exception message");
				}
				break;
			}
			catch(Exception e) {
				System.out.println("There was a format issue with the provided date");
				System.out.println("Please try again");
			}
		}
		
		while(true) {
			System.out.println("What time will this event occur? (format HH:MM, 24 hour clock)");
			String time = s.nextLine();
			String[] dissembledTime;
			try {
				dissembledTime = time.split("/");
				
				if (dissembledTime.length != 2) {
					throw new Exception("Exception message");
				}
				hour = Integer.parseInt(dissembledTime[0]);
				minute = Integer.parseInt(dissembledTime[1]);
				
				if(hour > 23 || hour < 0) {
					throw new Exception("Exception message");
				}
				if(minute > 59 || minute < 0) {
					throw new Exception("Exception message");
				}
				break;
			}
			catch(Exception e) {
				System.out.println("There was a format issue with the provided time");
				System.out.println("Please try again");
			}
		}
		
		// date (Set minute/hour/day/month)
		System.out.println("Will this event include a meeting?(y/n)");
		String subdecision = s.next();
		
		while(true) {
			try {
				System.out.println("how long will this event be?(1-60 min)");
				eventLength = Integer.parseInt(s.nextLine());
				
				if(eventLength > 60|| eventLength < 1) {
					throw new Exception("Exception message");
				}
				break;
			}
			catch(Exception e) {
				System.out.println("Please choose a number from 1-60");
			}
		}
		
		//check if meeting
		if(subdecision =="y"){
			//setup meeting
		    System.out.println("Please enter contact name");
		    String contact_name = s.nextLine();
		    System.out.println("Please enter contact number");
		    String contact_num = s.nextLine();
		    
		    event_calendar.add(new Meeting(contact_name, contact_num, name, eventLength, year,month, day, hour, minute));
		}
		else{
		   //setup non_meeting
		   System.out.println("Please enter a brief description of the event");
		   String description = s.nextLine();
		   event_calendar.add(new non_meeting(name, description,eventLength, year, month, day, hour, minute));
		}
	}
	
	
	//remove event
	public void rm_byName(){
		
		//get event name
		System.out.println("Please choose event to remove (by name)");
		String rmName = s.nextLine();
		boolean rm_flag = false;
		
		//find and remove
		for(int i = 0; i < event_calendar.size(); i++) {
			if(event_calendar.get(i).name == rmName) {
				event_calendar.remove(i);
				rm_flag = true;
				break;
			}
		}
		
		//signal to user if event was found/removed
		if(rm_flag) {
			System.out.println("Removed event from calendar");
		}
		else {
			System.out.println("Failed to find event, Please try again");
		}
	}
}