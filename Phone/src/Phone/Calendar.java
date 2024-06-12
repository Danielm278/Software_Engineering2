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
			//get event name, time, length and type
			System.out.println("Please enter event Name");
			String name = s.nextLine();
			System.out.println("When will this event occur?");
			int month = 1;
			int day = 1;
			int hour = 1;
			int minute = 1;
            // date (Set minute/hour/day/month)
            System.out.println("Will this event include a meeting?(y/n)");
            String subdecision = s.next();
            System.out.println("how long will this event be?(1-60 min)");
            int eventLength = Integer.parseInt(s.nextLine());
            
            //check if meeting
            if(subdecision =="y"){
            		//setup meeting
                    System.out.println("Please enter contact name");
                    String contact_name = s.nextLine();
                    System.out.println("Please enter contact number");
                    String contact_num = s.nextLine();
                    
                    event_calendar.add(new Meeting(contact_name, contact_num, name, eventLength,month, day, hour, minute));
            }
            else{
            	   //setup non_meeting
                   System.out.println("Please enter a brief description of the event");
                   String description = s.nextLine();
                   event_calendar.add(new non_meeting(name, description,eventLength, month, day, hour, minute));
            }
            break;
        
        //remove event by name
		case 1:
			System.out.println("Please choose event to remove (by name)");
			String rmName = s.nextLine();
			boolean rm_flag = false;
			
			for(int i = 0; i < event_calendar.size(); i++) {
				if(event_calendar.get(i).name == rmName) {
					event_calendar.remove(i);
					rm_flag = true;
					break;
				}
			}
			
			if(rm_flag) {
				System.out.println("Removed event from calendar");
			}
			else {
				System.out.println("Failed to find event, Please try again");
			}
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
}
