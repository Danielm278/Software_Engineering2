package Phone;

public class Meeting extends Event{
	
	String contactName;
	String contactNum;
	
	public Meeting(String contactName,
					String name, int eventLen, int year, int month, int date, int hour, int minute) {
		super(name, eventLen, year, month, date, hour, minute);
		// TODO Auto-generated constructor stub
		this.contactName = contactName;
	}

	
	public boolean contactRemoved(){//Place the contactlist rootnode as parameter
		//remove event if contact is no longer in list
		return false;
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("  	Contact: " + contactName);
	}
}