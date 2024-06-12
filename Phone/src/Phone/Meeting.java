package Phone;

public class Meeting extends Event{
	
	String contactName;
	String contactNum;
	
	public Meeting(String contactName, String contactNum,
					String name, int eventLen, int month, int date, int hour, int minute) {
		super(name, eventLen, month, date, hour, minute);
		// TODO Auto-generated constructor stub
		this.contactName = contactName;
		this.contactNum = contactNum;
	}

	
	public boolean contactRemoved(){//Place the contactlist rootnode as parameter
		//remove event if contact is no longer in list
		return false;
	}
}