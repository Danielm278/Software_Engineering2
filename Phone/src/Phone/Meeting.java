package Phone;

public class Meeting extends Event{
	
	String contactName;
	String contactNum;
	
	public Meeting(String contactName, String contactNum,
					String name, int eventLen, int year, int month, int date, int hour, int minute) {
		super(name, eventLen, year, month, date, hour, minute);
		// TODO Auto-generated constructor stub
		this.contactName = contactName;
		this.contactNum = contactNum;
	}
}
