package Phone;

import java.util.ArrayList;
import java.util.Comparator;

public class Calendar extends App {
	
	//set up the event calendar
	ArrayList<Event> event_calendar = new ArrayList<Event>();
	EntryNode contactList;
	
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
	
	public Calendar() {
		super.appId = "2";
		super.appName = "Calendar";
		addMenuOptions();
	}
		
	public void startApp(EntryNode contactList) {
		this.contactList = contactList;
		
		ArrayList<Event> temp = new ArrayList<Event>();
		for(int i = 0; i < event_calendar.size(); i++) {
			if((event_calendar.get(i) instanceof Meeting)) {
				Meeting currMeeting = (Meeting)event_calendar.get(i);
				
				if(!find_contact(currMeeting.contactName, currMeeting.contactNum)) {
					continue;
				}
			}
			temp.add(event_calendar.get(i));
		}
		event_calendar = temp;
		
		super.startApp();
	}
	
	@Override
	public int waitForInputAndRun() {
		//get the user decision in super
		int decision = super.waitForInputAndRun();
		
		
		ArrayList<Event> temp = new ArrayList<Event>();
		for(int i = 0; i < event_calendar.size(); i++) {
			if(event_calendar.get(i).removeDate()) {
				continue;
			}
			
			temp.add(event_calendar.get(i));
			
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
			String st_date;
			System.out.println("Please enter the date. (Format is MM/DD/YYYY)");
			st_date = s.nextLine();
			int[] prt_date = validateDate(st_date);
			
			if(prt_date != null) {
				print_byDate(prt_date);
			}
			else {
				System.out.println("Formatting error has occurred\nPlease try again.");
			}
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
	
	@SuppressWarnings({ "deprecation"})
	public void print_byDate(int[] date) {
		event_calendar = sortByDate();
		int count = 0;
		boolean foundFlg = false;
		for(int i = 0; i < event_calendar.size(); i++) {
			Event currEvent = event_calendar.get(i);
			if(currEvent.date.getYear() == (date[0]-1900)) {
				if(currEvent.date.getMonth() == (date[1]-1)) {
					if(currEvent.date.getDate() == (date[2])) {
					count += 1;
					System.out.println(count + ")	Name: "+ currEvent.name);
					System.out.println("  	Date: "+ currEvent.date.toString());
					System.out.println("  	Length: "+ currEvent.eventLength);
					
					if(currEvent instanceof Meeting) {
						System.out.print("  	Contact: " + ((Meeting)currEvent).contactName);
						System.out.println(", " + ((Meeting)currEvent).contactNum);
					}
					else if(currEvent instanceof non_meeting) {
						System.out.print("  	Description: " + ((non_meeting)currEvent).description);
					}
					
					}
					foundFlg = true;
				}
			}
		}
		
		if(!foundFlg) {
			System.out.print("<No events found for this date>");
		}
	}
	
	public ArrayList<Event> sortByDate(){
		ArrayList<Event> sortedList = event_calendar;
		
		sortedList.sort(new Comparator<Event>() {
			@Override
		    public int compare(Event o1, Event o2) {
		        if(o1.date.after(o2.date)) {
		        	return 1;
		        }
		        else if(o2.date.after(o1.date)) {
		        	return -1;
		        }
		        else {
		        	return 0;
		        }
		    }
		});
		
		return sortedList;
	}
	
	public int[] validateDate(String date) {
		try {
			String[] dissembledDate;
			int year;
			int month;
			int day;
			
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
							throw new Exception("Exception message");
						}
					}
					else {
						if(day > 28) {
							throw new Exception("Exception message");
						}
					}
				}
			}
			else {
				throw new Exception("Exception message");
			}
			
			int[] date_split = {year, month, day};
			return date_split;
		}
		catch(Exception e) {
			return null;
		}
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
			System.out.println("When will this event occur? (Format MM/DD/YYYY)");
			String date = s.nextLine();
			int[] dissembledDate;
			try {
				dissembledDate = validateDate(date);
				year = dissembledDate[0];
				month = dissembledDate[1];
				day = dissembledDate[2];
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
				dissembledTime = time.split(":");
				
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
		s.nextLine();
		
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
		if(subdecision.equals("y")){
			//setup meeting
		    System.out.println("Please enter contact name");
		    String contact_name = s.nextLine();
		    System.out.println("Please enter contact number");
		    String contact_num = s.nextLine();
		    boolean found = find_contact(contact_name, contact_num);
		    	
		    	
	    	if(found) {
	    		System.out.print("Meeting added successfully");
			    event_calendar.add(new Meeting(contact_name, contact_num, name, eventLength, year,month, day, hour, minute));
	    	}
	    	else {
	    		System.out.print("Unfortunately contact cannot be found\nPlease try again");
	    	}
		    
		    
		}
		else{
		   //setup non_meeting
		   System.out.println("Please enter a brief description of the event");
		   String description = s.nextLine();
		   event_calendar.add(new non_meeting(name, description,eventLength, year, month, day, hour, minute));

		}
	}
	
	public boolean find_contact(String contact_name, String contact_num) {
		EntryNode rootNode = contactList;
    	boolean found = false;
    	
    	while(rootNode.next != null) {
    		if(rootNode.name.equals(contact_name)) {
    			if(rootNode.number.equals(contact_num)) {
	    			return true;
	    		}
    		}
    		
    		rootNode = rootNode.next;
    	}
    	
    	return false;
	}
	
	//remove event
	public void rm_byName(){
		
		//get event name
		System.out.println("Please choose event to remove (by name)");
		String rmName = s.nextLine();
		boolean rm_flag = false;
		
		//find and remove
		for(int i = 0; i < event_calendar.size(); i++) {
			if(event_calendar.get(i).name.equals(rmName)) {
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